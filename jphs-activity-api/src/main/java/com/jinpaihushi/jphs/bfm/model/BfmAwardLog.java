package com.jinpaihushi.jphs.bfm.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * BFM_AWARD_LOG 
 * 继承自父类的字段:
 * id : 	
 * creatorId : 	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-10-26 16:45:32
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BfmAwardLog extends BaseModel implements Predicate<BfmAwardLog>,
		Updator<BfmAwardLog> {


    /**  */
	@Length(max = 50, message = "{bfmAwardLog.transactionId.illegal.length}")
	private String transactionId;

    /** 领取金额 */
	private Double price;

    /** 领取的月份 */
	private Integer bmonth;

    /** 完成单量 */
	private Integer orderNumber;

	public BfmAwardLog(){}

	public BfmAwardLog(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getTransactionId() {
    	return transactionId;
    }
  	
	/**
	 * 设置
	 */
	public void setTransactionId(String transactionId) {
    	this.transactionId = transactionId;
    }

	/**
	 * 获取领取金额
	 */
	public Double getPrice() {
    	return price;
    }
  	
	/**
	 * 设置领取金额
	 */
	public void setPrice(Double price) {
    	this.price = price;
    }

	/**
	 * 获取领取的月份
	 */
	public Integer getBmonth() {
    	return bmonth;
    }
  	
	/**
	 * 设置领取的月份
	 */
	public void setBmonth(Integer bmonth) {
    	this.bmonth = bmonth;
    }

	/**
	 * 获取完成单量
	 */
	public Integer getOrderNumber() {
    	return orderNumber;
    }
  	
	/**
	 * 设置完成单量
	 */
	public void setOrderNumber(Integer orderNumber) {
    	this.orderNumber = orderNumber;
    }

    public String toString() {
		return new StringBuilder().append("BfmAwardLog{").
			append("id=").append(id).
			append(",transactionId=").append(transactionId).
			append(",price=").append(price).
			append(",bmonth=").append(bmonth).
			append(",orderNumber=").append(orderNumber).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, transactionId, price, bmonth, 
	 * orderNumber, creatorId, createTime, status
	 */
	public BfmAwardLog copy(){
		BfmAwardLog bfmAwardLog = new BfmAwardLog();
     	bfmAwardLog.id = this.id;
     	bfmAwardLog.transactionId = this.transactionId;
     	bfmAwardLog.price = this.price;
     	bfmAwardLog.bmonth = this.bmonth;
     	bfmAwardLog.orderNumber = this.orderNumber;
     	bfmAwardLog.creatorId = this.creatorId;
     	bfmAwardLog.createTime = this.createTime;
     	bfmAwardLog.status = this.status;
		return bfmAwardLog;
	}
	
	/**
	 * 比较字段：
	 * id, transactionId, price, bmonth, 
	 * orderNumber, creatorId, createTime, status
	 */
	@Override
	public boolean test(BfmAwardLog t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.transactionId == null || this.transactionId.equals(t.transactionId))
				&& (this.price == null || this.price.equals(t.price))
				&& (this.bmonth == null || this.bmonth.equals(t.bmonth))
				&& (this.orderNumber == null || this.orderNumber.equals(t.orderNumber))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(BfmAwardLog element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.transactionId != null && !this.transactionId.isEmpty()) {
			element.transactionId = this.transactionId;
		}
		if (this.price != null) {
			element.price = this.price;
		}
		if (this.bmonth != null) {
			element.bmonth = this.bmonth;
		}
		if (this.orderNumber != null) {
			element.orderNumber = this.orderNumber;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
