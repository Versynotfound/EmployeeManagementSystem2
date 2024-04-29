package com.yuqi.web.filter;

import com.yuqi.constant.LoginConstant;
import com.yuqi.pojo.LoginUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 权限验证的过滤器
 * @author yuqi
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        LoginUser loginUser = (LoginUser)session.getAttribute("user");
        if(null == loginUser){
            req.setAttribute("login_msg","您尚未登录");
            resp.sendRedirect("/EmployeeManagementSystem2/index.html");
            return;
        }
        Integer role =  loginUser.getIdentity();
        if(!LoginConstant.ADMIN_ROLE.equals(role)){
            resp.sendRedirect("/EmployeeManagementSystem2/unauthorized.html");
            return;
        }
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
