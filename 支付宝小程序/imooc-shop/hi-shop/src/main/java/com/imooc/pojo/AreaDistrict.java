package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "area_district")
public class AreaDistrict {
    @Id
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name")
    private String districtName;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 简拼
     */
    @Column(name = "easy_spell")
    private String easySpell;

    @Column(name = "city_id")
    private Integer cityId;

    /**
     * @return district_id
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * @param districtId
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * @return district_name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
}