package com.yuqi.service.impl;

import com.yuqi.mapper.ApplicationMapper;
import com.yuqi.mapper.ApplicationMapper;
import com.yuqi.pojo.Application;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Application;
import com.yuqi.service.ApplicationService;
import com.yuqi.service.ApplicationService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 申请记录Application业务实现类
 * @author yuqi
 */
public class ApplicationServiceImpl implements ApplicationService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public void add(Application application) {
        SqlSession sqlSession = factory.openSession();
        ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
        mapper.add(application);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateApplication(Application application) {
        SqlSession sqlSession = factory.openSession();
        ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
        mapper.update(application);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Application> selectByPageAndCondition(int currentPage, int pageSize, Application application) {
        SqlSession sqlSession = factory.openSession();
        ApplicationMapper mapper = sqlSession.getMapper(ApplicationMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        Integer staffId = application.getStaffId();
        if (staffId != null){
            application.setStaffId(staffId);
        }
        Integer type = application.getType();
        if (type != null){
            application.setType(type);
        }
        Integer status = application.getStatus();
        if (status != null){
            application.setStatus(status);
        }

        List<Application> rows = mapper.selectByPageAndCondition(begin, pageSize,application);
        int totalCount = mapper.selectTotalCountByCondition(application);

        PageBean<Application> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }
}
