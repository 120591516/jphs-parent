package com.jinpaihushi.jphs.analysis.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;
import java.util.Date;

/**
 * ANALYSIS_RESULT 日志分析结果
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * 
 * @author wangwenteng
 * @date 2017-11-30 14:09:03
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AnalysisResult extends BaseModel implements Predicate<AnalysisResult>,
		Updator<AnalysisResult> {


    /** 分析任务id */
	@Length(max = 50, message = "{analysisResult.analysisTaskId.illegal.length}")
	private String analysisTaskId;

    /** 用户ip */
	@Length(max = 50, message = "{analysisResult.ip.illegal.length}")
	private String ip;

    /** 访问用户所在省 */
	@Length(max = 50, message = "{analysisResult.province.illegal.length}")
	private String province;

    /** 访问时间 */
	private Date visitTime;

    /** 开始时间 */
	private Date starttime;

    /** 结束时间 */
	private Date endtime;

	public AnalysisResult(){}

	public AnalysisResult(String id){
		this.id = id;
	}

	/**
	 * 获取分析任务id
	 */
	public String getAnalysisTaskId() {
    	return analysisTaskId;
    }
  	
	/**
	 * 设置分析任务id
	 */
	public void setAnalysisTaskId(String analysisTaskId) {
    	this.analysisTaskId = analysisTaskId;
    }

	/**
	 * 获取用户ip
	 */
	public String getIp() {
    	return ip;
    }
  	
	/**
	 * 设置用户ip
	 */
	public void setIp(String ip) {
    	this.ip = ip;
    }

	/**
	 * 获取访问用户所在省
	 */
	public String getProvince() {
    	return province;
    }
  	
	/**
	 * 设置访问用户所在省
	 */
	public void setProvince(String province) {
    	this.province = province;
    }

	/**
	 * 获取访问时间
	 */
	public Date getVisitTime() {
    	return visitTime;
    }
  	
	/**
	 * 设置访问时间
	 */
	public void setVisitTime(Date visitTime) {
    	this.visitTime = visitTime;
    }

	/**
	 * 获取开始时间
	 */
	public Date getStarttime() {
    	return starttime;
    }
  	
	/**
	 * 设置开始时间
	 */
	public void setStarttime(Date starttime) {
    	this.starttime = starttime;
    }

	/**
	 * 获取结束时间
	 */
	public Date getEndtime() {
    	return endtime;
    }
  	
	/**
	 * 设置结束时间
	 */
	public void setEndtime(Date endtime) {
    	this.endtime = endtime;
    }

    public String toString() {
		return new StringBuilder().append("AnalysisResult{").
			append("id=").append(id).
			append(",analysisTaskId=").append(analysisTaskId).
			append(",ip=").append(ip).
			append(",province=").append(province).
			append(",visitTime=").append(visitTime).
			append(",starttime=").append(starttime).
			append(",endtime=").append(endtime).
			append(",createTime=").append(createTime).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, analysisTaskId, ip, province, 
	 * visitTime, starttime, endtime, createTime
	 */
	public AnalysisResult copy(){
		AnalysisResult analysisResult = new AnalysisResult();
     	analysisResult.id = this.id;
     	analysisResult.analysisTaskId = this.analysisTaskId;
     	analysisResult.ip = this.ip;
     	analysisResult.province = this.province;
     	analysisResult.visitTime = this.visitTime;
     	analysisResult.starttime = this.starttime;
     	analysisResult.endtime = this.endtime;
     	analysisResult.createTime = this.createTime;
		return analysisResult;
	}
	
	/**
	 * 比较字段：
	 * id, analysisTaskId, ip, province, 
	 * visitTime, starttime, endtime, createTime
	 */
	@Override
	public boolean test(AnalysisResult t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.analysisTaskId == null || this.analysisTaskId.equals(t.analysisTaskId))
				&& (this.ip == null || this.ip.equals(t.ip))
				&& (this.province == null || this.province.equals(t.province))
				&& (this.visitTime == null || this.visitTime.equals(t.visitTime))
				&& (this.starttime == null || this.starttime.equals(t.starttime))
				&& (this.endtime == null || this.endtime.equals(t.endtime))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
		;
	}
	
	@Override
	public void update(AnalysisResult element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.analysisTaskId != null && !this.analysisTaskId.isEmpty()) {
			element.analysisTaskId = this.analysisTaskId;
		}
		if (this.ip != null && !this.ip.isEmpty()) {
			element.ip = this.ip;
		}
		if (this.province != null && !this.province.isEmpty()) {
			element.province = this.province;
		}
		if (this.visitTime != null) {
			element.visitTime = this.visitTime;
		}
		if (this.starttime != null) {
			element.starttime = this.starttime;
		}
		if (this.endtime != null) {
			element.endtime = this.endtime;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
	}
}
