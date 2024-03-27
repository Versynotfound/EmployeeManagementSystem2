package com.yuqi.service;

import com.yuqi.pojo.*;
import com.yuqi.pojo.User;
import com.yuqi.pojo.User;

/**
 * UserService接口，定义了用户服务层的相关操作方法。
 * @author yuqi
 */
public interface UserService {
    /**
     * 添加
     * @param user 用户
     */
    void add(User user);

    /**
     * 修改
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 修改密码
     * @param id 用户id
     * @param password 修改后密码
     */
    void updatePassword(int id,String password);

    /**
     * 批量删除
     * @param ids 数组
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除
     * @param user 用户
     */
    void deleteById(User user);

    /**
     * 带条件分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param user 封装了查询条件的用户
     * @return PageBean对象
     */
    PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User login(String username, String password);
}
