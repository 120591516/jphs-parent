package com.jinpaihushi.jphs.remark.controller;

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
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.remark.model.Remark;
import com.jinpaihushi.jphs.remark.service.RemarkService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-09-29 16:55:54
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Remark", path = "/remark")
public class RemarkController extends BaseController<Remark> {

	@Autowired
	private RemarkService remarkService;

	@Override
	protected BaseService<Remark> getService() {
		return remarkService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Remark remark, Integer p, Integer n) {
		startPage(p, n);
		Page<Remark> list = remarkService.query(remark);
		PageInfos<Remark> pageInfo = new PageInfos<Remark>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "shop/remark/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Remark remark = remarkService.loadById(id);
		modelMap.put("remark", remark);
		return "shop/remark/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "shop/remark/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Remark remark = remarkService.loadById(id);
		modelMap.put("remark", remark);
		return "shop/remark/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Remark remark) {

		if (remark.getId() != null && !remark.getId().equals("")) {
			boolean b = remarkService.update(remark);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/remark/err.jhtml";
			}
		} else {
			remark.setId(UUID.randomUUID().toString());
			String result = remarkService.insert(remark);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/remark/err.jhtml";
			}
		}
		return "redirect:/remark/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = remarkService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/remark/err.jhtml";
		}

		return "redirect:/remark/index.jhtml";
	}
	
	
	@RequestMapping(name = "添加或修改数据", path = "/addRemark.json")
	@ResponseBody
	public JSONObject addRemark(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Remark remark) {
			
			 JSONObject message = new JSONObject();
		if (remark.getId() != null && !remark.getId().equals("")) {
			boolean b = remarkService.update(remark);
			if (b == false) {
				// 跳转到错误页
				 message.put("result", "0");
			}
				message.put("result", "1");
		} else {
			remark.setId(UUID.randomUUID().toString());
			String result = remarkService.insert(remark);
			if (result.length() <= 0) {
				// 跳转到错误页
				 message.put("result", "0");
			}
				message.put("result", "1");
		}
		return message;
	}
}
