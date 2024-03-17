package com.yuqi.mapper;

import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * AnnouncementMapper接口，提供操作公告announcement相关数据的方法集合
 * @author yuqi
 */
public interface AnnouncementMapper {
    /**
     * 添加
     * @param announcement 公告
     */
    @Insert("insert into tb_announcement values( null,#{announcement.title},#{announcement.content},#{announcement.createdTime},#{announcement.lastUpdateTime},#{announcement.userId})")
    void add(@Param("announcement") Announcement announcement);

    /**
     * 修改
     * @param announcement 公告
     */
    @Update("update tb_announcement set title = #{title},content=#{content},created_time = #{createdTime},last_update_time = #{lastUpdateTime},user_id = #{userId} where id = #{id}")
    void update(Announcement announcement);

    /**
     * 批量删除
     * @param ids id数组
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 根据id删除
     * @param id id
     */
    @Delete("delete from tb_announcement where id = #{id}")
    void deleteById(int id);

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param announcement 封装了条件的Announcement对象
     * @return 公告集合
     */
    List<Announcement> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("announcement") Announcement announcement);

    /**
     * 查询带条件下总记录数
     * @param announcement 封装了条件的Announcement对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Announcement announcement);
}
