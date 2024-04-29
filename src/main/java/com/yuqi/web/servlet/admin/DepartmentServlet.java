package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.Department;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.DepartmentService;
import com.yuqi.service.impl.DepartmentServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 部门Servlet
 * @author yuqi
 */
@WebServlet("/department/*")
public class DepartmentServlet extends BaseServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();

//    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 调用service查询
//        List<Department> departments = departmentService.selectAll();
//        // 转为JSON格式
//        String jsonString = JSON.toJSONString(departments);
//        // 写数据，因为存在中文所以要setContentType
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Department department = JSON.parseObject(params, Department.class);
        departmentService.add(department);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        departmentService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Department department = JSON.parseObject(params, Department.class);

        departmentService.updateDepartment(department);

        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Department department = JSON.parseObject(params, Department.class);

        departmentService.deleteById(department);

        response.getWriter().write("success");
    }

//    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 接收当前页码和每页显示条数
//        String currentPageStr = request.getParameter("currentPage");
//        String pageSizeStr = request.getParameter("pageSize");
//        // 前端传过来的只能是String类型，需要转换
//        int currentPage = Integer.parseInt(currentPageStr);
//        int pageSize = Integer.parseInt(pageSizeStr);
//
//        PageBean<Department> pageBean = departmentService.selectByPage(currentPage, pageSize);
//
//        String jsonString = JSON.toJSONString(pageBean);
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        // 获取查询条件对象。前端传过来的数据既有url数据也有请求体数据，两个方法分开接收
        // getReader,获取请求体数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Department department = JSON.parseObject(params,Department.class);

        PageBean<Department> pageBean = departmentService.selectByPageAndCondition(currentPage, pageSize,department);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    
    public void selectDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = departmentService.selectDepartments();
        String jsonString = JSON.toJSONString(departments);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
