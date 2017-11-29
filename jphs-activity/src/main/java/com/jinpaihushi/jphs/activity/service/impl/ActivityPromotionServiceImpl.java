package com.jinpaihushi.jphs.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.ActivityPromotionDao;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.activity.service.ActivityPromotionService;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.price.dao.PriceDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-10-30 14:25:22
 * @version 1.0
 */
@Service("activityPromotionService")
public class ActivityPromotionServiceImpl extends BaseServiceImpl<ActivityPromotion> implements ActivityPromotionService{

	@Autowired
	private ActivityPromotionDao activityPromotionDao;
	@Autowired
	private PriceDao priceDao;
	
	@Autowired
	private CommodityPriceDao commodityPriceDao;

	@Override
	protected BaseDao<ActivityPromotion> getDao(){
		return activityPromotionDao;
	}
	
	/**
	 * 业务，查询详情
	 * @param id
	 * @return
	 */
	public ActivityPromotion loadByIds(String id){
		ActivityPromotion activityPromotion = activityPromotionDao.loadById(id);
		String [] p = activityPromotion.getPriceId().split(",");
		String priceName = "";
		if(activityPromotion.getResourceType() == 1){
			for(int a =0;a<p.length;a++){
				Price price = priceDao.loadById(p[a]);
				priceName+=price.getTitle()+"<br>";
			}
		}else{
			for(int a =0;a<p.length;a++){
				CommodityPrice price = commodityPriceDao.loadById(p[a]);
				priceName+=price.getName()+"<br>";
			}
		}
		activityPromotion.setPriceId(priceName);
		return activityPromotion;
	}

	/**
	 * 查询立减
	 * @return
	 */
	public ActivityPromotion ifPromotion(String userId,String platformId,String goodsId,String priceId,int resourceType){
		ActivityPromotion activityPromotion = new ActivityPromotion();
		activityPromotion.setResourceId(goodsId);
		activityPromotion.setPlatformId(platformId);
		activityPromotion.setPriceId(priceId);
		activityPromotion.setResourceType(resourceType);
		ActivityPromotion activityPromotionOne = activityPromotionDao.getActivityForGoods(activityPromotion);
		if(activityPromotionOne == null ){
			return null;
		}
		if(activityPromotionOne.getType() == 1){
			return activityPromotionOne;
		}else{
			int onumber = 0;
			if(resourceType == 1){
				onumber = activityPromotionDao.getIndexNumberP(activityPromotionOne.getId(), userId, activityPromotionOne.getBeginTime(), activityPromotionOne.getBeginTime());
			} else if (resourceType == 2){
				onumber = activityPromotionDao.getIndexNumberCommodityOrder(activityPromotionOne.getId(), userId, activityPromotionOne.getBeginTime(), activityPromotionOne.getBeginTime());
			} else if (resourceType == 3){
				onumber = activityPromotionDao.getJkwyOrderNumberActivityPromotion(activityPromotionOne.getId(), userId, activityPromotionOne.getBeginTime(), activityPromotionOne.getBeginTime());
			}
			if(activityPromotionOne.getType() == 2){
				if(onumber == 0){
					return activityPromotionOne;
				}
				return null;
			}
			if(activityPromotionOne.getType() == 3){
				if(onumber == 1){
					return activityPromotionOne;
				}
				return null;
			}
		}
		return null;
	}
	
}