package com.yuqi.service.impl;

import com.yuqi.mapper.AnnouncementMapper;
import com.yuqi.mapper.AttendanceMapper;
import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.Attendance;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.AttendanceService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 考勤记录Attendance业务实现类
 * @author yuqi
 */
public class AttendanceServiceImpl implements AttendanceService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(Attendance attendance) {
        SqlSession sqlSession = factory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        mapper.add(attendance);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        SqlSession sqlSession = factory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        mapper.update(attendance);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Attendance attendance) {
        SqlSession sqlSession = factory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        mapper.deleteById(attendance.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Attendance> selectByPageAndCondition(int currentPage, int pageSize, Attendance attendance) {
        SqlSession sqlSession = factory.openSession();
        AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        // 全是选择不需要设置模糊查询条件

        List<Attendance> rows = mapper.selectByPageAndCondition(begin, pageSize,attendance);
        int totalCount = mapper.selectTotalCountByCondition(attendance);

        PageBean<Attendance> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }
}
