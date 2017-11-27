package com.jinpaihushi.jphs.activity.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.activity.service.ActivityPromotionService;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.service.CommodityPriceService;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackagePriceService;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.service.PriceService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author scj
 * @date 2017-10-30 14:25:22
 * @version 1.0
 */
@Controller
@RequestMapping(name = "ActivityPromotion", path = "/activity/promotion")
public class ActivityPromotionController extends BaseController<ActivityPromotion> {

    @Autowired
    private ActivityPromotionService activityPromotionService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private CommodityPriceService commodityPriceService;

    @Autowired
    private JkwyPackagePriceService jkwyPackagePriceService;

    @Autowired
    private ServiceImagesService serviceImagesService;

    @Override
    protected BaseService<ActivityPromotion> getService() {
        return activityPromotionService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            ActivityPromotion activityPromotion, Integer p, Integer n) {
        startPage(p, n);
        activityPromotion.setOrderby("AP.create_time DESC");
        Page<ActivityPromotion> list = activityPromotionService.query(activityPromotion);
        PageInfos<ActivityPromotion> pageInfo = new PageInfos<ActivityPromotion>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "activity/promotion/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        ActivityPromotion activityPromotion = activityPromotionService.loadById(id);
        ServiceImages serviceImages = new ServiceImages();
        serviceImages.setSourceId(activityPromotion.getId());
        serviceImages.setStatus(1);
        serviceImages.setType(1);
        serviceImages.setDevice_type(2);
        serviceImages = serviceImagesService.load(serviceImages);
        if (null != serviceImages) {
            activityPromotion.setImage(serviceImages.getUrl());
        }
        modelMap.put("activityPromotion", activityPromotion);
        return "activity/promotion/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "activity/promotion/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        ActivityPromotion activityPromotion = activityPromotionService.loadByIds(id);
        ServiceImages serviceImages = new ServiceImages();
        serviceImages.setSourceId(activityPromotion.getId());
        serviceImages.setStatus(1);
        serviceImages.setType(1);
        serviceImages.setDevice_type(2);
        serviceImages = serviceImagesService.load(serviceImages);
        if (null != serviceImages) {
            activityPromotion.setImage(serviceImages.getUrl());
        }
        modelMap.put("activityPromotion", activityPromotion);
        return "activity/promotion/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            ActivityPromotion activityPromotion) {
        SystemUser user = (SystemUser) hs.getAttribute("session_user");
        ServiceImages serviceImages = new ServiceImages();
        if (activityPromotion.getId() != null && !activityPromotion.getId().equals("")) {
            boolean b = activityPromotionService.update(activityPromotion);
            //获取原来的图片
            serviceImages.setSourceId(activityPromotion.getId());
            serviceImages = serviceImagesService.load(serviceImages);
            if (serviceImages != null) {
                serviceImages.setUrl(activityPromotion.getImage());
                serviceImagesService.update(serviceImages);
            }
            else {
                serviceImages = new ServiceImages();
                serviceImages.setUrl(activityPromotion.getImage());
                serviceImages.setSourceId(activityPromotion.getId());
                serviceImages.setId(UUIDUtils.getId());
                serviceImages.setCreatorId(user.getId());
                serviceImages.setCreatorName(user.getName());
                serviceImages.setCreateTime(new Date());
                serviceImages.setDevice_type(2);
                serviceImages.setType(1);
                serviceImages.setStatus(1);
                serviceImagesService.insert(serviceImages);
            }
            if (b == false) {
                // 跳转到错误页
                return "redirect:/activity/promotion/err.jhtml";
            }
        }
        else {
            try {

                activityPromotion.setCreatorId(user.getId());
                activityPromotion.setCreatorName(user.getName());
            }
            catch (Exception e) {
            }
            activityPromotion.setId(UUID.randomUUID().toString());
            serviceImages.setId(UUIDUtils.getId());
            serviceImages.setUrl(activityPromotion.getImage());
            serviceImages.setSourceId(activityPromotion.getId());
            serviceImages.setCreatorId(user.getId());
            serviceImages.setCreatorName(user.getName());
            serviceImages.setCreateTime(new Date());
            serviceImages.setDevice_type(2);
            serviceImages.setType(1);
            serviceImages.setStatus(1);
            serviceImagesService.insert(serviceImages);
            String result = activityPromotionService.insert(activityPromotion);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/activity/promotion/err.jhtml";
            }
        }
        return "redirect:/activity/promotion/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            ActivityPromotion activityPromotion) {
        activityPromotion.setStatus(-1);
        boolean b = activityPromotionService.update(activityPromotion);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/activity/promotion/err.jhtml";
        }

        return "redirect:/activity/promotion/index.jhtml";
    }

    @RequestMapping(name = "根据product id获取商品", path = "/getIfGoodsIdPriceList.json")
    @ResponseBody
    public JSONObject getIfGoodsIdPriceList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, String id, int type) {
        JSONObject json = new JSONObject();
        if (type == 1) {
            Price price = new Price();
            price.setGoodsId(id);
            price.setStatus(1);
            price.setOrderby("P.create_time DESC");
            List<Map<String, Object>> p = priceService.getIfGoodsIdPriceList(price);
            json.put("price", p);
        }
        else if (type == 2) {
            CommodityPrice commodity_price = new CommodityPrice();
            commodity_price.setCommodityId(id);
            commodity_price.setStatus(1);
            commodity_price.setOrderby("CP.create_time DESC");
            List<Map<String, Object>> cp = commodityPriceService.commodityPriceAllList(commodity_price);
            json.put("price", cp);
        }
        else if (type == 3) {
            JkwyPackagePrice jkwyPackagePrice = new JkwyPackagePrice();
            jkwyPackagePrice.setJkwyPackageId(id);
            jkwyPackagePrice.setStatus(0);
            jkwyPackagePrice.setOrderby("create_time DESC");
            List<JkwyPackagePrice> cp = jkwyPackagePriceService.query(jkwyPackagePrice);
            json.put("price", cp);
        }
        return json;
    }

}
