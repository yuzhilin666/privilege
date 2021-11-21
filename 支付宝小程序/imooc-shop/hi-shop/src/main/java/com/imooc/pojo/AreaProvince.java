package com.imooc.pojo;

import javax.persistence.*;

@Table(name = "area_province")
public class AreaProvince {
    @Id
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name")
    private String provinceName;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 简拼
     */
    @Column(name = "easy_spell")
    private String easySpell;

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

    /**
     * @return province_name
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
}