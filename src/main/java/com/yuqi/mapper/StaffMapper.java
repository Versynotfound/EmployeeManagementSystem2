package com.yuqi.mapper;

import com.yuqi.pojo.Staff;
import com.yuqi.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * StaffMapper接口，提供操作员工Staff相关数据的方法集合
 * @author yuqi
 */
public interface StaffMapper {
    /**
     * 添加
     * @param staff 员工
     */
    @Insert("insert into tb_staff values( null,#{staff.name},#{staff.gender},#{staff.level},#{staff.avatar},#{staff.phone},#{staff.status},#{staff.departmentId},#{staff.userId})")
    void add(@Param("staff") Staff staff);

    /**
     * 修改
     * @param staff 员工
     */
    @Update("update tb_staff set name = #{name},gender = #{gender},level = #{level},phone = #{phone},status = #{status},department_id = #{departmentId},avatar = #{avatar},user_id = #{userId} where id = #{id}")
    void update(Staff staff);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_staff where id = #{id}")
    void deleteById(int id);
    
    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param staff 封装了条件的Staff对象
     * @return 员工集合
     */
    List<Staff> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("staff") Staff staff);

    /**
     * 查询带条件下总记录数
     * @param staff 封装了条件的Staff对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Staff staff);

    /**
     * 查询所有主管
     * @return 主管集合
     */
    @Select("select * from tb_staff where level = 1")
    List<Staff> selectHeaders();

}
