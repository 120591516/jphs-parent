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
import com.jinpaihushi.jphs.family.model.FamilyRegister;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.jphs.family.service.FamilyRegisterService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:57
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyRegister", path = "/family/register")
public class FamilyRegisterController extends BaseController<FamilyRegister> {

	@Autowired
	private FamilyRegisterService familyRegisterService;
	@Autowired
	private FamilyOrderService familyOrderService;

	@Override
	protected BaseService<FamilyRegister> getService() {
		return familyRegisterService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyRegister familyRegister, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyRegister> list = familyRegisterService.query(familyRegister);
		PageInfos<FamilyRegister> pageInfo = new PageInfos<FamilyRegister>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/register/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyRegister familyRegister = familyRegisterService.loadById(id);
		modelMap.put("familyRegister", familyRegister);
		return "family/register/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,ModelMap modelMap,String id) {
		FamilyOrder familyOrder= familyOrderService.loadById(id);
		modelMap.put("familyOrder", familyOrder);
		return "family/register/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyRegister familyRegister = familyRegisterService.loadById(id);
		modelMap.put("familyRegister", familyRegister);
		return "family/register/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyRegister familyRegister) {

		if (familyRegister.getId() != null && !familyRegister.getId().equals("")) {
			boolean b = familyRegisterService.update(familyRegister);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/register/err.jhtml";
			}
		} else {
			try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyRegister.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
			familyRegister.setId(UUID.randomUUID().toString());
			String result = familyRegisterService.insert(familyRegister);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/register/err.jhtml";
			}
		}
		return "redirect:/family/order/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyRegisterService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/register/err.jhtml";
		}

		return "redirect:/family/register/index.jhtml";
	}
	
	

}
