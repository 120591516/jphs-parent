package com.jinpaihushi.jphs.jkwy.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * JKWY_RELATION 
 * 继承自父类的字段:
 * id : 	
 * status : 态状（0正常，-1删除）	
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
public class JkwyRelation extends BaseModel implements Predicate<JkwyRelation>,
		Updator<JkwyRelation> {


    /** 姓名 */
	@Length(max = 50, message = "{jkwyRelation.name.illegal.length}")
	private String name;

    /** 电话 */
	@Length(max = 50, message = "{jkwyRelation.phone.illegal.length}")
	private String phone;

    /** 性别 */
	private Integer sex;

    /** 关系 */
	@Length(max = 50, message = "{jkwyRelation.relation.illegal.length}")
	private String relation;

    /** 身份证 */
	@Length(max = 50, message = "{jkwyRelation.sfz.illegal.length}")
	private String sfz;

    /** 出生日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	private List<JkwyOrder> jkwyOrderList;

	private int ifOrder;
	private String jpTitle;
	private String jppTitle;
	
	public JkwyRelation(){}

	public int getIfOrder() {
		return ifOrder;
	}

	public void setIfOrder(int ifOrder) {
		this.ifOrder = ifOrder;
	}

	public String getJpTitle() {
		return jpTitle;
	}

	public void setJpTitle(String jpTitle) {
		this.jpTitle = jpTitle;
	}

	public String getJppTitle() {
		return jppTitle;
	}

	public void setJppTitle(String jppTitle) {
		this.jppTitle = jppTitle;
	}

	public JkwyRelation(String id){
		this.id = id;
	}

	public List<JkwyOrder> getJkwyOrderList() {
		return jkwyOrderList;
	}

	public void setJkwyOrderList(List<JkwyOrder> jkwyOrderList) {
		this.jkwyOrderList = jkwyOrderList;
	}

	/**
	 * 获取姓名
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置姓名
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取电话
	 */
	public String getPhone() {
    	return phone;
    }
  	
	/**
	 * 设置电话
	 */
	public void setPhone(String phone) {
    	this.phone = phone;
    }

	/**
	 * 获取性别
	 */
	public Integer getSex() {
    	return sex;
    }
  	
	/**
	 * 设置性别
	 */
	public void setSex(Integer sex) {
    	this.sex = sex;
    }

	/**
	 * 获取关系
	 */
	public String getRelation() {
    	return relation;
    }
  	
	/**
	 * 设置关系
	 */
	public void setRelation(String relation) {
    	this.relation = relation;
    }

	/**
	 * 获取身份证
	 */
	public String getSfz() {
    	return sfz;
    }
  	
	/**
	 * 设置身份证
	 */
	public void setSfz(String sfz) {
    	this.sfz = sfz;
    }

	/**
	 * 获取出生日期
	 */
	public Date getBirthday() {
    	return birthday;
    }
  	
	/**
	 * 设置出生日期
	 */
	public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }

    public String toString() {
		return new StringBuilder().append("JkwyRelation{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",phone=").append(phone).
			append(",sex=").append(sex).
			append(",relation=").append(relation).
			append(",sfz=").append(sfz).
			append(",birthday=").append(birthday).
			append(",status=").append(status).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",creatorName=").append(creatorName).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, phone, sex, 
	 * relation, sfz, birthday, status, 
	 * createTime, creatorId, creatorName
	 */
	public JkwyRelation copy(){
		JkwyRelation jkwyRelation = new JkwyRelation();
     	jkwyRelation.id = this.id;
     	jkwyRelation.name = this.name;
     	jkwyRelation.phone = this.phone;
     	jkwyRelation.sex = this.sex;
     	jkwyRelation.relation = this.relation;
     	jkwyRelation.sfz = this.sfz;
     	jkwyRelation.birthday = this.birthday;
     	jkwyRelation.status = this.status;
     	jkwyRelation.createTime = this.createTime;
     	jkwyRelation.creatorId = this.creatorId;
     	jkwyRelation.creatorName = this.creatorName;
		return jkwyRelation;
	}
	
	/**
	 * 比较字段：
	 * id, name, phone, sex, 
	 * relation, sfz, birthday, status, 
	 * createTime, creatorId, creatorName
	 */
	@Override
	public boolean test(JkwyRelation t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.phone == null || this.phone.equals(t.phone))
				&& (this.sex == null || this.sex.equals(t.sex))
				&& (this.relation == null || this.relation.equals(t.relation))
				&& (this.sfz == null || this.sfz.equals(t.sfz))
				&& (this.birthday == null || this.birthday.equals(t.birthday))
				&& (this.status == null || this.status.equals(t.status))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.creatorName == null || this.creatorName.equals(t.creatorName))
		;
	}
	
	@Override
	public void update(JkwyRelation element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.phone != null && !this.phone.isEmpty()) {
			element.phone = this.phone;
		}
		if (this.sex != null) {
			element.sex = this.sex;
		}
		if (this.relation != null && !this.relation.isEmpty()) {
			element.relation = this.relation;
		}
		if (this.sfz != null && !this.sfz.isEmpty()) {
			element.sfz = this.sfz;
		}
		if (this.birthday != null) {
			element.birthday = this.birthday;
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
