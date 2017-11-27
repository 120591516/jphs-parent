package com.jinpaihushi.jphs.platform.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_ROLE 
 * 继承自父类的字段:
 * id : 	
 * status : 角色状态	
 * createTime : 创建时间	
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformRole extends BaseModel implements Predicate<PlatformRole>, Updator<PlatformRole> {

    /** 平台id */
    @Length(max = 50, message = "{platformRole.platformId.illegal.length}")
    private String platformId;

    /** 角色名称 */
    @NotEmpty(message = "{platformRole.name.empty}")
    @Length(max = 20, message = "{platformRole.name.illegal.length}")
    private String name;

    /** 角色描述 */
    @Length(max = 200, message = "{platformRole.describe.illegal.length}")
    private String describe;

    private Boolean checked = false;

    private List<PlatformModel> moduleList;

    public PlatformRole() {
    }

    public PlatformRole(String id) {
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
     * 获取角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置角色描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<PlatformModel> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<PlatformModel> moduleList) {
        this.moduleList = moduleList;
    }

    public String toString() {
        return new StringBuilder().append("PlatformRole{").append("id=").append(id).append(",platformId=")
                .append(platformId).append(",name=").append(name).append(",describe=").append(describe)
                .append(",status=").append(status).append(",createTime=").append(createTime).append('}').toString();
    }

    /**
     * 复制字段：
     * id, platformId, name, describe, 
     * status, createTime
     */
    public PlatformRole copy() {
        PlatformRole platformRole = new PlatformRole();
        platformRole.id = this.id;
        platformRole.platformId = this.platformId;
        platformRole.name = this.name;
        platformRole.describe = this.describe;
        platformRole.status = this.status;
        platformRole.createTime = this.createTime;
        return platformRole;
    }

    /**
     * 比较字段：
     * id, platformId, name, describe, 
     * status, createTime
     */
    @Override
    public boolean test(PlatformRole t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id))
                && (this.platformId == null || this.platformId.equals(t.platformId))
                && (this.name == null || this.name.equals(t.name))
                && (this.describe == null || this.describe.equals(t.describe))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime));
    }

    @Override
    public void update(PlatformRole element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.platformId != null && !this.platformId.isEmpty()) {
            element.platformId = this.platformId;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.describe != null && !this.describe.isEmpty()) {
            element.describe = this.describe;
        }
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
    }
}
