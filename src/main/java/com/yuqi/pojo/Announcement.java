package com.yuqi.pojo;

import lombok.Data;

/**
 * 公告类
 * @author yuqi
 */
@Data
public class Announcement {
    /**
     * 公告id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private String createdTime;
    /**
     * 最后修改时间
     */
    private String lastUpdateTime;
    /**
     * 创建者id（只有管理员可以创建）
     */
    private Integer userId;

    private String username;
}
