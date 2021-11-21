package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "user_like_item")
public class UserLikeItem {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "item_id")
    private String itemId;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return item_id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}