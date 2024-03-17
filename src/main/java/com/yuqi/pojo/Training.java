package com.yuqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训类
 * @author yuqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动描述
     */
    private String description;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 发起人id
     */
    private Integer adminId;

    private String adminName;
}
