package com.jinpaihushi.jphs.activity.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;

/**
 * 
 * @author scj
 * @date 2017-10-30 14:25:22
 * @version 1.0
 */
@Repository("activityPromotionDao")
public interface ActivityPromotionDao extends BaseDao<ActivityPromotion> {

    /**
     * @param activityPromotion
     *          resourceId 参加活动商品/服务id
     *          resourceType 类型
     *          platformId 平台id
     *          priceId 
     * 获取服务/商品的活动优惠信息
     * @return
     */
    ActivityPromotion getActivityForGoods(ActivityPromotion activityPromotion);

    /**
     * 查询用户下单数量
     * @param map
     * @return
     */
    Integer getIndexNumberP(Map<String,Object> map);
    
}
