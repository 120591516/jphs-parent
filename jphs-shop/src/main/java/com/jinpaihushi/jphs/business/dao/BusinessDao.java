package com.jinpaihushi.jphs.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.commodity.model.CommodityTreeNode;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:44:48
 * @version 1.0
 */
@Repository("businessDao")
public interface BusinessDao extends BaseDao<Business> {

	List<CommodityTreeNode> getTreeNode(Business business);
	
	
	
}
