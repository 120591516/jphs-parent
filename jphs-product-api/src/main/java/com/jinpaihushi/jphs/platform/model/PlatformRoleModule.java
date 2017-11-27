package com.jinpaihushi.jphs.platform.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_ROLE_MODULE 角色权限中间表
 * 继承自父类的字段:
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformRoleModule extends BaseModel implements Predicate<PlatformRoleModule>,
		Updator<PlatformRoleModule> {


    /**  */
	@NotEmpty(message = "{platformRoleModule.platformRoleId.empty}")
	@Length(max = 50, message = "{platformRoleModule.platformRoleId.illegal.length}")
	private String platformRoleId;

    /**  */
	@NotEmpty(message = "{platformRoleModule.platformModuleId.empty}")
	@Length(max = 255, message = "{platformRoleModule.platformModuleId.illegal.length}")
	private String platformModuleId;

	public PlatformRoleModule(){}


	/**
	 * 获取
	 */
	public String getPlatformRoleId() {
    	return platformRoleId;
    }
  	
	/**
	 * 设置
	 */
	public void setPlatformRoleId(String platformRoleId) {
    	this.platformRoleId = platformRoleId;
    }

	/**
	 * 获取
	 */
	public String getPlatformModuleId() {
    	return platformModuleId;
    }
  	
	/**
	 * 设置
	 */
	public void setPlatformModuleId(String platformModuleId) {
    	this.platformModuleId = platformModuleId;
    }

    public String toString() {
		return new StringBuilder().append("PlatformRoleModule{").
			append("platformRoleId=").append(platformRoleId).
			append(",platformModuleId=").append(platformModuleId).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * platformRoleId, platformModuleId
	 */
	public PlatformRoleModule copy(){
		PlatformRoleModule platformRoleModule = new PlatformRoleModule();
     	platformRoleModule.platformRoleId = this.platformRoleId;
     	platformRoleModule.platformModuleId = this.platformModuleId;
		return platformRoleModule;
	}
	
	/**
	 * 比较字段：
	 * platformRoleId, platformModuleId
	 */
	@Override
	public boolean test(PlatformRoleModule t) {
		if(t == null) return false;
		return (this.platformRoleId == null || this.platformRoleId.equals(t.platformRoleId))
				&& (this.platformModuleId == null || this.platformModuleId.equals(t.platformModuleId))
		;
	}
	
	@Override
	public void update(PlatformRoleModule element) {
		if (element == null)
			return;
		if (this.platformRoleId != null && !this.platformRoleId.isEmpty()) {
			element.platformRoleId = this.platformRoleId;
		}
		if (this.platformModuleId != null && !this.platformModuleId.isEmpty()) {
			element.platformModuleId = this.platformModuleId;
		}
	}
}
