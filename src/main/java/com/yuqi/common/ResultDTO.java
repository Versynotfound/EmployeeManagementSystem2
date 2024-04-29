package com.yuqi.common;

import com.yuqi.constant.BaseConstant;
import lombok.Data;

/**
 * 返回数据
 * @param <T>
 * @author yuqi
 */
@Data
public class ResultDTO<T> {

    private String code;

    private String msg;

    private T data;

    public static <T> ResultDTO<T> success(Object message){
        ResultDTO resultDto = new ResultDTO();
        resultDto.setCode(BaseConstant.SUCCESS_CODE);
        resultDto.setMsg(BaseConstant.SUCCESS_MSG);
        resultDto.setData(message);
        return resultDto;
    }

    public static <T> ResultDTO<T> fail(String message){
        ResultDTO resultDto = new ResultDTO();
        resultDto.setCode(BaseConstant.FAIL_CODE);
        resultDto.setMsg(message);
        return resultDto;
    }
}
