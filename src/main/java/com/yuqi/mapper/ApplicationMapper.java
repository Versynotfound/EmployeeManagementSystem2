package com.yuqi.mapper;

import com.yuqi.pojo.Announcement;
import com.yuqi.pojo.Application;
import com.yuqi.pojo.Attendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ApplicationMapper接口，提供操作申请Application相关数据的方法集合
 * @author yuqi
 */
public interface ApplicationMapper {
    /**
     * 员工添加
     * @param application 申请信息
     */
    @Insert("insert into tb_application values( null,#{application.type},#{application.startTime},#{application.endTime},#{application.reason},#{application.status},#{application.staffId},#{application.adminId})")
    void add(@Param("application") Application application);

    /**
     * 管理员审批
     * @param application 申请信息
     */
    @Update("update tb_application set type = #{type},start_time = #{startTime},end_time = #{endTime},reason = #{reason},status = #{status},staff_id = #{staffId},admin_id = #{adminId} where id = #{id}")
    void update(Application application);

    /**
     * 根据条件分页查询
     * @param begin 当前页码
     * @param size 每页显示条数
     * @param application 封装了条件的Application对象
     * @return 申请集合
     */
    List<Application> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("application") Application application);

    /**
     * 查询带条件下总记录数
     * @param application 封装了条件的Application对象
     * @return 总记录数
     */
    int selectTotalCountByCondition(Application application);
}
