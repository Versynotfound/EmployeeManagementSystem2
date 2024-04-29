package com.yuqi.pojo.activity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yuqi
 */
@Data
public class ActivityScoreReq implements Serializable {
    /**
     * 参与记录
     */
    private Integer id;

    /**
     * 得分
     */
    private BigDecimal score;
}
