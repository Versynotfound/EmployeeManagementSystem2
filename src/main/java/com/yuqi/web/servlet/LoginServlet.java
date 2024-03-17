package com.yuqi.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.Department;
import com.yuqi.pojo.User;
import com.yuqi.service.UserService;
import com.yuqi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        User user = userService.login(_user.getUsername(),_user.getPassword());

        if (user != null && user.getIdentity().equals(_user.getIdentity())){
            // 将登录成功后user对象存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
