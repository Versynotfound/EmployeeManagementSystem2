package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.common.ResultDTO;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Staff;
import com.yuqi.service.StaffService;
import com.yuqi.service.impl.StaffServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 员工servlet
 * @author yuqi
 */
@WebServlet("/staff/*")
public class  StaffServlet extends BaseServlet {
    private StaffService staffService = new StaffServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Staff staff = JSON.parseObject(params, Staff.class);
        staffService.add(staff);
        response.getWriter().write("success");
    }
    
    public void updateStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Staff staff = JSON.parseObject(params, Staff.class);
        staffService.updateStaff(staff);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        staffService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Staff staff = JSON.parseObject(params, Staff.class);
        staffService.deleteById(staff);

        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        Staff staff = JSON.parseObject(params,Staff.class);
        PageBean<Staff> pageBean = staffService.selectByPageAndCondition(currentPage, pageSize, staff);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectHeaders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service查询
        List<Staff> staffs = staffService.selectHeaders();
        // 转为JSON格式
        String jsonString = JSON.toJSONString(staffs);
        // 写数据，因为存在中文所以要setContentType
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectStaffs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffs = staffService.selectStaffs();
        String jsonString = JSON.toJSONString(staffs);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Map<String,String> parseMap = JSON.parseObject(params, Map.class);
        String id = parseMap.get("id");
        Staff staff = new Staff();
        if(null != id && !"".equals(id)){
            staff = staffService.selectByUserId(Integer.valueOf(id));
        }
        String jsonString = JSON.toJSONString(staff);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void changeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Staff staff =  JSON.parseObject(params, Staff.class);
        try {
            Staff oldStaff = staffService.selectByUserId(staff.getUserId());
            if (null == oldStaff) {
                staffService.add(staff);
            } else {
                staffService.updateByUserId(staff);
            }
        }catch (Exception e){
            ResultDTO<String> result = ResultDTO.fail("操作失败");
            String resultString = JSON.toJSONString(result);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resultString);
            return;
        }
        ResultDTO<String> result = ResultDTO.success("成功");
        String resultString = JSON.toJSONString(result);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(resultString);
    }






}
