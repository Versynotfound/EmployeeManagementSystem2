package com.yuqi.service.impl;

import com.yuqi.mapper.TrainingMapper;
import com.yuqi.mapper.TrainingParticMapper;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Training;
import com.yuqi.pojo.TrainingParticipation;
import com.yuqi.service.TrainingParticService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author yuqi
 */
public class TrainingParticServiceImpl implements TrainingParticService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(TrainingParticipation trainingPartic){
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);
        mapper.add(trainingPartic);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
    public PageBean<TrainingParticipation> selectByStaffId(int currentPage, int pageSize, int id){
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        List<TrainingParticipation> rows = mapper.selectByStaffId(begin, pageSize,id);
        int totalCount = mapper.selectTotalCountByStaffId(id);

        PageBean<TrainingParticipation> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    };
}
