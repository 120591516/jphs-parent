package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_PACKAGE_PLATFORM 套餐--平台中间表
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * 
 * @author wangwenteng
 * @date 2017-11-22 11:06:05
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyPackagePlatform extends BaseModel implements Predicate<JkwyPackagePlatform>,
		Updator<JkwyPackagePlatform> {


    /** 套餐id */
	@Length(max = 50, message = "{jkwyPackagePlatform.jkwyPackageId.illegal.length}")
	private String jkwyPackageId;

    /** 平台id */
	@Length(max = 50, message = "{jkwyPackagePlatform.platformId.illegal.length}")
	private String platformId;

	public JkwyPackagePlatform(){}

	public JkwyPackagePlatform(String id){
		this.id = id;
	}

	/**
	 * 获取套餐id
	 */
	public String getJkwyPackageId() {
    	return jkwyPackageId;
    }
  	
	/**
	 * 设置套餐id
	 */
	public void setJkwyPackageId(String jkwyPackageId) {
    	this.jkwyPackageId = jkwyPackageId;
    }

	/**
	 * 获取平台id
	 */
	public String getPlatformId() {
    	return platformId;
    }
  	
	/**
	 * 设置平台id
	 */
	public void setPlatformId(String platformId) {
    	this.platformId = platformId;
    }

    public String toString() {
		return new StringBuilder().append("JkwyPackagePlatform{").
			append("id=").append(id).
			append(",jkwyPackageId=").append(jkwyPackageId).
			append(",platformId=").append(platformId).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, jkwyPackageId, platformId, status
	 */
	public JkwyPackagePlatform copy(){
		JkwyPackagePlatform jkwyPackagePlatform = new JkwyPackagePlatform();
     	jkwyPackagePlatform.id = this.id;
     	jkwyPackagePlatform.jkwyPackageId = this.jkwyPackageId;
     	jkwyPackagePlatform.platformId = this.platformId;
     	jkwyPackagePlatform.status = this.status;
		return jkwyPackagePlatform;
	}
	
	/**
	 * 比较字段：
	 * id, jkwyPackageId, platformId, status
	 */
	@Override
	public boolean test(JkwyPackagePlatform t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.jkwyPackageId == null || this.jkwyPackageId.equals(t.jkwyPackageId))
				&& (this.platformId == null || this.platformId.equals(t.platformId))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(JkwyPackagePlatform element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.jkwyPackageId != null && !this.jkwyPackageId.isEmpty()) {
			element.jkwyPackageId = this.jkwyPackageId;
		}
		if (this.platformId != null && !this.platformId.isEmpty()) {
			element.platformId = this.platformId;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
