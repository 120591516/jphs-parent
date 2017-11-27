package com.jinpaihushi.jphs.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommoditySaleByNurse;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Repository("commodityOrderInfoDao")
public interface CommodityOrderInfoDao extends BaseDao<CommodityOrderInfo> {

	Integer updateByOrderNo(CommodityOrderInfo commodityOrderInfo);
	
	Integer getAllNumber(String id);
	
	Integer getAllNumberByCommoditById(CommodityOrderInfo commodityOrderInfo);
	
	Double getMoneyByNurse(Map<String, Object> map);

	List<CommodityOrderInfo> getListByCoId(String coId);

	List<CommodityOrderInfo> judgeProfit(CommodityOrderInfo commodityOrderInfo);

	Integer confimOrder(String id);
	/**
	 * 根据订单ID查询订单商品
	 * @param coId
	 * @return
	 */
	List<CommodityOrderInfo> getList(String coId);
	
	List<CommodityOrderInfo> getOrderInfo(String coId);
	
	List<CommodityOrderInfo> getConfirmList(String comId);
	
	Integer getCountByOrderNo(String orderNo);

	List<CommoditySaleByNurse> alreadyRefund(CommodityOrderInfo commodityOrderInfo);

	List<CommoditySaleByNurse> alreadyBalance(CommodityOrderInfo commodityOrderInfo);

	List<CommoditySaleByNurse> turnOver(CommodityOrderInfo commodityOrderInfo);

	List<CommoditySaleByNurse> all(CommodityOrderInfo commodityOrderInfo);
}
