package com.jinpaihushi.jphs.nurse.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;

public interface NurseStaticsService extends BaseService<BaseModel> {

    /**
     * 人员属性分析
     * @return
     */
    Map<String, Object> getProperties();

    /**
     * 服务人员发布的服务排名TOP10
     * @return
     */
    Map<String, Object> getNurseServerTop(String year);

    /**
     * 服务人员发布的服务排名TOP10的详细信息
     * @param year
     * @return
     */
    List<Map<String, Object>> getServerTotal(String year);

    /**
     * 服务人员订单TOP10
     * @param query
     * @return
     */
    Map<String, Object> getNurseOrderTop(Map<String, Object> query);

    /**
     * 服务人员订单排名详情
     * @param year
     * @param p 页数
     * @param n 显示行数
     * @return
     */
    Map<String, Object> getNurseOrderDetail(HttpServletRequest request, String year, int p, int n);
}
