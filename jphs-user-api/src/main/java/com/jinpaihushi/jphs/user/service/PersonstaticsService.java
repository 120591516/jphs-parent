package com.jinpaihushi.jphs.user.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.statistics.model.StatisticsModel;
import com.jinpaihushi.service.BaseService;

public interface PersonstaticsService extends BaseService<StatisticsModel> {

    /**
     * 获取一周的注册量
     * @param weeks 2017-10-11T2017-10-15
     * @return
     */
    List<Map<String, Object>> userRegisterByWeek(String weeks);

    /**
     * 获取一个月的注册量
     * @param month
     * @return
     */
    List<Map<String, Object>> userRegisterByMonth(String month);

    /**
     * 获取一年的注册量 按月分组
     * @param year
     * @return
     */
    List<Map<String, Object>> userRegisterByYear(String year);

    /**
     * 获取全年的注册量 按天分组
     * @param year
     * @return
     */
    Map<String, Object> userAllNumByYear(String year);

    /**
     * 护士一周的注册量及审核量
     * @param weeks
     * @return
     */
    List<Map<String, Object>> nurseRegisterByWeek(String weeks, Integer type);

    /**
     * 护士一个月的注册量及审核量
     * @param month
     * @return
     */
    List<Map<String, Object>> nurseRegisterByMonth(String month, Integer type);

    /**
     * 护士一年的注册量及审核量
     * @param year
     * @return
     */
    List<Map<String, Object>> nurseRegisterByYear(String year, Integer type);

    /**
     * 护士全年的注册量及审核量
     * @param year
     * @return
     */
    Map<String, Object> nurseAllNumByYear(String year, Integer type);

}
