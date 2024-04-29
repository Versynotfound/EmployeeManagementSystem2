package com.yuqi.service;

import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.PageBean;

/**
 * AnnouncementService接口，定义了公告服务层的相关操作方法。
 * @author yuqi
 */
public interface AnnouncementService {
    /**
     * 添加
     * @param announcement 公告
     */
    void add(Announcement announcement);

    /**
     * 修改
     * @param announcement 公告
     */
    void updateAnnouncement(Announcement announcement);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param announcement 公告
     */
    void deleteById(Announcement announcement);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param announcement 封装了查询条件的公告
     * @return PageBean对象
     */
    PageBean<Announcement> selectByPageAndCondition(int currentPage, int pageSize, Announcement announcement);

}
