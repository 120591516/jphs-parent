package com.jinpaihushi.jphs.commodity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.service.CommodityLogisticsService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.commodity.service.CommodityReturnService;
import com.jinpaihushi.jphs.export.model.ExportFile;
import com.jinpaihushi.jphs.export.service.ExportFileService;
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.logistics.service.LogisticsService;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.remark.model.Remark;
import com.jinpaihushi.jphs.remark.service.RemarkService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.logistics.KdniaoTrackQueryAPI;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.jinpaihushi.util.ExcelUtil;

/**
 * 
 * @author yangsong
 * @date 2017-09-21 09:17:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品订单", path = "/commodity/order")
public class CommodityOrderController extends BaseController<CommodityOrder> {

    @Autowired
    private CommodityOrderService commodityOrderService;
	@Autowired
	private CommodityOrderInfoService commodityOrderInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExportFileService exportFileService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private CommodityLogisticsService commodityLogisticsService;
	@Autowired
	private CommodityReturnService commodityReturnService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private	RemarkService remarkService;


    @Override
    protected BaseService<CommodityOrder> getService() {
        return commodityOrderService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CommodityOrder commodityOrder, Integer p, Integer n) {
       
        //Page<CommodityOrder> list = commodityOrderService.query(commodityOrder);
		
		System.out.println(StringUtils.isEmpty(commodityOrder.getTitle()));
			
		CommodityOrderInfo coi = new CommodityOrderInfo();
		List<CommodityOrderInfo> coiList = new ArrayList<CommodityOrderInfo>();
	
		if(!(StringUtils.isEmpty(commodityOrder.getTitle()))){
			coi.setTitle(commodityOrder.getTitle());
			coiList = commodityOrderInfoService.list(coi);
			String ids = "";
			System.out.println(coiList.size());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for (int i = 0;i<coiList.size() ;i++ ){
				 
				ids +=",'"+coiList.get(i).getCommodityOrderId()+"'";
			}
			if(ids!=""){
				commodityOrder.setId(ids.substring(1));
			} 
			
		}
		startPage(p, n);
        Page<CommodityOrder> list = null;

		if(commodityOrder.getFlag() == 1){
			list = commodityOrderService.getList(commodityOrder);
		}else{
			list = commodityOrderService.getTkList(commodityOrder);
		}
		for (int i = 0; i<list.size();i++ ){
			 
			List<CommodityReturn> crList = commodityReturnService.getListByCoId(list.get(i).getId());
			list.get(i).setFlag(crList.size());
		}
        PageInfos<CommodityOrder> pageInfo = new PageInfos<CommodityOrder>(list, req);
		Logistics l = new Logistics();
		l.setStatus(0);
		List<Logistics> lList = logisticsService.list(l);
        modelMap.put("list", list);
		modelMap.put("lList", lList);
        modelMap.put("pageInfo", pageInfo);
        return "order/commodity/order/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        CommodityOrder commodityOrder = commodityOrderService.loadById(id);

        modelMap.put("commodityOrder", commodityOrder);
		
        return "order/commodity/order/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "order/commodity/order/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) throws Exception {
        CommodityOrder commodityOrder = commodityOrderService.loadById(id);
		List<CommodityOrderInfo> coiList= commodityOrderInfoService.getList(commodityOrder.getId());
		String orderNo = commodityOrder.getOrderNo();
			 
		String[] orderArr = orderNo.split("-");
		Integer count  = commodityOrderInfoService.getCountByOrderNo(orderArr[0]);
		for (int i = 0; i<coiList.size();i++ ){
			CommodityReturn commodityReturn = new CommodityReturn();
			commodityReturn.setCommodityOrderInfoId(coiList.get(i).getId());
			
			CommodityReturn cr = commodityReturnService.getNotStatus(commodityReturn);
			if(cr!=null){
				coiList.get(i).setCrReason(cr.getReason());
				coiList.get(i).setCrStatus(cr.getStatus());
				coiList.get(i).setCrId(cr.getId());
			}
		}
		String remark = "";
		if(coiList.size()>0){
			remark = coiList.get(0).getRemark();
		}
		User user = userService.loadById(commodityOrder.getCreatorId());
		 
		CommodityLogistics cl =  commodityLogisticsService.getInfo(id);


		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();

		Remark remarkModel = new Remark();
		remarkModel.setSourceId(id);
		Remark r = remarkService.load(remarkModel);
		if(r!=null){
			modelMap.put("r", r);
		}

		if(cl!=null){
		String result = api.getOrderTracesByJson(cl.getCode(), cl.getNo());
			
		JSONArray arry = JSONObject.fromObject(result).getJSONArray("Traces");
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < arry.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String AcceptStation = arry.getJSONObject(i).getString("AcceptStation") ;
				String AcceptTime = arry.getJSONObject(i).getString("AcceptTime") ;
				map.put("AcceptTime", AcceptTime);
				map.put("AcceptStation", AcceptStation);
				list.add(map);
			}
			modelMap.put("LogisticsList", list);
		}

		Transaction transaction = new Transaction();
		transaction.setOrderId(id);
		transaction.setOperate(3);
		Transaction t = transactionService.load(transaction);

		Logistics l = new Logistics();
		l.setStatus(0);
		List<Logistics> lList = logisticsService.list(l);


		 
		List<CommodityReturn> crList = commodityReturnService.getListByCoId(id);
		if(crList.size() > 0){
			commodityOrder.setFlag(2);
		}
		
		modelMap.put("lList", lList);
        modelMap.put("commodityOrder", commodityOrder);
		modelMap.put("coiList", coiList);
		modelMap.put("user", user);
		modelMap.put("remark", remark);
		modelMap.put("cl", cl);
		modelMap.put("transaction", t);
		
		modelMap.put("count", count);

        return "order/commodity/order/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            CommodityOrder commodityOrder) {

        if (commodityOrder.getId() != null && !commodityOrder.getId().equals("")) {
            boolean b = commodityOrderService.update(commodityOrder);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/commodity/order/err.jhtml";
            }
        }
        else {
            commodityOrder.setId(UUID.randomUUID().toString());
            String result = commodityOrderService.insert(commodityOrder);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/commodity/order/err.jhtml";
            }
        }
        return "redirect:/commodity/order/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = commodityOrderService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/commodity/order/err.jhtml";
        }

        return "redirect:/commodity/order/index.jhtml";
    }


	@RequestMapping(name = "添加物流信息", path = "/addLogistics.json")
	@ResponseBody
	public JSONObject addLogistics(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityLogistics commodityLogistics) {
			 JSONObject message = new JSONObject();
			

			commodityLogistics.setType(1);
			commodityLogistics.setStatus(1);
			String no = commodityLogistics.getNo();
			commodityLogistics.setNo("");
			CommodityLogistics cl =	commodityLogisticsService.load(commodityLogistics);
			if(cl == null){
				commodityLogistics.setId(UUID.randomUUID().toString());
				commodityLogistics.setNo(no);
				String result = commodityLogisticsService.insert(commodityLogistics);
				if(result.length() >0){
					CommodityOrder commodityOrder = new CommodityOrder();
					commodityOrder.setId(commodityLogistics.getCommodityOrderId());
					commodityOrder.setSchedule(2);
					commodityOrder.setSendTime(new Date());
					boolean b = commodityOrderService.update(commodityOrder);
					if(!b){
						message.put("result", "0");
					}else{
						message.put("result", "1");
					}
				}
			}else{
				message.put("result", "0");
			}
		 return message;
	}


	@RequestMapping(name = "生成订单Excel", path = "/getExcel.jhtml")
	@ResponseBody
	public void getExcel(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			CommodityOrder commodityOrder) {

		CommodityOrderInfo coi = new CommodityOrderInfo();
		List<CommodityOrderInfo> coiList = new ArrayList<CommodityOrderInfo>();
	
		if(!(StringUtils.isEmpty(commodityOrder.getTitle()))){
			coi.setTitle(commodityOrder.getTitle());
			coiList = commodityOrderInfoService.list(coi);
			String ids = "";
			System.out.println(coiList.size());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for (int i = 0;i<coiList.size() ;i++ ){
				 
				ids +=",'"+coiList.get(i).getCommodityOrderId()+"'";
			}
			if(ids!=""){
				commodityOrder.setId(ids.substring(1));
			} 
			
		}
		 
        Page<CommodityOrder> list = null;

		if(commodityOrder.getFlag() == 1){
			list = commodityOrderService.getList(commodityOrder);
		}else{
			list = commodityOrderService.getTkList(commodityOrder);
		}
		for (int i = 0; i<list.size();i++ ){
			 
			List<CommodityReturn> crList = commodityReturnService.getListByCoId(list.get(i).getId());
			list.get(i).setFlag(crList.size());
		}
		int count = list.size();
 
		 com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray();

		for (int i = 0; i < list.size(); i++) {

			Integer schedule = list.get(i).getSchedule();
			String status = "";
			String tkFlag = "无";
			if(schedule == 0){
				status = "待支付";
			}else if(schedule == 1){
				status = "待发货";
			}else if(schedule == 2){
				status = "待收货";
			}else if(schedule == 4){
				status = "已完成";
			}else if(schedule == 5){
				status = "用户已删除";
			}else if(schedule == -3){
				status = "已退货";
			}
			

			String comInfo = "";
			CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
			commodityOrderInfo.setCommodityOrderId(list.get(i).getId());
			List<CommodityOrderInfo> coList= commodityOrderInfoService.list(commodityOrderInfo);
			String comRemark = "";
			for (int j = 0;j<coList.size() ;j++ ){
				comInfo += coList.get(j).getTitle()+"X"+coList.get(j).getNumber()+"；";
				comRemark = coList.get(j).getRemark();
			}


			if(list.get(i).getFlag()>0){
				tkFlag = "有";
			}

			Remark remark = new Remark();
			remark.setSourceId(list.get(i).getId());
			Remark r = remarkService.load(remark);

			String content = "暂无";
			if(r!=null){
				content = r.getContent();
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			String time = format.format(list.get(i).getCreateTime().getTime());
			 Map<String, Object> map = new HashMap<String,Object>();
			 map.put("xh", i+1);
			 map.put("orderNo", list.get(i).getOrderNo());
			 map.put("status", status);
			 map.put("userName", list.get(i).getReceiveName());
			 map.put("phone",list.get(i).getPhone());
			 map.put("address", list.get(i).getAddress());
			 map.put("detailAddress", list.get(i).getDetailAddress());
			 map.put("createTime",time );
			 map.put("comAndNum",comInfo);
			 map.put("tkFlag", tkFlag);
			 map.put("payPrice",  list.get(i).getPayPrice());
			 map.put("remark",comRemark);
			 map.put("content", content);
			ja.add(map);
		}

		Map<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("xh", "序号");
		headMap.put("orderNo", "订单编号");
		headMap.put("status", "订单状态");
		headMap.put("userName", "姓名");
		headMap.put("phone", "联系方式");
		headMap.put("address", "地址");
		headMap.put("detailAddress", "详细地址");
		headMap.put("createTime", "下单时间");
		headMap.put("comAndNum", "所含商品及数量");
		headMap.put("tkFlag", "退款请求");
		headMap.put("payPrice", "支付金额");
		headMap.put("remark", "商品备注");
		headMap.put("content", "订单备注");

		String title = "商品订单信息";
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
		exportFile.setFileName(title+".xlsx");
		exportFile.setCreatorIp(ip);
		exportFile.setCreatorName(name);
		exportFile.setCreatorPhone(phone);
		exportFile.setCreatorId(systemUser.getId());
		exportFile.setCreateTime(new Date());
		exportFile.setStatus(1);
		exportFileService.insert(exportFile);
		ExcelUtil.downloadExcelFile(title, headMap, ja, resp);
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
	 
	@RequestMapping(name = "退款", path = "/refund.jhtml")
	public String refund(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Transaction transaction, String totalMoney, String crId) {
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		CommodityOrderInfo coi = new CommodityOrderInfo();
		coi.setCommodityOrderId(transaction.getOrderId());

		
		if(transaction.getPayType() == 2){
			String orderId = transaction.getOutTradeNo();
			 
			String[] orderArr = orderId.split("-");

			 
			transaction.setOutTradeNo(orderArr[0]);
		}
		String i = transactionService.refund(transaction, totalMoney);
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 if (Integer.parseInt(i) == 1) {
			CommodityReturn cr = new CommodityReturn();
			cr.setStatus(2);
			cr.setId(crId);
			commodityReturnService.update(cr);

			List<CommodityOrderInfo> coiList = commodityOrderInfoService.list(coi);
			System.out.println(coiList);
				int k = 0;
				for (int j = 0;j<coiList.size() ;j++ ){
					CommodityReturn cr1 = new CommodityReturn();
					System.out.println("ID===="+coiList.get(j).getId());
					cr1.setCommodityOrderInfoId(coiList.get(j).getId());
					cr1.setStatus(2);
					CommodityReturn crModel = commodityReturnService.load(cr1);
					if(crModel!=null){
						k++;
					}
				}
				System.out.println("k===="+k);
				if(k == coiList.size()){
					CommodityOrder co = new CommodityOrder();
					co.setId(transaction.getOrderId());
					co.setSchedule(-3);
					commodityOrderService.update(co);
				}
		}
	
			 
		return "redirect:/commodity/order/detail.jhtml?id=" + transaction.getOrderId();
	}

*/

@RequestMapping(name = "退款", path = "/refund.jhtml")
	@ResponseBody
	public JSONObject refund(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Transaction transaction, String totalMoney, String crId,String coiId) {
		JSONObject message = new JSONObject();
 
		
		CommodityOrderInfo coi = new CommodityOrderInfo();
		coi.setCommodityOrderId(transaction.getOrderId());

		
		if(transaction.getPayType() == 2){
			String orderId = transaction.getOutTradeNo();
			 
			String[] orderArr = orderId.split("-");

			 
			transaction.setOutTradeNo(orderArr[0]);
		}
		String i = transactionService.refund(transaction, totalMoney);
		 if (Integer.parseInt(i) == 1) {
			CommodityReturn cr = new CommodityReturn();
			cr.setStatus(2);
			cr.setId(crId);
			commodityReturnService.update(cr);

			System.out.println(".................................................................");
			CommodityOrderInfo coiModel = commodityOrderInfoService.loadById(coiId);
			System.out.println("coiModel==STATUS=="+coiModel.getStatus());

			if(coiModel !=null){
				 coiModel.setStatus(-1);
				 commodityOrderInfoService.update(coiModel);
			}

			List<CommodityOrderInfo> coiList = commodityOrderInfoService.list(coi);
			System.out.println(coiList);
				int k = 0;
				for (int j = 0;j<coiList.size() ;j++ ){
					CommodityReturn cr1 = new CommodityReturn();
					System.out.println("ID===="+coiList.get(j).getId());
					cr1.setCommodityOrderInfoId(coiList.get(j).getId());
					cr1.setStatus(2);
					CommodityReturn crModel = commodityReturnService.load(cr1);
					if(crModel!=null){
						k++;
					}
				}
				System.out.println("k===="+k);
				if(k == coiList.size()){
					CommodityOrder co = new CommodityOrder();
					co.setId(transaction.getOrderId());
					co.setSchedule(-3);
					commodityOrderService.update(co);
				}
		}
	
			 
		return message;
	}


@RequestMapping(name = "退款", path = "/update.jhtml")
	@ResponseBody
	public JSONObject update(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			 String crId,String orderId) {
		JSONObject message = new JSONObject();
 
		 	CommodityOrderInfo coi = new CommodityOrderInfo();
			coi.setCommodityOrderId(orderId);
		 
			CommodityReturn cr = new CommodityReturn();
			cr.setStatus(2);
			cr.setId(crId);
			System.out.println("crId===="+cr.getId());
			commodityReturnService.update(cr);

			List<CommodityOrderInfo> coiList = commodityOrderInfoService.list(coi);
				
				int k = 0;
				for (int j = 0;j<coiList.size() ;j++ ){
					CommodityReturn cr1 = new CommodityReturn();
					System.out.println("ID===="+coiList.get(j).getId());
					cr1.setCommodityOrderInfoId(coiList.get(j).getId());
					cr1.setStatus(2);
					CommodityReturn crModel = commodityReturnService.load(cr1);
					if(crModel!=null){
						k++;
					}
				}
				System.out.println("k===="+k);
				if(k == coiList.size()){
					CommodityOrder co = new CommodityOrder();
					co.setId(orderId);
					co.setSchedule(-3);
					commodityOrderService.update(co);
				}
		 
	
			 
		return message;
	}
}
