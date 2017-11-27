package com.jinpaihushi.jphs.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;

@Repository("orderStatisticsDao")
public interface OrderStatisticsDao extends BaseDao<StatisticsModel> {

    /**
     * 按周统计订单量
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<StatisticsModel> getQuantityByWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 按周统计订单量
     * @param month 月份
     * @return
     */
    List<StatisticsModel> quantityByMonth(@Param("month") String month);

    /**
     * 按年统计订单量
     * @param year 月份
     * @return
     */
    List<StatisticsModel> quantityByYear(@Param("year") String year);

    /**
     * 查询全年的订单量
     * @param map
     *        @param year 查询的年份
     * @return
     */
    List<StatisticsModel> getAllNumByYear(Map<String, Object> map);

    /**
     * 服务排名
     * @param year
     * @return
     */
    List<Map<String, Object>> getServiceRanking(@Param("year") String year);

    /**
     * 区域排名
     * @param year
     * @return
     */
    List<Map<String, Object>> getCityRanking(@Param("year") String year);

    /**
     * 查询全年的服务订单量
     * @param map
     *      @param year 查询的年份
     *      @param goodsId 筛选的服务id
     * @return
     */
    List<StatisticsModel> getAllServiceNumByYear(Map<String, Object> map);

    /**
     * 查询全年的城市订单量
     * @param map
     *      @param year 查询的年份
     *      @param city 筛选的城市
     * @return
     */
    List<StatisticsModel> getAllCityNumByYear(Map<String, Object> map);
}
