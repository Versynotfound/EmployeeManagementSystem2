package com.yuqi.pojo;

import lombok.Data;

/**
 * 员工类
 * @author yuqi
 */
@Data
public class Staff {

    /**
     * 员工id
     */
    private Integer id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 性别，0-男，1-女
     */
    private Integer gender;
    /**
     * 身份，0-员工，1-主管
     */
    private Integer level;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 电话
     */
    private String phone;
    /**
     * 员工状态，0-在职，1-离职，2-休假，3-请假
     */
    private Integer status;
    /**
     * 关联的部门id
     */
    private Integer departmentId;
    /**
     * 关联的用户id
     */
    private Integer userId;

    private String username;

    private String departmentName;

    public String getGenderStr() {
        if(null == this.gender){
            return "未知";
        }
        if (0 == this.gender) {
            return "男";
        } else if (1 == this.gender) {
            return "女";
        }
        return "未知";
    }

    public String getLevelStr() {
        if(null == this.level){
            return "未知";
        }
        if (this.level == 0) {
            return "员工";
        } else if (this.level == 1) {
            return "主管";
        }
        return "未知";
    }

    public String getStatusStr() {
        if(null == this.status){
            return "未知";
        }
        if (this.status == 0) {
            return "在职";
        } else if (this.status == 1) {
            return "离职";
        } else if (this.status == 2) {
            return "休假";
        } else if (this.status == 3) {
            return "请假";
        } else {
            return "未知";
        }
    }
}
