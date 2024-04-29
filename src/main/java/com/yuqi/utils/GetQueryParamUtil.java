package com.yuqi.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author yuqi
 */
public class GetQueryParamUtil<T> {
    public static <T> T getQueryParam(Class<T> tClass, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        return JSON.parseObject(params, tClass);
    }
}
