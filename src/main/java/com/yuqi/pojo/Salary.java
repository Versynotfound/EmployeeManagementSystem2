package com.yuqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * 薪资类
 * @author yuqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    /**
     * 薪资主键
     */
    private Integer id;
    /**
     * 月份
     */
    private String month;
    /**
     * 底薪
     */
    private double basicSalary;
    /**
     * 满勤奖励
     */
    private double fullAttendanceBonus;
    /**
     * 业绩奖金
     */
    private double performanceBonus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的员工id
     */
    private Integer staffId;
    /**
     * 关联的部门id
     */
    private Integer departmentId;

    private String staffName;

    private String departmentName;

    /**
     * 总金额
     */
    public double getTotalSalary() {
        return basicSalary + fullAttendanceBonus + performanceBonus;
    }
}

