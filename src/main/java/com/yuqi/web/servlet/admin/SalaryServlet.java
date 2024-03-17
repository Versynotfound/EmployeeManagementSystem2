package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.pojo.Staff;
import com.yuqi.service.SalaryService;
import com.yuqi.service.StaffService;
import com.yuqi.service.impl.SalaryServiceImpl;
import com.yuqi.service.impl.StaffServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 薪资信息servlet
 * @author yuqi
 */
@WebServlet("/salary/*")
public class SalaryServlet extends BaseServlet {
    private SalaryService salaryService = new SalaryServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Salary salary = JSON.parseObject(params, Salary.class);
        salaryService.add(salary);
        response.getWriter().write("success");
    }

    public void updateSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Salary salary = JSON.parseObject(params, Salary.class);
        salaryService.updateSalary(salary);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        salaryService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Salary salary = JSON.parseObject(params, Salary.class);
        salaryService.deleteById(salary);

        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        Salary salary = JSON.parseObject(params,Salary.class);
        PageBean<Salary> pageBean = salaryService.selectByPageAndCondition(currentPage, pageSize, salary);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
