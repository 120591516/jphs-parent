package com.jinpaihushi.jphs.family.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

import net.sf.json.JSONObject;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:57
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyOrder", path = "/family/order")
public class FamilyOrderController extends BaseController<FamilyOrder> {

	@Autowired
	private FamilyOrderService familyOrderService;

	@Override
	protected BaseService<FamilyOrder> getService() {
		return familyOrderService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyOrder familyOrder, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyOrder> list = familyOrderService.familyOrderIndex(familyOrder);
		PageInfos<FamilyOrder> pageInfo = new PageInfos<FamilyOrder>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/order/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyOrder familyOrders = new FamilyOrder();
		familyOrders.setId(id);
		FamilyOrder familyOrder = familyOrderService.familyOrderOneId(familyOrders);
		modelMap.put("familyOrder", familyOrder);
		return "family/order/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "family/order/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyOrder familyOrders = new FamilyOrder();
		familyOrders.setId(id);
		FamilyOrder familyOrder = familyOrderService.familyOrderOneId(familyOrders);
		System.out.println(familyOrder.toString());
		System.out.println(new JSONObject().fromObject(familyOrder));
		modelMap.put("familyOrder", familyOrder);
		return "family/order/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyOrder familyOrder) {

		if (familyOrder.getId() != null && !familyOrder.getId().equals("")) {
			boolean b = familyOrderService.update(familyOrder);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/order/err.jhtml";
			}
		} else {
			familyOrder.setId(UUID.randomUUID().toString());
			String result = familyOrderService.insert(familyOrder);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/order/err.jhtml";
			}
		}
		return "redirect:/family/order/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyOrderService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/order/err.jhtml";
		}

		return "redirect:/family/order/index.jhtml";
	}
	
	

}
