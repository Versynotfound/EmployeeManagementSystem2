package com.yuqi.service;

import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;

/**
 * SalaryMapper接口，提供操作薪资salary相关数据的方法集合
 * @author yuqi
 */
public interface SalaryService {
    /**
     * 添加
     * @param salary 薪资信息
     */
    void add(Salary salary);

    /**
     * 修改
     * @param salary 薪资信息
     */
    void updateSalary(Salary salary);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param salary 薪资信息
     */
    void deleteById(Salary salary);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param salary 封装了查询条件的薪资信息
     * @return PageBean对象
     */
    PageBean<Salary> selectByPageAndCondition(int currentPage, int pageSize, Salary salary);

    /**
     * 根据员工id和年月取消全勤奖
     * @param id 员工id
     * @param month 年月
     */
    void cancelFullAttendanceBonus(int id, String month);
}
