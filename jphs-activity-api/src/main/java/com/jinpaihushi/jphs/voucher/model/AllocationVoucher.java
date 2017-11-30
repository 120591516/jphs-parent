package com.jinpaihushi.jphs.voucher.model;

/**
 * 分配优惠券实体类
 * @author Administrator
 *
 */
public class AllocationVoucher {

	/**
	 * 批次号
	 */
	private String batchNo;
	
	/**
	 * 发放数量
	 */
	private Integer number;
	
	/**
	 * 发放方式，	1：走原始数据，从仓库表取出开始时间，结束时间存放在用户优惠券表
	 * 			2：按照当前时间加一定天数决定结束时间
	 */
	private Integer type;
	
	/**
	 * 叠加天数
	 */
	private Integer day;

	private Integer ifRepeat;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getIfRepeat() {
		return ifRepeat;
	}

	public void setIfRepeat(Integer ifRepeat) {
		this.ifRepeat = ifRepeat;
	}

	@Override
	public String toString() {
		return "AllocationVoucher [batchNo=" + batchNo + ", number=" + number + ", type=" + type + ", day=" + day
				+ ", ifRepeat=" + ifRepeat + "]";
	}
	
}
