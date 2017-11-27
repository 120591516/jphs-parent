package com.jinpaihushi.jphs.sms.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.sms.model.SmsTemplate;
import com.jinpaihushi.jphs.sms.service.SmsTemplateService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Controller
@RequestMapping(name = "模板中心", path = "/sms/template")
public class SmsTemplateController extends BaseController<SmsTemplate> {

	@Autowired
	private SmsTemplateService smsTemplateService;

	@Override
	protected BaseService<SmsTemplate> getService() {
		return smsTemplateService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			SmsTemplate smsTemplate, Integer p, Integer n) {
		startPage(p, n);
		smsTemplate.setStatus(0);
		smsTemplate.setOrderby("create_time DESC");
		Page<SmsTemplate> list = smsTemplateService.query(smsTemplate);
		PageInfos<SmsTemplate> pageInfo = new PageInfos<SmsTemplate>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "message/sms/template/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SmsTemplate smsTemplate = smsTemplateService.loadById(id);
		modelMap.put("smsTemplate", smsTemplate);
		return "message/sms/template/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "message/sms/template/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		SmsTemplate smsTemplate = smsTemplateService.loadById(id);
		modelMap.put("smsTemplate", smsTemplate);
		return "message/sms/template/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, SmsTemplate smsTemplate) {
		   try {
	            SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
	            smsTemplate.setCreatorId(systemUser.getId());
	            smsTemplate.setCreatorName(systemUser.getName());
	        }
	        catch (Exception e) {
	        }
		if (smsTemplate.getId() != null && !smsTemplate.getId().equals("")) {
			boolean b = smsTemplateService.update(smsTemplate);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/sms/template/err.jhtml";
			}
		} else {
			smsTemplate.setId(UUID.randomUUID().toString());
			String result = smsTemplateService.insert(smsTemplate);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/sms/template/err.jhtml";
			}
		}
		return "redirect:/sms/template/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = smsTemplateService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/sms/template/err.jhtml";
		}

		return "redirect:/sms/template/index.jhtml";
	}
	
	

}
