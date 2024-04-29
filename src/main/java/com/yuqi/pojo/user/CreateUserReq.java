package com.yuqi.pojo.user;

import lombok.Data;

/**
 * 注册新用户
 * @author yuqi
 */
@Data
public class CreateUserReq {
    /**
     * 登录名
     */
    private String loginName ;
    /**
     * 密码
     */
    private String password ;
    /**
     * 身份，0-用户，1-管理员
     */
    private Integer identity;
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
     * 当前登录用户ID
     */
    private Integer loginUserId;
}
