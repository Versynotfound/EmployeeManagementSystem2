package com.yuqi.service.impl;

import com.yuqi.mapper.DepartmentMapper;
import com.yuqi.pojo.Department;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.DepartmentService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 部门Department业务实现类
 * @author yuqi
 */
public class DepartmentServiceImpl implements DepartmentService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
//    @Override
//    public List<Department> selectAll() {
//        SqlSession sqlSession = factory.openSession();
//        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
//        List<Department> departments = mapper.selectAll();
//        sqlSession.close();
//        return departments;
//    }

    @Override
    public void add(Department department) {
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        mapper.add(department);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateDepartment(Department department) {
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        mapper.update(department);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Department department) {
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        mapper.deleteById(department.getId());
        sqlSession.commit();
        sqlSession.close();
    }

//    @Override
//    public PageBean<Department> selectByPage(int currentPage, int pageSize) {
//        SqlSession sqlSession = factory.openSession();
//        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
//        int begin = ( currentPage - 1 ) * pageSize;
//        // int size = pageSize;
//        List<Department> rows = mapper.selectByPage(begin, pageSize);
//        int totalCount = mapper.selectTotalCount();
//        // 封装PageBean对象
//        PageBean<Department> pageBean = new PageBean<>();
//        pageBean.setRows(rows);
//        pageBean.setTotalCount(totalCount);
//        sqlSession.close();
//        return pageBean;
//    }

    @Override
    public PageBean<Department> selectByPageAndCondition(int currentPage, int pageSize, Department department) {
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;
        // 处理department条件，设置模糊表达式
        String name = department.getName();
        if (name != null && !name.isEmpty()){
            department.setName("%" + name + "%");
        }
        // 如果有，其他成员变量也要设置...

        List<Department> rows = mapper.selectByPageAndCondition(begin, pageSize,department);
        int totalCount = mapper.selectTotalCountByCondition(department);

        PageBean<Department> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public List<Department> selectDepartments(){
        SqlSession sqlSession = factory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        List<Department> rows = mapper.selectDepartments();
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }
}
