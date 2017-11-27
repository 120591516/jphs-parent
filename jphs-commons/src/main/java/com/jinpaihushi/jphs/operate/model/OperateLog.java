package com.jinpaihushi.jphs.operate.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * OPERATE_LOG 
 * 继承自父类的字段:
 * id : 	
 * type : 操作类型：1.增，2.删，3.改，4.查	
 * creatorId : 操作人id	
 * creatorName : 操作人姓名	
 * createTime : 操作时间	
 * status : 状态	
 * 
 * @author scj
 * @date 2017-10-16 15:00:33
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OperateLog extends BaseModel implements Predicate<OperateLog>,
		Updator<OperateLog> {


    /** 操作模块-一级分类 */
	private Integer moduleOne;

    /** 操作模块-一级分类名称 */
	@Length(max = 50, message = "{operateLog.moduleOneName.illegal.length}")
	private String moduleOneName;

    /** 操作模块-二级分类 */
	private Integer moduleTwo;

    /** 操作模二级分类名称 */
	@Length(max = 50, message = "{operateLog.moduleTwoName.illegal.length}")
	private String moduleTwoName;

    /** 操作sql */
	@Length(max = 65535, message = "{operateLog.sql.illegal.length}")
	private String sql;

    /** 操作路径 */
	@Length(max = 200, message = "{operateLog.path.illegal.length}")
	private String path;

    /** 说明 */
	@Length(max = 500, message = "{operateLog.remark.illegal.length}")
	private String remark;

    /** 操作ip */
	@Length(max = 50, message = "{operateLog.creatorIp.illegal.length}")
	private String creatorIp;

    /** 操作人手机号 */
	@Length(max = 50, message = "{operateLog.creatorPhone.illegal.length}")
	private String creatorPhone;

    /** 操作状态 */
	private Integer result;

	public OperateLog(){}

	public OperateLog(String id){
		this.id = id;
	}

	/**
	 * 获取操作模块-一级分类
	 */
	public Integer getModuleOne() {
    	return moduleOne;
    }
  	
	/**
	 * 设置操作模块-一级分类
	 */
	public void setModuleOne(Integer moduleOne) {
    	this.moduleOne = moduleOne;
    }

	/**
	 * 获取操作模块-一级分类名称
	 */
	public String getModuleOneName() {
    	return moduleOneName;
    }
  	
	/**
	 * 设置操作模块-一级分类名称
	 */
	public void setModuleOneName(String moduleOneName) {
    	this.moduleOneName = moduleOneName;
    }

	/**
	 * 获取操作模块-二级分类
	 */
	public Integer getModuleTwo() {
    	return moduleTwo;
    }
  	
	/**
	 * 设置操作模块-二级分类
	 */
	public void setModuleTwo(Integer moduleTwo) {
    	this.moduleTwo = moduleTwo;
    }

	/**
	 * 获取操作模二级分类名称
	 */
	public String getModuleTwoName() {
    	return moduleTwoName;
    }
  	
	/**
	 * 设置操作模二级分类名称
	 */
	public void setModuleTwoName(String moduleTwoName) {
    	this.moduleTwoName = moduleTwoName;
    }

	/**
	 * 获取操作sql
	 */
	public String getSql() {
    	return sql;
    }
  	
	/**
	 * 设置操作sql
	 */
	public void setSql(String sql) {
    	this.sql = sql;
    }

	/**
	 * 获取操作路径
	 */
	public String getPath() {
    	return path;
    }
  	
	/**
	 * 设置操作路径
	 */
	public void setPath(String path) {
    	this.path = path;
    }

	/**
	 * 获取说明
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置说明
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

	/**
	 * 获取操作ip
	 */
	public String getCreatorIp() {
    	return creatorIp;
    }
  	
	/**
	 * 设置操作ip
	 */
	public void setCreatorIp(String creatorIp) {
    	this.creatorIp = creatorIp;
    }

	/**
	 * 获取操作人手机号
	 */
	public String getCreatorPhone() {
    	return creatorPhone;
    }
  	
	/**
	 * 设置操作人手机号
	 */
	public void setCreatorPhone(String creatorPhone) {
    	this.creatorPhone = creatorPhone;
    }

	/**
	 * 获取操作状态
	 */
	public Integer getResult() {
    	return result;
    }
  	
	/**
	 * 设置操作状态
	 */
	public void setResult(Integer result) {
    	this.result = result;
    }

    public String toString() {
		return new StringBuilder().append("OperateLog{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",moduleOne=").append(moduleOne).
			append(",moduleOneName=").append(moduleOneName).
			append(",moduleTwo=").append(moduleTwo).
			append(",moduleTwoName=").append(moduleTwoName).
			append(",sql=").append(sql).
			append(",path=").append(path).
			append(",remark=").append(remark).
			append(",creatorIp=").append(creatorIp).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append(",creatorPhone=").append(creatorPhone).
			append(",createTime=").append(createTime).
			append(",result=").append(result).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, moduleOne, moduleOneName, 
	 * moduleTwo, moduleTwoName, sql, path, 
	 * remark, creatorIp, creatorId, creatorName, 
	 * creatorPhone, createTime, result, status
	 */
	public OperateLog copy(){
		OperateLog operateLog = new OperateLog();
     	operateLog.id = this.id;
     	operateLog.type = this.type;
     	operateLog.moduleOne = this.moduleOne;
     	operateLog.moduleOneName = this.moduleOneName;
     	operateLog.moduleTwo = this.moduleTwo;
     	operateLog.moduleTwoName = this.moduleTwoName;
     	operateLog.sql = this.sql;
     	operateLog.path = this.path;
     	operateLog.remark = this.remark;
     	operateLog.creatorIp = this.creatorIp;
     	operateLog.creatorId = this.creatorId;
     	operateLog.creatorName = this.creatorName;
     	operateLog.creatorPhone = this.creatorPhone;
     	operateLog.createTime = this.createTime;
     	operateLog.result = this.result;
     	operateLog.status = this.status;
		return operateLog;
	}
	
	/**
	 * 比较字段：
	 * id, type, moduleOne, moduleOneName, 
	 * moduleTwo, moduleTwoName, sql, path, 
	 * remark, creatorIp, creatorId, creatorName, 
	 * creatorPhone, createTime, result, status
	 */
	@Override
	public boolean test(OperateLog t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.moduleOne == null || this.moduleOne.equals(t.moduleOne))
				&& (this.moduleOneName == null || this.moduleOneName.equals(t.moduleOneName))
				&& (this.moduleTwo == null || this.moduleTwo.equals(t.moduleTwo))
				&& (this.moduleTwoName == null || this.moduleTwoName.equals(t.moduleTwoName))
				&& (this.sql == null || this.sql.equals(t.sql))
				&& (this.path == null || this.path.equals(t.path))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.creatorIp == null || this.creatorIp.equals(t.creatorIp))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
				&& (this.creatorPhone == null || this.creatorPhone.equals(t.creatorPhone))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.result == null || this.result.equals(t.result))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(OperateLog element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.moduleOne != null) {
			element.moduleOne = this.moduleOne;
		}
		if (this.moduleOneName != null && !this.moduleOneName.isEmpty()) {
			element.moduleOneName = this.moduleOneName;
		}
		if (this.moduleTwo != null) {
			element.moduleTwo = this.moduleTwo;
		}
		if (this.moduleTwoName != null && !this.moduleTwoName.isEmpty()) {
			element.moduleTwoName = this.moduleTwoName;
		}
		if (this.sql != null && !this.sql.isEmpty()) {
			element.sql = this.sql;
		}
		if (this.path != null && !this.path.isEmpty()) {
			element.path = this.path;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
		}
		if (this.creatorIp != null && !this.creatorIp.isEmpty()) {
			element.creatorIp = this.creatorIp;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.creatorName != null && !this.creatorName.isEmpty()) {
			element.creatorName = this.creatorName;
		}
		if (this.creatorPhone != null && !this.creatorPhone.isEmpty()) {
			element.creatorPhone = this.creatorPhone;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.result != null) {
			element.result = this.result;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
