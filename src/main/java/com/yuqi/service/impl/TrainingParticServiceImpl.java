package com.yuqi.service.impl;

import com.yuqi.constant.BaseConstant;
import com.yuqi.mapper.TrainingParticMapper;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.QueryStaffInvolvedActivity;
import com.yuqi.pojo.TrainingParticipation;
import com.yuqi.pojo.activity.ActivityScoreReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedReq;
import com.yuqi.pojo.activity.QueryActivityInvolvedResp;
import com.yuqi.service.TrainingParticService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
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
    }

    /**
     * 获取当前登录用户已参与的活动数据
     * @param queryStaffInvolvedActivity
     * @return
     */
    @Override
    public List<TrainingParticipation> queryStaffInvolvedActivity(QueryStaffInvolvedActivity queryStaffInvolvedActivity) {
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);
        List<TrainingParticipation> result = mapper.queryStaffInvolvedActivity(queryStaffInvolvedActivity);
        sqlSession.close();
        return result;
    }

    @Override
    public int selectCountById(Integer staffId,Integer activeId) {
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);
        int result = mapper.selectCountById(staffId,activeId);
        sqlSession.close();
        return result;
    }

    /**
     * 分页查询参与记录
     * @param queryActivityInvolvedReq
     * @return
     */
    @Override
    public PageBean<QueryActivityInvolvedResp> pageInvolvedActivityList(QueryActivityInvolvedReq queryActivityInvolvedReq) {
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);

        PageBean<QueryActivityInvolvedResp> pageBean = new PageBean<>();
        pageBean.setCurrentPage(queryActivityInvolvedReq.getCurrentPage());
        pageBean.setPageSize(queryActivityInvolvedReq.getPageSize());

        Integer totalCount = mapper.pageInvolvedActivityListCount(queryActivityInvolvedReq);
        pageBean.setTotalCount(totalCount);

        if(BaseConstant.ZERO.equals(totalCount)){
            List<QueryActivityInvolvedResp> result = new ArrayList<>();
            pageBean.setRows(result);
            sqlSession.close();
            return pageBean;
        }

        List<QueryActivityInvolvedResp> result = mapper.pageInvolvedActivityList(queryActivityInvolvedReq);

        pageBean.setRows(result);
        sqlSession.close();
        return pageBean;
    }

    /**
     * 参与活动得分
     * @param activityScoreReq
     */
    @Override
    public void updateScoreById(ActivityScoreReq activityScoreReq) {
        SqlSession sqlSession = factory.openSession();
        TrainingParticMapper mapper = sqlSession.getMapper(TrainingParticMapper.class);
        mapper.updateScoreById(activityScoreReq);
        sqlSession.commit();
        sqlSession.close();
    }

}
