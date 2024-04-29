package com.yuqi.service.impl;

import com.yuqi.mapper.AnnouncementMapper;
import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.PageBean;
import com.yuqi.service.AnnouncementService;
import com.yuqi.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 公告Announcement业务实现类
 * @author yuqi
 */
public class AnnouncementServiceImpl implements AnnouncementService {
    SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public void add(Announcement announcement) {
        SqlSession sqlSession = factory.openSession();
        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        announcement.setCreatedTime(formattedDateTime);
        announcement.setLastUpdateTime(formattedDateTime);

        mapper.add(announcement);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        SqlSession sqlSession = factory.openSession();

        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        announcement.setLastUpdateTime(formattedDateTime);

        mapper.update(announcement);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Announcement announcement) {
        SqlSession sqlSession = factory.openSession();
        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
        mapper.deleteById(announcement.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Announcement> selectByPageAndCondition(int currentPage, int pageSize, Announcement announcement) {
        SqlSession sqlSession = factory.openSession();
        AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
        int begin = ( currentPage - 1 ) * pageSize;

        String title = announcement.getTitle();
        if (title != null && !title.isEmpty()){
            announcement.setTitle("%" + title + "%");
        }

        List<Announcement> rows = mapper.selectByPageAndCondition(begin, pageSize,announcement);
        int totalCount = mapper.selectTotalCountByCondition(announcement);

        PageBean<Announcement> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }
}
