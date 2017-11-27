package com.jinpaihushi.jphs.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;

@Repository("personStaticsDao")
public interface PersonStaticsDao extends BaseDao<StatisticsModel> {
    /**
     * 按周统计注册量
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<StatisticsModel> userRegisterByWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 按周统计注册量
     * @param month 月份
     * @return
     */
    List<StatisticsModel> userRegisterByMonth(@Param("month") String month);

    /**
     * 按年统计注册量
     * @param year 月份
     * @return
     */
    List<StatisticsModel> userRegisterByYear(@Param("year") String year);

    /**
     * 查询全年的注册量
     * @param map
     *        @param year 查询的年份
     *        @param device 注册短
     * @return
     */
    List<StatisticsModel> userAllNumByYear(Map<String, Object> map);

    /**
     * 护士按周统计注册量
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param type 护士类型
     * @return
     */
    List<StatisticsModel> nurseRegisterByWeek(Map<String, Object> map);

    /**
     * 护士按周统计注册量
     * @param month 月份
     * @param type 护士类型
     * @return
     */
    List<StatisticsModel> nurseRegisterByMonth(Map<String, Object> map);

    /**
     * 护士按年统计注册量
     * @param year 月份
     * @param type 护士类型
     * @return
     */
    List<StatisticsModel> nurseRegisterByYear(Map<String, Object> map);

    /**
     * 护士查询全年的注册量
     * @param map
     *        @param year 查询的年份
     *        @Param("type")int type 护士类型
     * @return
     */
    List<StatisticsModel> nurseAllNumByYear(Map<String, Object> map);
}
