package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Carousel {
    @Id
    private String id;

    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品分类id
     */
    @Column(name = "cat_id")
    private String catId;

    /**
     * 轮播图类型，用于判断，会讲一下 a:if 的使用
1：商品，点击后根据 item_id 显示某个具体商品
2：商品分类，点击后显示该分类下的所有商品列表

     */
    private Integer type;

    /**
     * 轮播图顺序
按需求提供两种接口，一种以sort来查询顺序，一种以update_time来查询
     */
    private Integer sort;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return image_url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品分类id
     *
     * @return cat_id - 商品分类id
     */
    public String getCatId() {
        return catId;
    }

    /**
     * 设置商品分类id
     *
     * @param catId 商品分类id
     */
    public void setCatId(String catId) {
        this.catId = catId;
    }

    /**
     * 获取轮播图类型，用于判断，会讲一下 a:if 的使用
1：商品，点击后根据 item_id 显示某个具体商品
2：商品分类，点击后显示该分类下的所有商品列表

     *
     * @return type - 轮播图类型，用于判断，会讲一下 a:if 的使用
1：商品，点击后根据 item_id 显示某个具体商品
2：商品分类，点击后显示该分类下的所有商品列表

     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置轮播图类型，用于判断，会讲一下 a:if 的使用
1：商品，点击后根据 item_id 显示某个具体商品
2：商品分类，点击后显示该分类下的所有商品列表

     *
     * @param type 轮播图类型，用于判断，会讲一下 a:if 的使用
1：商品，点击后根据 item_id 显示某个具体商品
2：商品分类，点击后显示该分类下的所有商品列表

     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取轮播图顺序
按需求提供两种接口，一种以sort来查询顺序，一种以update_time来查询
     *
     * @return sort - 轮播图顺序
按需求提供两种接口，一种以sort来查询顺序，一种以update_time来查询
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置轮播图顺序
按需求提供两种接口，一种以sort来查询顺序，一种以update_time来查询
     *
     * @param sort 轮播图顺序
按需求提供两种接口，一种以sort来查询顺序，一种以update_time来查询
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}