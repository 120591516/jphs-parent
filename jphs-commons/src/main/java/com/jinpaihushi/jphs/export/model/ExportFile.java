package com.jinpaihushi.jphs.export.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * EXPORT_FILE 
 * 继承自父类的字段:
 * id : 	
 * creatorName : 下载人姓名	
 * creatorId : 	
 * createTime : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-10-12 14:13:45
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ExportFile extends BaseModel implements Predicate<ExportFile>,
		Updator<ExportFile> {


    /** 下载文件类型名称 */
	@Length(max = 50, message = "{exportFile.fileName.illegal.length}")
	private String fileName;

    /** 执行的sql */
	@Length(max = 65535, message = "{exportFile.executeSql.illegal.length}")
	private String executeSql;

    /** ip地址 */
	@Length(max = 50, message = "{exportFile.creatorIp.illegal.length}")
	private String creatorIp;

    /** 手机号 */
	@Length(max = 50, message = "{exportFile.creatorPhone.illegal.length}")
	private String creatorPhone;

	public ExportFile(){}

	public ExportFile(String id){
		this.id = id;
	}

	/**
	 * 获取下载文件类型名称
	 */
	public String getFileName() {
    	return fileName;
    }
  	
	/**
	 * 设置下载文件类型名称
	 */
	public void setFileName(String fileName) {
    	this.fileName = fileName;
    }

	/**
	 * 获取执行的sql
	 */
	public String getExecuteSql() {
    	return executeSql;
    }
  	
	/**
	 * 设置执行的sql
	 */
	public void setExecuteSql(String executeSql) {
    	this.executeSql = executeSql;
    }

	/**
	 * 获取ip地址
	 */
	public String getCreatorIp() {
    	return creatorIp;
    }
  	
	/**
	 * 设置ip地址
	 */
	public void setCreatorIp(String creatorIp) {
    	this.creatorIp = creatorIp;
    }

	/**
	 * 获取手机号
	 */
	public String getCreatorPhone() {
    	return creatorPhone;
    }
  	
	/**
	 * 设置手机号
	 */
	public void setCreatorPhone(String creatorPhone) {
    	this.creatorPhone = creatorPhone;
    }

    public String toString() {
		return new StringBuilder().append("ExportFile{").
			append("id=").append(id).
			append(",fileName=").append(fileName).
			append(",executeSql=").append(executeSql).
			append(",creatorIp=").append(creatorIp).
			append(",creatorName=").append(creatorName).
			append(",creatorPhone=").append(creatorPhone).
			append(",creatorId=").append(creatorId).
			append(",createTime=").append(createTime).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, fileName, executeSql, creatorIp, 
	 * creatorName, creatorPhone, creatorId, createTime, 
	 * status
	 */
	public ExportFile copy(){
		ExportFile exportFile = new ExportFile();
     	exportFile.id = this.id;
     	exportFile.fileName = this.fileName;
     	exportFile.executeSql = this.executeSql;
     	exportFile.creatorIp = this.creatorIp;
     	exportFile.creatorName = this.creatorName;
     	exportFile.creatorPhone = this.creatorPhone;
     	exportFile.creatorId = this.creatorId;
     	exportFile.createTime = this.createTime;
     	exportFile.status = this.status;
		return exportFile;
	}
	
	/**
	 * 比较字段：
	 * id, fileName, executeSql, creatorIp, 
	 * creatorName, creatorPhone, creatorId, createTime, 
	 * status
	 */
	@Override
	public boolean test(ExportFile t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.fileName == null || this.fileName.equals(t.fileName))
				&& (this.executeSql == null || this.executeSql.equals(t.executeSql))
				&& (this.creatorIp == null || this.creatorIp.equals(t.creatorIp))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorPhone == null || this.creatorPhone.equals(t.creatorPhone))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(ExportFile element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.fileName != null && !this.fileName.isEmpty()) {
			element.fileName = this.fileName;
		}
		if (this.executeSql != null && !this.executeSql.isEmpty()) {
			element.executeSql = this.executeSql;
		}
		if (this.creatorIp != null && !this.creatorIp.isEmpty()) {
			element.creatorIp = this.creatorIp;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.creatorPhone != null && !this.creatorPhone.isEmpty()) {
			element.creatorPhone = this.creatorPhone;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
