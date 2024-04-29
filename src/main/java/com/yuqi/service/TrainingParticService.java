package com.yuqi.service;

import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.QueryStaffInvolvedActivity;
import com.yuqi.pojo.TrainingParticipation;
import com.yuqi.pojo.activity.ActivityScoreReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedResp;

import java.util.List;

/**
 * TrainingParticService接口，定义了培训参与情况服务层的相关操作方法。
 * @author yuqi
 */
public interface TrainingParticService {
    /**
     * 添加
     * @param trainingPartic 培训参与情况
     */
    void add(TrainingParticipation trainingPartic);
    /**
     * 根据员工id查询个人参与的培训信息
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param id 员工id
     * @return PageBean对象
     */
    PageBean<TrainingParticipation> selectByStaffId(int currentPage, int pageSize, int id);


    /**
     * 获取当前登录用户已参与的活动数据
     * @param queryStaffInvolvedActivity
     * @return
     */
    List<TrainingParticipation> queryStaffInvolvedActivity(QueryStaffInvolvedActivity queryStaffInvolvedActivity);


    /**
     * 根据用户ID查询用户参与活动记录
     * @param staffId
     * @return
     */
    int selectCountById(Integer staffId,Integer activeId);

    /**
     * 分页查询参与记录
     * @param queryActivityInvolvedReq
     * @return
     */
    PageBean<QueryActivityInvolvedResp> pageInvolvedActivityList(QueryActivityInvolvedReq queryActivityInvolvedReq);

    /**
     * 参与活动得分
     * @param activityScoreReq
     */
    void updateScoreById(ActivityScoreReq activityScoreReq);
}
