package com.yuqi.mapper;

import com.yuqi.pojo.Attendance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * AttendanceMapper接口，提供操作考勤记录Attendance相关数据的方法集合
 * @author yuqi
 */
public interface AttendanceMapper {
    /**
     * 添加
     * @param attendance 薪资信息
     */
    @Insert("insert into tb_attendance values( null,#{attendance.date},#{attendance.status},#{attendance.staffId})")
    void add(@Param("attendance") Attendance attendance);

    /**
     * 修改
     * @param attendance 薪资信息
     */
    @Update("update tb_attendance set date = #{date},status = #{status},staff_id = #{staffId} where id = #{id}")
    void update(Attendance attendance);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_attendance where id = #{id}")
    void deleteById(int id);

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param attendance 封装了条件的Attendance对象
     * @return 薪资信息集合
     */
    List<Attendance> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("attendance") Attendance attendance);

    /**
     * 查询带条件下总记录数
     * @param attendance 封装了条件的Attendance对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Attendance attendance);
}
