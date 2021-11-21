package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "orders_subitems")
public class OrdersSubitems {
    @Id
    private String id;

    /**
     * 归属哪个订单
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品分类的名称
     */
    @Column(name = "item_cat_name")
    private String itemCatName;

    /**
     * 从商品header_images 中获取第一张图
     */
    @Column(name = "item_image")
    private String itemImage;

    /**
     * 购买时候的商品金额
     */
    private Integer price;
    
    /**
     * 购买件数
     */
    @Column(name = "buy_counts")
    private Integer buyCounts;

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
     * 获取归属哪个订单
     *
     * @return order_id - 归属哪个订单
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置归属哪个订单
     *
     * @param orderId 归属哪个订单
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * 获取商品名称
     *
     * @return item_name - 商品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名称
     *
     * @param itemName 商品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取商品分类的名称
     *
     * @return item_cat_name - 商品分类的名称
     */
    public String getItemCatName() {
        return itemCatName;
    }

    /**
     * 设置商品分类的名称
     *
     * @param itemCatName 商品分类的名称
     */
    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }

    /**
     * 获取从商品header_images 中获取第一张图
     *
     * @return item_image - 从商品header_images 中获取第一张图
     */
    public String getItemImage() {
        return itemImage;
    }

    /**
     * 设置从商品header_images 中获取第一张图
     *
     * @param itemImage 从商品header_images 中获取第一张图
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    /**
     * 获取购买时候的商品金额
     *
     * @return price - 购买时候的商品金额
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置购买时候的商品金额
     *
     * @param price 购买时候的商品金额
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取购买件数
     *
     * @return buy_counts - 购买件数
     */
    public Integer getBuyCounts() {
        return buyCounts;
    }

    /**
     * 设置购买件数
     *
     * @param buyCounts 购买件数
     */
    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

}