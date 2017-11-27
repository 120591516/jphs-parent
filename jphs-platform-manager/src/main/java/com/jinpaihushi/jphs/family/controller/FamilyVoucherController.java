package com.jinpaihushi.jphs.family.controller;

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
import com.jinpaihushi.jphs.family.model.FamilyVoucher;
import com.jinpaihushi.jphs.family.service.FamilyVoucherService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:57
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyVoucher", path = "/family/voucher")
public class FamilyVoucherController extends BaseController<FamilyVoucher> {

	@Autowired
	private FamilyVoucherService familyVoucherService;

	@Override
	protected BaseService<FamilyVoucher> getService() {
		return familyVoucherService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyVoucher familyVoucher, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyVoucher> list = familyVoucherService.query(familyVoucher);
		PageInfos<FamilyVoucher> pageInfo = new PageInfos<FamilyVoucher>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/family/voucher/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyVoucher familyVoucher = familyVoucherService.loadById(id);
		modelMap.put("familyVoucher", familyVoucher);
		return "family/family/voucher/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "family/family/voucher/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyVoucher familyVoucher = familyVoucherService.loadById(id);
		modelMap.put("familyVoucher", familyVoucher);
		return "family/family/voucher/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyVoucher familyVoucher) {

		if (familyVoucher.getId() != null && !familyVoucher.getId().equals("")) {
			boolean b = familyVoucherService.update(familyVoucher);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/voucher/err.jhtml";
			}
		} else {
			familyVoucher.setId(UUID.randomUUID().toString());
			String result = familyVoucherService.insert(familyVoucher);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/voucher/err.jhtml";
			}
		}
		return "redirect:/family/voucher/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyVoucherService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/voucher/err.jhtml";
		}

		return "redirect:/family/voucher/index.jhtml";
	}
	
	

}
