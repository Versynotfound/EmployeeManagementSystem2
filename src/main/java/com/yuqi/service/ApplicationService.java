package com.yuqi.service;

import com.yuqi.pojo.Application;
import com.yuqi.pojo.PageBean;

/**
 * Application接口，定义了申请服务层的相关操作方法。
 * @author yuqi
 */
public interface ApplicationService {
    /**
     * 员工添加
     * @param application 申请信息
     */
    void add(Application application);

    /**
     * 管理员审批
     * @param application 申请信息
     */
    void updateApplication(Application application) throws Exception;

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param application 封装了查询条件的申请信息
     * @return PageBean对象
     */
    PageBean<Application> selectByPageAndCondition(int currentPage, int pageSize, Application application);

}
