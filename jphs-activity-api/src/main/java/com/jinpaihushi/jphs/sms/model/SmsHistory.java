package com.jinpaihushi.jphs.sms.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SMS_HISTORY 继承自父类的字段: id : status : 状态（0正常，-1删除） createTime : creatorId :
 * creatorName :
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SmsHistory extends BaseModel implements Predicate<SmsHistory>, Updator<SmsHistory> {

	/**  */
	@Length(max = 50, message = "{smsHistory.smsTemplateId.illegal.length}")
	private String smsTemplateId;

	/**  */
	@Length(max = 50, message = "{smsHistory.smsTemplate.illegal.length}")
	private SmsTemplate smsTemplate;

	/** 接收手机号 */
	@Length(max = 65535, message = "{smsHistory.phone.illegal.length}")
	private String phone;

	/** 发送者IP */
	@Length(max = 50, message = "{smsHistory.sendIp.illegal.length}")
	private String sendIp;

	public SmsHistory() {
	}

	public SmsHistory(String id) {
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	/**
	 * 设置
	 */
	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	/**
	 * 获取接收手机号
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置接收手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取发送者IP
	 */
	public String getSendIp() {
		return sendIp;
	}

	/**
	 * 设置发送者IP
	 */
	public void setSendIp(String sendIp) {
		this.sendIp = sendIp;
	}

	public String toString() {
		return new StringBuilder().append("SmsHistory{").append("id=").append(id).append(",smsTemplateId=")
				.append(smsTemplateId).append(",smsTemplate=").append(smsTemplate).append(",phone=").append(phone)
				.append(",sendIp=").append(sendIp).append(",status=").append(status).append(",createTime=")
				.append(createTime).append(",creatorId=").append(creatorId).append(",creatorName=").append(creatorName)
				.append('}').toString();
	}

	/**
	 * 复制字段： id, smsTemplateId, phone, sendIp, status, createTime, creatorId,
	 * creatorName
	 */
	public SmsHistory copy() {
		SmsHistory smsHistory = new SmsHistory();
		smsHistory.id = this.id;
		smsHistory.smsTemplateId = this.smsTemplateId;
		smsHistory.smsTemplate = this.smsTemplate;
		smsHistory.phone = this.phone;
		smsHistory.sendIp = this.sendIp;
		smsHistory.status = this.status;
		smsHistory.createTime = this.createTime;
		smsHistory.creatorId = this.creatorId;
		smsHistory.creatorName = this.creatorName;
		return smsHistory;
	}

	public SmsTemplate getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(SmsTemplate smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	/**
	 * 比较字段： id, smsTemplateId, phone, sendIp, status, createTime, creatorId,
	 * creatorName
	 */
	@Override
	public boolean test(SmsHistory t) {
		if (t == null)
			return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.smsTemplateId == null || this.smsTemplateId.equals(t.smsTemplateId))
				&& (this.smsTemplate == null || this.smsTemplate.equals(t.smsTemplate))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.sendIp == null || this.sendIp.equals(t.sendIp))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName));
	}

	@Override
	public void update(SmsHistory element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.smsTemplateId != null && !this.smsTemplateId.isEmpty()) {
			element.smsTemplateId = this.smsTemplateId;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.sendIp != null && !this.sendIp.isEmpty()) {
			element.sendIp = this.sendIp;
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
