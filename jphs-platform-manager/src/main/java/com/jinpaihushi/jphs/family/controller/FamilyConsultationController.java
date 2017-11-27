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
import com.jinpaihushi.jphs.family.model.FamilyConsultation;
import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.service.FamilyConsultationService;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:56
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyConsultation", path = "/family/consultation")
public class FamilyConsultationController extends BaseController<FamilyConsultation> {

	@Autowired
	private FamilyConsultationService familyConsultationService;
	@Autowired
	private FamilyOrderService familyOrderService;

	@Override
	protected BaseService<FamilyConsultation> getService() {
		return familyConsultationService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyConsultation familyConsultation, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyConsultation> list = familyConsultationService.query(familyConsultation);
		PageInfos<FamilyConsultation> pageInfo = new PageInfos<FamilyConsultation>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/consultation/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyConsultation familyConsultation = familyConsultationService.loadById(id);
		modelMap.put("familyConsultation", familyConsultation);
		return "family/consultation/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,ModelMap modelMap,String id) {
		FamilyOrder familyOrder= familyOrderService.loadById(id);
		modelMap.put("familyOrder", familyOrder);
		return "family/consultation/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyConsultation familyConsultation = familyConsultationService.loadById(id);
		modelMap.put("familyConsultation", familyConsultation);
		return "family/consultation/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyConsultation familyConsultation) {

		if (familyConsultation.getId() != null && !familyConsultation.getId().equals("")) {
			boolean b = familyConsultationService.update(familyConsultation);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/consultation/err.jhtml";
			}
		} else {
			try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyConsultation.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
			familyConsultation.setId(UUID.randomUUID().toString());
			String result = familyConsultationService.insert(familyConsultation);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/consultation/err.jhtml";
			}
		}
		return "redirect:/family/order/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyConsultationService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/consultation/err.jhtml";
		}

		return "redirect:/family/consultation/index.jhtml";
	}
	
	

}
