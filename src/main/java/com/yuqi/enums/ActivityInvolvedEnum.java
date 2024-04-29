package com.yuqi.enums;

import lombok.Getter;

/**
 * 是否参与活动枚举
 * @author yuqi
 */
@Getter
public enum ActivityInvolvedEnum {
    // 是否参与活动
    NO(0,"未参与"),

    YES(1,"已参与");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(ActivityInvolvedEnum userIdentityEnum : ActivityInvolvedEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }


    private int code;

    private String msg;

    ActivityInvolvedEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
