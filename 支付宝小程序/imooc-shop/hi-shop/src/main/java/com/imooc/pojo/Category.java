package com.imooc.pojo;

import javax.persistence.*;

public class Category {
    @Id
    private Integer id;

    /**
     * 商品所属分类的名称
     */
    private String name;

    /**
     * 分类名称的英文编码
     */
    private String code;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品所属分类的名称
     *
     * @return name - 商品所属分类的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品所属分类的名称
     *
     * @param name 商品所属分类的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类名称的英文编码
     *
     * @return code - 分类名称的英文编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置分类名称的英文编码
     *
     * @param code 分类名称的英文编码
     */
    public void setCode(String code) {
        this.code = code;
    }
}