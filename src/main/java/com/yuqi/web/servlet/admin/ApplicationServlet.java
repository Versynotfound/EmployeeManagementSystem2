package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.Application;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.service.ApplicationService;
import com.yuqi.service.SalaryService;
import com.yuqi.service.impl.ApplicationServiceImpl;
import com.yuqi.service.impl.SalaryServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 申请servlet
 * @author yuqi
 */
@WebServlet("/application/*")
public class ApplicationServlet extends BaseServlet {
    private ApplicationService applicationService = new ApplicationServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Application application = JSON.parseObject(params, Application.class);
        applicationService.add(application);
        response.getWriter().write("success");
    }

    public void updateApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Application application = JSON.parseObject(params, Application.class);
        applicationService.updateApplication(application);
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        Application application = JSON.parseObject(params,Application.class);
        PageBean<Application> pageBean = applicationService.selectByPageAndCondition(currentPage, pageSize, application);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
