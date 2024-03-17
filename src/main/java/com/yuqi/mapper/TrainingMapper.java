package com.yuqi.mapper;

import com.yuqi.pojo.Training;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * TrainingMapper接口，提供操作培训Training相关数据的方法集合
 * @author yuqi
 */
public interface TrainingMapper {
    /**
     * 根据培训名称查询培训id
     * @param name 培训名称
     */
    @Select("select id from tb_training where name = #{name}")
    int getTrainingIdByName(String name);
    /**
     * 添加
     * @param training 培训信息
     */
    @Insert("insert into tb_training values( null,#{training.name},#{training.description},#{training.startTime},#{training.endTime},#{training.adminId})")
    void add(@Param("training") Training training);

    /**
     * 修改
     * @param training 培训信息
     */
    @Update("update tb_training set name = #{name},description = #{description},start_time = #{startTime},end_time = #{endTime},admin_id = #{adminId} where id = #{id}")
    void update(Training training);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_training where id = #{id}")
    void deleteById(int id);

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param training 封装了条件的Training对象
     * @return 培训信息集合
     */
    List<Training> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("training") Training training);

    /**
     * 查询带条件下总记录数
     * @param training 封装了条件的Training对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Training training);

}
