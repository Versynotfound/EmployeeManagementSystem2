package com.yuqi.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页查询的JavaBean
 * @author yuqi
 */
@Getter
@Setter
@ToString
public class PageBean<T> {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 当前页数据
     */
    private List<T> rows;
}
