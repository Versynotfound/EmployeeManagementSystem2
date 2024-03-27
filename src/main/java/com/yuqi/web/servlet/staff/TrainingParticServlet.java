package com.yuqi.web.servlet.staff;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.*;
import com.yuqi.service.TrainingParticService;
import com.yuqi.service.TrainingService;
import com.yuqi.service.impl.TrainingParticServiceImpl;
import com.yuqi.service.impl.TrainingServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 培训参与servlet
 * @author yuqi
 */
@WebServlet("/trainingPartic/*")
public class TrainingParticServlet extends BaseServlet {
    private TrainingParticService trainingParticService = new TrainingParticServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        TrainingParticipation trainingPartic = JSON.parseObject(params, TrainingParticipation.class);
        trainingParticService.add(trainingPartic);
        response.getWriter().write("success");
    }
    public void selectByStaffId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        int id = JSON.parseObject(params, int.class);
        System.out.println(params);

        PageBean<TrainingParticipation> pageBean = trainingParticService.selectByStaffId(currentPage, pageSize, id);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
