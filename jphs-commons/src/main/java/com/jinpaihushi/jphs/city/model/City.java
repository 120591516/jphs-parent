package com.jinpaihushi.jphs.city.model;

import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * CITY 
 * 继承自父类的字段:
 * id : 	
 * type : 	
 * createTime : 	
 * creatorId : 	
 * status : 	
 * 
 * @author scj
 * @date 2017-11-13 16:28:56
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class City extends BaseModel implements Predicate<City>,
		Updator<City> {


    /**  */
	@Length(max = 50, message = "{city.name.illegal.length}")
	private String name;

    /** 经度 */
	@Length(max = 50, message = "{city.lon.illegal.length}")
	private String lon;

    /** 纬度 */
	@Length(max = 50, message = "{city.lat.illegal.length}")
	private String lat;

	public City(){}

	public City(String id){
		this.id = id;
	}

	/**
	 * 获取
	 */
	public String getName() {
    	return name;
    }
  	
	/**
	 * 设置
	 */
	public void setName(String name) {
    	this.name = name;
    }

	/**
	 * 获取经度
	 */
	public String getLon() {
    	return lon;
    }
  	
	/**
	 * 设置经度
	 */
	public void setLon(String lon) {
    	this.lon = lon;
    }

	/**
	 * 获取纬度
	 */
	public String getLat() {
    	return lat;
    }
  	
	/**
	 * 设置纬度
	 */
	public void setLat(String lat) {
    	this.lat = lat;
    }

    public String toString() {
		return new StringBuilder().append("City{").
			append("id=").append(id).
			append(",name=").append(name).
			append(",lon=").append(lon).
			append(",lat=").append(lat).
			append(",type=").append(type).
			append(",createTime=").append(createTime).
			append(",creatorId=").append(creatorId).
			append(",status=").append(status).
			append('}').toString();
    }
	
    /**
	 * 复制字段：
	 * id, name, lon, lat, 
	 * type, createTime, creatorId, status
	 */
	public City copy(){
		City city = new City();
     	city.id = this.id;
     	city.name = this.name;
     	city.lon = this.lon;
     	city.lat = this.lat;
     	city.type = this.type;
     	city.createTime = this.createTime;
     	city.creatorId = this.creatorId;
     	city.status = this.status;
		return city;
	}
	
	/**
	 * 比较字段：
	 * id, name, lon, lat, 
	 * type, createTime, creatorId, status
	 */
	@Override
	public boolean test(City t) {
		if(t == null) return false;
		return (this.id == null || this.id.equals(t.id))
				&& (this.name == null || this.name.equals(t.name))
				&& (this.lon == null || this.lon.equals(t.lon))
				&& (this.lat == null || this.lat.equals(t.lat))
				&& (this.type == null || this.type.equals(t.type))
				&& (this.createTime == null || this.createTime.equals(t.createTime))
				&& (this.creatorId == null || this.creatorId.equals(t.creatorId))
				&& (this.status == null || this.status.equals(t.status))
		;
	}
	
	@Override
	public void update(City element) {
		if (element == null)
			return;
		if (this.id != null && !this.id.isEmpty()) {
			element.id = this.id;
		}
		if (this.name != null && !this.name.isEmpty()) {
			element.name = this.name;
		}
		if (this.lon != null && !this.lon.isEmpty()) {
			element.lon = this.lon;
		}
		if (this.lat != null && !this.lat.isEmpty()) {
			element.lat = this.lat;
		}
		if (this.type != null) {
			element.type = this.type;
		}
		if (this.createTime != null) {
			element.createTime = this.createTime;
		}
		if (this.creatorId != null && !this.creatorId.isEmpty()) {
			element.creatorId = this.creatorId;
		}
		if (this.status != null) {
			element.status = this.status;
		}
	}
}
