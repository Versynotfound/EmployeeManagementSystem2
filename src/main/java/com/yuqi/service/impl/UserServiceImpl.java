package com.yuqi.service.impl;

import com.yuqi.mapper.StaffMapper;
import com.yuqi.mapper.UserMapper;
import com.yuqi.pojo.CheckoutLoginInfo;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Staff;
import com.yuqi.pojo.User;
import com.yuqi.pojo.user.CreateUserReq;
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
    public void add(CreateUserReq createUserReq) throws Exception {
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        StaffMapper staffMapper = sqlSession.getMapper(StaffMapper.class);

        if (createUserReq.getPassword() == null || createUserReq.getPassword().isEmpty()) {
            createUserReq.setPassword("1234");
        }


        try {
            User user = packageUser(createUserReq);
            userMapper.add(user);
            Staff staff =  packageStaff(createUserReq,user);
            staffMapper.add(staff);
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();
            throw new Exception("操作失败");
        }finally {
            sqlSession.close();
        }
    }


    private Staff packageStaff(CreateUserReq createUserReq,User user){
        Staff staff = new Staff();
        staff.setUserId(user.getId());
        staff.setName(createUserReq.getName());
        staff.setLevel(createUserReq.getLevel());
        staff.setDepartmentId(createUserReq.getDepartmentId());
        staff.setGender(createUserReq.getGender());
        staff.setStatus(createUserReq.getStatus());
        staff.setPhone(createUserReq.getPhone());
        return staff;
    }


    private User packageUser(CreateUserReq createUserReq){
        User user = new User();
        user.setUsername(createUserReq.getLoginName());
        user.setPassword(createUserReq.getPassword());
        user.setIdentity(createUserReq.getIdentity());
        return user;
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
    public CheckoutLoginInfo login(String username) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        CheckoutLoginInfo user = mapper.selectLoginByLoginName(username);
        sqlSession.close();
        return user;
    }

    /**
     * 根据登录用户名id查询用户是否存在
     * @param loginName
     * @return
     */
    @Override
    public int selectCountByLoginName(String loginName) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectCountByLoginName(loginName);
    }
}
