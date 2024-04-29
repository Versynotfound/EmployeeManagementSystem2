package com.yuqi.enums;

import lombok.Getter;

/**
 * 员工身份
 * @author yuqi
 */
@Getter
public enum StaffLevelEnum {
    // 员工身份：普通员工和主管
    NORMAL(0,"员工"),

    MANAGE(1,"主管");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(StaffLevelEnum userIdentityEnum : StaffLevelEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }


    private int code;

    private String msg;

    StaffLevelEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
