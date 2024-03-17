package com.yuqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门类
 * @author yuqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    /**
     * 部门id
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门描述
     */
    private String description;
    /**
     * 主管id
     */
    private Integer managerId;

    private String managerName;
}
