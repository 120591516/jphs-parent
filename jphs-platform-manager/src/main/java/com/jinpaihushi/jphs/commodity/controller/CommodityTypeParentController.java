package com.jinpaihushi.jphs.commodity.controller;

import java.util.Map;
import java.util.UUID;
import java.util.Date;
import java.util.List;


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
import com.jinpaihushi.jphs.commodity.model.CommodityTypeParent;
import com.jinpaihushi.jphs.commodity.model.CommodityType;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeParentService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-10-20 18:20:46
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品一级分类", path = "/commodity/type/parent")
public class CommodityTypeParentController extends BaseController<CommodityTypeParent> {

	@Autowired
	private CommodityTypeParentService commodityTypeParentService;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private CommodityTypeService commodityTypeService;

	@Override
	protected BaseService<CommodityTypeParent> getService() {
		return commodityTypeParentService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			CommodityTypeParent commodityTypeParent, Integer p, Integer n) {
		startPage(p, n);
		Page<CommodityTypeParent> list = commodityTypeParentService.query(commodityTypeParent);
		CommodityType commodityType = new CommodityType();
		for (int i = 0;i<list.size() ;i++ ){
			SystemUser systemUser = systemUserService.loadById(list.get(i).getCreatorId());
			if(systemUser != null){
				list.get(i).setCreatorName(systemUser.getName());
			}
			
			commodityType.setCommodityTypeParentId(list.get(i).getId());
			List<CommodityType> ctList = commodityTypeService.list(commodityType);
			if(ctList.size()>0){
				list.get(i).setCount(ctList.size());
			}else{
				list.get(i).setCount(0);
			}

		}

		PageInfos<CommodityTypeParent> pageInfo = new PageInfos<CommodityTypeParent>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "shop/commodity/type/parent/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityTypeParent commodityTypeParent = commodityTypeParentService.loadById(id);
		modelMap.put("commodityTypeParent", commodityTypeParent);
		return "shop/commodity/type/parent/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "shop/commodity/type/parent/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityTypeParent commodityTypeParent = commodityTypeParentService.loadById(id);
		SystemUser systemUser = systemUserService.loadById(commodityTypeParent.getCreatorId());
		modelMap.put("commodityTypeParent", commodityTypeParent);
		modelMap.put("systemUser", systemUser);
		return "shop/commodity/type/parent/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityTypeParent commodityTypeParent) {

		if (commodityTypeParent.getId() != null && !commodityTypeParent.getId().equals("")) {
			boolean b = commodityTypeParentService.update(commodityTypeParent);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/commodity/type/parent/err.jhtml";
			}
		} else {
			commodityTypeParent.setId(UUID.randomUUID().toString());
			SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");
			commodityTypeParent.setCreatorId(session_user.getId());
			commodityTypeParent.setCreateTime(new Date());
			commodityTypeParent.setStatus(1);
			String result = commodityTypeParentService.insert(commodityTypeParent);

			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/commodity/type/parent/err.jhtml";
			}
		}
		return "redirect:/commodity/type/parent/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = commodityTypeParentService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/commodity/type/parent/err.jhtml";
		}

		return "redirect:/commodity/type/parent/index.jhtml";
	}
	
	

}
