package com.yuqi.pojo;

import lombok.Data;

/**
 * 部门类
 * @author yuqi
 */
@Data
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
