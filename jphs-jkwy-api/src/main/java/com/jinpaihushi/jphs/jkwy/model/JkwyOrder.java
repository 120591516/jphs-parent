package com.jinpaihushi.jphs.jkwy.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_ORDER 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:26
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyOrder extends BaseModel implements Predicate<JkwyOrder>, Updator<JkwyOrder> {

    /** 单号 */
    @Length(max = 50, message = "{jkwyOrder.no.illegal.length}")
    private String no;

    /**  */
    @Length(max = 50, message = "{jkwyOrder.jkwyPackageId.illegal.length}")
    private String jkwyPackageId;

    /**  */
    @Length(max = 50, message = "{jkwyOrder.jkwyPackagePriceId.illegal.length}")
    private String jkwyPackagePriceId;

    /**  */
    @Length(max = 50, message = "{jkwyOrder.voucherUserId.illegal.length}")
    private String voucherUserId;

    /** 优惠金额 */
    private Double voucherPrice;

    /** 活动id */
    @Length(max = 50, message = "{jkwyOrder.activityPromotionId.illegal.length}")
    private String activityPromotionId;

    /** 活动优惠价格 */
    private Double activityPromotionPrice;

    /** 推广码 */
    @Length(max = 255, message = "{jkwyOrder.code.illegal.length}")
    private String code;

    /** 分享人id 推荐人  */
    @Length(max = 50, message = "{order.recommendId.illegal.length}")
    private String recommendId;

    /** 原价 */
    private Double oldPrice;

    /** 销售价 */
    private Double price;

    /** 支付价 */
    private Double payPrice;

    /** 支付时间 */
    private Date payTime;

    private String platformId;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /** 套餐结束日期 */
    private Integer schedule;

    /** 套餐结束日期 */
    private Date endTime;

    /** 上门地址 */
    @Length(max = 255, message = "{jkwyOrder.address.illegal.length}")
    private String address;

    /** 详细地址 */
    @Length(max = 500, message = "{jkwyOrder.detailAddress.illegal.length}")
    private String detailAddress;

    private JkwyPackagePrice jkwyPackagePrice;

    private JkwyPackage jkwyPackage;

    private List<JkwyRelation> jkwyRelationList;

    public List<JkwyRelation> getJkwyRelationList() {
        return jkwyRelationList;
    }

    public void setJkwyRelationList(List<JkwyRelation> jkwyRelationList) {
        this.jkwyRelationList = jkwyRelationList;
    }

    private JkwyOrderContent jkwyOrderContent;

    public JkwyOrder() {
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public JkwyOrder(String id) {
        this.id = id;
    }

    public JkwyPackagePrice getJkwyPackagePrice() {
        return jkwyPackagePrice;
    }

    public void setJkwyPackagePrice(JkwyPackagePrice jkwyPackagePrice) {
        this.jkwyPackagePrice = jkwyPackagePrice;
    }

    public JkwyPackage getJkwyPackage() {
        return jkwyPackage;
    }

    public void setJkwyPackage(JkwyPackage jkwyPackage) {
        this.jkwyPackage = jkwyPackage;
    }

    /**
     * 获取单号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置单号
     */
    public void setNo(String no) {
        this.no = no;
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
     * 获取
     */
    public String getJkwyPackagePriceId() {
        return jkwyPackagePriceId;
    }

    /**
     * 设置
     */
    public void setJkwyPackagePriceId(String jkwyPackagePriceId) {
        this.jkwyPackagePriceId = jkwyPackagePriceId;
    }

    /**
     * 获取
     */
    public String getVoucherUserId() {
        return voucherUserId;
    }

    /**
     * 设置
     */
    public void setVoucherUserId(String voucherUserId) {
        this.voucherUserId = voucherUserId;
    }

    /**
     * 获取优惠金额
     */
    public Double getVoucherPrice() {
        return voucherPrice;
    }

    /**
     * 设置优惠金额
     */
    public void setVoucherPrice(Double voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    /**
     * 获取活动id
     */
    public String getActivityPromotionId() {
        return activityPromotionId;
    }

    /**
     * 设置活动id
     */
    public void setActivityPromotionId(String activityPromotionId) {
        this.activityPromotionId = activityPromotionId;
    }

    /**
     * 获取活动优惠价格
     */
    public Double getActivityPromotionPrice() {
        return activityPromotionPrice;
    }

    /**
     * 设置活动优惠价格
     */
    public void setActivityPromotionPrice(Double activityPromotionPrice) {
        this.activityPromotionPrice = activityPromotionPrice;
    }

    /**
     * 获取推广码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置推广码
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    /**
     * 获取原价
     */
    public Double getOldPrice() {
        return oldPrice;
    }

    /**
     * 设置原价
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
     * 获取支付价
     */
    public Double getPayPrice() {
        return payPrice;
    }

    /**
     * 设置支付价
     */
    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    /**
     * 获取支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取套餐结束日期
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置套餐结束日期
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取上门地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置上门地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 设置详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public JkwyOrderContent getJkwyOrderContent() {
        return jkwyOrderContent;
    }

    public void setJkwyOrderContent(JkwyOrderContent jkwyOrderContent) {
        this.jkwyOrderContent = jkwyOrderContent;
    }

    public String toString() {
        return new StringBuilder().append("JkwyOrder{").append("id=").append(id).append(",no=").append(no)
                .append(",jkwyPackageId=").append(jkwyPackageId).append(",jkwyPackagePriceId=")
                .append(jkwyPackagePriceId).append(",voucherUserId=").append(voucherUserId).append(",voucherPrice=")
                .append(voucherPrice).append(",activityPromotionId=").append(activityPromotionId)
                .append(",activityPromotionPrice=").append(activityPromotionPrice).append(",code=").append(code)
                .append(",oldPrice=").append(oldPrice).append(",price=").append(price).append(",payPrice=")
                .append(payPrice).append(",payTime=").append(payTime).append(",endTime=").append(endTime)
                .append(",address=").append(address).append(",detailAddress=").append(detailAddress).append(",status=")
                .append(status).append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段：
     * id, no, jkwyPackageId, jkwyPackagePriceId, 
     * voucherUserId, voucherPrice, activityPromotionId, activityPromotionPrice, 
     * code, oldPrice, price, payPrice, 
     * payTime, endTime, address, detailAddress, 
     * status, createTime, creatorId, creatorName
     */
    public JkwyOrder copy() {
        JkwyOrder jkwyOrder = new JkwyOrder();
        jkwyOrder.id = this.id;
        jkwyOrder.no = this.no;
        jkwyOrder.jkwyPackageId = this.jkwyPackageId;
        jkwyOrder.jkwyPackagePriceId = this.jkwyPackagePriceId;
        jkwyOrder.voucherUserId = this.voucherUserId;
        jkwyOrder.voucherPrice = this.voucherPrice;
        jkwyOrder.activityPromotionId = this.activityPromotionId;
        jkwyOrder.activityPromotionPrice = this.activityPromotionPrice;
        jkwyOrder.code = this.code;
        jkwyOrder.oldPrice = this.oldPrice;
        jkwyOrder.price = this.price;
        jkwyOrder.payPrice = this.payPrice;
        jkwyOrder.payTime = this.payTime;
        jkwyOrder.endTime = this.endTime;
        jkwyOrder.address = this.address;
        jkwyOrder.detailAddress = this.detailAddress;
        jkwyOrder.status = this.status;
        jkwyOrder.createTime = this.createTime;
        jkwyOrder.creatorId = this.creatorId;
        jkwyOrder.creatorName = this.creatorName;
        return jkwyOrder;
    }

    /**
     * 比较字段：
     * id, no, jkwyPackageId, jkwyPackagePriceId, 
     * voucherUserId, voucherPrice, activityPromotionId, activityPromotionPrice, 
     * code, oldPrice, price, payPrice, 
     * payTime, endTime, address, detailAddress, 
     * status, createTime, creatorId, creatorName
     */
    @Override
    public boolean test(JkwyOrder t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.no == null || this.no.equals(t.no))
                && (this.jkwyPackageId == null || this.jkwyPackageId.equals(t.jkwyPackageId))
                && (this.jkwyPackagePriceId == null || this.jkwyPackagePriceId.equals(t.jkwyPackagePriceId))
                && (this.voucherUserId == null || this.voucherUserId.equals(t.voucherUserId))
                && (this.voucherPrice == null || this.voucherPrice.equals(t.voucherPrice))
                && (this.activityPromotionId == null || this.activityPromotionId.equals(t.activityPromotionId))
                && (this.activityPromotionPrice == null || this.activityPromotionPrice.equals(t.activityPromotionPrice))
                && (this.code == null || this.code.equals(t.code))
                && (this.oldPrice == null || this.oldPrice.equals(t.oldPrice))
                && (this.price == null || this.price.equals(t.price))
                && (this.payPrice == null || this.payPrice.equals(t.payPrice))
                && (this.payTime == null || this.payTime.equals(t.payTime))
                && (this.endTime == null || this.endTime.equals(t.endTime))
                && (this.address == null || this.address.equals(t.address))
                && (this.detailAddress == null || this.detailAddress.equals(t.detailAddress))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(JkwyOrder element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.no != null && !this.no.isEmpty()) {
            element.no = this.no;
        }
        if (this.jkwyPackageId != null && !this.jkwyPackageId.isEmpty()) {
            element.jkwyPackageId = this.jkwyPackageId;
        }
        if (this.jkwyPackagePriceId != null && !this.jkwyPackagePriceId.isEmpty()) {
            element.jkwyPackagePriceId = this.jkwyPackagePriceId;
        }
        if (this.voucherUserId != null && !this.voucherUserId.isEmpty()) {
            element.voucherUserId = this.voucherUserId;
        }
        if (this.voucherPrice != null) {
            element.voucherPrice = this.voucherPrice;
        }
        if (this.activityPromotionId != null && !this.activityPromotionId.isEmpty()) {
            element.activityPromotionId = this.activityPromotionId;
        }
        if (this.activityPromotionPrice != null) {
            element.activityPromotionPrice = this.activityPromotionPrice;
        }
        if (this.code != null && !this.code.isEmpty()) {
            element.code = this.code;
        }
        if (this.oldPrice != null) {
            element.oldPrice = this.oldPrice;
        }
        if (this.price != null) {
            element.price = this.price;
        }
        if (this.payPrice != null) {
            element.payPrice = this.payPrice;
        }
        if (this.payTime != null) {
            element.payTime = this.payTime;
        }
        if (this.endTime != null) {
            element.endTime = this.endTime;
        }
        if (this.address != null && !this.address.isEmpty()) {
            element.address = this.address;
        }
        if (this.detailAddress != null && !this.detailAddress.isEmpty()) {
            element.detailAddress = this.detailAddress;
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
