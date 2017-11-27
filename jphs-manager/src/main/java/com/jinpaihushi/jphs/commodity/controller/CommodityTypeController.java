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
import com.jinpaihushi.jphs.commodity.model.CommodityType;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.jphs.commodity.model.CommodityTypeParent;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeParentService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

/**
 * 
 * @author yangsong
 * @date 2017-10-23 10:27:34
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品二级分类", path = "/commodity/type")
public class CommodityTypeController extends BaseController<CommodityType> {

	@Autowired
	private CommodityTypeService commodityTypeService;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private CommodityTypeParentService commodityTypeParentService;
	@Autowired
    private CommodityService commodityService;

	@Override
	protected BaseService<CommodityType> getService() {
		return commodityTypeService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			CommodityType commodityType, Integer p, Integer n) {
		startPage(p, n);
		Page<CommodityType> list = commodityTypeService.query(commodityType);
		Commodity commodity =new Commodity();
		for (int i = 0;i<list.size() ;i++ ){
			SystemUser systemUser = systemUserService.loadById(list.get(i).getCreatorId());
			CommodityTypeParent  ctp= commodityTypeParentService.loadById(list.get(i).getCommodityTypeParentId());
			

			if(systemUser != null){
				list.get(i).setCreatorName(systemUser.getName());
			}
			if(ctp != null){
				list.get(i).setCommodityTypeParentId(ctp.getName());
			}
			
			commodity.setCommodityTypeId(list.get(i).getId());
			List<Commodity> comList = commodityService.list(commodity);
			if(comList.size()>0){
				list.get(i).setCount(comList.size());
			}else{
				list.get(i).setCount(0);
			}
			
		}

		CommodityTypeParent commodityTypeParent = new CommodityTypeParent();

		Page<CommodityTypeParent> ctpList = commodityTypeParentService.query(commodityTypeParent);

		modelMap.put("ctpList", ctpList);
		PageInfos<CommodityType> pageInfo = new PageInfos<CommodityType>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "shop/commodity/type/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityType commodityType = commodityTypeService.loadById(id);
		modelMap.put("commodityType", commodityType);

		CommodityTypeParent commodityTypeParent = new CommodityTypeParent();

		Page<CommodityTypeParent> list = commodityTypeParentService.query(commodityTypeParent);

		modelMap.put("list", list);

		return "shop/commodity/type/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		CommodityTypeParent commodityTypeParent = new CommodityTypeParent();

		Page<CommodityTypeParent> list = commodityTypeParentService.query(commodityTypeParent);

		modelMap.put("list", list);


		return "shop/commodity/type/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		CommodityType commodityType = commodityTypeService.loadById(id);
		CommodityTypeParent ctp = commodityTypeParentService.loadById(commodityType.getCommodityTypeParentId());
		SystemUser systemUser = systemUserService.loadById(commodityType.getCreatorId());
		modelMap.put("commodityType", commodityType);
		modelMap.put("ctp", ctp);
		modelMap.put("systemUser", systemUser);
		return "shop/commodity/type/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, CommodityType commodityType) {

		if (commodityType.getId() != null && !commodityType.getId().equals("")) {
			boolean b = commodityTypeService.update(commodityType);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/commodity/type/err.jhtml";
			}
		} else {
			commodityType.setId(UUID.randomUUID().toString());
			SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");
			commodityType.setCreatorId(session_user.getId());
			commodityType.setStatus(1);
			commodityType.setCreateTime(new Date());
			String result = commodityTypeService.insert(commodityType);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/commodity/type/err.jhtml";
			}
		}
		return "redirect:/commodity/type/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = commodityTypeService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/commodity/type/err.jhtml";
		}

		return "redirect:/commodity/type/index.jhtml";
	}
	
	

}
