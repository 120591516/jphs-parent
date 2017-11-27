package com.jinpaihushi.jphs.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityTypeParentDao;
import com.jinpaihushi.jphs.commodity.model.CommodityTypeParent;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeParentService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-10-20 18:20:47
 * @version 1.0
 */
@Service("commodityTypeParentService")
public class CommodityTypeParentServiceImpl extends BaseServiceImpl<CommodityTypeParent> implements CommodityTypeParentService{

	@Autowired
	private CommodityTypeParentDao commodityTypeParentDao;
	
	@Override
	protected BaseDao<CommodityTypeParent> getDao(){
		return commodityTypeParentDao;
	}

}