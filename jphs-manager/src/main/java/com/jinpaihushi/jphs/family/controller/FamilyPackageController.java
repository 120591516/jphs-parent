package com.jinpaihushi.jphs.family.controller;

import java.util.Date;
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
import com.jinpaihushi.jphs.family.model.FamilyPackage;
import com.jinpaihushi.jphs.family.service.FamilyPackageService;
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
@RequestMapping(name = "FamilyPackage", path = "/family/package")
public class FamilyPackageController extends BaseController<FamilyPackage> {

	@Autowired
	private FamilyPackageService familyPackageService;

	@Override
	protected BaseService<FamilyPackage> getService() {
		return familyPackageService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyPackage familyPackage, Integer p, Integer n) {
		startPage(p, n);
		familyPackage.setOrderby("FP.create_time DESC");
		Page<FamilyPackage> list = familyPackageService.query(familyPackage);
		PageInfos<FamilyPackage> pageInfo = new PageInfos<FamilyPackage>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/package/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyPackage familyPackage = familyPackageService.loadById(id);
		modelMap.put("familyPackage", familyPackage);
		return "family/package/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "family/package/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyPackage familyPackage = familyPackageService.loadById(id);
		modelMap.put("familyPackage", familyPackage);
		return "family/package/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyPackage familyPackage) {

		if (familyPackage.getId() != null && !familyPackage.getId().equals("")) {
			boolean b = familyPackageService.update(familyPackage);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/package/err.jhtml";
			}
		} else {
		 try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyPackage.setCreatorId(systemUser.getId());
                familyPackage.setCreatorName(systemUser.getName());
            }
            catch (Exception e) {
            }
			familyPackage.setId(UUID.randomUUID().toString());
			familyPackage.setCreateTime(new Date());
			String result = familyPackageService.insert(familyPackage);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/family/package/err.jhtml";
			}
		}
		return "redirect:/family/package/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,  FamilyPackage familyPackage) {
		boolean b = familyPackageService.update(familyPackage);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/package/err.jhtml";
		}

		return "redirect:/family/package/index.jhtml";
	}
	
	

}
