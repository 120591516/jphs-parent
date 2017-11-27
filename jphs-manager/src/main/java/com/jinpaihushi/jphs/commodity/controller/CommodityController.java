package com.jinpaihushi.jphs.commodity.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.business.service.BusinessService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityImages;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.model.CommodityPriceList;
import com.jinpaihushi.jphs.commodity.model.CommodityType;
import com.jinpaihushi.jphs.commodity.model.CommodityTypeParent;
import com.jinpaihushi.jphs.commodity.service.CommodityImagesService;
import com.jinpaihushi.jphs.commodity.service.CommodityPriceService;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeParentService;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-09-18 10:32:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品", path = "/commodity")
public class CommodityController extends BaseController<Commodity> {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CommodityImagesService commodityImagesService;

	@Autowired
    private CommodityPriceService commodityPriceService;

	@Autowired
    private CommodityTypeService commodityTypeService;

	@Autowired
	private CommodityTypeParentService commodityTypeParentService;
	
	@Autowired
    private SystemUserService systemUserService;


    @Override
    protected BaseService<Commodity> getService() {
        return commodityService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Commodity commodity,String ctpId, Integer p, Integer n) {

		String ctId = "";
		if(ctpId != null){
			String ctIds = "";
			if(commodity.getCommodityTypeId()== null){
			CommodityType ct = new CommodityType();
			List<CommodityType> ctList = commodityTypeService.list(ct);
			
			for (int i = 0; i<ctList.size();i++ ){
				ctIds +=",'"+ctList.get(i).getId()+"'";
			}
			
			 
		 } else{
			 ctId = commodity.getCommodityTypeId();
			 ctIds +=",'"+commodity.getCommodityTypeId()+"'";
			
			 CommodityType ct = new CommodityType();
			 ct.setCommodityTypeParentId(ctpId);
			 List<CommodityType> ctList= commodityTypeService.list(ct);
			 modelMap.put("ctList", ctList);
		 }
		 
		  commodity.setCommodityTypeId(ctIds.substring(1));
		}
		 

		 
        startPage(p, n);
        
        Page<Commodity> list = commodityService.getPageList(commodity);
		commodity.setCommodityTypeId(ctId);
		CommodityImages ci= new CommodityImages();
		Business business = new Business();
		CommodityType commodityType = new CommodityType();
		CommodityPrice commodityPrice = new CommodityPrice();
		  
		 List<Business> businessList = businessService.list(business);
		 List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
		for (int i = 0;i<list.size() ;i++ ){
			int totals = 0;
			ci.setSourceId(list.get(i).getId());
			ci.setType(1);
			CommodityImages ci1 = commodityImagesService.load(ci);
			if(ci1 != null){
				list.get(i).setUrl(ci1.getUrl());
			}
			

			business.setId(list.get(i).getBusinessId());
			Business business1 = businessService.load(business);
			if(business1!=null){
				list.get(i).setBusinessName(business1.getName());

			}
			
			commodityType.setId(list.get(i).getCommodityTypeId());
			CommodityType ct =commodityTypeService.load(commodityType);
			if(ct!= null){
				list.get(i).setCommodityType(ct.getName());
				 
				CommodityTypeParent ctp = commodityTypeParentService.loadById(ct.getCommodityTypeParentId());
				if(ctp != null){
				list.get(i).setCommodityTypeParent(ctp.getName());
				}
			}

			commodityPrice.setCommodityId(list.get(i).getId());
			List<CommodityPrice> cpList = commodityPriceService.list(commodityPrice);
			for (int j = 0;j<cpList.size() ;j++ ){
			totals += cpList.get(j).getNumber();
			}

			list.get(i).setSkuNameList(cpList);
			list.get(i).setTotals(totals);
		}
        PageInfos<Commodity> pageInfo = new PageInfos<Commodity>(list, req);
         
		CommodityTypeParent ctp = new CommodityTypeParent();
        List<CommodityTypeParent> ctpList= commodityTypeParentService.list(ctp);
	
        modelMap.put("list", list);
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
        modelMap.put("pageInfo", pageInfo);
		modelMap.put("ctpList", ctpList);
		modelMap.put("ctpId", ctpId);
        return "shop/commodity/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Commodity commodity = commodityService.loadById(id);
        modelMap.put("commodity", commodity);
		if(commodity!=null){
			 modelMap.put("ctId", commodity.getCommodityTypeId());
		CommodityType ct = commodityTypeService.loadById(commodity.getCommodityTypeId());
		if(ct!= null){
			modelMap.put("ctpId", ct.getCommodityTypeParentId());
		}
		}
			CommodityPrice cp = new CommodityPrice();
		cp.setCommodityId(id);
		List<CommodityPrice> cpList = commodityPriceService.list(cp); 

		 Business business = new Business();
        List<Business> businessList = businessService.list(business);
        CommodityType commodityType = new CommodityType();
        List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
		modelMap.put("cpList",cpList);
		
		CommodityImages ci= new CommodityImages();
		ci.setSourceId(id);
		ci.setType(1);
		CommodityImages ci1 = commodityImagesService.load(ci);
		ci.setType(2);
		CommodityImages ci2 = commodityImagesService.load(ci);
		modelMap.put("ci1",ci1);
		modelMap.put("ci2",ci2);

		CommodityTypeParent ctp = new CommodityTypeParent();
        List<CommodityTypeParent> ctpList= commodityTypeParentService.list(ctp);
		modelMap.put("ctpList", ctpList);
		
        return "shop/commodity/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        Business business = new Business();
        List<Business> businessList = businessService.list(business);
        CommodityType commodityType = new CommodityType();
        List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
		CommodityTypeParent ctp = new CommodityTypeParent();
        List<CommodityTypeParent> ctpList= commodityTypeParentService.list(ctp);
		modelMap.put("ctpList", ctpList);
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
        return "shop/commodity/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
       Commodity commodity = commodityService.loadById(id);
        modelMap.put("commodity", commodity);
		modelMap.put("ctId", commodity.getCommodityTypeId());
		CommodityPrice cp = new CommodityPrice();
		cp.setCommodityId(id);
		List<CommodityPrice> cpList = commodityPriceService.list(cp); 

		 Business business = new Business();
        List<Business> businessList = businessService.list(business);

		business.setId(commodity.getBusinessId());
		Business b = businessService.load(business);
        CommodityType commodityType = new CommodityType();

        List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
		commodityType.setId(commodity.getCommodityTypeId());
		CommodityType commodityType1= commodityTypeService.load(commodityType);
		if(commodityType1.getCommodityTypeParentId()!=null){
			CommodityTypeParent ctp = commodityTypeParentService.loadById(commodityType1.getCommodityTypeParentId());
			modelMap.put("ctp", ctp);
		}
		
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
		modelMap.put("cpList",cpList);
		
		CommodityImages ci= new CommodityImages();
		ci.setSourceId(id);
		ci.setType(1);
		CommodityImages ci1 = commodityImagesService.load(ci);
		ci.setType(2);
		CommodityImages ci2 = commodityImagesService.load(ci);
		modelMap.put("ci1",ci1);
		modelMap.put("ci2",ci2);
		modelMap.put("cpList",cpList);
		modelMap.put("business",b);
		modelMap.put("commodityType",commodityType1);

        return "shop/commodity/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Commodity commodity, String url, String content, CommodityPriceList cpList) {

	
	if(req.getParameter("privacy") == null){
		commodity.setPrivacy(0);
	}else{
		commodity.setPrivacy(1);
	}
	if(req.getParameter("security")== null ){
		commodity.setSecurity(0);
	}else{
		commodity.setSecurity(1);
	}
	if(req.getParameter("quality")== null){
		commodity.setQuality(0);
	}else{
		commodity.setQuality(1);	
	}
	 if(req.getParameter("supportVoucher")== null){
		commodity.setSupportVoucher(1);
	}else{
		commodity.setSupportVoucher(Integer.parseInt(req.getParameter("supportVoucher")));	
	}
	 SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");

       String result =  null;
	   String id = null;
	   String coId = null;
        if (commodity.getId() != null && !commodity.getId().equals("")) {

			
			

        	boolean b = commodityService.update(commodity);
        	if(b != false){
				CommodityImages ci= new CommodityImages();
				ci.setSourceId(commodity.getId());
				if(url!=null){

					ci.setType(1);
					CommodityImages ci1 =  commodityImagesService.load(ci);
					ci1.setUrl(url);
					commodityImagesService.update(ci1);
				}
				 
				if(content != null){

					 
					String content1 = content.replaceAll("＜","<");
					String content2 = content1.replaceAll("＞",">");

					ci.setType(2);
					CommodityImages ci2 =  commodityImagesService.load(ci);
					if(ci2 != null){
						ci2.setUrl(content2);
						commodityImagesService.update(ci2);	
					}else{
						ci.setId(UUID.randomUUID().toString());
						 
						ci.setType(2);
						ci.setDeviceType(2);
						ci.setUrl(content2);
						ci.setStatus(0);
						ci.setCreateTime(new Date());
						ci.setCreatorId(systemUser.getId());
						commodityImagesService.insert(ci);	
					}
					
				}
				
				if(cpList.getCpList()!= null){
					for (int i = 0; i < cpList.getCpList().size(); i++) {

					CommodityPrice cp = new CommodityPrice();
					cp.setId(cpList.getCpList().get(i).getId());
					cp.setCommodityId(commodity.getId());
					cp.setName(cpList.getCpList().get(i).getName());
					cp.setNumber(cpList.getCpList().get(i).getNumber());
					cp.setCostPrice(cpList.getCpList().get(i).getCostPrice());
					cp.setOldPrice(cpList.getCpList().get(i).getOldPrice());
					cp.setPrice(cpList.getCpList().get(i).getPrice());
					cp.setProfit(cpList.getCpList().get(i).getProfit());
					 
					
					 

					if(cpList.getCpList().get(i).getId() == null){
							 
						cp.setId(UUID.randomUUID().toString());
						cp.setCreateTime(new Date());
						cp.setStatus(1);

						cp.setCreatorId(systemUser.getId());
						commodityPriceService.insert(cp);
					}else{
						 
						boolean bs = commodityPriceService.update(cp);
							 
					}
					
				}
				}

			}
			
			
			if (b == false) {
        		// 跳转到错误页
        		return "redirect:/commodity/err.jhtml";
        	}
        } else {
			coId = UUID.randomUUID().toString();
        	commodity.setId(coId);
			commodity.setCount(0);
			commodity.setBrowser(0);
			commodity.setShareNumber(0);
			commodity.setCreatorId(systemUser.getId());
        	 result = commodityService.insert(commodity);
        	if (result.length() <= 0) {
        		// 跳转到错误页
        		return "redirect:/commodity/err.jhtml";
        	}
		

			 if(result != null){
        	CommodityImages commodityImages = new CommodityImages();
        	commodityImages.setDeviceType(2);
        	commodityImages.setStatus(0);
        	commodityImages.setCreateTime(new Date());
        	commodityImages.setSourceId(coId);
			commodityImages.setCreatorId(systemUser.getId());
        	if(url != null){
				
        		commodityImages.setId(UUID.randomUUID().toString());
        		commodityImages.setUrl(url);
        		commodityImages.setType(1);
				
        		commodityImagesService.insert(commodityImages);
        	}
        	if(content != null){
        		commodityImages.setId(UUID.randomUUID().toString());
				System.out.println("CONTENT ====="+content);
					String content1 = content.replaceAll("＜","<");
					String content2 = content1.replaceAll("＞",">");
					 
        		commodityImages.setUrl(content2);
        		commodityImages.setType(2);

        		id = commodityImagesService.insert(commodityImages);
        	}
        }
        
		  System.out.println("。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		  System.out.println(cpList.getCpList()!= null);
		  if(coId != null){
			 if(cpList.getCpList()!= null){
				for (int i = 0; i < cpList.getCpList().size(); i++) {

					CommodityPrice cp = new CommodityPrice();
					cp.setId(UUID.randomUUID().toString());
					cp.setCommodityId(coId);
					cp.setName(cpList.getCpList().get(i).getName());
					cp.setNumber(cpList.getCpList().get(i).getNumber());
					cp.setCostPrice(cpList.getCpList().get(i).getCostPrice());
					cp.setOldPrice(cpList.getCpList().get(i).getOldPrice());
					cp.setPrice(cpList.getCpList().get(i).getPrice());
					cp.setProfit(cpList.getCpList().get(i).getProfit());
					//cp.setLogisticsPrice(0.0);
					//cp.setsort(0);
					cp.setCreateTime(new Date());
					cp.setCreatorId(systemUser.getId());
					cp.setStatus(1);
					//cp.setCreatorName();

					System.out.println("SKU ====="+cpList.getCpList().get(i).getName());
					System.out.println("成本价 ====="+cpList.getCpList().get(i).getCostPrice());
					System.out.println("原价 ====="+cpList.getCpList().get(i).getOldPrice());
					System.out.println("销售价 ====="+cpList.getCpList().get(i).getPrice());
					System.out.println("分销收益 ====="+cpList.getCpList().get(i).getProfit());
					System.out.println("库存 ====="+cpList.getCpList().get(i).getNumber());
					commodityPriceService.insert(cp);
				}
			  }
		  }
		 
 System.out.println("。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        }
       
        return "redirect:/commodity/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = commodityService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/commodity/err.jhtml";
        }

        return "redirect:/commodity/index.jhtml";
    }



    @RequestMapping(name = "修改商品价格状态", path = "/update.jhtml")
    @ResponseBody
    public JSONObject update(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, CommodityPrice commodityPrice) {

       
        JSONObject message = new JSONObject();
        boolean result = commodityPriceService.update(commodityPrice);
        message.put("result", result);

        return message;
    }

	@RequestMapping(name = "修改商品状态", path = "/updateStatus.jhtml")
    @ResponseBody
    public JSONObject updateStatus(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, Commodity commodity) {

       
        JSONObject message = new JSONObject();
        boolean result = commodityService.update(commodity);
        message.put("result", result);

        return message;
    }


	@SuppressWarnings("static-access")
	@RequestMapping(name = "查询商品", path = "/getCtList.json")
    @ResponseBody
    public JSONObject getCtList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) throws IOException {

        JSONObject message = new JSONObject();
        CommodityType ct = new CommodityType();
        ct.setCommodityTypeParentId(id);
        ct.setStatus(1);
        List<CommodityType> ctList = commodityTypeService.list(ct);
        
        for(int a=0;a<ctList.size();a++){
        	if(ctList.get(a).getUpdateTime() == null || ctList.get(a).getUpdateTime().equals("")){
        		ctList.get(a).setUpdateTime(new Date());
        	}
        }
        
        message.put("ctList", new JSONArray().fromObject(ctList));

        return message;
    }
	
	@RequestMapping(name = "查询所有商品", path = "/getAllCtList.json")
    @ResponseBody
    public JSONObject getAllCtList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) throws IOException {
        JSONObject message = new JSONObject();
        Commodity commodity = new Commodity();
        commodity.setStatus(1);
        commodity.setOrderby("C.create_time DESC");
        List<Map<String ,Object>> c = commodityService.getAllCtList(commodity);
        message.put("commodity", c);
        
        return message;
    }
	
}
