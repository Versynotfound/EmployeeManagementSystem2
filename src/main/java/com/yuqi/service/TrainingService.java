package com.yuqi.service;

import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.pojo.Training;

/**
 * TrainingService接口，定义了培训服务层的相关操作方法。
 * @author yuqi
 */
public interface TrainingService {
    /**
     * 添加
     * @param training 培训信息
     */
    void add(Training training);

    /**
     * 修改
     * @param training 培训信息
     */
    void updateTraining(Training training);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param training 培训信息
     */
    void deleteById(Training training);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param training 封装了查询条件的培训信息
     * @return PageBean对象
     */
    PageBean<Training> selectByPageAndCondition(int currentPage, int pageSize, Training training);

    /**
     * 根据培训名称查询id
     * @param name 培训名称
     * @return 培训id
     */
     int getTrainingIdByName(String name);

}
