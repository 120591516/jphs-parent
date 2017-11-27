package com.jinpaihushi.jphs.platform.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_USER 平台管理用户表
 * 继承自父类的字段:
 * id : 	
 * createTime : 创建时间	
 * creatorId : 创建人id	
 * creatorName : 创建人姓名	
 * status : 状态（1、正常 0、冻结 (冻结账户不可登录)、-1删除）	
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformUser extends BaseModel implements Predicate<PlatformUser>, Updator<PlatformUser> {

    /** 平台id */
    @NotEmpty(message = "{platformUser.platformId.empty}")
    @Length(max = 50, message = "{platformUser.platformId.illegal.length}")
    private String platformId;

    /** 登录名 */
    @NotEmpty(message = "{platformUser.loginName.empty}")
    @Length(max = 50, message = "{platformUser.loginName.illegal.length}")
    private String loginName;

    /** 登录密码 */
    @NotEmpty(message = "{platformUser.loginPwd.empty}")
    @Length(max = 50, message = "{platformUser.loginPwd.illegal.length}")
    private String loginPwd;

    /** 用户真实姓名 */
    @NotEmpty(message = "{platformUser.userName.empty}")
    @Length(max = 50, message = "{platformUser.userName.illegal.length}")
    private String userName;

    /** 用户手机号 */
    @NotEmpty(message = "{platformUser.userPhone.empty}")
    @Length(max = 50, message = "{platformUser.userPhone.illegal.length}")
    private String userPhone;

    /** 用户邮箱 */
    @NotEmpty(message = "{platformUser.userEmail.empty}")
    @Length(max = 50, message = "{platformUser.userEmail.illegal.length}")
    private String userEmail;

    /** 登录时间 */
    @NotNull(message = "{platformUser.userLoginTime.null}")
    private Date userLoginTime;

    /** 上次登录时间 */
    @NotNull(message = "{platformUser.userLastTime.null}")
    private Date userLastTime;

    private List<PlatformRole> roleList;

    /** 用户权限 */
    private List<String> privilegeList;

    public PlatformUser() {
    }

    public PlatformUser(String id) {
        this.id = id;
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

    /**
     * 获取登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取登录密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置登录密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 获取用户真实姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户真实姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置用户手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取登录时间
     */
    public Date getUserLoginTime() {
        return userLoginTime;
    }

    /**
     * 设置登录时间
     */
    public void setUserLoginTime(Date userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    /**
     * 获取上次登录时间
     */
    public Date getUserLastTime() {
        return userLastTime;
    }

    /**
     * 设置上次登录时间
     */
    public void setUserLastTime(Date userLastTime) {
        this.userLastTime = userLastTime;
    }

    public List<PlatformRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<PlatformRole> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public String toString() {
        return new StringBuilder().append("PlatformUser{").append("id=").append(id).append(",platformId=")
                .append(platformId).append(",loginName=").append(loginName).append(",loginPwd=").append(loginPwd)
                .append(",userName=").append(userName).append(",userPhone=").append(userPhone).append(",userEmail=")
                .append(userEmail).append(",createTime=").append(createTime).append(",creatorId=").append(creatorId)
                .append(",creatorName=").append(creatorName).append(",status=").append(status).append(",userLoginTime=")
                .append(userLoginTime).append(",userLastTime=").append(userLastTime).append('}').toString();
    }

    /**
     * 复制字段：
     * id, platformId, loginName, loginPwd, 
     * userName, userPhone, userEmail, createTime, 
     * creatorId, creatorName, status, userLoginTime, 
     * userLastTime
     */
    public PlatformUser copy() {
        PlatformUser platformUser = new PlatformUser();
        platformUser.id = this.id;
        platformUser.platformId = this.platformId;
        platformUser.loginName = this.loginName;
        platformUser.loginPwd = this.loginPwd;
        platformUser.userName = this.userName;
        platformUser.userPhone = this.userPhone;
        platformUser.userEmail = this.userEmail;
        platformUser.createTime = this.createTime;
        platformUser.creatorId = this.creatorId;
        platformUser.creatorName = this.creatorName;
        platformUser.status = this.status;
        platformUser.userLoginTime = this.userLoginTime;
        platformUser.userLastTime = this.userLastTime;
        return platformUser;
    }

    /**
     * 比较字段：
     * id, platformId, loginName, loginPwd, 
     * userName, userPhone, userEmail, createTime, 
     * creatorId, creatorName, status, userLoginTime, 
     * userLastTime
     */
    @Override
    public boolean test(PlatformUser t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.platformId == null || this.platformId.equals(t.platformId))
                && (this.loginName == null || this.loginName.equals(t.loginName))
                && (this.loginPwd == null || this.loginPwd.equals(t.loginPwd))
                && (this.userName == null || this.userName.equals(t.userName))
                && (this.userPhone == null || this.userPhone.equals(t.userPhone))
                && (this.userEmail == null || this.userEmail.equals(t.userEmail))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName))
                && (this.status == null || this.status.equals(t.status))
                && (this.userLoginTime == null || this.userLoginTime.equals(t.userLoginTime))
                && (this.userLastTime == null || this.userLastTime.equals(t.userLastTime));
    }

    @Override
    public void update(PlatformUser element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
        if (this.loginName != null && !this.loginName.isEmpty()) {
            element.loginName = this.loginName;
        }
        if (this.loginPwd != null && !this.loginPwd.isEmpty()) {
            element.loginPwd = this.loginPwd;
        }
        if (this.userName != null && !this.userName.isEmpty()) {
            element.userName = this.userName;
        }
        if (this.userPhone != null && !this.userPhone.isEmpty()) {
            element.userPhone = this.userPhone;
        }
        if (this.userEmail != null && !this.userEmail.isEmpty()) {
            element.userEmail = this.userEmail;
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
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.userLoginTime != null) {
            element.userLoginTime = this.userLoginTime;
        }
        if (this.userLastTime != null) {
            element.userLastTime = this.userLastTime;
        }
    }
}
