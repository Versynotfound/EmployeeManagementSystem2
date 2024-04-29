package com.yuqi.mapper;

import com.yuqi.pojo.QueryStaffInvolvedActivity;
import com.yuqi.pojo.TrainingParticipation;
import com.yuqi.pojo.activity.ActivityScoreReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedResp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TrainingParticMapper接口，提供操作培训参与情况TrainingPartic相关数据的方法集合
 * @author yuqi
 */
public interface TrainingParticMapper {
    /**
     * 添加
     * @param trainingPartic 培训参与情况
     */
    @Insert("insert into tb_training_participation(training_id,staff_id) values(#{trainingParticipation.trainingId},#{trainingParticipation.staffId})")
    void add(@Param("trainingParticipation") TrainingParticipation trainingPartic);

    /**
     * 根据员工id查询个人参与的培训信息
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param id 员工id
     * @return 参与的培训集合
     */
    List<TrainingParticipation> selectByStaffId(@Param("begin")int begin, @Param("size")int size,@Param("id") int id);

    /**
     * 查询总记录数
     * @param id 员工id
     * @return 总记录数
     */
    int selectTotalCountByStaffId(int id);

    /**
     * 获取当前登录用户已参加过的活动
     * @param queryStaffInvolvedActivity
     * @return
     */
    List<TrainingParticipation> queryStaffInvolvedActivity(@Param("req") QueryStaffInvolvedActivity queryStaffInvolvedActivity);

    /**
     * 根据用户ID以及活动ID查询用户是否已参与该活动
     * @param staffId
     * @param activeId
     * @return
     */
    int selectCountById(@Param("staffId") Integer staffId,@Param("activeId")Integer activeId);

    /**
     * 查询当前登录用户已参加过的活动
     * @param queryActivityInvolvedReq
     * @return
     */
    List<QueryActivityInvolvedResp> pageInvolvedActivityList(@Param("req") QueryActivityInvolvedReq queryActivityInvolvedReq);

    /**
     * 获取当前登录用户已参加过的活动总数
     * @param queryActivityInvolvedReq
     * @return
     */
    Integer pageInvolvedActivityListCount(@Param("req") QueryActivityInvolvedReq queryActivityInvolvedReq);

    /**
     * 更新活动分数
     * @param activityScoreReq
     * @return
     */
    int updateScoreById(@Param("req") ActivityScoreReq activityScoreReq);
}
