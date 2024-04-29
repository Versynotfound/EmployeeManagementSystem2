package com.yuqi.utils;

import com.yuqi.constant.LoginConstant;
import com.yuqi.enums.UserIdentityEnum;
import com.yuqi.pojo.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuqi
 */
public class LoginUserUtil {
    public static LoginUser getLoginUser(HttpServletRequest request){
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute(LoginConstant.LOGIN_USER_TITLE);
        return loginUser;
    }

    public static Boolean findLoginUserIsNormal(LoginUser loginUser){
        if(null == loginUser){
            return null;
        }
        if(UserIdentityEnum.NORMAL.getCode() == loginUser.getIdentity()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
