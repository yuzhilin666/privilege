package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "area_city")
public class AreaCity {
    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 简拼
     */
    @Column(name = "easy_spell")
    private String easySpell;

    private String zipcode;

    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取拼音
     *
     * @return spell - 拼音
     */
    public String getSpell() {
        return spell;
    }

    /**
     * 设置拼音
     *
     * @param spell 拼音
     */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * 获取简拼
     *
     * @return easy_spell - 简拼
     */
    public String getEasySpell() {
        return easySpell;
    }

    /**
     * 设置简拼
     *
     * @param easySpell 简拼
     */
    public void setEasySpell(String easySpell) {
        this.easySpell = easySpell;
    }

    /**
     * @return zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return province_id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}