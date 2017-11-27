package com.jinpaihushi.jphs.commodity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:20:37
 * @version 1.0
 */
@Repository("commodityPriceDao")
public interface CommodityPriceDao extends BaseDao<CommodityPrice> {

	Integer getCount(String commodityId);
	
	/**
	 * 查询-
	 * @param commodityPrice
	 * @return
	 */
	List<Map<String,Object>> commodityPriceAllList(CommodityPrice commodityPrice);
	
}
