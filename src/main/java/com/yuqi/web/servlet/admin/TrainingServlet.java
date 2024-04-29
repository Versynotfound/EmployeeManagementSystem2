package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.enums.ActivityInvolvedEnum;
import com.yuqi.pojo.*;
import com.yuqi.service.TrainingParticService;
import com.yuqi.service.TrainingService;
import com.yuqi.service.impl.TrainingParticServiceImpl;
import com.yuqi.service.impl.TrainingServiceImpl;
import com.yuqi.utils.GetQueryParamUtil;
import com.yuqi.utils.LoginUserUtil;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 培训信息servlet
 * @author yuqi
 */
@WebServlet("/training/*")
public class TrainingServlet extends BaseServlet {
    private TrainingService trainingService = new TrainingServiceImpl();

    private TrainingParticService trainingParticService = new TrainingParticServiceImpl();

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
        Training training = GetQueryParamUtil.getQueryParam(Training.class,request);
        PageBean<Training> pageBean = trainingService.selectByPageAndCondition(training.getCurrentPage(), training.getPageSize(), training);
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean isNormal = LoginUserUtil.findLoginUserIsNormal(loginUser);
        if(isNormal){
            appendActiveInvolved(loginUser.getUserId(),pageBean);
        }
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 活动列表追加当前登录用户是否已参与对应活动
     * @param staffId
     * @param result
     */
    private void appendActiveInvolved(Integer staffId,PageBean<Training> result){
        if(null == result){
            return;
        }
        List<Training> resultList = result.getRows();
        if(null == resultList || resultList.isEmpty()){
            return;
        }
        List<Integer> activeIdList = resultList.stream().map(e->e.getId()).collect(Collectors.toList());
        QueryStaffInvolvedActivity queryStaffInvolvedActivity = new QueryStaffInvolvedActivity();
        queryStaffInvolvedActivity.setAcitiveIdList(activeIdList);
        queryStaffInvolvedActivity.setStaffId(staffId);
        List<TrainingParticipation> involvedList = trainingParticService.queryStaffInvolvedActivity(queryStaffInvolvedActivity);
        if(null == involvedList || involvedList.isEmpty()){
            resultList.stream().forEach(e->{
                e.setActiveInvolved(ActivityInvolvedEnum.NO.getCode());
            });
            result.setRows(resultList);
            return;
        }
        Map<Integer,TrainingParticipation> involvedMap = involvedList.stream().collect(Collectors.toMap(e->e.getTrainingId(),e->e,(e1,e2)->e2));
        for(Training training : resultList){
            Integer activeId = training.getId();
            TrainingParticipation trainingParticipation =  involvedMap.get(activeId);
            if(null != trainingParticipation){
                training.setActiveInvolved(ActivityInvolvedEnum.YES.getCode());
            }else{
                training.setActiveInvolved(ActivityInvolvedEnum.NO.getCode());
            }
        }
        result.setRows(resultList);
    }




    public void getTrainingIdByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int trainingId = trainingService.getTrainingIdByName(name);
        response.getWriter().write(String.valueOf(trainingId));
    }
}
