package com.imooc.pojo.vo;

public class OrdersSubitemsVO {
    private String id;

    private String orderId;

    private String itemId;

    private String itemName;

    private String itemCatName;

    private String itemImage;

    private Integer price;
    
    private String priceY;

    private Integer buyCounts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPriceY() {
		return priceY;
	}

	public void setPriceY(String priceY) {
		this.priceY = priceY;
	}

	public Integer getBuyCounts() {
		return buyCounts;
	}

	public void setBuyCounts(Integer buyCounts) {
		this.buyCounts = buyCounts;
	}

}