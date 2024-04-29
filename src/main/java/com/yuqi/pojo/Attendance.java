package com.yuqi.pojo;

import com.yuqi.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考勤记录类
 * @author yuqi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Attendance extends BasePage {
    /**
     * 主键
     */
    private Integer id ;
    /**
     * 日期
     */
    private String date;
    /**
     * 考勤状态，0-出勤，1-迟到，2-早退，3-请假，4-缺勤
     */
    private Integer status;
    /**
     * 员工Id
     */
    private Integer staffId;

    private String staffName;

    private Integer departmentId;
    private String departmentName;

    public String getStatusStr(){
        if (this.status == 0) {
            return "出勤";
        } else if (this.status == 1) {
            return "迟到";
        } else if (this.status == 2) {
            return "早退";
        } else if (this.status == 3) {
            return "请假";
        } else if (this.status == 4) {
            return "缺勤";
        } else {
            return "未知";
        }
    }
}
