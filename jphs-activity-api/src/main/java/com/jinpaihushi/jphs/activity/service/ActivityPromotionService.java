package com.jinpaihushi.jphs.activity.service;

import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-10-30 14:25:22
 * @version 1.0
 */
public interface ActivityPromotionService extends BaseService<ActivityPromotion> {

	/**
	 * 业务，查询详情
	 * @param id
	 * @return
	 */
	ActivityPromotion loadByIds(String id);

	/**
	 * 查询立减
	 * @return
	 */
	ActivityPromotion ifPromotion(String userId,String platformId,String goodsId,String priceId,int resourceType);
	
}