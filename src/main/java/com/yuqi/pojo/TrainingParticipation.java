package com.yuqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训参与类，为员工和培训的中间表创建
 * @author yuqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingParticipation {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 培训活动Id
     */
   private Integer trainingId;
    /**
     * 员工Id
     */
   private Integer staffId;
    /**
     * 成绩
     */
   private Double score;

   private String trainingName;
   private String staffName;
   private String startTime;
   private String endTime;
}
