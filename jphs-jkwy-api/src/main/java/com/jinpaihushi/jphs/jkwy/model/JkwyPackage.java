package com.jinpaihushi.jphs.jkwy.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_PACKAGE 
 * 继承自父类的字段:
 * id : 	
 * status : 状态0正常，-1删除	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-22 09:42:00
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyPackage extends BaseModel implements Predicate<JkwyPackage>, Updator<JkwyPackage> {

    /** 标题 */
    @Length(max = 50, message = "{jkwyPackage.title.illegal.length}")
    private String title;

    /** 副标题 */
    @Length(max = 255, message = "{jkwyPackage.subTitle.illegal.length}")
    private String subTitle;

    /** 超文本介绍内容 */
    @Length(max = 65535, message = "{jkwyPackage.content.illegal.length}")
    private String content;

    /** 排序 */
    private Integer sort;
    /** 排序 */
    private Integer deviceType;
    private String imgUrl;
    /**
     * 平台id
     */
    private String platformId;
    
    public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	private List<JkwyPackagePrice> jkwyPackagePriceList;
    private List<JkwyRelation> jkwyRelationList;
    private ActivityPromotion activityPromotion;
    
    private List<JkwyPackagePrice> packagePrice;

    public JkwyPackage() {
    }

    public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public JkwyPackage(String id) {
        this.id = id;
    }
    
    public ActivityPromotion getActivityPromotion() {
		return activityPromotion;
	}

	public void setActivityPromotion(ActivityPromotion activityPromotion) {
		this.activityPromotion = activityPromotion;
	}

	public List<JkwyRelation> getJkwyRelationList() {
		return jkwyRelationList;
	}

	public void setJkwyRelationList(List<JkwyRelation> jkwyRelationList) {
		this.jkwyRelationList = jkwyRelationList;
	}

	public List<JkwyPackagePrice> getJkwyPackagePriceList() {
		return jkwyPackagePriceList;
	}

	public void setJkwyPackagePriceList(List<JkwyPackagePrice> jkwyPackagePriceList) {
		this.jkwyPackagePriceList = jkwyPackagePriceList;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	/**
     * 获取标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取副标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置副标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取超文本介绍内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置超文本介绍内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<JkwyPackagePrice> getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(List<JkwyPackagePrice> packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String toString() {
        return new StringBuilder().append("JkwyPackage{").append("id=").append(id).append(",title=").append(title)
                .append(",subTitle=").append(subTitle).append(",content=").append(content).append(",sort=").append(sort)
                .append(",status=").append(status).append(",createTime=").append(createTime).append(",creatorId=")
                .append(creatorId).append(",creatorName=").append(creatorName).append('}').toString();
    }

    /**
     * 复制字段：
     * id, title, subTitle, content, 
     * sort, status, createTime, creatorId, 
     * creatorName
     */
    public JkwyPackage copy() {
        JkwyPackage jkwyPackage = new JkwyPackage();
        jkwyPackage.id = this.id;
        jkwyPackage.title = this.title;
        jkwyPackage.subTitle = this.subTitle;
        jkwyPackage.content = this.content;
        jkwyPackage.sort = this.sort;
        jkwyPackage.status = this.status;
        jkwyPackage.createTime = this.createTime;
        jkwyPackage.creatorId = this.creatorId;
        jkwyPackage.creatorName = this.creatorName;
        return jkwyPackage;
    }

    /**
     * 比较字段：
     * id, title, subTitle, content, 
     * sort, status, createTime, creatorId, 
     * creatorName
     */
    @Override
    public boolean test(JkwyPackage t) {
        if (t == null)
            return false;
        return (this.id == null || this.id.equals(t.id)) && (this.title == null || this.title.equals(t.title))
                && (this.subTitle == null || this.subTitle.equals(t.subTitle))
                && (this.content == null || this.content.equals(t.content))
                && (this.sort == null || this.sort.equals(t.sort))
                && (this.status == null || this.status.equals(t.status))
                && (this.createTime == null || this.createTime.equals(t.createTime))
                && (this.creatorId == null || this.creatorId.equals(t.creatorId))
                && (this.creatorName == null || this.creatorName.equals(t.creatorName));
    }

    @Override
    public void update(JkwyPackage element) {
        if (element == null)
            return;
        if (this.id != null && !this.id.isEmpty()) {
            element.id = this.id;
        }
        if (this.title != null && !this.title.isEmpty()) {
            element.title = this.title;
        }
        if (this.subTitle != null && !this.subTitle.isEmpty()) {
            element.subTitle = this.subTitle;
        }
        if (this.content != null && !this.content.isEmpty()) {
            element.content = this.content;
        }
        if (this.sort != null) {
            element.sort = this.sort;
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
