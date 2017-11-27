package com.jinpaihushi.jphs.remark.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * REMARK 
 * 继承自父类的字段:
 * id : 	
 * status : 状态（0正常，-1删除）	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-09-29 16:55:54
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Remark extends BaseModel implements Predicate<Remark>,
		Updator<Remark> {


    /**  */
	@Length(max = 50, message = "{remark.sourceId.illegal.length}")
	private String sourceId;

    /** 注备内容 */
	@Length(max = 65535, message = "{remark.content.illegal.length}")
	private String content;

	public Remark(){}

	public Remark(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getSourceId() {
    	return sourceId;
    }
  	
	/**
	 * 设置
	 */
	public void setSourceId(String sourceId) {
    	this.sourceId = sourceId;
    }

	/**
	 * 获取注备内容
	 */
	public String getContent() {
    	return content;
    }
  	
	/**
	 * 设置注备内容
	 */
	public void setContent(String content) {
    	this.content = content;
    }

    public String toString() {
		return new StringBuilder().append("Remark{").
			append("id=").append(id).
			append(",sourceId=").append(sourceId).
			append(",content=").append(content).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, sourceId, content, status, 
	 * createTime, creatorId, creatorName
	 */
	public Remark copy(){
		Remark remark = new Remark();
     	remark.id = this.id;
     	remark.sourceId = this.sourceId;
     	remark.content = this.content;
     	remark.status = this.status;
     	remark.createTime = this.createTime;
     	remark.creatorId = this.creatorId;
     	remark.creatorName = this.creatorName;
		return remark;
	}
	
	/**
	 * 比较字段：
	 * id, sourceId, content, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(Remark t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.sourceId == null || this.sourceId.equals(t.sourceId))
				&& (this.content == null || this.content.equals(t.content))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(Remark element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.sourceId != null && !this.sourceId.isEmpty()) {
			element.sourceId = this.sourceId;
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
