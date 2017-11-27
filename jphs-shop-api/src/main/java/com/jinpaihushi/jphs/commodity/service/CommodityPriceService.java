package com.jinpaihushi.jphs.commodity.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:20:37
 * @version 1.0
 */
public interface CommodityPriceService extends BaseService<CommodityPrice> {

	/**
	 * 查询-
	 * @param commodityPrice
	 * @return
	 */
	List<Map<String,Object>> commodityPriceAllList(CommodityPrice commodityPrice);

}