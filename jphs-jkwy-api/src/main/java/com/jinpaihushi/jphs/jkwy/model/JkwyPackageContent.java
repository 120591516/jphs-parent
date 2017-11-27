package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_PACKAGE_CONTENT 服务模块
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-25 15:47:42
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyPackageContent extends BaseModel implements Predicate<JkwyPackageContent>,
		Updator<JkwyPackageContent> {


    /**  */
	@Length(max = 50, message = "{jkwyPackageContent.jkwyPackagePriceId.illegal.length}")
	private String jkwyPackagePriceId;

    /** 服务标题 */
	@Length(max = 50, message = "{jkwyPackageContent.title.illegal.length}")
	private String title;

    /** 服务内容 */
	@Length(max = 255, message = "{jkwyPackageContent.subTitle.illegal.length}")
	private String subTitle;

    /** 超文本服务内容说明 */
	@Length(max = 65535, message = "{jkwyPackageContent.content.illegal.length}")
	private String content;

    /** 总服务次数 */
	private Integer number;

    /** 剩余服务次数 */
	private Integer surplusNumber;

	public JkwyPackageContent(){}

	public JkwyPackageContent(String id){
		this.id = id;
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
	 * 获取服务内容
	 */
	public String getSubTitle() {
    	return subTitle;
    }
  	
	/**
	 * 设置服务内容
	 */
	public void setSubTitle(String subTitle) {
    	this.subTitle = subTitle;
    }

	/**
	 * 获取超文本服务内容说明
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置超文本服务内容说明
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
		return new StringBuilder().append("JkwyPackageContent{").
			append("id=").append(id).
			append(",jkwyPackagePriceId=").append(jkwyPackagePriceId).
			append(",title=").append(title).
			append(",subTitle=").append(subTitle).
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
	 * id, jkwyPackagePriceId, title, subTitle, 
	 * content, number, surplusNumber, status, 
	 * createTime, creatorId, creatorName
	 */
	public JkwyPackageContent copy(){
		JkwyPackageContent jkwyPackageContent = new JkwyPackageContent();
     	jkwyPackageContent.id = this.id;
     	jkwyPackageContent.jkwyPackagePriceId = this.jkwyPackagePriceId;
     	jkwyPackageContent.title = this.title;
     	jkwyPackageContent.subTitle = this.subTitle;
     	jkwyPackageContent.content = this.content;
     	jkwyPackageContent.number = this.number;
     	jkwyPackageContent.surplusNumber = this.surplusNumber;
     	jkwyPackageContent.status = this.status;
     	jkwyPackageContent.createTime = this.createTime;
     	jkwyPackageContent.creatorId = this.creatorId;
     	jkwyPackageContent.creatorName = this.creatorName;
		return jkwyPackageContent;
	}
	
	/**
	 * 比较字段：
	 * id, jkwyPackagePriceId, title, subTitle, 
	 * content, number, surplusNumber, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(JkwyPackageContent t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.jkwyPackagePriceId == null || this.jkwyPackagePriceId.equals(t.jkwyPackagePriceId))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.subTitle == null || this.subTitle.equals(t.subTitle))
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
	public void update(JkwyPackageContent element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.jkwyPackagePriceId != null && !this.jkwyPackagePriceId.isEmpty()) {
			element.jkwyPackagePriceId = this.jkwyPackagePriceId;
		}
		if (this.title != null && !this.title.isEmpty()) {
			element.title = this.title;
		}
		if (this.subTitle != null && !this.subTitle.isEmpty()) {
			element.subTitle = this.subTitle;
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
