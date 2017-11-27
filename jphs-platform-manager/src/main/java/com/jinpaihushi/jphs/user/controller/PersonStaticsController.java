package com.jinpaihushi.jphs.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;
import com.jinpaihushi.jphs.user.service.PersonstaticsService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;

@Controller
@RequestMapping(name = "注册量统计", path = "/person/statistics")
public class PersonStaticsController extends BaseController<StatisticsModel> {
    @Autowired
    private PersonstaticsService personstaticsService;

    @Override
    protected BaseService<StatisticsModel> getService() {
        return null;
    }

    @RequestMapping(name = "注册量统计", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        return "person.statistics.index";
    }

    @RequestMapping(name = "用户注册量统计", path = "/userRegister.jhtml")
    public String userRegister(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        Date date = new Date();
        //格式化为当前日期
        String startDay = CycleTimeUtils.getWeekStartDay(date);
        String endDay = CycleTimeUtils.getWeekEndtDay(date);
        String weeks = startDay + "T" + endDay;
        req.setAttribute("flag", false);
        List<Map<String, Object>> registerByWeek = personstaticsService.userRegisterByWeek(weeks);
        pushDimensionalData(req, registerByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        List<Map<String, Object>> registerByMonth = personstaticsService
                .userRegisterByMonth(DateUtils.formartMonth(date));
        pushDimensionalData(req, registerByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        List<Map<String, Object>> registerByYear = personstaticsService.userRegisterByYear(DateUtils.formartYear(date));
        pushDimensionalData(req, registerByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        Map<String, Object> allDataByYear = personstaticsService.userAllNumByYear(DateUtils.formartYear(date));
        getTableData(req, allDataByYear);
        return "person.statistics.userRegister";
    }

    @RequestMapping(name = "用户注册量按周统计", path = "/userRegisterByWeek.jhtml")
    public String userRegisterByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String weeks) {
        List<Map<String, Object>> registerByWeek = personstaticsService.userRegisterByWeek(weeks);
        pushDimensionalData(req, registerByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        return "person.statistics.userRegisterByWeek";
    }

    @RequestMapping(name = "用户注册量按月统计", path = "/userRegisterByMonth.jhtml")
    public String userRegisterByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String month) {
        List<Map<String, Object>> registerByMonth = personstaticsService.userRegisterByMonth(month);
        pushDimensionalData(req, registerByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        return "person.statistics.userRegisterByMonth";
    }

    @RequestMapping(name = "用户注册量按年统计", path = "/userRegisterByYear.jhtml")
    public String userRegisterByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year) {
        req.setAttribute("flag", false);
        List<Map<String, Object>> registerByYear = personstaticsService.userRegisterByYear(year);
        pushDimensionalData(req, registerByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        return "person.statistics.userRegisterByYear";
    }

    @RequestMapping(name = "用户全年注册量统计", path = "/userRegisterByAnnual.jhtml")
    public String userRegisterByAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year) {
        req.setAttribute("flag", false);
        Map<String, Object> allDataByYear = personstaticsService.userAllNumByYear(year);
        getTableData(req, allDataByYear);
        return "order.statistics.userRegisterByAnnual";
    }

    /**
     * 全年数据公用方法
     * @param req
     * @param allDataByYear
     */
    private void getTableData(HttpServletRequest req, Map<String, Object> allDataByYear) {
        req.setAttribute("months", allDataByYear.get("months"));
        req.setAttribute("days", allDataByYear.get("days"));
        req.setAttribute("allData", allDataByYear.get("allData"));
        req.setAttribute("year", allDataByYear.get("year"));
    }

    @RequestMapping(name = "护士注册量统计", path = "/nurseRegister.jhtml")
    public String nurseRegister(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Integer type) {
        //        @RequestParam(value = "type", defaultValue = "0", required = true)
        Date date = new Date();
        //格式化为当前日期
        String startDay = CycleTimeUtils.getWeekStartDay(date);
        String endDay = CycleTimeUtils.getWeekEndtDay(date);
        String weeks = startDay + "T" + endDay;
        req.setAttribute("flag", false);
        List<Map<String, Object>> registerByWeek = personstaticsService.nurseRegisterByWeek(weeks, type);
        pushDimensionalData(req, registerByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        List<Map<String, Object>> registerByMonth = personstaticsService
                .nurseRegisterByMonth(DateUtils.formartMonth(date), type);
        pushDimensionalData(req, registerByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        List<Map<String, Object>> registerByYear = personstaticsService.nurseRegisterByYear(DateUtils.formartYear(date),
                type);
        pushDimensionalData(req, registerByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        Map<String, Object> allDataByYear = personstaticsService.nurseAllNumByYear(DateUtils.formartYear(date), type);
        getTableData(req, allDataByYear);
        req.setAttribute("context", "''");
        if (type != null) {
            if (type == 1) {
                req.setAttribute("context", "'护士'");
            }
            if (type == 2) {
                req.setAttribute("context", "'康复师'");
            }
            if (type == 3) {
                req.setAttribute("context", "'母婴师'");
            }
        }
        return "person.statistics.nurseRegister";
    }

    @RequestMapping(name = "护士注册量按周统计", path = "/nurseRegisterByWeek.jhtml")
    public String nurseRegisterByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String weeks, Integer type) {
        List<Map<String, Object>> registerByWeek = personstaticsService.nurseRegisterByWeek(weeks, type);
        pushDimensionalData(req, registerByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        return "person.statistics.nurseRegisterByWeek";
    }

    @RequestMapping(name = "护士注册量按月统计", path = "/nurseRegisterByMonth.jhtml")
    public String nurseRegisterByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String month, Integer type) {
        List<Map<String, Object>> registerByMonth = personstaticsService.nurseRegisterByMonth(month, type);
        pushDimensionalData(req, registerByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        return "person.statistics.nurseRegisterByMonth";
    }

    @RequestMapping(name = "护士注册量按年统计", path = "/nurseRegisterByYear.jhtml")
    public String nurseRegisterByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year, Integer type) {
        req.setAttribute("flag", false);
        List<Map<String, Object>> registerByYear = personstaticsService.nurseRegisterByYear(year, type);
        pushDimensionalData(req, registerByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        return "person.statistics.nurseRegisterByYear";
    }

    @RequestMapping(name = "护士全年注册量统计", path = "/nurseRegisterByAnnual.jhtml")
    public String nurseRegisterByAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String year, Integer type) {
        req.setAttribute("flag", false);
        Map<String, Object> allDataByYear = personstaticsService.nurseAllNumByYear(year, type);
        getTableData(req, allDataByYear);
        return "person.statistics.nurseRegisterByAnnual";
    }
}
