package com.yuqi.service;

import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Staff;
import com.yuqi.pojo.Staff;
import com.yuqi.pojo.User;

import java.util.List;

/**
 * StaffService接口，定义了员工服务层的相关操作方法。
 * @author yuqi
 */
public interface StaffService {
    /**
     * 添加
     * @param staff 员工
     */
    void add(Staff staff);

    /**
     * 修改
     * @param staff 员工
     */
    void updateStaff(Staff staff);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param staff 员工
     */
    void deleteById(Staff staff);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param staff 封装了查询条件的员工
     * @return PageBean对象
     */
    PageBean<Staff> selectByPageAndCondition(int currentPage, int pageSize, Staff staff);

    /**
     * 查询所有主管
     * @return 主管集合
     */
    List<Staff> selectHeaders();

    /**
     * 查询所有员工
     * @return 员工集合
     */
    List<Staff> selectStaffs();

    /**
     * 根据用户id查询员工信息
     * @return 员工
     */
    Staff selectByUserId(int id);
}
