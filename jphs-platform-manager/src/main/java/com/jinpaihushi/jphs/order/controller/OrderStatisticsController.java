package com.jinpaihushi.jphs.order.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Controller
@RequestMapping(name = "服务订单统计", path = "/order/statistics")
public class OrderStatisticsController extends BaseController<Order> {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Override
    protected BaseService<Order> getService() {
        return orderService;
    }

    @RequestMapping(name = "订单量统计", path = "/quantity.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        Date date = new Date();
        //格式化为当前日期
        String startDay = CycleTimeUtils.getWeekStartDay(date);
        String endDay = CycleTimeUtils.getWeekEndtDay(date);
        String weeks = startDay + "T" + endDay;
        req.setAttribute("flag", true);
        List<Map<String, Object>> quantityByWeek = orderService.getQuantityByWeek(weeks);
        pushDimensionalData(req, quantityByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        List<Map<String, Object>> quantityByMonth = orderService.quantityByMonth(DateUtils.formartMonth(date));
        pushDimensionalData(req, quantityByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        List<Map<String, Object>> quantityByYear = orderService.quantityByYear(DateUtils.formartYear(date));
        pushDimensionalData(req, quantityByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        Map<String, Object> allDataByYear = orderService.getAllNumByYear(DateUtils.formartYear(date));
        getTableData(req, allDataByYear);
        return "order.statistics.quantity";
    }

    @RequestMapping(name = "订单量周统计", path = "/quantityByWeek.jhtml")
    public String quantityByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String weeks) {
        List<Map<String, Object>> quantityByWeek = orderService.getQuantityByWeek(weeks);
        pushDimensionalData(req, quantityByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        return "order.statistics.quantityByWeek";
    }

    @RequestMapping(name = "订单量月统计", path = "/quantityByMonth.jhtml")
    public String quantityByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String month) {
        List<Map<String, Object>> quantityByMonth = orderService.quantityByMonth(month);
        pushDimensionalData(req, quantityByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        return "order.statistics.quantityByMonth";
    }

    @RequestMapping(name = "订单量年统计", path = "/quantityByYear.jhtml")
    public String quantityByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        List<Map<String, Object>> quantityByYear = orderService.quantityByYear(year);
        pushDimensionalData(req, quantityByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        return "order.statistics.quantityByYear";
    }

    @RequestMapping(name = "全年订单量统计", path = "/quantityByAnnual.jhtml")
    public String quantityByAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        Map<String, Object> allDataByYear = orderService.getAllNumByYear(year);
        getTableData(req, allDataByYear);
        return "order.statistics.quantityByAnnual";
    }

    /* *//**
         * 该方法用来处理多种对比数据的折线
         * @param req
         * @param list
         * @param timeName x轴数据 K
         * @param deviceListName 对比的各个数据类型 K
         * @param dataName 总数据 K
         *//*
           private void pushDimensionalData(HttpServletRequest req, List<Map<String, Object>> list, String timeName,
                String deviceListName, String dataName) {
            //获取时间列表
            Object[] timeList = (Object[]) list.get(0).get("time");
            //获取平台的名称
            String[] deviceName = (String[]) list.get(1).get("deviceName");
            deviceName.toString();
            list.remove(0);
            req.setAttribute(timeName, Arrays.toString(timeList));
           
            req.setAttribute(deviceListName, Arrays.toString(deviceName));
            list.remove(0);
            String[] totalWeek = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                @SuppressWarnings("unchecked")
                List<StatisticsModel> dataList = (List<StatisticsModel>) list.get(i).get(deviceName[i]);
                //            List<OrderStatistics> dataList = (List<OrderStatistics>) list.get(i).get(deviceName[i].replaceAll("'", ""));
                int[] weekNum = new int[dataList.size()];
                for (int j = 0; j < dataList.size(); j++) {
                    weekNum[j] = dataList.get(j).getNum();
                }
                totalWeek[i] = Arrays.toString(weekNum);
            }
            req.setAttribute(dataName, Arrays.toString(totalWeek));
           }*/

    /**
     * 全年数据公用方法
     * @param req
     * @param allDataByYear
     */
    private void getTableData(HttpServletRequest req, Map<String, Object> allDataByYear) {
        req.setAttribute("months", allDataByYear.get("months"));
        req.setAttribute("days", allDataByYear.get("days"));
        //        req.setAttribute("allData", Arrays.deepToString((Object[]) allDataByYear.get("allData")));
        req.setAttribute("allData", allDataByYear.get("allData"));
        req.setAttribute("year", allDataByYear.get("year"));
    }

    @RequestMapping(name = "服务排名", path = "/serviceRankingIndex.jhtml")
    public String serviceRankingIndex(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year) {
        req.setAttribute("flag", true);
        if (StringUtils.isEmpty(year))
            year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> serviceRanking = orderService.getServiceRanking(year);
        Object[] xAxisData = (Object[]) serviceRanking.get("xAxisData");
        Object[] yAxisData = (Object[]) serviceRanking.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        Goods goods = new Goods();
        goods.setStatus(1);
        List<Goods> goodsList = goodsService.list(goods);
        req.setAttribute("goods", goodsList);
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        Map<String, Object> allDataByYear = orderService.getAllServiceNumByYear(map);
        getTableData(req, allDataByYear);
        return "order.statistics.serviceRankingIndex";
    }

    @RequestMapping(name = "服务年数据", path = "/serviceRankingAnnual.jhtml")
    public String serviceRankingAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year, String goodsId) {
        Goods goods = new Goods();
        goods.setStatus(1);
        List<Goods> goodsList = goodsService.list(goods);
        req.setAttribute("goods", goodsList);
        req.setAttribute("goodsId", goodsId);
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("goodsId", goodsId);
        Map<String, Object> allDataByYear = orderService.getAllServiceNumByYear(map);
        getTableData(req, allDataByYear);
        req.setAttribute("flag", true);
        return "order.statistics.serviceRankingAnnual";
    }

    @RequestMapping(name = "服务排名按年", path = "/serviceRanking.jhtml")
    public String serviceRanking(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        if (StringUtils.isEmpty(year))
            year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> serviceRanking = orderService.getServiceRanking(year);
        Object[] xAxisData = (Object[]) serviceRanking.get("xAxisData");
        Object[] yAxisData = (Object[]) serviceRanking.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        return "order.statistics.serviceRanking";
    }

    @RequestMapping(name = "城市排名", path = "/cityRankingIndex.jhtml")
    public String cityRankingIndex(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        if (StringUtils.isEmpty(year))
            year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> serviceRanking = orderService.getCityRanking(year);
        Object[] xAxisData = (Object[]) serviceRanking.get("xAxisData");
        Object[] yAxisData = (Object[]) serviceRanking.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        req.setAttribute("flag", true);
        Map<String, Object> allDataByYear = orderService.getAllCityNumByYear(map);
        getTableData(req, allDataByYear);
        return "order.statistics.cityRankingIndex";
    }

    @RequestMapping(name = "城市排名按年", path = "/cityRanking.jhtml")
    public String cityRanking(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        if (StringUtils.isEmpty(year))
            year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> serviceRanking = orderService.getCityRanking(year);
        Object[] xAxisData = (Object[]) serviceRanking.get("xAxisData");
        Object[] yAxisData = (Object[]) serviceRanking.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        return "order.statistics.cityRanking";
    }

    @RequestMapping(name = "城市全年订单量", path = "/cityRankingAnnual.jhtml")
    public String cityRankingAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year, String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("city", city);
        req.setAttribute("city", city);
        Map<String, Object> allDataByYear = orderService.getAllCityNumByYear(map);
        getTableData(req, allDataByYear);
        req.setAttribute("flag", true);
        return "order.statistics.cityRankingAnnual";
    }
}
