package com.yuqi.mapper;

import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.Training;
import com.yuqi.pojo.TrainingParticipation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Insert("insert into tb_training_participation(id,training_id,staff_id) values( null,#{trainingPartic.trainingId},#{trainingPartic.staffId})")
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
}
