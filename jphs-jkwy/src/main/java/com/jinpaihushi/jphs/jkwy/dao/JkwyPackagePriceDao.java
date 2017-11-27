package com.jinpaihushi.jphs.jkwy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Repository("jkwyPackagePriceDao")
public interface JkwyPackagePriceDao extends BaseDao<JkwyPackagePrice> {
	
	/**
	 * 根据业务需求，根据套餐规格查询购买订单列表
	 * @param jkwyOrder
	 * @return
	 */
	List<JkwyPackagePrice> getHealthyArchives(JkwyOrder jkwyOrder);
	
}
