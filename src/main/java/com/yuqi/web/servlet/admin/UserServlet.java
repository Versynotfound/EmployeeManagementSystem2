package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.common.ResultDTO;
import com.yuqi.constant.BaseConstant;
import com.yuqi.pojo.*;
import com.yuqi.pojo.user.CreateUserReq;
import com.yuqi.service.StaffService;
import com.yuqi.service.UserService;
import com.yuqi.service.impl.StaffServiceImpl;
import com.yuqi.service.impl.UserServiceImpl;
import com.yuqi.utils.GetQueryParamUtil;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 用户servlet
 *
 * @author yuqi
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    private StaffService staffService = new StaffServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateUserReq createUserReq = GetQueryParamUtil.getQueryParam(CreateUserReq.class,request);

        int userExits = userService.selectCountByLoginName(createUserReq.getLoginName());
        if(BaseConstant.ZERO != userExits){
            ResultDTO<String> resultDto =  ResultDTO.fail("用户已存在,请勿重复创建");
            String jsonString = JSON.toJSONString(resultDto);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
            return;
        }

        try {
            userService.add(createUserReq);
        }catch (Exception e){
            ResultDTO<String> resultDto =  ResultDTO.fail("操作失败");
            String jsonString = JSON.toJSONString(resultDto);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
            return;
        }

        ResultDTO<String> resultDto =  ResultDTO.success("操作成功");
        String jsonString = JSON.toJSONString(resultDto);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user = JSON.parseObject(params, User.class);
        userService.updateUser(user);
        response.getWriter().write("success");
    }

    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user = JSON.parseObject(params, User.class);
        int id = user.getId();
        String password = user.getPassword();
        userService.updatePassword(id, password);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        userService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user = JSON.parseObject(params, User.class);
        userService.deleteById(user);
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user = JSON.parseObject(params, User.class);
        PageBean<User> pageBean = userService.selectByPageAndCondition(currentPage, pageSize, user);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void getUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("user");
        if (null != loginUser) {
            Staff staff = staffService.selectByUserId(loginUser.getId());
            response.setContentType("text/plain;charset=utf-8");
            String result = JSON.toJSONString(ResultDTO.success(staff));
            response.getWriter().write(result);
        }
    }

    public void getPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("user");
        if (loginUser != null) {
            CheckoutLoginInfo checkoutLoginInfo = userService.login(loginUser.getUserName());
            String password = checkoutLoginInfo.getPassword();
            response.setContentType("text/plain;charset=utf-8");
            response.getWriter().write(password);
        }
    }

    public void getUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("user");
        if (loginUser != null) {
            String userId = Integer.toString(loginUser.getId());
            response.getWriter().write(userId);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.getWriter().write("success");
    }



}
