package com.jinpaihushi.jphs.jkwy.service;

import java.util.List;

import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
public interface JkwyRelationService extends BaseService<JkwyRelation> {

    /**
     * 查询用户亲属
     * @param jkwyRelation
     * @return
     */
    List<JkwyRelation> getUserRelationOfOrder(JkwyRelation jkwyRelation);

    /**
     * 根据订单id获取套餐内的亲友信息
     * @param id
     * @return
     */
    List<JkwyRelation> getUserRelationByOrderId(String id);

    /**
     * 更新亲友关系，如果该亲友已经绑定套餐，则无法修改该亲友信息
     * @param jkwyRelation
     * @return
     */
    int updateRelation(JkwyRelation jkwyRelation);
}