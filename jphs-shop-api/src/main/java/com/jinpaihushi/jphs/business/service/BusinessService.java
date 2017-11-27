package com.jinpaihushi.jphs.business.service;

import java.util.List;

import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.commodity.model.CommodityTreeNode;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:28:55
 * @version 1.0
 */
public interface BusinessService extends BaseService<Business> {

	/**
	 * 获取商家树
	 * @param business
	 * @return
	 */
	List<CommodityTreeNode> getTreeNode(Business business);

}