package com.jinpaihushi.jphs.sms.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.sms.model.SmsHistory;
import com.jinpaihushi.jphs.sms.model.SmsTemplate;
import com.jinpaihushi.jphs.sms.service.SmsHistoryService;
import com.jinpaihushi.jphs.sms.service.SmsTemplateService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.util.POIUtil;
import com.jinpaihushi.utils.PageInfos;

import net.sf.json.JSONObject;

import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Controller
@RequestMapping(name = "短信中心", path = "/sms/history")
public class SmsHistoryController extends BaseController<SmsHistory> {

	@Autowired
	private SmsHistoryService smsHistoryService;
	
	@Autowired
	private SmsTemplateService smsTemplateService;
	
	 @Autowired
	private DoPostSmsService doPostSmsService;
	

	@Override
	protected BaseService<SmsHistory> getService() {
		return smsHistoryService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			SmsHistory smsHistory, Integer p, Integer n) {
		startPage(p, n);
		smsHistory.setStatus(0);
		smsHistory.setOrderby("create_time DESC");
		Page<SmsHistory> list = smsHistoryService.query(smsHistory);
		PageInfos<SmsHistory> pageInfo = new PageInfos<SmsHistory>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "message/sms/history/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SmsHistory smsHistory = smsHistoryService.loadById(id);
		modelMap.put("smsHistory", smsHistory);
		return "message/sms/history/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap,SmsTemplate template) {
		template.setStatus(0);
		template.setType(1);
		List<SmsTemplate> list= smsTemplateService.query(template);
		modelMap.put("list", list);
		template.setStatus(0);
		template.setType(2);
		List<SmsTemplate> list2= smsTemplateService.query(template);
		modelMap.put("list2", list2);
		return "message/sms/history/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id,SmsHistory smsHistory) {
		//SmsHistory smsHistory = smsHistoryService.loadById(id);
		//modelMap.put("smsHistory", smsHistory);
		//startPage(p, n);
		smsHistory.setId(id);
		Page<SmsHistory> list = smsHistoryService.query(smsHistory);
		//PageInfos<SmsHistory> pageInfo = new PageInfos<SmsHistory>(list, req);
		modelMap.put("smsHistory", list);
		//modelMap.put("pageInfo", pageInfo);
		return "message/sms/history/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,SmsHistory smsHistory) {
		String ip="";
		  try {
	            SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
	            smsHistory.setCreatorId(systemUser.getId());
	            smsHistory.setCreatorName(systemUser.getName());
	            
	             ip = req.getHeader("x-forwarded-for");
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = req.getHeader("Proxy-Client-IP");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = req.getHeader("WL-Proxy-Client-IP");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = req.getRemoteAddr();
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = req.getHeader("http_client_ip");
	            }
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	                ip = req.getHeader("HTTP_X_FORWARDED_FOR");
	            }
	            // 如果是多级代理，那么取第一个ip为客户ip
	            if (ip != null && ip.indexOf(",") != -1) {
	                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
	            }
	          System.out.println(ip);
	        }
	        catch (Exception e) {
	        }
		if (smsHistory.getId() != null && !smsHistory.getId().equals("")) {
			boolean b = smsHistoryService.update(smsHistory);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/sms/history/err.jhtml";
			}
		} else {
			String tid=smsHistory.getSmsTemplateId().replaceAll(",", "");
			String phone=smsHistory.getPhone().trim();
			SmsTemplate template= smsTemplateService.loadById(tid);
			String sms_id=template.getSmsId().trim();
			if(phone.equals("")||phone==null||sms_id.equals("")||sms_id==null)
			{
				return "redirect:/sms/history/err.jhtml";
			}
			 // 发送短信
            doPostSmsService.sendSms(phone, sms_id, "");
			smsHistory.setId(UUID.randomUUID().toString());
		
			smsHistory.setSmsTemplateId(tid);
			smsHistory.setPhone(phone);
			smsHistory.setSendIp(ip);
			String result = smsHistoryService.insert(smsHistory);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/sms/history/err.jhtml";
			}
		}
		return "redirect:/sms/history/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		SmsHistory history=smsHistoryService.loadById(id);
		history.setStatus(-1);
		boolean b = smsHistoryService.update(history);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/sms/history/err.jhtml";
		}
		
		return "redirect:/sms/history/index.jhtml";
	}
	
	@ResponseBody
	@RequestMapping(name = "读取手机号", path = "/readFile.json")
	public JSONObject readFile(@RequestParam("file") MultipartFile  file) {
		String phone="";
		JSONObject js =new JSONObject();
		//获取文件后缀(不支持其他文件)
		//1.execle
		//2.txt
		System.out.println(file.getOriginalFilename());
		 String prefix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		 if(!prefix.equals("xlsx"))
		 {
			 js.put("message", "格式不支持！");
			 js.put("phone", "");
			 return js;
		 }
		try {
				String phone_old=POIUtil.readExcelToSting(file);
				phone=phone_old.substring(0,phone_old.length()-1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String phoneLength []=phone.split(",");
		 js.put("message", "已成功导入"+(phoneLength.length+1)+"条手机号码。");
		 js.put("phone", phone);
		
		return js;
	}
	
	
	

}
