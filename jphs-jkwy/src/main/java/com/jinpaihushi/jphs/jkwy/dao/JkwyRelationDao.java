package com.jinpaihushi.jphs.jkwy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Repository("jkwyRelationDao")
public interface JkwyRelationDao extends BaseDao<JkwyRelation> {

    /**
     * 查询用户亲属
     * @param jkwyRelation
     * @return
     */
    List<JkwyRelation> getUserRelationOfOrder(JkwyRelation jkwyRelation);

    /**
     * 根据订单id获取订单内的亲友信息
     * @param id 订单id
     * @return
     */
    List<JkwyRelation> getUserRelationByOrderId(String id);
    
    /**
     * 查询未购买套餐的亲属
     * @param jkwyRelation
     * @return
     */
    List<JkwyRelation> getUserRelationIsNotOrder(JkwyRelation jkwyRelation);

}
