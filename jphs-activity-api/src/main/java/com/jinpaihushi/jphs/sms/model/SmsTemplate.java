package com.jinpaihushi.jphs.sms.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SMS_TEMPLATE 
 * 继承自父类的字段:
 * id : 	
 * type : 类型（1标准短信，2推广短信）	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SmsTemplate extends BaseModel implements Predicate<SmsTemplate>,
		Updator<SmsTemplate> {


    /** 大于模板id */
	@Length(max = 50, message = "{smsTemplate.smsId.illegal.length}")
	private String smsId;

    /** 标题 */
	@Length(max = 255, message = "{smsTemplate.title.illegal.length}")
	private String title;

    /** 内容 */
	@Length(max = 65535, message = "{smsTemplate.content.illegal.length}")
	private String content;

	public SmsTemplate(){}

	public SmsTemplate(String id){
		this.id = id;
	}

	/**
	 * 获取大于模板id
	 */
	public String getSmsId() {
    	return smsId;
    }
  	
	/**
	 * 设置大于模板id
	 */
	public void setSmsId(String smsId) {
    	this.smsId = smsId;
    }

	/**
	 * 获取标题
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置内容
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("SmsTemplate{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",smsId=").append(smsId).
			append(",title=").append(title).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, smsId, title, 
	 * content, status, createTime, creatorId, 
	 * creatorName
	 */
	public SmsTemplate copy(){
		SmsTemplate smsTemplate = new SmsTemplate();
     	smsTemplate.id = this.id;
     	smsTemplate.type = this.type;
     	smsTemplate.smsId = this.smsId;
     	smsTemplate.title = this.title;
     	smsTemplate.content = this.content;
     	smsTemplate.status = this.status;
     	smsTemplate.createTime = this.createTime;
     	smsTemplate.creatorId = this.creatorId;
     	smsTemplate.creatorName = this.creatorName;
		return smsTemplate;
	}
	
	/**
	 * 比较字段：
	 * id, type, smsId, title, 
	 * content, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(SmsTemplate t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.smsId == null || this.smsId.equals(t.smsId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(SmsTemplate element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.smsId != null && !this.smsId.isEmpty()) {
			element.smsId = this.smsId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
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
