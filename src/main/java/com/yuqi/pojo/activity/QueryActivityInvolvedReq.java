package com.yuqi.pojo.activity;

import com.yuqi.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuqi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryActivityInvolvedReq extends BasePage {
    /**
     * 活动名称
     */
    private String activeName;

    /**
     * 员工ID
     */
    private Integer staffId;

    /**
     * 部门ID
     */
    private Integer departmentId;
}
