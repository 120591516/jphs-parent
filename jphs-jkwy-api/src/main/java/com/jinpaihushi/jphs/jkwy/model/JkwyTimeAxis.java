package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_TIME_AXIS 
 * 继承自父类的字段:
 * id : 	
 * type : 类型（自己定义123...）	
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
public class JkwyTimeAxis extends BaseModel implements Predicate<JkwyTimeAxis>,
		Updator<JkwyTimeAxis> {


    /**  */
	@Length(max = 50, message = "{jkwyTimeAxis.resourceId.illegal.length}")
	private String resourceId;

    /** 时间轴标题 */
	@Length(max = 50, message = "{jkwyTimeAxis.title.illegal.length}")
	private String title;

    /** 时间轴内容介绍 */
	@Length(max = 255, message = "{jkwyTimeAxis.content.illegal.length}")
	private String content;

	public JkwyTimeAxis(){}

	public JkwyTimeAxis(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getResourceId() {
    	return resourceId;
    }
  	
	/**
	 * 设置
	 */
	public void setResourceId(String resourceId) {
    	this.resourceId = resourceId;
    }

	/**
	 * 获取时间轴标题
	 */
	public String getTitle() {
    	return title;
    }
  	
	/**
	 * 设置时间轴标题
	 */
	public void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * 获取时间轴内容介绍
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置时间轴内容介绍
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("JkwyTimeAxis{").
			append("id=").append(id).
			append(",resourceId=").append(resourceId).
			append(",type=").append(type).
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
	 * id, resourceId, type, title, 
	 * content, status, createTime, creatorId, 
	 * creatorName
	 */
	public JkwyTimeAxis copy(){
		JkwyTimeAxis jkwyTimeAxis = new JkwyTimeAxis();
     	jkwyTimeAxis.id = this.id;
     	jkwyTimeAxis.resourceId = this.resourceId;
     	jkwyTimeAxis.type = this.type;
     	jkwyTimeAxis.title = this.title;
     	jkwyTimeAxis.content = this.content;
     	jkwyTimeAxis.status = this.status;
     	jkwyTimeAxis.createTime = this.createTime;
     	jkwyTimeAxis.creatorId = this.creatorId;
     	jkwyTimeAxis.creatorName = this.creatorName;
		return jkwyTimeAxis;
	}
	
	/**
	 * 比较字段：
	 * id, resourceId, type, title, 
	 * content, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(JkwyTimeAxis t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.resourceId == null || this.resourceId.equals(t.resourceId))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.title == null || this.title.equals(t.title))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(JkwyTimeAxis element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.resourceId != null && !this.resourceId.isEmpty()) {
			element.resourceId = this.resourceId;
		}
		if (this.type != null) {
			element.type = this.type;
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
