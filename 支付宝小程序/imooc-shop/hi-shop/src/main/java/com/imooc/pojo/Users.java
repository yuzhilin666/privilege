package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Users {
    @Id
    private String id;

    /**
     * 阿里的用户id，获取后注册保存
     */
    @Column(name = "alipay_user_id")
    private String alipayUserId;

    private String username;

    private String password;

    private String nickname;

    @Column(name = "face_image")
    private String faceImage;

    /**
     * 是否实名认证
1：已实名
0：未实名
     */
    @Column(name = "is_certified")
    private Integer isCertified;

    @Column(name = "regist_time")
    private Date registTime;

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
     * 获取阿里的用户id，获取后注册保存
     *
     * @return alipay_user_id - 阿里的用户id，获取后注册保存
     */
    public String getAlipayUserId() {
        return alipayUserId;
    }

    /**
     * 设置阿里的用户id，获取后注册保存
     *
     * @param alipayUserId 阿里的用户id，获取后注册保存
     */
    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return face_image
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * @param faceImage
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取是否实名认证
1：已实名
0：未实名
     *
     * @return is_certified - 是否实名认证
1：已实名
0：未实名
     */
    public Integer getIsCertified() {
        return isCertified;
    }

    /**
     * 设置是否实名认证
1：已实名
0：未实名
     *
     * @param isCertified 是否实名认证
1：已实名
0：未实名
     */
    public void setIsCertified(Integer isCertified) {
        this.isCertified = isCertified;
    }

    /**
     * @return regist_time
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * @param registTime
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}