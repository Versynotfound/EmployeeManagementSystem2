package com.yuqi.pojo.activity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuqi
 */
@Data
public class QueryActivityInvolvedResp {

    /**
     * 参与记录ID
     */
    private Integer id;

    /**
     * 活动ID
     */
    private Integer activeId;
    /**
     * 活动名称
     */
    private String activeIdName;
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

    /**
     * 部门ID
     */
    private Integer departmentId;
    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 参与人
     */
    private String involvedName;

    /**
     * 得分
     */
    private BigDecimal score;
}
