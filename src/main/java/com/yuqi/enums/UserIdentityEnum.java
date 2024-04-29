package com.yuqi.enums;

import lombok.Getter;

/**
 * 登录用户角色
 * @author yuqi
 */
@Getter
public enum UserIdentityEnum {
    // 登录用户角色
    NORMAL(0,"员工"),

    ADMIN(1,"管理员");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(UserIdentityEnum userIdentityEnum : UserIdentityEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }

    private int code;

    private String msg;

    UserIdentityEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
