package com.yuqi.mapper;

import com.yuqi.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * DepartmentMapper接口，提供操作部门department相关数据的方法集合
 * @author yuqi
 */
public interface DepartmentMapper {
//    /**
//     * 查询所有
//     * @return 部门集合
//     */
//    @Select("select * from tb_department")
//    @ResultMap("departmentResultMap")
//    List<Department> selectAll();

    /**
     * 添加
     * @param department 部门
     */
    @Insert("insert into tb_department values( null,#{department.name},#{department.description},#{department.managerId})")
    void add(@Param("department") Department department);

    /**
     * 批量删除。复杂功能不使用注解，使用xml
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

//    /**
//     * 分页查询
//     * @param begin 当前页码
//     * @param size 每页显示条数
//     * @return 部门集合
//     */
//    @Select("select * from tb_department limit #{begin},#{size}")
//    @ResultMap("departmentResultMap")
//    List<Department> selectByPage(@Param("begin")int begin,@Param("size")int size);

//    /**
//     * 查询总记录数
//     * @return 总记录数
//     */
//    @Select("select count(*) from tb_department")
//    int selectTotalCount();

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param department 封装了条件的Department对象
     * @return 部门集合
     */
    List<Department> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("department") Department department);

    /**
     * 查询带条件下总记录数
     * @param department 封装了条件的Department对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Department department);

    /**
     * 修改
     * @param department 部门
     */
    @Update("update tb_department set name = #{name},description=#{description},manager_id = #{managerId} where id = #{id}")
    void update(Department department);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_department where id = #{id}")
    void deleteById(int id);

    
    /**
     * 查询所有部门
     * @return 部门集合
     */
    @Select("select * from tb_department")
    List<Department> selectDepartments();

}
