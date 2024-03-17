package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.pojo.Training;
import com.yuqi.service.SalaryService;
import com.yuqi.service.TrainingService;
import com.yuqi.service.impl.SalaryServiceImpl;
import com.yuqi.service.impl.TrainingServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 培训信息servlet
 * @author yuqi
 */
@WebServlet("/training/*")
public class TrainingServlet extends BaseServlet {
    private TrainingService trainingService = new TrainingServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Training training = JSON.parseObject(params, Training.class);
        trainingService.add(training);
        response.getWriter().write("success");
    }

    public void updateTraining(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Training training = JSON.parseObject(params, Training.class);
        trainingService.updateTraining(training);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        trainingService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Training training = JSON.parseObject(params, Training.class);
        trainingService.deleteById(training);

        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        Training training = JSON.parseObject(params,Training.class);
        PageBean<Training> pageBean = trainingService.selectByPageAndCondition(currentPage, pageSize, training);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void getTrainingIdByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int trainingId = trainingService.getTrainingIdByName(name);
        response.getWriter().write(String.valueOf(trainingId));
    }
}
