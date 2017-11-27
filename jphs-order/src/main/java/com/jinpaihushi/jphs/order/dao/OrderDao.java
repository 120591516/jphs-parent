package com.jinpaihushi.jphs.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.model.Order;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Repository("orderDao")
public interface OrderDao extends BaseDao<Order> {

    Page<Order> getList(Order order);

    /**
     * 获取用户的订单
     * 
     * @param userId
     * @return
     */
    List<Map<String, Object>> getUserOrder(Map<String, Object> query);

    /**
     * 订单详情
     * 
     * @param deviceType
     * @param id
     *            订单id
     * @return
     */
    Order getUserOrderDetail(Order order);

    /**
     * 查询护士最新即将执行的订单
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getUptoDataGoods(Map<String, Object> map);

    /**
     * 待接单订单列表
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> orderNotList(Map<String, Object> map);

    /**
     * 护士已接单列表
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> nurseOrderList(Map<String, Object> map);

    /**
     * 订单详情
     * 
     * @param order
     * @return
     */
    Order nurseOrderDetails(Order order);

    /**
     * 已枪订单
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> orderaccNotNullList(Map<String, Object> map);

    /**
     * @param query
     *            query 参数 1. 日期 confirmTime 2. 订单状态 schedule 已完成的
     * @return
     */
    List<Order> getCompletedOrder(Map<String, Object> query);

    List<Map<String, Object>> getOrderUnpaid(@Param("condition") String condition);

    Map<String, Object> getSmsMessage(String orderId);

    List<Map<String, Object>> getNotInRank();

    List<Map<String, Object>> exportExcel(Order order);

    /**
     * 白富美活动，查询护士订单完成进度
     * @param map
     * @return
     */
    Map<String, Object> dzhsbfmHdNurseNumber(Map<String, Object> map);

    /**
     * 护士相同手机号推荐的数量
     * @param map
     * @return
     */
    Map<String, Object> dzhsbfmHdNurseNumberRecommend(Map<String, Object> map);

    /**
     * 查询活动期内未完成的订单量
     * @param map
     * @return
     */
    int getHsbfmAwardOrderNumberfalse(Map<String, Object> map);

    /**
     * 推荐订单未完成数量
     * @return
     */
    int getHsbfmAwardOrderRecommendNumberfalse(Map<String, Object> map);

    /**
     * 活动的记录信息
     * @return
     */
    List<Map<String, Object>> getSendTransaction();

    /**
     * 活动参与次数
     * @param activityId 活动id 
     * @param userId 用户id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    int countByActivity(@Param("activityId") String activityId, @Param("userId") String userId,
            @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
