package com.yuqi.enums;

import lombok.Getter;

/**
 * 审批状态类型枚举
 * @author yuqi
 */
@Getter
public enum ApplyCationTypeEnum {
    // 审批状态：
    RESIGNATION(1,"离职"),
    LEAVE(0,"请假");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(ApplyCationTypeEnum userIdentityEnum : ApplyCationTypeEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }


    private int code;

    private String msg;

    ApplyCationTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
