package com.jinpaihushi.jphs.analysis.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;
import java.util.Date;

/**
 * ANALYSIS_TASK 日志分析任务记录表
 * 继承自父类的字段:
 * id : 	
 * status : 默认 0，1、执行中  -1 已结束	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-21 10:44:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AnalysisTask extends BaseModel implements Predicate<AnalysisTask>,
		Updator<AnalysisTask> {


    /** 任务名称 */
	@Length(max = 100, message = "{analysisTask.name.illegal.length}")
	private String name;

    /** 链接地址 */
	@Length(max = 255, message = "{analysisTask.url.illegal.length}")
	private String url;

    /** 开始时间 */
	private Date beginTime;

    /** 结束时间 */
	private Date endTime;

	public AnalysisTask(){}

	public AnalysisTask(String id){
		this.id = id;
	}

	/**
	 * 获取任务名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置任务名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取链接地址
	 */
	public String getUrl() {
    	return url;
    }
  	
	/**
	 * 设置链接地址
	 */
	public void setUrl(String url) {
    	this.url = url;
    }

	/**
	 * 获取开始时间
	 */
	public Date getBeginTime() {
    	return beginTime;
    }
  	
	/**
	 * 设置开始时间
	 */
	public void setBeginTime(Date beginTime) {
    	this.beginTime = beginTime;
    }

	/**
	 * 获取结束时间
	 */
	public Date getEndTime() {
    	return endTime;
    }
  	
	/**
	 * 设置结束时间
	 */
	public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }

    public String toString() {
		return new StringBuilder().append("AnalysisTask{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",url=").append(url).
			append(",beginTime=").append(beginTime).
			append(",endTime=").append(endTime).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, url, beginTime, 
	 * endTime, status, createTime, creatorId, 
	 * creatorName
	 */
	public AnalysisTask copy(){
		AnalysisTask analysisTask = new AnalysisTask();
     	analysisTask.id = this.id;
     	analysisTask.name = this.name;
     	analysisTask.url = this.url;
     	analysisTask.beginTime = this.beginTime;
     	analysisTask.endTime = this.endTime;
     	analysisTask.status = this.status;
     	analysisTask.createTime = this.createTime;
     	analysisTask.creatorId = this.creatorId;
     	analysisTask.creatorName = this.creatorName;
		return analysisTask;
	}
	
	/**
	 * 比较字段：
	 * id, name, url, beginTime, 
	 * endTime, status, createTime, creatorId, 
	 * creatorName
	 */
	@Override
	public boolean test(AnalysisTask t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.beginTime == null || this.beginTime.equals(t.beginTime))
				&& (this.endTime == null || this.endTime.equals(t.endTime))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(AnalysisTask element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.beginTime != null) {
			element.beginTime = this.beginTime;
		}
		if (this.endTime != null) {
			element.endTime = this.endTime;
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
