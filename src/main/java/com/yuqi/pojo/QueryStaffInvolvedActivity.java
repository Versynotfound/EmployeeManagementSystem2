package com.yuqi.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author yuqi
 */
@Data
public class QueryStaffInvolvedActivity {

    /**
     * 活动ID列表
     */
    private List<Integer> acitiveIdList;


    /**
     * 员工ID
     */
    private Integer staffId;
}
