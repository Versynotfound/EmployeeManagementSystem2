package com.yuqi.pojo;


import lombok.Data;

/**
 * @author yuqi
 */
@Data
public class LoginUser {

    /**
     * 登录用户ID
     */
    private Integer id;
    /**
     * 登录名称
     */
    private String userName ;
    /**
     * 身份，0-用户，1-管理员
     */
    private Integer identity;

    /**
     * 身份名称
     */
    private String identityName;


    /**
     * 员工ID
     */
    private Integer userId;

    /**
     * 员工名称
     */
    private String name;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 身份，0-员工，1-主管
     */
    private Integer level;

    /**
     * 身份名称
     */
    private String levelName;
}
