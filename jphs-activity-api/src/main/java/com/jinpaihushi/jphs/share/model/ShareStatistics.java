package com.jinpaihushi.jphs.share.model;

import java.util.Date;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * SHARE_STATISTICS 
 * 继承自父类的字段:
 * id : 	
 * type : 资源类型，1服务，2商品,3其他分类	
 * status : 0正常，-1删除	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author scj
 * @date 2017-11-02 17:09:13
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ShareStatistics extends BaseModel implements Predicate<ShareStatistics>,
		Updator<ShareStatistics> {


    /** 资源id-服务。商品 */
	@Length(max = 50, message = "{shareStatistics.goodsId.illegal.length}")
	private String goodsId;
	private String goodsName;

    /** 资源名称 */
	@Length(max = 255, message = "{shareStatistics.name.illegal.length}")
	private String name;

    /** 源资链接地址 */
	@Length(max = 500, message = "{shareStatistics.url.illegal.length}")
	private String url;

    /** 分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台 */
	private Integer sharePlatform;

    /** 1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端 */
	private Integer shareDevice;

	private Date endTime;
	private Date beginTime;
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public ShareStatistics(){}

	public ShareStatistics(String id){
		this.id = id;
	}

	/**
	 * 获取资源id-服务。商品
	 */
	public String getGoodsId() {
    	return goodsId;
    }
  	
	/**
	 * 设置资源id-服务。商品
	 */
	public void setGoodsId(String goodsId) {
    	this.goodsId = goodsId;
    }

	/**
	 * 获取资源名称
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置资源名称
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取源资链接地址
	 */
	public String getUrl() {
    	return url;
    }
  	
	/**
	 * 设置源资链接地址
	 */
	public void setUrl(String url) {
    	this.url = url;
    }

	/**
	 * 获取分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台
	 */
	public Integer getSharePlatform() {
    	return sharePlatform;
    }
  	
	/**
	 * 设置分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台
	 */
	public void setSharePlatform(Integer sharePlatform) {
    	this.sharePlatform = sharePlatform;
    }

	/**
	 * 获取1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端
	 */
	public Integer getShareDevice() {
    	return shareDevice;
    }
  	
	/**
	 * 设置1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端
	 */
	public void setShareDevice(Integer shareDevice) {
    	this.shareDevice = shareDevice;
    }

    public String toString() {
		return new StringBuilder().append("ShareStatistics{").
			append("id=").append(id).
			append(",type=").append(type).
			append(",goodsId=").append(goodsId).
			append(",name=").append(name).
			append(",url=").append(url).
			append(",sharePlatform=").append(sharePlatform).
			append(",shareDevice=").append(shareDevice).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, type, goodsId, name, 
	 * url, sharePlatform, shareDevice, status, 
	 * createTime, creatorId, creatorName
	 */
	public ShareStatistics copy(){
		ShareStatistics shareStatistics = new ShareStatistics();
     	shareStatistics.id = this.id;
     	shareStatistics.type = this.type;
     	shareStatistics.goodsId = this.goodsId;
     	shareStatistics.name = this.name;
     	shareStatistics.url = this.url;
     	shareStatistics.sharePlatform = this.sharePlatform;
     	shareStatistics.shareDevice = this.shareDevice;
     	shareStatistics.status = this.status;
     	shareStatistics.createTime = this.createTime;
     	shareStatistics.creatorId = this.creatorId;
     	shareStatistics.creatorName = this.creatorName;
		return shareStatistics;
	}
	
	/**
	 * 比较字段：
	 * id, type, goodsId, name, 
	 * url, sharePlatform, shareDevice, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(ShareStatistics t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.goodsId == null || this.goodsId.equals(t.goodsId))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.url == null || this.url.equals(t.url))
				&& (this.sharePlatform == null || this.sharePlatform.equals(t.sharePlatform))
				&& (this.shareDevice == null || this.shareDevice.equals(t.shareDevice))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(ShareStatistics element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.goodsId != null && !this.goodsId.isEmpty()) {
			element.goodsId = this.goodsId;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.url != null && !this.url.isEmpty()) {
			element.url = this.url;
		}
		if (this.sharePlatform != null) {
			element.sharePlatform = this.sharePlatform;
		}
		if (this.shareDevice != null) {
			element.shareDevice = this.shareDevice;
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
