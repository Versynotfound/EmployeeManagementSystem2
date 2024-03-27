package com.yuqi.service.impl;

import com.yuqi.mapper.UserMapper;
import com.yuqi.mapper.UserMapper;
import com.yuqi.mapper.UserMapper;
import com.yuqi.mapper.UserMapper;
import com.yuqi.pojo.*;
import com.yuqi.pojo.User;
import com.yuqi.pojo.User;
import com.yuqi.service.UserService;
import com.yuqi.service.UserService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 用户User业务实现类
 * @author yuqi
 */
public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.add(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updatePassword(int id,String password){
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updatePassword(id,password);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteById(user.getId());
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        String username = user.getUsername();
        if (username != null && !username.isEmpty()){
            user.setUsername("%" + username + "%");
        }
        Integer identity = user.getIdentity();
        if (identity != null){
            user.setIdentity(identity);
        }

        List<User> rows = mapper.selectByPageAndCondition(begin, pageSize,user);
        int totalCount = mapper.selectTotalCountByCondition(user);

        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public User login(String username, String password) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username,password);
        sqlSession.close();
        return user;
    }
}
