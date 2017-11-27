package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_ORDER_CONTENT 
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
public class JkwyOrderContent extends BaseModel implements Predicate<JkwyOrderContent>,
		Updator<JkwyOrderContent> {


    /**  */
	@Length(max = 50, message = "{jkwyOrderContent.jkwyOrderId.illegal.length}")
	private String jkwyOrderId;

    /** 服务标题 */
	@Length(max = 50, message = "{jkwyOrderContent.title.illegal.length}")
	private String title;

    /** 超文本服务内容介绍 */
	@Length(max = 65535, message = "{jkwyOrderContent.content.illegal.length}")
	private String content;

    /** 总服务次数 */
	private Integer number;

    /** 剩余服务次数 */
	private Integer surplusNumber;

	public JkwyOrderContent(){}

	public JkwyOrderContent(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getJkwyOrderId() {
    	return jkwyOrderId;
    }
  	
	/**
	 * 设置
	 */
	public void setJkwyOrderId(String jkwyOrderId) {
    	this.jkwyOrderId = jkwyOrderId;
    }

	/**
	 * 获取服务标题
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置服务标题
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取超文本服务内容介绍
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置超文本服务内容介绍
	 */
	public void setContent(String content) {
    	this.content = content;
    }

	/**
	 * 获取总服务次数
	 */
	public Integer getNumber() {
    	return number;
    }
  	
	/**
	 * 设置总服务次数
	 */
	public void setNumber(Integer number) {
    	this.number = number;
    }

	/**
	 * 获取剩余服务次数
	 */
	public Integer getSurplusNumber() {
    	return surplusNumber;
    }
  	
	/**
	 * 设置剩余服务次数
	 */
	public void setSurplusNumber(Integer surplusNumber) {
    	this.surplusNumber = surplusNumber;
    }

    public String toString() {
		return new StringBuilder().append("JkwyOrderContent{").
			append("id=").append(id).
			append(",jkwyOrderId=").append(jkwyOrderId).
			append(",title=").append(title).
			append(",content=").append(content).
			append(",number=").append(number).
			append(",surplusNumber=").append(surplusNumber).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, jkwyOrderId, title, content, 
	 * number, surplusNumber, status, createTime, 
	 * creatorId, creatorName
	 */
	public JkwyOrderContent copy(){
		JkwyOrderContent jkwyOrderContent = new JkwyOrderContent();
     	jkwyOrderContent.id = this.id;
     	jkwyOrderContent.jkwyOrderId = this.jkwyOrderId;
     	jkwyOrderContent.title = this.title;
     	jkwyOrderContent.content = this.content;
     	jkwyOrderContent.number = this.number;
     	jkwyOrderContent.surplusNumber = this.surplusNumber;
     	jkwyOrderContent.status = this.status;
     	jkwyOrderContent.createTime = this.createTime;
     	jkwyOrderContent.creatorId = this.creatorId;
     	jkwyOrderContent.creatorName = this.creatorName;
		return jkwyOrderContent;
	}
	
	/**
	 * 比较字段：
	 * id, jkwyOrderId, title, content, 
	 * number, surplusNumber, status, createTime, 
	 * creatorId, creatorName
	 */
	@Override
	public boolean test(JkwyOrderContent t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.jkwyOrderId == null || this.jkwyOrderId.equals(t.jkwyOrderId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.number == null || this.number.equals(t.number))
				&& (this.surplusNumber == null || this.surplusNumber.equals(t.surplusNumber))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(JkwyOrderContent element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.jkwyOrderId != null && !this.jkwyOrderId.isEmpty()) {
			element.jkwyOrderId = this.jkwyOrderId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.content != null && !this.content.isEmpty()) {
			element.content = this.content;
		}
		if (this.number != null) {
			element.number = this.number;
		}
		if (this.surplusNumber != null) {
			element.surplusNumber = this.surplusNumber;
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
