package com.business.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2021-03-10 20:32:06
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 414147565653447071L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
}