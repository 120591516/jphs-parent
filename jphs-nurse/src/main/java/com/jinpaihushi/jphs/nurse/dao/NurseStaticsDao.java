package com.jinpaihushi.jphs.nurse.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 服务人员统计
 * @author wangwenteng
 *
 */
@Repository("nurseStatics")
public interface NurseStaticsDao {
    /**
     * 服务人员职业分布
     * @return
     */
    List<Map<String, Object>> getNurseJob();

    /**
     * 护士的区域分布
     * @return
     */
    List<Map<String, Object>> getNurseAddress();

    /**
     * 护士年龄分组
     * @return
     */
    List<Map<String, Object>> getNurseAge();

    /**
     * 护士级别分析
     * @return
     */
    List<Map<String, Object>> getNurseJobtitle();

    /**
     * 护士发布的服务TOP10
     * @return
     */
    List<Map<String, Object>> getNurseServerTop(String year);

    /**
     * 护士发布的服务TOP10的详细信息
     * @return
     */
    List<Map<String, Object>> getServerTotal(String year);

    /**
     * 护士订单TOP10
     * @return
     */
    List<Map<String, Object>> getNurseOrderTop(Map<String, Object> query);

    /**
     * 服务人员订单排名详情
     * @param year
     * @return
     */
    List<Map<String, Object>> getNurseOrderDetail(String year);

    /**
     * 初始化排名序号
     */
    void setRank();
}
