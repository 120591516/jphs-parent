package com.jinpaihushi.jphs.jkwy.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_PACKAGE_PRICE 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyPackagePrice extends BaseModel implements Predicate<JkwyPackagePrice>, Updator<JkwyPackagePrice> {

    /**  */
    @Length(max = 50, message = "{jkwyPackagePrice.jkwyPackageId.illegal.length}")
    private String jkwyPackageId;

    /** 价格标题 */
    @Length(max = 255, message = "{jkwyPackagePrice.title.illegal.length}")
    private String title;

    /** 价原 */
    private Double oldPrice;

    /** 销售价 */
    private Double price;

    /** 服务时长 */
    private Integer serviceTime;

    /** 单位 */
    @Length(max = 50, message = "{jkwyPackagePrice.unit.illegal.length}")
    private String unit;

    /** 支持套餐人数 */
    private Integer supportNumber;

    /** 支持续费（0不支持，1支持） */
    private Integer supportFee;

    /** 支持变更升级套餐（0不支持，1支持） */
    private Integer supportChange;

    private JkwyPackageContent jkwyPackageContent;

    private JkwyPackage jkwyPackage;

    private ActivityPromotion activityPromotion;

    private List<JkwyOrder> jkwyOrderList;

    private List<JkwyPackageContent> jkwyPackageContentList;

    public JkwyPackagePrice() {
    }

    public JkwyPackagePrice(String id) {
        this.id = id;
    }

    public ActivityPromotion getActivityPromotion() {
        return activityPromotion;
    }

    public void setActivityPromotion(ActivityPromotion activityPromotion) {
        this.activityPromotion = activityPromotion;
    }

    public JkwyPackage getJkwyPackage() {
        return jkwyPackage;
    }

    public void setJkwyPackage(JkwyPackage jkwyPackage) {
        this.jkwyPackage = jkwyPackage;
    }

    public List<JkwyOrder> getJkwyOrderList() {
        return jkwyOrderList;
    }

    public void setJkwyOrderList(List<JkwyOrder> jkwyOrderList) {
        this.jkwyOrderList = jkwyOrderList;
    }

    public JkwyPackageContent getJkwyPackageContent() {
        return jkwyPackageContent;
    }

    public void setJkwyPackageContent(JkwyPackageContent jkwyPackageContent) {
        this.jkwyPackageContent = jkwyPackageContent;
    }

    /**
     * 获取
     */
    public String getJkwyPackageId() {
        return jkwyPackageId;
    }

    /**
     * 设置
     */
    public void setJkwyPackageId(String jkwyPackageId) {
        this.jkwyPackageId = jkwyPackageId;
    }

    /**
     * 获取价格标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置价格标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取价原
     */
    public Double getOldPrice() {
        return oldPrice;
    }

    /**
     * 设置价原
     */
    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    /**
     * 获取销售价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置销售价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取服务时长
     */
    public Integer getServiceTime() {
        return serviceTime;
    }

    /**
     * 设置服务时长
     */
    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取支持套餐人数
     */
    public Integer getSupportNumber() {
        return supportNumber;
    }

    /**
     * 设置支持套餐人数
     */
    public void setSupportNumber(Integer supportNumber) {
        this.supportNumber = supportNumber;
    }

    /**
     * 获取支持续费（0不支持，1支持）
     */
    public Integer getSupportFee() {
        return supportFee;
    }

    /**
     * 设置支持续费（0不支持，1支持）
     */
    public void setSupportFee(Integer supportFee) {
        this.supportFee = supportFee;
    }

    /**
     * 获取支持变更升级套餐（0不支持，1支持）
     */
    public Integer getSupportChange() {
        return supportChange;
    }

    /**
     * 设置支持变更升级套餐（0不支持，1支持）
     */
    public void setSupportChange(Integer supportChange) {
        this.supportChange = supportChange;
    }

    public List<JkwyPackageContent> getJkwyPackageContentList() {
        return jkwyPackageContentList;
    }

    public void setJkwyPackageContentList(List<JkwyPackageContent> jkwyPackageContentList) {
        this.jkwyPackageContentList = jkwyPackageContentList;
    }

    public String toString() {
        return new StringBuilder().append("JkwyPackagePrice{").append("id=").append(id).append(",jkwyPackageId=")
                .append(jkwyPackageId).append(",title=").append(title).append(",oldPrice=").append(oldPrice)
                .append(",price=").append(price).append(",serviceTime=").append(serviceTime).append(",unit=")
                .append(unit).append(",supportNumber=").append(supportNumber).append(",supportFee=").append(supportFee)
                .append(",supportChange=").append(supportChange).append(",status=").append(status)
                .append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段：
     * id, jkwyPackageId, title, oldPrice, 
     * price, serviceTime, unit, supportNumber, 
     * supportFee, supportChange, status, createTime, 
     * creatorId, creatorName
     */
    public JkwyPackagePrice copy() {
        JkwyPackagePrice jkwyPackagePrice = new JkwyPackagePrice();
        jkwyPackagePrice.id = this.id;
        jkwyPackagePrice.jkwyPackageId = this.jkwyPackageId;
        jkwyPackagePrice.title = this.title;
        jkwyPackagePrice.oldPrice = this.oldPrice;
        jkwyPackagePrice.price = this.price;
        jkwyPackagePrice.serviceTime = this.serviceTime;
        jkwyPackagePrice.unit = this.unit;
        jkwyPackagePrice.supportNumber = this.supportNumber;
        jkwyPackagePrice.supportFee = this.supportFee;
        jkwyPackagePrice.supportChange = this.supportChange;
        jkwyPackagePrice.status = this.status;
        jkwyPackagePrice.createTime = this.createTime;
        jkwyPackagePrice.creatorId = this.creatorId;
        jkwyPackagePrice.creatorName = this.creatorName;
        return jkwyPackagePrice;
    }

    /**
     * 比较字段：
     * id, jkwyPackageId, title, oldPrice, 
     * price, serviceTime, unit, supportNumber, 
     * supportFee, supportChange, status, createTime, 
     * creatorId, creatorName
     */
    @Override
    public boolean test(JkwyPackagePrice t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.jkwyPackageId == null || this.jkwyPackageId.equals(t.jkwyPackageId))
                && (this.title == null || this.title.equals(t.title))
                && (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
                && (this.price == null || this.price.equals(t.price))
                && (this.serviceTime == null || this.serviceTime.equals(t.serviceTime))
                && (this.unit == null || this.unit.equals(t.unit))
                && (this.supportNumber == null || this.supportNumber.equals(t.supportNumber))
                && (this.supportFee == null || this.supportFee.equals(t.supportFee))
                && (this.supportChange == null || this.supportChange.equals(t.supportChange))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(JkwyPackagePrice element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.jkwyPackageId != null && !this.jkwyPackageId.isEmpty()) {
            element.jkwyPackageId = this.jkwyPackageId;
        }
        if (this.title != null && !this.title.isEmpty()) {
            element.title = this.title;
        }
        if (this.oldPrice != null) {
            element.oldPrice = this.oldPrice;
        }
        if (this.price != null) {
            element.price = this.price;
        }
        if (this.serviceTime != null) {
            element.serviceTime = this.serviceTime;
        }
        if (this.unit != null && !this.unit.isEmpty()) {
            element.unit = this.unit;
        }
        if (this.supportNumber != null) {
            element.supportNumber = this.supportNumber;
        }
        if (this.supportFee != null) {
            element.supportFee = this.supportFee;
        }
        if (this.supportChange != null) {
            element.supportChange = this.supportChange;
        }
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
        if (this.creatorId != null && !this.creatorId.isEmpty()) {
            element.creatorId = this.creatorId;
        }
        if (this.creatorName != null && !this.creatorName.isEmpty()) {
            element.creatorName = this.creatorName;
        }
    }
}
