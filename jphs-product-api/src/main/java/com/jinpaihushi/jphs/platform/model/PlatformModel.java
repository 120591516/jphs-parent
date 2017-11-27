package com.jinpaihushi.jphs.platform.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * PLATFORM_MODEL 
 * 继承自父类的字段:
 * id : 	
 * status : 状态	
 * createTime : 	
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlatformModel extends BaseModel implements Predicate<PlatformModel>, Updator<PlatformModel> {

    /** 名称 */
    @NotEmpty(message = "{platformModel.name.empty}")
    @Length(max = 50, message = "{platformModel.name.illegal.length}")
    private String name;

    /** 链接地址 */
    @NotEmpty(message = "{platformModel.url.empty}")
    @Length(max = 200, message = "{platformModel.url.illegal.length}")
    private String url;

    /** 上级菜单ID */
    @NotEmpty(message = "{platformModel.parentId.empty}")
    @Length(max = 50, message = "{platformModel.parentId.illegal.length}")
    private String parentId;

    private String text;

    private String state = "open";

    private Boolean checked = false;

    private List<PlatformModel> children;

    public PlatformModel() {
    }

    public PlatformModel(String id) {
        this.id = id;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
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
     * 获取上级菜单ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级菜单ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<PlatformModel> getChildren() {
        return children;
    }

    public void setChildren(List<PlatformModel> children) {
        this.children = children;
    }

    public String toString() {
        return new StringBuilder().append("PlatformModel{").append("id=").append(id).append(",name=").append(name)
                .append(",status=").append(status).append(",url=").append(url).append(",parentId=").append(parentId)
                .append(",createTime=").append(createTime).append('}').toString();
    }

    /**
     * 复制字段：
     * id, name, status, url, 
     * parentId, createTime
     */
    public PlatformModel copy() {
        PlatformModel platformModel = new PlatformModel();
        platformModel.id = this.id;
        platformModel.name = this.name;
        platformModel.status = this.status;
        platformModel.url = this.url;
        platformModel.parentId = this.parentId;
        platformModel.createTime = this.createTime;
        return platformModel;
    }

    /**
     * 比较字段：
     * id, name, status, url, 
     * parentId, createTime
     */
    @Override
    public boolean test(PlatformModel t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.name == null || this.name.equals(t.name))
                && (this.status == null || this.status.equals(t.status)) && (this.url == null || this.url.equals(t.url))
                && (this.parentId == null || this.parentId.equals(t.parentId))
                && (this.createTime == null || this.createTime.equals(t.createTime));
    }

    @Override
    public void update(PlatformModel element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.name != null && !this.name.isEmpty()) {
            element.name = this.name;
        }
        if (this.status != null) {
            element.status = this.status;
        }
        if (this.url != null && !this.url.isEmpty()) {
            element.url = this.url;
        }
        if (this.parentId != null && !this.parentId.isEmpty()) {
            element.parentId = this.parentId;
        }
        if (this.createTime != null) {
            element.createTime = this.createTime;
        }
    }
}
