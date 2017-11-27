package com.jinpaihushi.jphs.price.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.dao.PriceDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.service.PriceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-08 09:34:35
 * @version 1.0
 */
@Service("priceService")
public class PriceServiceImpl extends BaseServiceImpl<Price> implements PriceService{

	@Autowired
	private PriceDao priceDao;
	
	@Override
	protected BaseDao<Price> getDao(){
		return priceDao;
	}
	
	public List<Price>  getGoodsPriceDetail(Price p){
		return priceDao.getGoodsPriceDetail(p);
	}

	/**
	 * 根据goodsid查询规格列表，只返回id,goodsID ,title
	 * @param price
	 * @return
	 */
	public List<Map<String,Object>> getIfGoodsIdPriceList(Price price){
		return priceDao.getIfGoodsIdPriceList(price);
	}
	
}