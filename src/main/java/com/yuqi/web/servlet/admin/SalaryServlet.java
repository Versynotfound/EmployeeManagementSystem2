package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.enums.UserIdentityEnum;
import com.yuqi.pojo.LoginUser;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.service.SalaryService;
import com.yuqi.service.impl.SalaryServiceImpl;
import com.yuqi.utils.GetQueryParamUtil;
import com.yuqi.utils.LoginUserUtil;
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

    /**
     * 获取薪资列表。管理员可以带员工查询条件,用户登录的时候则带自己的员工ID作为条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Salary salary = GetQueryParamUtil.getQueryParam(Salary.class,request);
        //员工登录的时候进行此处查询
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Integer roleId = loginUser.getIdentity();
        if(UserIdentityEnum.NORMAL.getCode() == roleId){
            salary.setStaffId(loginUser.getUserId());
        }

        PageBean<Salary> pageBean = salaryService.selectByPageAndCondition(salary.getCurrentPage(), salary.getPageSize(), salary);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }



}
