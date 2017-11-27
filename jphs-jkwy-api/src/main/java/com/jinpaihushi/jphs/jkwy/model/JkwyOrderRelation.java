package com.jinpaihushi.jphs.jkwy.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_ORDER_RELATION 
 * 继承自父类的字段:
 * id : 	
 * status : 状态(0正常，-1删除)	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class JkwyOrderRelation extends BaseModel implements Predicate<JkwyOrderRelation>,
		Updator<JkwyOrderRelation> {


    /** 订单id */
	@Length(max = 50, message = "{jkwyOrderRelation.jkwyOrderId.illegal.length}")
	private String jkwyOrderId;

    /** 亲友关系id */
	@Length(max = 50, message = "{jkwyOrderRelation.jkwyRelation.illegal.length}")
	private String jkwyRelation;

	public JkwyOrderRelation(){}

	public JkwyOrderRelation(String id){
		this.id = id;
	}

	/**
	 * 获取订单id
	 */
	public String getJkwyOrderId() {
    	return jkwyOrderId;
    }
  	
	/**
	 * 设置订单id
	 */
	public void setJkwyOrderId(String jkwyOrderId) {
    	this.jkwyOrderId = jkwyOrderId;
    }

	/**
	 * 获取亲友关系id
	 */
	public String getJkwyRelation() {
    	return jkwyRelation;
    }
  	
	/**
	 * 设置亲友关系id
	 */
	public void setJkwyRelation(String jkwyRelation) {
    	this.jkwyRelation = jkwyRelation;
    }

    public String toString() {
		return new StringBuilder().append("JkwyOrderRelation{").
			append("id=").append(id).
			append(",jkwyOrderId=").append(jkwyOrderId).
			append(",jkwyRelation=").append(jkwyRelation).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, jkwyOrderId, jkwyRelation, status, 
	 * createTime, creatorId, creatorName
	 */
	public JkwyOrderRelation copy(){
		JkwyOrderRelation jkwyOrderRelation = new JkwyOrderRelation();
     	jkwyOrderRelation.id = this.id;
     	jkwyOrderRelation.jkwyOrderId = this.jkwyOrderId;
     	jkwyOrderRelation.jkwyRelation = this.jkwyRelation;
     	jkwyOrderRelation.status = this.status;
     	jkwyOrderRelation.createTime = this.createTime;
     	jkwyOrderRelation.creatorId = this.creatorId;
     	jkwyOrderRelation.creatorName = this.creatorName;
		return jkwyOrderRelation;
	}
	
	/**
	 * 比较字段：
	 * id, jkwyOrderId, jkwyRelation, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(JkwyOrderRelation t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.jkwyOrderId == null || this.jkwyOrderId.equals(t.jkwyOrderId))
				&& (this.jkwyRelation == null || this.jkwyRelation.equals(t.jkwyRelation))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(JkwyOrderRelation element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.jkwyOrderId != null && !this.jkwyOrderId.isEmpty()) {
			element.jkwyOrderId = this.jkwyOrderId;
		}
		if (this.jkwyRelation != null && !this.jkwyRelation.isEmpty()) {
			element.jkwyRelation = this.jkwyRelation;
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
