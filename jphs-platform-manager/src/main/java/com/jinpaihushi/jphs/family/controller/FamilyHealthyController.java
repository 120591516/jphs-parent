package com.jinpaihushi.jphs.family.controller;

import java.util.List;
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
import com.jinpaihushi.jphs.family.model.FamilyHealthy;
import com.jinpaihushi.jphs.family.model.FamilyMember;
import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.service.FamilyHealthyService;
import com.jinpaihushi.jphs.family.service.FamilyMemberService;
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
@RequestMapping(name = "FamilyHealthy", path = "/family/healthy")
public class FamilyHealthyController extends BaseController<FamilyHealthy> {

	@Autowired
	private FamilyHealthyService familyHealthyService;
	@Autowired
	private FamilyOrderService familyOrderService;
	@Autowired
	private FamilyMemberService familyMemberService;
	
	@Override
	protected BaseService<FamilyHealthy> getService() {
		return familyHealthyService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyHealthy familyHealthy, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyHealthy> list = familyHealthyService.query(familyHealthy);
		PageInfos<FamilyHealthy> pageInfo = new PageInfos<FamilyHealthy>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/healthy/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyHealthy familyHealthy = familyHealthyService.loadById(id);
		modelMap.put("familyHealthy", familyHealthy);
		return "family/healthy/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,ModelMap modelMap,String id) {
		FamilyOrder familyOrder= familyOrderService.loadById(id);
		FamilyMember familyMembers = new FamilyMember();
		familyMembers.setStatus(1);
		familyMembers.setCreatorId(familyOrder.getCreatorId());
		List<FamilyMember> familyMember =familyMemberService.list(familyMembers);
		modelMap.put("familyMemberList", familyMember);
		modelMap.put("familyOrder", familyOrder);
		return "family/healthy/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			FamilyHealthy familyHealthy) {
		FamilyHealthy familyHealthys = familyHealthyService.getDetailMemberAndHealthy(familyHealthy);
		modelMap.put("familyHealthy", familyHealthys);
		return "family/healthy/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyHealthy familyHealthy) {

		if (familyHealthy.getId() != null && !familyHealthy.getId().equals("")) {
			boolean b = familyHealthyService.update(familyHealthy);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/healthy/err.jhtml";
			}
		} else {
			try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyHealthy.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
			familyHealthy.setId(UUID.randomUUID().toString());
			String result = familyHealthyService.insert(familyHealthy);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/healthy/err.jhtml";
			}
		}
		return "redirect:/family/order/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyHealthyService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/healthy/err.jhtml";
		}

		return "redirect:/family/healthy/index.jhtml";
	}
	
	

}
