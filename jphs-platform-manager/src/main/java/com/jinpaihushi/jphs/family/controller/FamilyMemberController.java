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
import com.jinpaihushi.jphs.family.model.FamilyMember;
import com.jinpaihushi.jphs.family.service.FamilyMemberService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:56
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyMember", path = "/family/member")
public class FamilyMemberController extends BaseController<FamilyMember> {

	@Autowired
	private FamilyMemberService familyMemberService;

	@Override
	protected BaseService<FamilyMember> getService() {
		return familyMemberService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyMember familyMember, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyMember> list = familyMemberService.query(familyMember);
		PageInfos<FamilyMember> pageInfo = new PageInfos<FamilyMember>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/family/member/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyMember familyMember = familyMemberService.loadById(id);
		modelMap.put("familyMember", familyMember);
		return "family/family/member/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "family/family/member/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyMember familyMember = familyMemberService.loadById(id);
		modelMap.put("familyMember", familyMember);
		return "family/family/member/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyMember familyMember) {

		if (familyMember.getId() != null && !familyMember.getId().equals("")) {
			boolean b = familyMemberService.update(familyMember);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/member/err.jhtml";
			}
		} else {
			familyMember.setId(UUID.randomUUID().toString());
			String result = familyMemberService.insert(familyMember);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/member/err.jhtml";
			}
		}
		return "redirect:/family/member/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyMemberService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/member/err.jhtml";
		}

		return "redirect:/family/member/index.jhtml";
	}
	
	

}
