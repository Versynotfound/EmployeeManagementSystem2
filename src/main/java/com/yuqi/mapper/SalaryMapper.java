package com.yuqi.mapper;

import com.yuqi.pojo.Salary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * SalaryMapper接口，提供操作薪资salary相关数据的方法集合
 * @author yuqi
 */
public interface SalaryMapper {
    /**
     * 添加
     * @param salary 薪资信息
     */
    @Insert("insert into tb_salary values( null,#{salary.month},#{salary.basicSalary},#{salary.fullAttendanceBonus},#{salary.performanceBonus},#{salary.remark},#{salary.staffId},#{salary.departmentId})")
    void add(@Param("salary") Salary salary);

    /**
     * 修改
     * @param salary 薪资信息
     */
    @Update("update tb_salary set month = #{month},basic_salary = #{basicSalary},full_attendance_bonus = #{fullAttendanceBonus},performance_bonus = #{performanceBonus},remark = #{remark},staff_id = #{staffId},department_id = #{departmentId} where id = #{id}")
    void update(Salary salary);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_salary where id = #{id}")
    void deleteById(int id);

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param salary 封装了条件的Salary对象
     * @return 薪资信息集合
     */
    List<Salary> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("salary") Salary salary);

    /**
     * 查询带条件下总记录数
     * @param salary 封装了条件的Salary对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Salary salary);

    /**
     * 根据员工id和年月取消全勤奖
     * @param id 员工id
     * @param month 年月
     */
    @Update("update tb_salary set full_attendance_bonus = 0 where staff_id = #{id} and month = #{month}")
    void cancelFullAttendanceBonus(@Param("id") int id, @Param("month") String month);
}
