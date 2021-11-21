package com.imooc.pojo.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

public class ItemsVO {
	private String id;
	private Integer catId;
	private String name;
	private String headerImages;
	private String belongAddress;
	private Integer priceDiscount;
	private Integer priceNormal;
	private Integer isDiscount;
	private String discounts;
	private Integer likeCounts;
	private Integer sellCounts;
	private Boolean is24hDeliver;
	private String serviceDesc;
	private String content;
	private String tags;
	private Boolean isNew;
	private Boolean isRecommend;
	private Date createTime;
	private Date updateTime;

	private String cover;
	private List<String> coverList;
	private List<String> tagList;
	private String priceDiscountYuan;
	private String priceNormalYuan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeaderImages() {
		return headerImages;
	}

	public void setHeaderImages(String headerImages) {
		this.headerImages = headerImages;
	}

	public String getBelongAddress() {
		return belongAddress;
	}

	public void setBelongAddress(String belongAddress) {
		this.belongAddress = belongAddress;
	}

	public Integer getPriceDiscount() {
		return priceDiscount;
	}

	public void setPriceDiscount(Integer priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public Integer getPriceNormal() {
		return priceNormal;
	}

	public void setPriceNormal(Integer priceNormal) {
		this.priceNormal = priceNormal;
	}

	public Integer getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

	public Integer getLikeCounts() {
		return likeCounts;
	}

	public void setLikeCounts(Integer likeCounts) {
		this.likeCounts = likeCounts;
	}

	public Integer getSellCounts() {
		return sellCounts;
	}

	public void setSellCounts(Integer sellCounts) {
		this.sellCounts = sellCounts;
	}

	public Boolean getIs24hDeliver() {
		return is24hDeliver;
	}

	public void setIs24hDeliver(Boolean is24hDeliver) {
		this.is24hDeliver = is24hDeliver;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getCoverList() {
		return coverList;
	}

	public void setCoverList(List<String> coverList) {
		this.coverList = coverList;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public String getPriceDiscountYuan() {
		return priceDiscountYuan;
	}

	public void setPriceDiscountYuan(String priceDiscountYuan) {
		this.priceDiscountYuan = priceDiscountYuan;
	}

	public String getPriceNormalYuan() {
		return priceNormalYuan;
	}

	public void setPriceNormalYuan(String priceNormalYuan) {
		this.priceNormalYuan = priceNormalYuan;
	}

}