package com.yuqi.pojo;

import com.yuqi.common.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询的JavaBean
 * @author yuqi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageBean<T> extends BasePage {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 当前页数据
     */
    private List<T> rows;
}
