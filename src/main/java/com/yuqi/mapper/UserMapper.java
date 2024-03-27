package com.yuqi.mapper;

import com.yuqi.pojo.*;
import com.yuqi.pojo.User;
import com.yuqi.pojo.User;
import com.yuqi.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * UserMapper接口，提供操作用户user相关数据的方法集合
 * @author yuqi
 */
public interface UserMapper {
    /**
     * 添加
     * @param user 用户
     */
    @Insert("insert into tb_user values( null,#{user.username},#{user.password},#{user.identity})")
    void add(@Param("user") User user);

    /**
     * 修改
     * @param user 用户
     */
    @Update("update tb_user set username = #{username},password=#{password},identity = #{identity} where id = #{id}")
    void update(User user);

    /**
     * 修改密码
     * @param id 用户id
     * @param password 修改后密码
     */
    @Update("update tb_user set password=#{password} where id = #{id}")
    void updatePassword(@Param("id")int id,@Param("password")String password);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_user where id = #{id}")
    void deleteById(int id);
    
    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param user 封装了条件的User对象
     * @return 用户集合
     */
    List<User> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("user") User user);

    /**
     * 查询带条件下总记录数
     * @param user 封装了条件的User对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(User user);

    /**
     * 根据用户名和密码查询
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);
}
