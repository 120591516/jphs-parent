package com.jinpaihushi.jphs.user.model;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.model.BaseModel;

/**
 * VOUCHER_REPERTORY 
 * 继承自父类的字段:
 * id : 优惠券仓库id	
 * status : 是否删除  0否（默认），-1删除	
 * 
 * @author yangsong
 * @date 2017-07-14 14:35:45
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class VoucherRepertory extends BaseModel {


    /** 优惠券id */
	@Length(max = 50, message = "{voucherRepertory.voucherId.illegal.length}")
	private String voucherId;

    /** 卡号 */
	@Length(max = 50, message = "{voucherRepertory.no.illegal.length}")
	private String no;

    /** 兑换码 */
	@Length(max = 50, message = "{voucherRepertory.code.illegal.length}")
	private String code;

    /** 金额 */
	private Double amount;

    /** 满xx减 */
	private Double conditionAmount;

    /** 满xx折 */
	private Double discountAmount;
	private VoucherUse voucherUser;
	public VoucherRepertory(){}

	public VoucherRepertory(String id){
		this.id = id;
	}

	/**
	 * 获取优惠券id
	 */
	public String getVoucherId() {
    	return voucherId;
    }
  	
	/**
	 * 设置优惠券id
	 */
	public void setVoucherId(String voucherId) {
    	this.voucherId = voucherId;
    }

	/**
	 * 获取卡号
	 */
	public String getNo() {
    	return no;
    }
  	
	/**
	 * 设置卡号
	 */
	public void setNo(String no) {
    	this.no = no;
    }

	/**
	 * 获取兑换码
	 */
	public String getCode() {
    	return code;
    }
  	
	/**
	 * 设置兑换码
	 */
	public void setCode(String code) {
    	this.code = code;
    }

	/**
	 * 获取金额
	 */
	public Double getAmount() {
    	return amount;
    }
  	
	/**
	 * 设置金额
	 */
	public void setAmount(Double amount) {
    	this.amount = amount;
    }

	/**
	 * 获取满xx减
	 */
	public Double getConditionAmount() {
    	return conditionAmount;
    }
  	
	/**
	 * 设置满xx减
	 */
	public void setConditionAmount(Double conditionAmount) {
    	this.conditionAmount = conditionAmount;
    }

	/**
	 * 获取满xx折
	 */
	public Double getDiscountAmount() {
    	return discountAmount;
    }
  	
	/**
	 * 设置满xx折
	 */
	public void setDiscountAmount(Double discountAmount) {
    	this.discountAmount = discountAmount;
    }
	
    public VoucherUse getVoucherUser() {
		return voucherUser;
	}

	public void setVoucherUser(VoucherUse voucherUser) {
		this.voucherUser = voucherUser;
	}

	public String toString() {
		return new StringBuilder().append("VoucherRepertory{").
			append("id=").append(id).
			append(",voucherId=").append(voucherId).
			append(",no=").append(no).
			append(",code=").append(code).
			append(",amount=").append(amount).
			append(",conditionAmount=").append(conditionAmount).
			append(",discountAmount=").append(discountAmount).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, voucherId, no, code, 
	 * amount, conditionAmount, discountAmount, status
	 */
	public VoucherRepertory copy(){
		VoucherRepertory voucherRepertory = new VoucherRepertory();
     	voucherRepertory.id = this.id;
     	voucherRepertory.voucherId = this.voucherId;
     	voucherRepertory.no = this.no;
     	voucherRepertory.code = this.code;
     	voucherRepertory.amount = this.amount;
     	voucherRepertory.conditionAmount = this.conditionAmount;
     	voucherRepertory.discountAmount = this.discountAmount;
     	voucherRepertory.status = this.status;
		return voucherRepertory;
	}
	
}
