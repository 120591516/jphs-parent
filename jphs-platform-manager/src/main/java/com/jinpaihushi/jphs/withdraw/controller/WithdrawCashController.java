package com.jinpaihushi.jphs.withdraw.controller;

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
import com.jinpaihushi.jphs.export.model.ExportFile;
import com.jinpaihushi.jphs.export.service.ExportFileService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.jphs.withdraw.service.WithdrawCashService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.util.ExcelUtil;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
@Controller
@RequestMapping(name = "提现", path = "/withdraw/cash")
public class WithdrawCashController extends BaseController<WithdrawCash> {

    @Autowired
    private WithdrawCashService withdrawCashService;
    @Autowired
    private ExportFileService exportFileService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @Override
    protected BaseService<WithdrawCash> getService() {
        return withdrawCashService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            WithdrawCash withdrawCash, Integer p, Integer n) {
        startPage(p, n);
        withdrawCash.setOrderby("WC.create_time DESC");
        Page<WithdrawCash> list = withdrawCashService.query(withdrawCash);
        PageInfos<WithdrawCash> pageInfo = new PageInfos<WithdrawCash>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "withdraw/cash/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id, Transaction transaction ,Integer p, Integer n) {
        /*WithdrawCash withdrawCash = withdrawCashService.withdrawCashTDetail(id);
        modelMap.put("withdrawCash", withdrawCash);
        JSONObject j = new JSONObject().fromObject(withdrawCash);
        System.out.println("---"+j);
        return "withdraw/cash/edit";*/
    	 WithdrawCash withdrawCash = withdrawCashService.loadById(id);
    	 
    	 User user= userService.loadById(withdrawCash.getCreatorId());
    	 withdrawCash.setName(user.getName());
    	 withdrawCash.setPhone(user.getPhone());
    	 
         transaction.setId(id);
         startPage(p, n);
         Page<Transaction> list = transactionService.withdrawCashTransactionDetail(transaction);
         PageInfos<Transaction> pageInfo = new PageInfos<Transaction>(list, req);
         System.out.println("---"+new net.sf.json.JSONArray().fromObject(list));
         modelMap.put("list", list);
         modelMap.put("pageInfo", pageInfo);
         modelMap.put("withdrawCash", withdrawCash);
         return "withdraw/cash/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "withdraw/cash/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id, Transaction transaction ,Integer p, Integer n) {
        WithdrawCash withdrawCash = withdrawCashService.withdrawCashTDetail(id);
        transaction.setId(id);
        startPage(p, n);
        Page<Transaction> list = transactionService.withdrawCashTransactionDetail(transaction);
        PageInfos<Transaction> pageInfo = new PageInfos<Transaction>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("withdrawCash", withdrawCash);
        return "withdraw/cash/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            WithdrawCash withdrawCash) {
/*
        if (withdrawCash.getId() != null && !withdrawCash.getId().equals("")) {
            boolean b = withdrawCashService.update(withdrawCash);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/withdraw/cash/err.jhtml";
            }
        }
        else {
            withdrawCash.setId(UUID.randomUUID().toString());
            String result = withdrawCashService.insert(withdrawCash);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/withdraw/cash/err.jhtml";
            }
        }*/
    	String id = "";
    	String name = "";
    	 try {
             SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
             id = systemUser.getId();
             name = systemUser.getName();
         }
         catch (Exception e) {
         }
    	 int b = withdrawCashService.withdrawCashTUpdate(withdrawCash.getId(),withdrawCash.getStatus(),withdrawCash.getRemark(),id,name);
         if (b != 1) {
             // 跳转到错误页
             return "redirect:/withdraw/cash/err.jhtml";
         }
        return "redirect:/withdraw/cash/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = withdrawCashService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/withdraw/cash/err.jhtml";
        }

        return "redirect:/withdraw/cash/index.jhtml";
    }


    @RequestMapping(name = "生成用户Excel", path = "/getExcel.jhtml")
	@ResponseBody
	public void getExcel(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			WithdrawCash withdrawCash) {
		List<Map<String, Object>> list = withdrawCashService.exportExcel(withdrawCash);
		JSONArray ja = new JSONArray();
		for (Map<String, Object> map : list) {
			ja.add(map);
		}
		Map<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("name", "姓名");
		headMap.put("phone", "手机号");
		headMap.put("amount", "提现金额");
		headMap.put("account_name", "账户名");
		headMap.put("alipay_account", "支付宝账户");
		headMap.put("wcStatus", "状态");
		headMap.put("audit_time", "审核时间");
		headMap.put("audit_user_name", "审核人");
		headMap.put("remark", "备注");
		headMap.put("create_time", "提现提交时间");

		String title = "护士提现记录";
		
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
    
}
