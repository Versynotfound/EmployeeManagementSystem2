package com.yuqi.web.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.yuqi.common.ResultDTO;
import com.yuqi.enums.ApplycationStatusEnum;
import com.yuqi.pojo.Application;
import com.yuqi.pojo.LoginUser;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.ApplicationService;
import com.yuqi.service.impl.ApplicationServiceImpl;
import com.yuqi.utils.GetQueryParamUtil;
import com.yuqi.utils.LoginUserUtil;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 申请servlet
 * @author yuqi
 */
@WebServlet("/application/*")
public class ApplicationServlet extends BaseServlet {
    private ApplicationService applicationService = new ApplicationServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Application application = GetQueryParamUtil.getQueryParam(Application.class,request);

        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean normal = LoginUserUtil.findLoginUserIsNormal(loginUser);

        application.setStatus(ApplycationStatusEnum.INIT.getCode());

        if(normal){
            application.setStaffId(loginUser.getUserId());
        }

        try {
            applicationService.add(application);
        }catch (Exception e){
            ResultDTO<String> result = ResultDTO.fail("系统异常");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
        }

        ResultDTO<String> result = ResultDTO.success("操作成功");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void updateApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Application application = GetQueryParamUtil.getQueryParam(Application.class,request);

        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean normal = LoginUserUtil.findLoginUserIsNormal(loginUser);

        if(normal){
            ResultDTO<String> result = ResultDTO.fail("您无权审批");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        try{
            application.setAdminId(loginUser.getUserId());
            application.setAdminName(loginUser.getUserName());
            applicationService.updateApplication(application);
        }catch (Exception e){
            ResultDTO<String> result = ResultDTO.fail("系统异常");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }

        ResultDTO<String> result = ResultDTO.success("操作成功");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Application application = GetQueryParamUtil.getQueryParam(Application.class,request);
        LoginUser loginUser = LoginUserUtil.getLoginUser(request);
        Boolean normal = LoginUserUtil.findLoginUserIsNormal(loginUser);
        if(normal){
            application.setStaffId(loginUser.getUserId());
        }
        PageBean<Application> pageBean = applicationService.selectByPageAndCondition(application.getCurrentPage(), application.getPageSize(), application);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
