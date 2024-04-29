package com.yuqi.enums;

import lombok.Getter;

/**
 * 审批状态枚举
 * @author yuqi
 */
@Getter
public enum ApplycationStatusEnum {
    // 审批状态
    INIT(0,"待审批"),

    SUCCESS(1,"成功"),

    FAIL(2,"拒绝");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(ApplycationStatusEnum userIdentityEnum : ApplycationStatusEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }


    private int code;

    private String msg;

    ApplycationStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
