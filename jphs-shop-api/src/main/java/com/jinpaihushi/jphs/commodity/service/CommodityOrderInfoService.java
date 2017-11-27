package com.jinpaihushi.jphs.commodity.service;

import java.util.List;

import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommoditySaleByNurse;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
public interface CommodityOrderInfoService extends BaseService<CommodityOrderInfo> {

	Integer updateByOrderNo(CommodityOrderInfo commodityOrderInfo);
	
	List<CommodityOrderInfo> getListByCoId(String coId);
	/**
	 * 
	 * @param commodityOrderInfo
	 * @return
	 * 获取护士 销售商品的订单
	 */
	List<CommoditySaleByNurse> judgeProfit(CommodityOrderInfo commodityOrderInfo);
	
	Integer confimOrder(String comId);
	
	List<CommodityOrderInfo> getList(String coId);
	
	Integer getCountByOrderNo(String orderNo);
	
}