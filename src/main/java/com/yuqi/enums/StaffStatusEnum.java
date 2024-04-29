package com.yuqi.enums;

import lombok.Getter;

/**
 * 员工状态枚举
 * @author yuqi
 */
@Getter
public enum StaffStatusEnum {
    // 员工状态
    ON_THE_JOB(0,"在职"),
    RESIGNATION(1,"离职"),
    VACATION(2,"休假"),
    LEAVE(3,"请假");

    public static String getDescByCode(Integer code){
        if(null == code){
           return null;
        }
        for(StaffStatusEnum userIdentityEnum : StaffStatusEnum.values()){
            if(userIdentityEnum.code == code){
                return userIdentityEnum.getMsg();
            }
        }
        return null;
    }


    private int code;

    private String msg;

    StaffStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
