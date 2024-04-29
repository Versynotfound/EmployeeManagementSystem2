package com.yuqi.service.impl;

import com.yuqi.mapper.SalaryMapper;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.service.SalaryService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 薪资Salary业务实现类
 * @author yuqi
 */
public class SalaryServiceImpl implements SalaryService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(Salary salary) {
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        mapper.add(salary);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateSalary(Salary salary) {
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        mapper.update(salary);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Salary salary) {
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        mapper.deleteById(salary.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Salary> selectByPageAndCondition(int currentPage, int pageSize, Salary salary) {
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        Integer staffId = salary.getStaffId();
        if (staffId != null){
            salary.setStaffId(staffId);
        }
        String month = salary.getMonth();
        if (month != null && !month.isEmpty()){
            salary.setMonth(month);
        }
        Integer departmentId = salary.getDepartmentId();
        if (departmentId != null){
            salary.setDepartmentId(departmentId);
        }

        List<Salary> rows = mapper.selectByPageAndCondition(begin, pageSize,salary);
        int totalCount = mapper.selectTotalCountByCondition(salary);

        PageBean<Salary> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void cancelFullAttendanceBonus(int id,String month){
        SqlSession sqlSession = factory.openSession();
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        mapper.cancelFullAttendanceBonus(id,month);
        sqlSession.commit();
        sqlSession.close();
    }
}
