package com.yuqi.service;

import com.yuqi.pojo.Attendance;
import com.yuqi.pojo.PageBean;

/**
 * AttendanceMapper接口，提供操作考勤记录Attendance相关数据的方法集合
 * @author yuqi
 */
public interface AttendanceService {
    /**
     * 添加
     * @param attendance 考勤记录
     */
    void add(Attendance attendance);

    /**
     * 修改
     * @param attendance 考勤记录
     */
    void updateAttendance(Attendance attendance);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param attendance 考勤记录
     */
    void deleteById(Attendance attendance);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param attendance 封装了查询条件的考勤记录
     * @return PageBean对象
     */
    PageBean<Attendance> selectByPageAndCondition(int currentPage, int pageSize, Attendance attendance);
}
