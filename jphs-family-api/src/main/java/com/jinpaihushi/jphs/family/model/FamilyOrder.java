package com.jinpaihushi.jphs.family.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.model.BaseModel;

/**
 * FAMILY_ORDER 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FamilyOrder extends BaseModel implements Predicate<FamilyOrder>,
		Updator<FamilyOrder> {


    /**  */
	@Length(max = 50, message = "{familyOrder.familyPackageId.illegal.length}")
	private String familyModeId;

    /** 实付金额 */
	private Double payPrice;

    /** 微信号 */
	@Length(max = 50, message = "{familyOrder.wxNo.illegal.length}")
	private String wxNo;

    /** 到期时间 */
	private Date endTime;

    /** 活动码 */
	@Length(max = 255, message = "{familyOrder.code.illegal.length}")
	private String code;
	
	private String phone;
	private String accessMode;

	private FamilyMode familyMode;
	
	private List<FamilyConsultation> familyConsultationList;
	private List<FamilyRegister> familyRegisterList;
	private List<FamilyHealthy> familyHealthyList;
	private List<FamilyMember> familyMemberList;
	
	private User user;
	
	public FamilyOrder(){}

	public FamilyOrder(String id){
		this.id = id;
	}
	
	public String getAccessMode(){
		return accessMode;
	}
	public void setAccessMode(String accessMode){
		this.accessMode=accessMode;
	}
	
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	
	/**
	 * 获取
	 */
	public List<FamilyMember> getFamilyMemberList() {
    	return familyMemberList;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyMemberList(List<FamilyMember> familyMemberList) {
    	this.familyMemberList = familyMemberList;
    }

	/**
	 * 获取
	 */
	public List<FamilyHealthy> getFamilyHealthyList() {
    	return familyHealthyList;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyHealthyList(List<FamilyHealthy> familyHealthyList) {
    	this.familyHealthyList = familyHealthyList;
    }
	
	/**
	 * 获取
	 */
	public List<FamilyRegister> getFamilyRegisterList() {
    	return familyRegisterList;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyRegisterList(List<FamilyRegister> familyRegisterList) {
    	this.familyRegisterList = familyRegisterList;
    }
	
	/**
	 * 获取
	 */
	public List<FamilyConsultation> getFamilyConsultationList() {
    	return familyConsultationList;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyConsultationList(List<FamilyConsultation> familyConsultationList) {
    	this.familyConsultationList = familyConsultationList;
    }
	
	/**
	 * 获取
	 */
	public User getUser() {
    	return user;
    }
  	
	/**
	 * 设置
	 */
	public void setUser(User user) {
    	this.user = user;
    }
	
	/**
	 * 获取
	 */
	public FamilyMode getFamilyMode() {
    	return familyMode;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyMode(FamilyMode familyMode) {
    	this.familyMode = familyMode;
    }
	
	/**
	 * 获取
	 */
	public String getFamilyModeId() {
    	return familyModeId;
    }
  	
	/**
	 * 设置
	 */
	public void setFamilyModeId(String familyModeId) {
    	this.familyModeId = familyModeId;
    }

	/**
	 * 获取实付金额
	 */
	public Double getPayPrice() {
    	return payPrice;
    }
  	
	/**
	 * 设置实付金额
	 */
	public void setPayPrice(Double payPrice) {
    	this.payPrice = payPrice;
    }

	/**
	 * 获取微信号
	 */
	public String getWxNo() {
    	return wxNo;
    }
  	
	/**
	 * 设置微信号
	 */
	public void setWxNo(String wxNo) {
    	this.wxNo = wxNo;
    }

	/**
	 * 获取到期时间
	 */
	public Date getEndTime() {
    	return endTime;
    }
  	
	/**
	 * 设置到期时间
	 */
	public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }

	/**
	 * 获取活动码
	 */
	public String getCode() {
    	return code;
    }
  	
	/**
	 * 设置活动码
	 */
	public void setCode(String code) {
    	this.code = code;
    }

	
    @Override
	public String toString() {
		return "FamilyOrder [familyModeId=" + familyModeId + ", payPrice=" + payPrice + ", wxNo=" + wxNo + ", endTime="
				+ endTime + ", code=" + code + ", familyMode=" + familyMode + ", familyConsultationList="
				+ familyConsultationList + ", familyRegisterList=" + familyRegisterList + ", familyHealthyList="
				+ familyHealthyList + ", user=" + user + "]";
	}

	/**
	 * 复制字段：
	 * id, familyPackageId, payPrice, wxNo, 
	 * endTime, code, status, createTime, 
	 * creatorId, creatorName
	 */
	public FamilyOrder copy(){
		FamilyOrder familyOrder = new FamilyOrder();
     	familyOrder.id = this.id;
     	familyOrder.familyModeId = this.familyModeId;
     	familyOrder.payPrice = this.payPrice;
     	familyOrder.wxNo = this.wxNo;
     	familyOrder.endTime = this.endTime;
     	familyOrder.code = this.code;
     	familyOrder.status = this.status;
     	familyOrder.createTime = this.createTime;
     	familyOrder.creatorId = this.creatorId;
     	familyOrder.creatorName = this.creatorName;
		return familyOrder;
	}
	
	/**
	 * 比较字段：
	 * id, familyPackageId, payPrice, wxNo, 
	 * endTime, code, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(FamilyOrder t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.familyModeId == null || this.familyModeId.equals(t.familyModeId))
				&& (this.payPrice == null || this.payPrice.equals(t.payPrice))
				&& (this.wxNo == null || this.wxNo.equals(t.wxNo))
				&& (this.endTime == null || this.endTime.equals(t.endTime))
				&& (this.code == null || this.code.equals(t.code))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(FamilyOrder element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.familyModeId != null && !this.familyModeId.isEmpty()) {
			element.familyModeId = this.familyModeId;
		}
		if (this.payPrice != null) {
			element.payPrice = this.payPrice;
		}
		if (this.wxNo != null && !this.wxNo.isEmpty()) {
			element.wxNo = this.wxNo;
		}
		if (this.endTime != null) {
			element.endTime = this.endTime;
		}
		if (this.code != null && !this.code.isEmpty()) {
			element.code = this.code;
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
