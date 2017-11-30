package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_CODE 
 * 继承自父类的字段:
 * id : 	
 * status : (0正常，-1删除)	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-30 09:24:38
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyCode extends BaseModel implements Predicate<JkwyCode>,
		Updator<JkwyCode> {


    /** 活动码 */
	@Length(max = 255, message = "{jkwyCode.code.illegal.length}")
	private String code;

    /** 推荐人id */
	@Length(max = 50, message = "{jkwyCode.recommendId.illegal.length}")
	private String recommendId;

    /** 推荐人姓名 */
	@Length(max = 50, message = "{jkwyCode.recommendName.illegal.length}")
	private String recommendName;

    /** 推荐人手机号 */
	@Length(max = 50, message = "{jkwyCode.recommendPhone.illegal.length}")
	private String recommendPhone;

	public JkwyCode(){}

	public JkwyCode(String id){
		this.id = id;
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

	/**
	 * 获取推荐人id
	 */
	public String getRecommendId() {
    	return recommendId;
    }
  	
	/**
	 * 设置推荐人id
	 */
	public void setRecommendId(String recommendId) {
    	this.recommendId = recommendId;
    }

	/**
	 * 获取推荐人姓名
	 */
	public String getRecommendName() {
    	return recommendName;
    }
  	
	/**
	 * 设置推荐人姓名
	 */
	public void setRecommendName(String recommendName) {
    	this.recommendName = recommendName;
    }

	/**
	 * 获取推荐人手机号
	 */
	public String getRecommendPhone() {
    	return recommendPhone;
    }
  	
	/**
	 * 设置推荐人手机号
	 */
	public void setRecommendPhone(String recommendPhone) {
    	this.recommendPhone = recommendPhone;
    }

    public String toString() {
		return new StringBuilder().append("JkwyCode{").
			append("id=").append(id).
			append(",code=").append(code).
			append(",recommendId=").append(recommendId).
			append(",recommendName=").append(recommendName).
			append(",recommendPhone=").append(recommendPhone).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, code, recommendId, recommendName, 
	 * recommendPhone, status, createTime, creatorId, 
	 * creatorName
	 */
	public JkwyCode copy(){
		JkwyCode jkwyCode = new JkwyCode();
     	jkwyCode.id = this.id;
     	jkwyCode.code = this.code;
     	jkwyCode.recommendId = this.recommendId;
     	jkwyCode.recommendName = this.recommendName;
     	jkwyCode.recommendPhone = this.recommendPhone;
     	jkwyCode.status = this.status;
     	jkwyCode.createTime = this.createTime;
     	jkwyCode.creatorId = this.creatorId;
     	jkwyCode.creatorName = this.creatorName;
		return jkwyCode;
	}
	
	/**
	 * 比较字段：
	 * id, code, recommendId, recommendName, 
	 * recommendPhone, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(JkwyCode t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.code == null || this.code.equals(t.code))
				&& (this.recommendId == null || this.recommendId.equals(t.recommendId))
				&& (this.recommendName == null || this.recommendName.equals(t.recommendName))
				&& (this.recommendPhone == null || this.recommendPhone.equals(t.recommendPhone))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(JkwyCode element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.code != null && !this.code.isEmpty()) {
			element.code = this.code;
		}
		if (this.recommendId != null && !this.recommendId.isEmpty()) {
			element.recommendId = this.recommendId;
		}
		if (this.recommendName != null && !this.recommendName.isEmpty()) {
			element.recommendName = this.recommendName;
		}
		if (this.recommendPhone != null && !this.recommendPhone.isEmpty()) {
			element.recommendPhone = this.recommendPhone;
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
