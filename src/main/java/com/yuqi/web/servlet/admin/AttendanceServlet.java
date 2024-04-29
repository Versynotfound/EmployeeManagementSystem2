package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.enums.UserIdentityEnum;
import com.yuqi.pojo.Attendance;
import com.yuqi.pojo.LoginUser;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.AttendanceService;
import com.yuqi.service.SalaryService;
import com.yuqi.service.impl.AttendanceServiceImpl;
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
 * 考勤记录Servlet
 * @author yuqi
 */
@WebServlet("/attendance/*")
public class AttendanceServlet extends BaseServlet {
    private AttendanceService attendanceService = new AttendanceServiceImpl();
    private SalaryService salaryService = new SalaryServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Attendance attendance = JSON.parseObject(params, Attendance.class);
        // 如果考勤状态状态不为0（出勤）或3（请假），取消当月全勤奖。
        if (attendance.getStatus() != 0 && attendance.getStatus() != 3) {
            // 获取日期字符串，砍掉日部分
            String date = attendance.getDate();
            String month = date.substring(0, 7);
            // 调用salaryService方法取消当月全勤奖
            salaryService.cancelFullAttendanceBonus(attendance.getStaffId(),month);
        }
        attendanceService.add(attendance);
        response.getWriter().write("success");
    }

    public void updateAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Attendance attendance = JSON.parseObject(params, Attendance.class);
        if (attendance.getStatus() != 0 && attendance.getStatus() != 3) {
            String date = attendance.getDate();
            String month = date.substring(0, 7);
            salaryService.cancelFullAttendanceBonus(attendance.getStaffId(),month);
        }
        attendanceService.updateAttendance(attendance);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        attendanceService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Attendance attendance = JSON.parseObject(params, Attendance.class);
        attendanceService.deleteById(attendance);

        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Attendance attendance = GetQueryParamUtil.getQueryParam(Attendance.class,request);
        //员工登录的时候进行此处查询
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Integer roleId = loginUser.getIdentity();
        if(UserIdentityEnum.NORMAL.getCode() == roleId){
            attendance.setStaffId(loginUser.getUserId());
        }
        PageBean<Attendance> pageBean = attendanceService.selectByPageAndCondition(attendance.getCurrentPage(), attendance.getPageSize(), attendance);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
