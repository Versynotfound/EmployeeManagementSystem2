//package com.yuqi.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * 权限的过滤器
// * @author yuqi
// */
//@WebFilter("/*")
//public class AuthorizationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest)request;
//
////        // 判断是否是跟登录相关的资源
////        String[] urls = {"login","/login.jsp","/js/","/element-ui/"};
////        String url = req.getRequestURI().toString();
////        for (String u : urls) {
////            if (url.contains(u)){
////                // 放行，把对应的req和res放过去
////                chain.doFilter(request, response);
////                // 如果没登录就访问非登录注册的资源，过滤器上面的代码会执行但是不会触发return。
////                // 执行到下面的代码后会跳到登录页面并请求登录资源，这时候又会经过过滤器，又会执行上面的代码给予登录资源触发return
////                return;
////            }
////        }
////
////        // 判断用户是否登录
////        HttpSession session = req.getSession();
////        Object user = session.getAttribute("user");
////        if (user!= null){
////            chain.doFilter(request, response);
////        } else {
////            req.getRequestDispatcher("/unauthorized.html").forward(request,response);
////        }
//
//    }
//    @Override
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}
