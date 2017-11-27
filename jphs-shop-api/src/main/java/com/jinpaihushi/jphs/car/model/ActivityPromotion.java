package com.jinpaihushi.jphs.car.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

/**
 * ACTIVITY_PROMOTION 
 * 继承自父类的字段:
 * id : 	
 * type : 活动类型（1=立减，2=首单立减，3=第二件立减，）	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-10-30 16:29:17
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ActivityPromotion extends BaseModel {

    /** 资源id */
    @Length(max = 50, message = "{activityPromotion.resourceId.illegal.length}")
    private String resourceId;

    /**  */
    @Length(max = 65535, message = "{activityPromotion.priceId.illegal.length}")
    private String priceId;

    /** 资源类型（1=服务，2=商品） */
    private Integer resourceType;

    /** 优惠价格 */
    private Double price;

    /** 活动开始时间 */
    private Date beginTime;

    /** 活动束结时间 */
    private Date endTime;

    private String goodsTitle;

    private String commodityTitle;

    private String siteName;

    private String platformName;

    private String image;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    /** 站点id */
    @Length(max = 50, message = "{activityPromotion.siteId.illegal.length}")
    private String siteId;

    /** 平台id */
    @Length(max = 50, message = "{activityPromotion.platformId.illegal.length}")
    private String platformId;

    public ActivityPromotion() {
    }

    public ActivityPromotion(String id) {
        this.id = id;
    }

    /**
     * 获取资源id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取
     */
    public String getPriceId() {
        return priceId;
    }

    /**
     * 设置
     */
    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    /**
     * 获取资源类型（1=服务，2=商品）
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型（1=服务，2=商品）
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取优惠价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置优惠价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取活动开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置活动开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取活动束结时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置活动束结时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取站点id
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * 设置站点id
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取平台id
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 设置平台id
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return new StringBuilder().append("ActivityPromotion{").append("id=").append(id).append(",resourceId=")
                .append(resourceId).append(",priceId=").append(priceId).append(",resourceType=").append(resourceType)
                .append(",type=").append(type).append(",price=").append(price).append(",beginTime=").append(beginTime)
                .append(",endTime=").append(endTime).append(",siteId=").append(siteId).append(",platformId=")
                .append(platformId).append(",status=").append(status).append(",createTime=").append(createTime)
                .append(",creatorId=").append(creatorId).append(",creatorName=").append(creatorName).append('}')
                .toString();
    }

    /**
     * 复制字段：
     * id, resourceId, priceId, resourceType, 
     * type, price, beginTime, endTime, 
     * siteId, platformId, status, createTime, 
     * creatorId, creatorName
     */
    public ActivityPromotion copy() {
        ActivityPromotion activityPromotion = new ActivityPromotion();
        activityPromotion.id = this.id;
        activityPromotion.resourceId = this.resourceId;
        activityPromotion.priceId = this.priceId;
        activityPromotion.resourceType = this.resourceType;
        activityPromotion.type = this.type;
        activityPromotion.price = this.price;
        activityPromotion.beginTime = this.beginTime;
        activityPromotion.endTime = this.endTime;
        activityPromotion.siteId = this.siteId;
        activityPromotion.platformId = this.platformId;
        activityPromotion.status = this.status;
        activityPromotion.createTime = this.createTime;
        activityPromotion.creatorId = this.creatorId;
        activityPromotion.creatorName = this.creatorName;
        return activityPromotion;
    }

}
