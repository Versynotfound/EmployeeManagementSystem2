package com.yuqi.common;

import lombok.Data;

/**
 * 基础分页
 * @author yuqi
 */
@Data
public class BasePage {
    private Integer currentPage = 1;

    private Integer pageSize = 5;
}
