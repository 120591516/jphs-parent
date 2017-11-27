package com.jinpaihushi.jphs.commodity.model;

import java.util.function.Predicate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY_TYPE_PARENT 
 * 继承自父类的字段:
 * id : 	
 * status : 	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-10-23 10:01:41
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommodityTypeParent extends BaseModel implements Predicate<CommodityTypeParent>,
		Updator<CommodityTypeParent> {


    /** 名称 */
	@Length(max = 50, message = "{commodityTypeParent.name.illegal.length}")
	private String name;

    /** 排序 */
	private Integer sort;

    /** 备注 */
	@Length(max = 255, message = "{commodityTypeParent.remark.illegal.length}")
	private String remark;
	
	private int count;

	public CommodityTypeParent(){}

	public CommodityTypeParent(String id){
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

	/**
	 * 获取备注
	 */
	public String getRemark() {
    	return remark;
    }
  	
	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }

	
	
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String toString() {
		return new StringBuilder().append("CommodityTypeParent{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",sort=").append(sort).
			append(",remark=").append(remark).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, sort, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	public CommodityTypeParent copy(){
		CommodityTypeParent commodityTypeParent = new CommodityTypeParent();
     	commodityTypeParent.id = this.id;
     	commodityTypeParent.name = this.name;
     	commodityTypeParent.sort = this.sort;
     	commodityTypeParent.remark = this.remark;
     	commodityTypeParent.status = this.status;
     	commodityTypeParent.createTime = this.createTime;
     	commodityTypeParent.creatorId = this.creatorId;
     	commodityTypeParent.creatorName = this.creatorName;
		return commodityTypeParent;
	}
	
	/**
	 * 比较字段：
	 * id, name, sort, remark, 
	 * status, createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(CommodityTypeParent t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.sort == null || this.sort.equals(t.sort))
				&& (this.remark == null || this.remark.equals(t.remark))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(CommodityTypeParent element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.sort != null) {
			element.sort = this.sort;
		}
		if (this.remark != null && !this.remark.isEmpty()) {
			element.remark = this.remark;
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
