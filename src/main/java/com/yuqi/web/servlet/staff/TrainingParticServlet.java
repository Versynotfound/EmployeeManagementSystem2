package com.yuqi.web.servlet.staff;

import com.alibaba.fastjson.JSON;
import com.yuqi.common.ResultDTO;
import com.yuqi.constant.BaseConstant;
import com.yuqi.pojo.InvolvedActiveReq;
import com.yuqi.pojo.LoginUser;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.TrainingParticipation;
import com.yuqi.pojo.activity.ActivityScoreReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedResp;
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
import java.io.IOException;

/**
 * 培训参与servlet
 * @author yuqi
 */
@WebServlet("/trainingPartic/*")
public class TrainingParticServlet extends BaseServlet {
    private TrainingParticService trainingParticService = new TrainingParticServiceImpl();

    private TrainingService trainingService = new TrainingServiceImpl();

    /**
     * 参与活动
     * @param request
     * @param response
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InvolvedActiveReq involvedActiveReq = GetQueryParamUtil.getQueryParam(InvolvedActiveReq.class,request);
        if(null == involvedActiveReq){
            response.setContentType("text/json;charset=utf-8");
            ResultDTO<String> result = ResultDTO.fail("参与活动信息不能为空");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        Integer activeId = involvedActiveReq.getActiveId();
        if(null == activeId){
            response.setContentType("text/json;charset=utf-8");
            ResultDTO<String> result = ResultDTO.fail("活动ID不能为空");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        int activeExits = trainingService.selectCountById(activeId);
        if(BaseConstant.ZERO == activeExits){
            response.setContentType("text/json;charset=utf-8");
            ResultDTO<String> result = ResultDTO.fail("活动不存在");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Integer count = trainingParticService.selectCountById(loginUser.getUserId(),activeId);
        if(!BaseConstant.ZERO.equals(count)){
            response.setContentType("text/json;charset=utf-8");
            ResultDTO<String> result = ResultDTO.fail("您已参与该活动,请勿重复参与");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        TrainingParticipation trainingParticipation = new TrainingParticipation();
        trainingParticipation.setTrainingId(activeId);
        trainingParticipation.setStaffId(loginUser.getUserId());
        trainingParticService.add(trainingParticipation);
        response.setContentType("text/json;charset=utf-8");
        ResultDTO<String> result = ResultDTO.success("参与成功");
        response.getWriter().write(JSON.toJSONString(result));
    }


    /**
     * 分页获取参与记录(admin以及普通用户都能查询)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageInvolvedActiveList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryActivityInvolvedReq queryActivityInvolvedReq = GetQueryParamUtil.getQueryParam(QueryActivityInvolvedReq.class,request);
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean normal = LoginUserUtil.findLoginUserIsNormal(loginUser);
        Integer currentPage = queryActivityInvolvedReq.getCurrentPage();
        currentPage = (currentPage-1)* queryActivityInvolvedReq.getPageSize();
        queryActivityInvolvedReq.setCurrentPage(currentPage);
        if(normal){
            queryActivityInvolvedReq.setStaffId(loginUser.getUserId());
        }
        PageBean<QueryActivityInvolvedResp> respPageBean = trainingParticService.pageInvolvedActivityList(queryActivityInvolvedReq);
        String jsonString = JSON.toJSONString(respPageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 活动打分
     * @param request
     * @param response
     */
    public void activeScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ActivityScoreReq activityScoreReq = GetQueryParamUtil.getQueryParam(ActivityScoreReq.class,request);
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean normal = LoginUserUtil.findLoginUserIsNormal(loginUser);
        if(normal){
            response.setContentType("text/json;charset=utf-8");
            ResultDTO<String> resultDto = ResultDTO.fail("您无权打分,个人得分将由管理员进行打分");
            response.getWriter().write(JSON.toJSONString(resultDto));
            return;
        }
        trainingParticService.updateScoreById(activityScoreReq);
        response.setContentType("text/json;charset=utf-8");
        ResultDTO<String> resultDto = ResultDTO.success("操作成功");
        response.getWriter().write(JSON.toJSONString(resultDto));
    }






}
