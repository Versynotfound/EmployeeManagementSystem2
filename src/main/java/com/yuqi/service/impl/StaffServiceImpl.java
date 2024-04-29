package com.yuqi.service.impl;

import com.yuqi.mapper.StaffMapper;
import com.yuqi.mapper.UserMapper;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Staff;
import com.yuqi.service.StaffService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 员工Staff业务实现类
 * @author yuqi
 */
public class StaffServiceImpl implements StaffService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(Staff staff) {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        mapper.add(staff);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateStaff(Staff staff) {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        mapper.update(staff);
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
    public void deleteById(Staff staff) {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        mapper.deleteById(staff.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Staff> selectByPageAndCondition(int currentPage, int pageSize, Staff staff) {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        String name = staff.getName();
        if (name != null && !name.isEmpty()){
            staff.setName("%" + name + "%");
        }
        Integer gender = staff.getGender();
        if (gender != null){
            staff.setGender(gender);
        }
        Integer level = staff.getLevel();
        if (level != null){
            staff.setLevel(level);
        }
        Integer status = staff.getStatus();
        if (status != null){
            staff.setStatus(status);
        }
        String username = staff.getUsername();
        if (username != null && !username.isEmpty()){
            staff.setUsername("%" + username + "%");
        }
        Integer departmentId = staff.getDepartmentId();
        if (departmentId != null){
            staff.setDepartmentId(departmentId);
        }

        List<Staff> rows = mapper.selectByPageAndCondition(begin, pageSize,staff);
        int totalCount = mapper.selectTotalCountByCondition(staff);

        PageBean<Staff> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public List<Staff> selectHeaders() {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        List<Staff> rows = mapper.selectHeaders();
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }

    @Override
    public List<Staff> selectStaffs() {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        List<Staff> rows = mapper.selectStaffs();
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }

    @Override
    public Staff selectByUserId(int id){
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        Staff rows = mapper.selectByUserId(id);
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }

    /**
     * 根据用户ID修改员工信息
     * @param staff
     */
    @Override
    public void updateByUserId(Staff staff) {
        SqlSession sqlSession = factory.openSession();
        StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
        mapper.updateByUserId(staff);
        sqlSession.commit();
        sqlSession.close();
    }

}
