package com.jinpaihushi.jphs.commodity.model;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.validator.constraints.Length;

import com.jinpaihushi.function.Updator;
import com.jinpaihushi.model.BaseModel;

/**
 * COMMODITY 
 * 继承自父类的字段:
 * id : 	
 * status : -1下架，0待上架，1已上架	
 * createTime : 	
 * creatorId : 	
 * creatorName : 	
 * 
 * @author yangsong
 * @date 2017-08-09 11:50:37
 * @company jinpaihushi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CommoditySaleByNurse extends BaseModel { 
	
	private String id;
	
	private String comId;
	
	private int status;
	
	private double profit;
	
	private double price;
	
	private String title;
	
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
}
