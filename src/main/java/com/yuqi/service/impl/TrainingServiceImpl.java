package com.yuqi.service.impl;

import com.yuqi.mapper.SalaryMapper;
import com.yuqi.mapper.TrainingMapper;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Salary;
import com.yuqi.pojo.Training;
import com.yuqi.service.SalaryService;
import com.yuqi.service.TrainingService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 培训Training业务实现类
 * @author yuqi
 */
public class TrainingServiceImpl implements TrainingService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(Training training) {
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        mapper.add(training);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateTraining(Training training) {
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        mapper.update(training);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Training training) {
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        mapper.deleteById(training.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Training> selectByPageAndCondition(int currentPage, int pageSize, Training training) {
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        String name = training.getName();
        if (name != null && !name.isEmpty()){
            training.setName("%" + name + "%");
        }

        List<Training> rows = mapper.selectByPageAndCondition(begin, pageSize,training);
        int totalCount = mapper.selectTotalCountByCondition(training);

        PageBean<Training> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public int getTrainingIdByName(String name){
        SqlSession sqlSession = factory.openSession();
        TrainingMapper mapper = sqlSession.getMapper(TrainingMapper.class);
        int id = mapper.getTrainingIdByName(name);
        sqlSession.close();
        return id;
    };
}
