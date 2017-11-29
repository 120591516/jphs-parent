package com.jinpaihushi.jphs.jkwy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.ActivityPromotionService;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderContent;
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderContentService;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderService;
import com.jinpaihushi.jphs.jkwy.service.JkwyRelationService;
import com.jinpaihushi.jphs.remark.model.Remark;
import com.jinpaihushi.jphs.remark.service.RemarkService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:26
 * @version 1.0
 */
@Controller
@RequestMapping(name = "健康无忧订单", path = "/jkwy/order")
public class JkwyOrderController extends BaseController<JkwyOrder> {

    @Autowired
    private JkwyOrderService jkwyOrderService;

    @Autowired
    private JkwyOrderContentService jkwyOrderContentService;

    @Autowired
    private JkwyRelationService jkwyRelationService;

    @Autowired
    private VoucherUseService voucherUseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RemarkService remarkService;

    @Autowired
    private ActivityPromotionService activityPromotionService;

    @Override
    protected BaseService<JkwyOrder> getService() {
        return jkwyOrderService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrder jkwyOrder, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyOrder> list = jkwyOrderService.query(jkwyOrder);
        PageInfos<JkwyOrder> pageInfo = new PageInfos<JkwyOrder>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/order/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrder jkwyOrder = jkwyOrderService.loadById(id);
        modelMap.put("jkwyOrder", jkwyOrder);
        return "jkwy/jkwy/order/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/order/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrder jkwyOrder = jkwyOrderService.loadById(id);
        //订单用户信息
        User user = userService.loadById(jkwyOrder.getCreatorId());
        //套餐内的服务内容
        JkwyOrderContent jkwyOrderContent = new JkwyOrderContent();
        jkwyOrderContent.setJkwyOrderId(id);
        jkwyOrderContent.setStatus(0);
        List<JkwyOrderContent> jkwyOrderContentList = jkwyOrderContentService.list(jkwyOrderContent);
        //套餐内的亲友关系
        List<JkwyRelation> relationList = jkwyRelationService.getUserRelationByOrderId(id);
        //查询是否有活动特价
        ActivityPromotion ap = null;
        if (StringUtils.isNotEmpty(jkwyOrder.getActivityPromotionId())) {
            ap = activityPromotionService.loadById(jkwyOrder.getActivityPromotionId());
        }
        modelMap.put("activityPromotion", ap);
        //查询优惠券信息
        List<VoucherUse> voucherList = null;
        if (StringUtils.isNotEmpty(jkwyOrder.getVoucherUserId())) {
            VoucherUse voucherUser = new VoucherUse();
            voucherUser.setId(jkwyOrder.getVoucherUserId());
            voucherList = voucherUseService.getList(voucherUser).getResult();
        }
        modelMap.put("voucherUse", voucherList != null ? voucherList.get(0) : null);
        //查询交易信息
        Transaction transaction = new Transaction();
        transaction.setOrderId(id);
        transaction.setOperate(3);
        List<Transaction> transactionUserList = transactionService.list(transaction);
        if (transactionUserList.size() > 0) {
            modelMap.put("transactionUser", transactionUserList.get(0));
        }
        else {
            modelMap.put("transactionUser", null);
        }
        //查询订单备注
        Remark remark = new Remark();
        remark.setSourceId(id);
        Remark r = remarkService.load(remark);
        modelMap.put("remark", r);
        modelMap.put("jkwyOrder", jkwyOrder);
        modelMap.put("user", user);
        modelMap.put("jkwyOrderContentList", jkwyOrderContentList);
        modelMap.put("relationList", relationList);
        modelMap.put("relationList", relationList);
        return "jkwy/jkwy/order/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrder jkwyOrder) {

        if (jkwyOrder.getId() != null && !jkwyOrder.getId().equals("")) {
            boolean b = jkwyOrderService.update(jkwyOrder);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/order/err.jhtml";
            }
        }
        else {
            jkwyOrder.setId(UUID.randomUUID().toString());
            String result = jkwyOrderService.insert(jkwyOrder);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/order/err.jhtml";
            }
        }
        return "redirect:/jkwy/order/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyOrderService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/order/err.jhtml";
        }

        return "redirect:/jkwy/order/index.jhtml";
    }

    @RequestMapping(name = "订单量统计", path = "/quantity.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {
        Date date = new Date();
        //格式化为当前日期
        String startDay = CycleTimeUtils.getWeekStartDay(date);
        String endDay = CycleTimeUtils.getWeekEndtDay(date);
        String weeks = startDay + "T" + endDay;
        req.setAttribute("flag", true);
        List<Map<String, Object>> quantityByWeek = jkwyOrderService.getQuantityByWeek(weeks);
        pushDimensionalData(req, quantityByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        List<Map<String, Object>> quantityByMonth = jkwyOrderService.quantityByMonth(DateUtils.formartMonth(date));
        pushDimensionalData(req, quantityByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        List<Map<String, Object>> quantityByYear = jkwyOrderService.quantityByYear(DateUtils.formartYear(date));
        pushDimensionalData(req, quantityByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        Map<String, Object> allDataByYear = jkwyOrderService.getAllNumByYear(DateUtils.formartYear(date));
        getTableData(req, allDataByYear);
        return "jkwy.order.statistics.quantity";
    }

    @RequestMapping(name = "订单量周统计", path = "/quantityByWeek.jhtml")
    public String quantityByWeek(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String weeks) {
        List<Map<String, Object>> quantityByWeek = jkwyOrderService.getQuantityByWeek(weeks);
        pushDimensionalData(req, quantityByWeek, "weekTimeList", "weekDeviceName", "weekTotal");
        return "jkwy.order.statistics.quantityByWeek";
    }

    @RequestMapping(name = "订单量月统计", path = "/quantityByMonth.jhtml")
    public String quantityByMonth(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String month) {
        List<Map<String, Object>> quantityByMonth = jkwyOrderService.quantityByMonth(month);
        pushDimensionalData(req, quantityByMonth, "monthTimeList", "monthDeviceName", "monthTotal");
        return "jkwy.order.statistics.quantityByMonth";
    }

    @RequestMapping(name = "订单量年统计", path = "/quantityByYear.jhtml")
    public String quantityByYear(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        List<Map<String, Object>> quantityByYear = jkwyOrderService.quantityByYear(year);
        pushDimensionalData(req, quantityByYear, "yearTimeList", "yearDeviceName", "yearTotal");
        return "jkwy.order.statistics.quantityByYear";
    }

    @RequestMapping(name = "全年订单量统计", path = "/quantityByAnnual.jhtml")
    public String quantityByAnnual(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String year) {
        req.setAttribute("flag", true);
        Map<String, Object> allDataByYear = jkwyOrderService.getAllNumByYear(year);
        getTableData(req, allDataByYear);
        return "jkwy.order.statistics.quantityByAnnual";
    }

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
}
