package com.jinpaihushi.jphs.platform.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_USER_ROLE 
 * 继承自父类的字段:
 * id : 	
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformUserRole extends BaseModel implements Predicate<PlatformUserRole>,
		Updator<PlatformUserRole> {


    /** 平台用户id */
	@Length(max = 50, message = "{platformUserRole.platformUserId.illegal.length}")
	private String platformUserId;

    /** 平台角色id */
	@Length(max = 50, message = "{platformUserRole.platformRoleId.illegal.length}")
	private String platformRoleId;

	public PlatformUserRole(){}

	public PlatformUserRole(String id){
		this.id = id;
	}

	/**
	 * 获取平台用户id
	 */
	public String getPlatformUserId() {
    	return platformUserId;
    }
  	
	/**
	 * 设置平台用户id
	 */
	public void setPlatformUserId(String platformUserId) {
    	this.platformUserId = platformUserId;
    }

	/**
	 * 获取平台角色id
	 */
	public String getPlatformRoleId() {
    	return platformRoleId;
    }
  	
	/**
	 * 设置平台角色id
	 */
	public void setPlatformRoleId(String platformRoleId) {
    	this.platformRoleId = platformRoleId;
    }

    public String toString() {
		return new StringBuilder().append("PlatformUserRole{").
			append("id=").append(id).
			append(",platformUserId=").append(platformUserId).
			append(",platformRoleId=").append(platformRoleId).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, platformUserId, platformRoleId
	 */
	public PlatformUserRole copy(){
		PlatformUserRole platformUserRole = new PlatformUserRole();
     	platformUserRole.id = this.id;
     	platformUserRole.platformUserId = this.platformUserId;
     	platformUserRole.platformRoleId = this.platformRoleId;
		return platformUserRole;
	}
	
	/**
	 * 比较字段：
	 * id, platformUserId, platformRoleId
	 */
	@Override
	public boolean test(PlatformUserRole t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.platformUserId == null || this.platformUserId.equals(t.platformUserId))
				&& (this.platformRoleId == null || this.platformRoleId.equals(t.platformRoleId))
		;
	}
	
	@Override
	public void update(PlatformUserRole element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.platformUserId != null && !this.platformUserId.isEmpty()) {
			element.platformUserId = this.platformUserId;
		}
		if (this.platformRoleId != null && !this.platformRoleId.isEmpty()) {
			element.platformRoleId = this.platformRoleId;
		}
	}
}
