package com.yuqi.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yuqi.enums.StaffLevelEnum;
import com.yuqi.enums.UserIdentityEnum;
import com.yuqi.pojo.CheckoutLoginInfo;
import com.yuqi.pojo.LoginUser;
import com.yuqi.pojo.User;
import com.yuqi.service.DepartmentService;
import com.yuqi.service.StaffService;
import com.yuqi.service.UserService;
import com.yuqi.service.impl.DepartmentServiceImpl;
import com.yuqi.service.impl.StaffServiceImpl;
import com.yuqi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 用于登录的servlet
 * @author yuqi
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User _user = JSON.parseObject(params, User.class);
        // 调用service查询
        CheckoutLoginInfo checkoutLoginInfo = userService.login(_user.getUsername());

        if(null == checkoutLoginInfo){
            response.getWriter().write("fail");
            return;
        }
        //登录角色不对,返回错误
        if(!checkoutLoginInfo.getIdentity().equals(_user.getIdentity())){
            response.getWriter().write("fail");
            return;
        }
        //密码不对,返回错误。里密码不用sql查是为了防止sql注入
        if(!checkoutLoginInfo.getPassword().equals(_user.getPassword())){
            response.getWriter().write("fail");
            return;
        }
        LoginUser loginUser = packageResultLoginUserParam(checkoutLoginInfo);
        // 将登录成功后user对象存储到session中
        HttpSession session = request.getSession();
        session.setAttribute("user",loginUser);
        response.getWriter().write("success");
    }

    /**
     * 组装返回的登录用户信息
     * @return
     */
    private LoginUser packageResultLoginUserParam(CheckoutLoginInfo checkoutLoginInfo){
        LoginUser loginUser = new LoginUser();
        loginUser.setId(checkoutLoginInfo.getId());
        loginUser.setUserId(checkoutLoginInfo.getUserId());
        loginUser.setUserName(checkoutLoginInfo.getUserName());
        loginUser.setIdentity(checkoutLoginInfo.getIdentity());
        loginUser.setIdentityName(UserIdentityEnum.getDescByCode(checkoutLoginInfo.getIdentity()));
        loginUser.setLevel(checkoutLoginInfo.getLevel());
        loginUser.setLevelName(StaffLevelEnum.getDescByCode(checkoutLoginInfo.getLevel()));
        loginUser.setDepartmentId(checkoutLoginInfo.getDepartmentId());
        loginUser.setDepartmentName(checkoutLoginInfo.getDepartmentName());
        loginUser.setName(checkoutLoginInfo.getName());
        return loginUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
