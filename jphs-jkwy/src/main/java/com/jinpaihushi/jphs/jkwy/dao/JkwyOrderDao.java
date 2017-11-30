package com.jinpaihushi.jphs.jkwy.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:26
 * @version 1.0
 */
@Repository("jkwyOrderDao")
public interface JkwyOrderDao extends BaseDao<JkwyOrder> {

    /**
     * 查询用户下单数量
     * @param map
     * @return
     */
    Integer getJkwyOrderNumber(@Param("activityId") String activityId, @Param("userId") String userId,
            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 订单量周统计
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<StatisticsModel> getQuantityByWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**订单量月统计
     * @param month
     * @return
     */
    List<StatisticsModel> quantityByMonth(String month);

    /**订单量年统计
     * @param month
     * @return
     */
    List<StatisticsModel> quantityByYear(String year);

    /**
     * @param map
     * @return
     */
    List<StatisticsModel> getAllNumByYear(Map<String, Object> map);

    /**
     * 查询用户下单记录-健康档案
     * @param jkwyOrder
     * @return
     */
    List<JkwyOrder> getHealthyArchivesAndOrder(JkwyOrder jkwyOrder);

    Page<JkwyOrder> queryList(JkwyOrder jkwyOrder);
}
