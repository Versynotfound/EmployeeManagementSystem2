package com.yuqi.service;

import com.yuqi.pojo.Department;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Staff;

import java.util.List;

/**
 * DepartmentService接口，定义了部门服务层的相关操作方法。
 * @author yuqi
 */
public interface DepartmentService {
//    /**
//     * 查询所有
//     * @return 部门集合
//     */
//    List<Department> selectAll();

    /**
     * 添加
     * @param department 部门
     */
    void add(Department department);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * 根据id删除，要先把此对象的id获取出来
     * @param department 部门
     */
    void deleteById(Department department);

    /**
     * 修改
     * @param department 部门
     */
    void updateDepartment(Department department);


//    /**
//     * 分页查询
//     * @param currentPage 当前页码
//     * @param pageSize 每页展示条数
//     * @return PageBean对象
//     */
//    PageBean<Department> selectByPage(int currentPage, int pageSize);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param department 封装了查询条件的部门
     * @return PageBean对象
     */
    PageBean<Department> selectByPageAndCondition(int currentPage, int pageSize, Department department);


    /**
     * 查询所有部门
     * @return 部门集合
     */
    List<Department> selectDepartments();
}
