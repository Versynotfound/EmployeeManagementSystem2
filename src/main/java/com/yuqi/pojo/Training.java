package com.yuqi.pojo;

import com.yuqi.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 培训类
 * @author yuqi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Training extends BasePage {
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

    /**
     * 是否参与了活动,0未参与  1已参与
     */
    private Integer activeInvolved;
}
