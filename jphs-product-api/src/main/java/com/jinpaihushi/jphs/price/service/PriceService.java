package com.jinpaihushi.jphs.price.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @version 1.0
 */
public interface PriceService extends BaseService<Price> {

	List<Price>  getGoodsPriceDetail(Price price);
	
	/**
	 * 根据goodsid查询规格列表，只返回id,goodsID ,title
	 * @param price
	 * @return
	 */
	List<Map<String,Object>> getIfGoodsIdPriceList(Price price);
	
}