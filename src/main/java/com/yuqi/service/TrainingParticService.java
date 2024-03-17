package com.yuqi.service;

import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.TrainingParticipation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TrainingParticService接口，定义了培训参与情况服务层的相关操作方法。
 * @author yuqi
 */
public interface TrainingParticService {
    /**
     * 添加
     * @param trainingPartic 培训参与情况
     */
    void add(TrainingParticipation trainingPartic);
    /**
     * 根据员工id查询个人参与的培训信息
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param id 员工id
     * @return PageBean对象
     */
    PageBean<TrainingParticipation> selectByStaffId(int currentPage, int pageSize, int id);
}
