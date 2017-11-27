package com.jinpaihushi.jphs.family.controller;

import java.util.Date;
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
import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.jphs.family.model.FamilyPackage;
import com.jinpaihushi.jphs.family.service.FamilyModeService;
import com.jinpaihushi.jphs.family.service.FamilyPackageService;
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
@RequestMapping(name = "FamilyMode", path = "/family/mode")
public class FamilyModeController extends BaseController<FamilyMode> {

	@Autowired
	private FamilyModeService familyModeService;
	@Autowired
	private FamilyPackageService familyPackageService;

	@Override
	protected BaseService<FamilyMode> getService() {
		return familyModeService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyMode familyMode, Integer p, Integer n) {
		startPage(p, n);
		Page<FamilyMode> list = familyModeService.familyIndexList(familyMode);
		PageInfos<FamilyMode> pageInfo = new PageInfos<FamilyMode>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/mode/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyMode familyMode = familyModeService.loadById(id);
		modelMap.put("familyMode", familyMode);
		FamilyPackage familyPackage = new FamilyPackage();
		familyPackage.setStatus(1);
		List<FamilyPackage> familyPackage_list = familyPackageService.list(familyPackage);
		modelMap.put("familyPackage_list", familyPackage_list);
		return "family/mode/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {
		FamilyPackage familyPackage = new FamilyPackage();
		familyPackage.setStatus(1);
		List<FamilyPackage> familyPackage_list = familyPackageService.list(familyPackage);
		modelMap.put("familyPackage_list", familyPackage_list);
		return "family/mode/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			FamilyMode familyMode ) {
		FamilyMode familyModes = familyModeService.detailPackageAndMode(familyMode);
		modelMap.put("familyMode", familyModes);
		return "family/mode/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyMode familyMode) {

		if (familyMode.getId() != null && !familyMode.getId().equals("")) {
			boolean b = familyModeService.update(familyMode);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/mode/err.jhtml";
			}
		} else {
			try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyMode.setCreatorId(systemUser.getId());
                familyMode.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
			familyMode.setCreateTime(new Date());
			familyMode.setId(UUID.randomUUID().toString());
			String result = familyModeService.insert(familyMode);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/mode/err.jhtml";
			}
		}
		return "redirect:/family/mode/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,FamilyMode familyMode) {

		boolean b = familyModeService.update(familyMode);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/mode/err.jhtml";
		}

		return "redirect:/family/mode/index.jhtml";
	}
	
	

}
