package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Items {
    @Id
    private String id;

    /**
     * 商品分类id
     */
    @Column(name = "cat_id")
    private Integer catId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品详情里最开始显示的几张商品图，这里构建一个json数组存取
     */
    @Column(name = "header_images")
    private String headerImages;

    /**
     * 商品所在地 简要地址显示 比如"江苏无锡" “上海” “浙江杭州”
     */
    @Column(name = "belong_address")
    private String belongAddress;

    /**
     * 优惠价格
     */
    @Column(name = "price_discount")
    private Integer priceDiscount;

    /**
     * 商品原价
     */
    @Column(name = "price_normal")
    private Integer priceNormal;

    /**
     * 是否有优惠
1：有优惠
0：无优惠
     */
    @Column(name = "is_discount")
    private Integer isDiscount;

    /**
     * 折扣力度：
比如填写  “7折” “88折” "促销"  等等
     */
    private String discounts;

    /**
     * 收藏数
     */
    @Column(name = "like_counts")
    private Integer likeCounts;

    /**
     * 销量
     */
    @Column(name = "sell_counts")
    private Integer sellCounts;

    /**
     * 是否24小时内发货：
1：是
0：否
     */
    @Column(name = "is_24h_deliver")
    private Boolean is24hDeliver;

    /**
     * 服务说明：
延误必陪
退货补运费
全国包邮
7天无理由
json格式如下：
{
"delay_with_pay" : "true",
"return_with_pay" : "true",
"free_shipping" : "true",
"seven_days_return" : "true"
}
     */
    @Column(name = "service_desc")
    private String serviceDesc;

    /**
     * 内容详情，这个里面构建一个json对象，包含有文字和图片数组
对象数组，数组中每个item都是作为每一项
contentType: 内容类型，1代表文字，2代表图片，不同类型可以扩展，根据公司规范自定义，比方说视频或音频也可以
[{"contentType" : "1", "color" : "#ffffff", "fontSize" : "14px", "fontBlod" : "true"},
{"contentType" : "2", "url" : "http://122.152.205.72:88/group1/M00/00/01/CpoxxFq7JtKAWuYEAAC7PPxU7sE344.jpg", "width" : "750rpx"}]
     */
    private String content;

    /**
     * 标签：
连帽卫衣,漂亮,红色,女士
类似这样的用 逗号 隔开的标签
     */
    private String tags;

    /**
     * 是否新品
1：是
0：不是
     */
    @Column(name = "is_new")
    private Boolean isNew;

    /**
     * 是否推荐商品（首页横向展示）
1：推荐
0：不推荐

     */
    @Column(name = "is_recommend")
    private Boolean isRecommend;

    /**
     * 是否可以在模糊查找的时候被搜索到
1：可以
0：不可以
     */
    @Column(name = "can_be_searched")
    private Boolean canBeSearched;

    /**
     * 是否上架：
1：上架
0：下架
     */
    @Column(name = "is_on")
    private Integer isOn;

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
     * 获取商品分类id
     *
     * @return cat_id - 商品分类id
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * 设置商品分类id
     *
     * @param catId 商品分类id
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品详情里最开始显示的几张商品图，这里构建一个json数组存取
     *
     * @return header_images - 商品详情里最开始显示的几张商品图，这里构建一个json数组存取
     */
    public String getHeaderImages() {
        return headerImages;
    }

    /**
     * 设置商品详情里最开始显示的几张商品图，这里构建一个json数组存取
     *
     * @param headerImages 商品详情里最开始显示的几张商品图，这里构建一个json数组存取
     */
    public void setHeaderImages(String headerImages) {
        this.headerImages = headerImages;
    }

    /**
     * 获取商品所在地 简要地址显示 比如"江苏无锡" “上海” “浙江杭州”
     *
     * @return belong_address - 商品所在地 简要地址显示 比如"江苏无锡" “上海” “浙江杭州”
     */
    public String getBelongAddress() {
        return belongAddress;
    }

    /**
     * 设置商品所在地 简要地址显示 比如"江苏无锡" “上海” “浙江杭州”
     *
     * @param belongAddress 商品所在地 简要地址显示 比如"江苏无锡" “上海” “浙江杭州”
     */
    public void setBelongAddress(String belongAddress) {
        this.belongAddress = belongAddress;
    }

    /**
     * 获取优惠价格
     *
     * @return price_discount - 优惠价格
     */
    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    /**
     * 设置优惠价格
     *
     * @param priceDiscount 优惠价格
     */
    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    /**
     * 获取商品原价
     *
     * @return price_normal - 商品原价
     */
    public Integer getPriceNormal() {
        return priceNormal;
    }

    /**
     * 设置商品原价
     *
     * @param priceNormal 商品原价
     */
    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
    }

    /**
     * 获取是否有优惠
1：有优惠
0：无优惠
     *
     * @return is_discount - 是否有优惠
1：有优惠
0：无优惠
     */
    public Integer getIsDiscount() {
        return isDiscount;
    }

    /**
     * 设置是否有优惠
1：有优惠
0：无优惠
     *
     * @param isDiscount 是否有优惠
1：有优惠
0：无优惠
     */
    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    /**
     * 获取折扣力度：
比如填写  “7折” “88折” "促销"  等等
     *
     * @return discounts - 折扣力度：
比如填写  “7折” “88折” "促销"  等等
     */
    public String getDiscounts() {
        return discounts;
    }

    /**
     * 设置折扣力度：
比如填写  “7折” “88折” "促销"  等等
     *
     * @param discounts 折扣力度：
比如填写  “7折” “88折” "促销"  等等
     */
    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    /**
     * 获取收藏数
     *
     * @return like_counts - 收藏数
     */
    public Integer getLikeCounts() {
        return likeCounts;
    }

    /**
     * 设置收藏数
     *
     * @param likeCounts 收藏数
     */
    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * 获取销量
     *
     * @return sell_counts - 销量
     */
    public Integer getSellCounts() {
        return sellCounts;
    }

    /**
     * 设置销量
     *
     * @param sellCounts 销量
     */
    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    /**
     * 获取是否24小时内发货：
1：是
0：否
     *
     * @return is_24h_deliver - 是否24小时内发货：
1：是
0：否
     */
    public Boolean getIs24hDeliver() {
        return is24hDeliver;
    }

    /**
     * 设置是否24小时内发货：
1：是
0：否
     *
     * @param is24hDeliver 是否24小时内发货：
1：是
0：否
     */
    public void setIs24hDeliver(Boolean is24hDeliver) {
        this.is24hDeliver = is24hDeliver;
    }

    /**
     * 获取服务说明：
延误必陪
退货补运费
全国包邮
7天无理由
json格式如下：
{
"delay_with_pay" : "true",
"return_with_pay" : "true",
"free_shipping" : "true",
"seven_days_return" : "true"
}
     *
     * @return service_desc - 服务说明：
延误必陪
退货补运费
全国包邮
7天无理由
json格式如下：
{
"delay_with_pay" : "true",
"return_with_pay" : "true",
"free_shipping" : "true",
"seven_days_return" : "true"
}
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * 设置服务说明：
延误必陪
退货补运费
全国包邮
7天无理由
json格式如下：
{
"delay_with_pay" : "true",
"return_with_pay" : "true",
"free_shipping" : "true",
"seven_days_return" : "true"
}
     *
     * @param serviceDesc 服务说明：
延误必陪
退货补运费
全国包邮
7天无理由
json格式如下：
{
"delay_with_pay" : "true",
"return_with_pay" : "true",
"free_shipping" : "true",
"seven_days_return" : "true"
}
     */
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    /**
     * 获取内容详情，这个里面构建一个json对象，包含有文字和图片数组
对象数组，数组中每个item都是作为每一项
contentType: 内容类型，1代表文字，2代表图片，不同类型可以扩展，根据公司规范自定义，比方说视频或音频也可以
[{"contentType" : "1", "color" : "#ffffff", "fontSize" : "14px", "fontBlod" : "true"},
{"contentType" : "2", "url" : "http://122.152.205.72:88/group1/M00/00/01/CpoxxFq7JtKAWuYEAAC7PPxU7sE344.jpg", "width" : "750rpx"}]
     *
     * @return content - 内容详情，这个里面构建一个json对象，包含有文字和图片数组
对象数组，数组中每个item都是作为每一项
contentType: 内容类型，1代表文字，2代表图片，不同类型可以扩展，根据公司规范自定义，比方说视频或音频也可以
[{"contentType" : "1", "color" : "#ffffff", "fontSize" : "14px", "fontBlod" : "true"},
{"contentType" : "2", "url" : "http://122.152.205.72:88/group1/M00/00/01/CpoxxFq7JtKAWuYEAAC7PPxU7sE344.jpg", "width" : "750rpx"}]
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容详情，这个里面构建一个json对象，包含有文字和图片数组
对象数组，数组中每个item都是作为每一项
contentType: 内容类型，1代表文字，2代表图片，不同类型可以扩展，根据公司规范自定义，比方说视频或音频也可以
[{"contentType" : "1", "color" : "#ffffff", "fontSize" : "14px", "fontBlod" : "true"},
{"contentType" : "2", "url" : "http://122.152.205.72:88/group1/M00/00/01/CpoxxFq7JtKAWuYEAAC7PPxU7sE344.jpg", "width" : "750rpx"}]
     *
     * @param content 内容详情，这个里面构建一个json对象，包含有文字和图片数组
对象数组，数组中每个item都是作为每一项
contentType: 内容类型，1代表文字，2代表图片，不同类型可以扩展，根据公司规范自定义，比方说视频或音频也可以
[{"contentType" : "1", "color" : "#ffffff", "fontSize" : "14px", "fontBlod" : "true"},
{"contentType" : "2", "url" : "http://122.152.205.72:88/group1/M00/00/01/CpoxxFq7JtKAWuYEAAC7PPxU7sE344.jpg", "width" : "750rpx"}]
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取标签：
连帽卫衣,漂亮,红色,女士
类似这样的用 逗号 隔开的标签
     *
     * @return tags - 标签：
连帽卫衣,漂亮,红色,女士
类似这样的用 逗号 隔开的标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签：
连帽卫衣,漂亮,红色,女士
类似这样的用 逗号 隔开的标签
     *
     * @param tags 标签：
连帽卫衣,漂亮,红色,女士
类似这样的用 逗号 隔开的标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取是否新品
1：是
0：不是
     *
     * @return is_new - 是否新品
1：是
0：不是
     */
    public Boolean getIsNew() {
        return isNew;
    }

    /**
     * 设置是否新品
1：是
0：不是
     *
     * @param isNew 是否新品
1：是
0：不是
     */
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取是否推荐商品（首页横向展示）
1：推荐
0：不推荐

     *
     * @return is_recommend - 是否推荐商品（首页横向展示）
1：推荐
0：不推荐

     */
    public Boolean getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置是否推荐商品（首页横向展示）
1：推荐
0：不推荐

     *
     * @param isRecommend 是否推荐商品（首页横向展示）
1：推荐
0：不推荐

     */
    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取是否可以在模糊查找的时候被搜索到
1：可以
0：不可以
     *
     * @return can_be_searched - 是否可以在模糊查找的时候被搜索到
1：可以
0：不可以
     */
    public Boolean getCanBeSearched() {
        return canBeSearched;
    }

    /**
     * 设置是否可以在模糊查找的时候被搜索到
1：可以
0：不可以
     *
     * @param canBeSearched 是否可以在模糊查找的时候被搜索到
1：可以
0：不可以
     */
    public void setCanBeSearched(Boolean canBeSearched) {
        this.canBeSearched = canBeSearched;
    }

    /**
     * 获取是否上架：
1：上架
0：下架
     *
     * @return is_on - 是否上架：
1：上架
0：下架
     */
    public Integer getIsOn() {
        return isOn;
    }

    /**
     * 设置是否上架：
1：上架
0：下架
     *
     * @param isOn 是否上架：
1：上架
0：下架
     */
    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
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