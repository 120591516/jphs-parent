package com.jinpaihushi.jphs.jkwy.service;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.service.BaseService;

import net.sf.json.JSONObject;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:26
 * @version 1.0
 */
public interface JkwyOrderService extends BaseService<JkwyOrder> {

    /**
     * 创建订单
     * @param jkwyOrder
     * @param jkwyRelationId
     * @return
     */
    JSONObject createOrder(JkwyOrder jkwyOrder, String jkwyRelationId);

    /**
     * 支付回调
     * @param type
     * @param no
     * @param trade_no
     * @param total_fee
     * @param packageParams
     * @return
     */
    boolean jkwyNotify(int type, String no, String trade_no, String total_fee, SortedMap<Object, Object> packageParams);

    /**
     * 查询用户订单列表
     * @param jkwyOrder
     * @return
     */
    JSONObject getHealthyArchives(JkwyOrder jkwyOrder);

    /**
    * 订单量周统计
    * @param weeks
    * @return
    */
    List<Map<String, Object>> getQuantityByWeek(String weeks);

    /**
     * 订单量月统计
     * @param month
     * @return
     */
    List<Map<String, Object>> quantityByMonth(String month);

    /**
     * 订单量年统计
     * @param year
     * @return
     */
    List<Map<String, Object>> quantityByYear(String year);

    /**
     * 订单量全年数据
     * @param year
     * @return
     */
    Map<String, Object> getAllNumByYear(String year);

    /**
     * 健康无忧订单列表
     * @param jkwyOrder
     * @return
     */
    Page<JkwyOrder> queryList(JkwyOrder jkwyOrder);
}