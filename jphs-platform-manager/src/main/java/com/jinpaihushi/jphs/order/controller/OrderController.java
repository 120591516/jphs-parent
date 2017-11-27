package com.jinpaihushi.jphs.order.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.cancel.model.CancelOrder;
import com.jinpaihushi.jphs.cancel.service.CancelOrderService;
import com.jinpaihushi.jphs.export.model.ExportFile;
import com.jinpaihushi.jphs.export.service.ExportFileService;
import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.jphs.insurance.service.InsuranceService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.jphs.order.service.OrderOtherService;
import com.jinpaihushi.jphs.order.service.OrderService;
import com.jinpaihushi.jphs.order.service.OrderServiceService;
import com.jinpaihushi.jphs.platform.model.PlatformUser;
import com.jinpaihushi.jphs.remark.model.Remark;
import com.jinpaihushi.jphs.remark.service.RemarkService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.util.ExcelUtil;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
@Controller
@RequestMapping(name = "服务订单", path = "/order")
public class OrderController extends BaseController<Order> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private CancelOrderService cancelOrderService;

    @Autowired
    private OrderOtherService orderOtherService;

    @Autowired
    private OrderServiceService orderServiceService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private RemarkService remarkService;

    @Override
    protected BaseService<Order> getService() {
        return orderService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Order order, Integer p, Integer n) {
        PlatformUser platformUser = (PlatformUser) req.getSession().getAttribute("session_user");
        startPage(p, n);
        order.setStatus(1);
        order.setPlatformId(platformUser.getPlatformId());
        // Page<Order> list = orderService.query(order);
        Page<Order> list = orderService.getList(order);
        PageInfos<Order> pageInfo = new PageInfos<Order>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        if (null != order && null != order.getSchedule()) {
            if (order.getSchedule() == 0) {
                modelMap.put("content", "待支付");
            }
            else if (order.getSchedule() == 1) {
                modelMap.put("content", "待接单");
            }
            else if (order.getSchedule() == 2) {
                modelMap.put("content", "已结单");
            }
            else if (order.getSchedule() == 3) {
                modelMap.put("content", "执行中");
            }
            else if (order.getSchedule() == 4) {
                modelMap.put("content", "待确定");
            }
            else if (order.getSchedule() == 5) {
                modelMap.put("content", "已完成");
            }
            else if (order.getSchedule() == 6) {
                modelMap.put("content", "已取消");
            }
            else {
                modelMap.put("content", "申诉中");
            }
        }
        else {
            modelMap.put("content", "全部订单");
        }
        return "order/order/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id, Nurse nurse, Integer p, Integer n) {
        Order order = orderService.getUserOrderDetail(id, null);
        String days = null;
        String hour = null;
        if (null != nurse && nurse.getWorkYear() != null) {
            // String appointmentTime =
            // format.format(order.getAppointmentTime());
            // days = appointmentTime.substring(0, 10);
            // hour = appointmentTime.substring(11, 13);
            // }else{
            days = nurse.getWorkYear().split(" ")[0];
            hour = nurse.getWorkYear().split(" ")[1];
            nurse.setCalendar(days);
            nurse.setH("h_" + Integer.parseInt(hour));
        }
        String city = null;
        if (order.getOrderOther().getAddress().split(",")[1].equals("市辖区")) {
            city = order.getOrderOther().getAddress().split(",")[0];
        }
        else {
            city = order.getOrderOther().getAddress().split(",")[1];
        }
        nurse.setAreas(city);
        startPage(p, n);
        List<Nurse> list = nurseService.getSomeNurse(nurse);
        PageInfos<Nurse> pageInfo = new PageInfos<Nurse>(list, req);
        modelMap.put("list", list);
        modelMap.put("order", order);
        modelMap.put("pageInfo", pageInfo);
        return "order/order/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/order/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        // 查询订单相关信息
        Order order = orderService.getUserOrderDetail(id, null);
        // 查询订单创建者的信息，即user
        User user = userService.loadById(order.getCreatorId());
        // 查询接单者的信息，即nurse
        User nurse = userService.loadById(order.getAcceptUserId());

        Transaction transaction = new Transaction();
        Insurance insurance = new Insurance();
        CancelOrder cancelOrder = new CancelOrder();
        cancelOrder.setSourceId(id);
        List<CancelOrder> list = cancelOrderService.list(cancelOrder);
        if (list.size() > 0) {
            modelMap.put("cancelOrder", list.get(0));
        }
        else {
            modelMap.put("cancelOrder", null);
        }
        // 查询用户积分的相关信息
        transaction.setOrderId(order.getId());
        transaction.setOperate(3);
        List<Transaction> transactionUserList = transactionService.list(transaction);
        if (transactionUserList.size() > 0) {
            modelMap.put("transactionUser", transactionUserList.get(0));
        }
        else {
            modelMap.put("transactionUser", null);
        }
        // 查询退款
        transaction.setOperate(4);

        Page<Transaction> refundList = transactionService.query(transaction);
        if (refundList.size() > 0) {
            modelMap.put("refund", refundList.get(0));
        }
        else {
            modelMap.put("refund", null);
        }

        // 查询护士积分的相关信息
        if (nurse != null) {
            transaction.setCreatorId(nurse.getId());
        }
        transaction.setOperate(null);
        Page<Transaction> transactionNurseList = transactionService.query(transaction);

        if (transactionNurseList.size() > 0) {
            modelMap.put("transactionNurse", transactionNurseList.get(0));
        }
        else {
            modelMap.put("transactionNurse", null);
        }

        // 查询保险
        insurance.setOrderId(order.getId());
        Page<Insurance> insuranceList = insuranceService.query(insurance);
        if (insuranceList.size() > 0) {
            modelMap.put("insurance", insuranceList.get(0));
        }
        else {
            modelMap.put("insurance", null);
        }

        //查询订单备注
        Remark remark = new Remark();
        remark.setSourceId(id);
        Remark r = remarkService.load(remark);
        modelMap.put("remark", r);
        modelMap.put("nurse", nurse);
        modelMap.put("user", user);
        modelMap.put("order", order);
        return "order/order/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Order order) {
        orderService.update(order);
        return "redirect:detail.jhtml?id=" + order.getId();
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = orderService.disableById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/order/err.jhtml";
        }

        return "redirect:/order/index.jhtml";
    }

    @RequestMapping(name = "修改患者联系方式", path = "/updatePatientName.jhtml")
    public String updatePatientName(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            com.jinpaihushi.jphs.order.model.OrderService orderServiceInfo) {

        orderServiceService.updatePatientPhone(orderServiceInfo);

        return "redirect:/order/detail.jhtml?id=" + orderServiceInfo.getId();
    }

    @RequestMapping(name = "跳转到添加页", path = "/editAcceptUserId.jhtml")
    public String editAcceptUserId(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id, String orderId, String acceptUserId) {
        orderService.editAcceptUserId(id, orderId, acceptUserId);
        return "redirect:/order/detail.jhtml?id=" + orderId;
    }

    @RequestMapping(name = "修改预约时间", path = "/updateAppointmentTime.jhtml")
    public String updateAppointmentTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, Order order) {

        orderService.update(order);

        return "redirect:/order/detail.jhtml?id=" + order.getId();
    }

    @RequestMapping(name = "修改服务地址", path = "/updateDetailAddress.jhtml")
    public String updateDetailAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, OrderOther orderOther) {
        System.out.println(orderOther.getAddress());
        orderOtherService.updateDetailAddress(orderOther);

        return "redirect:/order/detail.jhtml?id=" + orderOther.getId();
    }

    /**
     * @param hs
     * @param req
     * @param resp
     * @param modelMap
     * @param transaction
     * @param type
     *            支付类型<!-- (1支付宝，2微信，3余额，4银联，5vip卡支付) -->
     * @return
     */
    @RequestMapping(name = "退款", path = "/refund.jhtml")
    public String refund(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Transaction transaction, String totalMoney, String cancelOrderId) {

        String i = transactionService.refund(transaction, totalMoney);
        if (Integer.parseInt(i) == 1) {
            CancelOrder cancelOrder = new CancelOrder();
            cancelOrder.setStatus(1);
            cancelOrder.setId(cancelOrderId);
            cancelOrderService.update(cancelOrder);
        }
        return "redirect:/order/detail.jhtml?id=" + transaction.getOrderId();
    }

    @RequestMapping(name = "跳转到添加页", path = "/edit.jhtml")
    public String edit(ModelMap modelMap) {

        return "order/order/detail/edit";
    }

    @RequestMapping(name = "跳转到修改页", path = "/toEdit.jhtml")
    public String toEdit(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id,
            Nurse nurse) {
        Order order = orderService.loadById(id);
        List<Nurse> list = nurseService.getSomeNurse(nurse);

        modelMap.put("list", list);
        modelMap.put("order", order);
        return "order/order/detail/edit";
    }

    @RequestMapping(name = "生成订单Excel", path = "/getExcel.jhtml")
    @ResponseBody
    public void getExcel(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Order order) {

        List<Map<String, Object>> list = orderService.exportExcel(order);
        JSONArray ja = new JSONArray();
        for (Map<String, Object> map : list) {
            ja.add(map);
        }

        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("status", "订单状态");
        headMap.put("order_no", "订单编号");
        headMap.put("userName", "姓名");
        headMap.put("userSex", "性别");
        headMap.put("userPhone", "联系方式");
        headMap.put("address", "地址");
        headMap.put("detail_address", "详细地址");
        headMap.put("create_time", "下单时间");
        headMap.put("appointment_time", "服务时间");
        headMap.put("productName", "所属品类");
        headMap.put("goodsName", "服务名称");
        headMap.put("serviceName", "服务规格及套餐");
        headMap.put("pay_price", "下单价格");
        headMap.put("remarks", "服务备注");
        headMap.put("nurseName", "接单护士");
        headMap.put("nursePhone", "接单护士电话");
        headMap.put("content", "订单备注");

        String title = "服务订单信息";
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        String phone = systemUser.getPhone();
        String name = systemUser.getName();

        ExportFile exportFile = new ExportFile();
        exportFile.setId(UUIDUtils.getId());
        exportFile.setFileName(title + ".xlsx");
        exportFile.setCreatorIp(ip);
        exportFile.setCreatorName(name);
        exportFile.setCreatorPhone(phone);
        exportFile.setCreatorId(systemUser.getId());
        exportFile.setCreateTime(new Date());
        exportFile.setStatus(1);
        exportFileService.insert(exportFile);
        ExcelUtil.downloadExcelFile(title, headMap, ja, resp);
    }

}
