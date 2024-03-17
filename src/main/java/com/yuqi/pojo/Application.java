package com.yuqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请类
 * @author yuqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 类型，0-请假，1-离职
     */
    private Integer type;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 原因
     */
    private String reason;
    /**
     * 状态，0-待审批，1-同意，2-拒绝
     */
    private Integer status;
    /**
     * 员工id
     */
    private Integer staffId;

    private Integer adminId;

    private String staffName;

    private String adminName;

    public String getTypeStr(){
        if (this.type == 0) {
            return "请假";
        } else if (this.type == 1) {
            return "离职";
        } else {
            return "未知";
        }
    }

    public String getStatusStr(){
        if (this.status == 0) {
            return "待审批";
        } else if (this.status == 1) {
            return "同意";
        } else if (this.status == 2) {
            return "拒绝";
        } else {
            return "--";
        }
    }
}
