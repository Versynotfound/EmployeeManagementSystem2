package com.yuqi.pojo;

import lombok.Data;

/**
 * 用户类
 * @author yuqi
 */
@Data
public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 登录名称
     */
    private String username ;
    /**
     * 密码
     */
    private String password ;
    /**
     * 身份，0-用户，1-管理员
     */
    private Integer identity;

    public String getIdentityStr() {
        if (this.identity == 0) {
            return "用户";
        } else if (this.identity == 1) {
            return "管理员";
        } else {
            return "未知";
        }
    }
}