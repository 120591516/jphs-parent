package com.jinpaihushi.jphs.nurse.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.nurse.service.NurseStaticsService;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.DateUtils;

@Controller
@RequestMapping(name = "服务人员统计", path = "/nurse/statistics")
public class NurseStaticsController extends BaseController<BaseModel> {
    @Autowired
    private NurseStaticsService nureseStaticsService;

    @Autowired
    private GoodsService goodsService;

    @Override
    protected BaseService<BaseModel> getService() {
        return null;
    }

    @RequestMapping(name = "服务人员统计", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        return "nurse.statistics.index";
    }

    @RequestMapping(name = "人员属性分析", path = "/properties.jhtml")
    public String properties(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        Map<String, Object> result = nureseStaticsService.getProperties();
        req.setAttribute("changeJob", result.get("changeJob"));
        req.setAttribute("changeAddress", result.get("changeAddress"));
        req.setAttribute("changeAge", result.get("changeAge"));
        req.setAttribute("changeJobtitle", result.get("changeJobtitle"));
        return "nurse.statistics.properties";
    }

    @RequestMapping(name = "个人服务分析", path = "/serviceRank.jhtml")
    public String serviceRank(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        req.setAttribute("flag", true);
        String year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> result = nureseStaticsService.getNurseServerTop(year);
        List<Map<String, Object>> allDataByYear = nureseStaticsService.getServerTotal(year);
        Object[] xAxisData = (Object[]) result.get("xAxisData");
        Object[] yAxisData = (Object[]) result.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("year", year);
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        req.setAttribute("allData", allDataByYear);
        return "nurse.statistics.serviceRank";
    }

    @RequestMapping(name = "个人服务分析按年", path = "/serviceRankByYear.jhtml")
    public String serviceRankByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> result = nureseStaticsService.getNurseServerTop(year);
        Object[] xAxisData = (Object[]) result.get("xAxisData");
        Object[] yAxisData = (Object[]) result.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        return "nurse.statistics.serviceRankByYear";
    }

    @RequestMapping(name = "个人订单分析", path = "/orderRank.jhtml")
    public String orderRank(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p) {
        Goods goods = new Goods();
        goods.setStatus(1);
        List<Goods> goodsList = goodsService.list(goods);
        req.setAttribute("goods", goodsList);
        req.setAttribute("flag", true);
        String year = DateUtils.formartYear(new Date());
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("year", year);
        Map<String, Object> result = nureseStaticsService.getNurseOrderTop(query);
        Map<String, Object> allDataByYear = nureseStaticsService.getNurseOrderDetail(req, year, p, 15);
        Object[] xAxisData = (Object[]) result.get("xAxisData");
        Object[] yAxisData = (Object[]) result.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("year", year);
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        modelMap.put("list", allDataByYear.get("list"));
        modelMap.put("pageInfo", allDataByYear.get("pageInfo"));
        return "nurse.statistics.orderRank";
    }

    @RequestMapping(name = "个人订单搜索", path = "/orderRankSearch.jhtml")
    public String orderRankSearch(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String goodsId, String year, String city) {
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("year", year);
        query.put("goodsId", goodsId);
        query.put("city", city);
        req.setAttribute("goodsId", goodsId);
        req.setAttribute("city", city);
        Goods goods = new Goods();
        goods.setStatus(1);
        List<Goods> goodsList = goodsService.list(goods);
        req.setAttribute("goods", goodsList);
        req.setAttribute("flag", true);
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> result = nureseStaticsService.getNurseOrderTop(query);
        Object[] xAxisData = (Object[]) result.get("xAxisData");
        Object[] yAxisData = (Object[]) result.get("yAxisData");
        req.setAttribute("xAxisData", Arrays.toString(xAxisData));
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("year", year);
        req.setAttribute("yAxisData", Arrays.toString(yAxisData));
        return "nurse.statistics.orderRankSearch";
    }

    /* @RequestMapping(name = "个人订单排名数据", path = "/orderRankData.jhtml")
    public String orderRankData(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year, int p, int n) {
        req.setAttribute("flag", true);
        String[] yearTimeList = { "'" + year + "-01'" };
        Map<String, Object> result = nureseStaticsService.getNurseOrderDetail(req, year, p, n);
        req.setAttribute("yearTimeList", Arrays.toString(yearTimeList));
        req.setAttribute("year", year);
        modelMap.put("list", result.get("list"));
        modelMap.put("pageInfo", result.get("pageInfo"));
        return "nurse.statistics.orderRankData";
    }*/
}
