package com.jinpaihushi.jphs.jkwy.controller;

import java.util.Date;
import java.util.List;

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
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackageContent;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageContentService;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackagePriceService;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageService;
import com.jinpaihushi.jphs.platform.model.Platform;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "套餐", path = "/jkwy/package")
public class JkwyPackageController extends BaseController<JkwyPackage> {

    @Autowired
    private JkwyPackageService jkwyPackageService;

    @Autowired
    private JkwyPackagePriceService jkwyPackagePriceService;

    @Autowired
    private ServiceImagesService serviceImagesService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private JkwyPackageContentService jkwyPackageContentService;

    @Override
    protected BaseService<JkwyPackage> getService() {
        return jkwyPackageService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackage jkwyPackage, Integer p, Integer n) {
        startPage(p, n);
        jkwyPackage.setOrderby("status DESC,create_time DESC");
        Page<JkwyPackage> list = jkwyPackageService.query(jkwyPackage);
        PageInfos<JkwyPackage> pageInfo = new PageInfos<JkwyPackage>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/package/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        JkwyPackage jkwyPackage = jkwyPackageService.getDetail(id);
        JkwyPackagePrice queryPrice = new JkwyPackagePrice();
        queryPrice.setJkwyPackageId(id);
        queryPrice.setStatus(0);
        List<JkwyPackagePrice> packagePrice = jkwyPackagePriceService.list(queryPrice);
        JkwyPackageContent queryContent = null;
        for (JkwyPackagePrice jkwyPackagePrice : packagePrice) {
            queryContent = new JkwyPackageContent();
            queryContent.setJkwyPackagePriceId(jkwyPackagePrice.getId());
            queryContent.setStatus(0);
            List<JkwyPackageContent> contentList = jkwyPackageContentService.list(queryContent);
            jkwyPackagePrice.setJkwyPackageContentList(contentList);
        }
        jkwyPackage.setPackagePrice(packagePrice);
        Platform platform = new Platform();
        platform.setStatus(1);
        List<Platform> list = platformService.list(platform);
        String[] platformIds = null == jkwyPackage.getPlatformId() ? "".split(",")
                : jkwyPackage.getPlatformId().split(",");
        for (int i = 0; i < platformIds.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (platformIds[i].equals(list.get(j).getName())) {
                    list.get(j).setChecked(true);
                }
            }
        }
        ServiceImages query = new ServiceImages();
        query.setSourceId(id);
        query.setStatus(1);
        List<ServiceImages> serviceImage = serviceImagesService.list(query);
        ServiceImages pc_image = new ServiceImages();
        ServiceImages web_image = new ServiceImages();
        ServiceImages qt_image = new ServiceImages();
        for (int a = 0; a < serviceImage.size(); a++) {
            if (serviceImage.get(a).getDevice_type() == 1) {
                pc_image = serviceImage.get(a);
            }
            else if (serviceImage.get(a).getDevice_type() == 2) {
                web_image = serviceImage.get(a);
            }
            else {
                qt_image = serviceImage.get(a);
            }
        }
        modelMap.put("pc_image", pc_image);
        modelMap.put("wap_image", web_image);
        modelMap.put("qt_image", qt_image);
        modelMap.put("jkwyPackage", jkwyPackage);
        modelMap.put("platform", list);
        return "jkwy/jkwy/package/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        Platform platform = new Platform();
        platform.setStatus(1);
        List<Platform> list = platformService.list(platform);
        modelMap.put("platform", list);
        return "jkwy/jkwy/package/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyPackage jkwyPackage = jkwyPackageService.getDetail(id);
        JkwyPackagePrice queryPrice = new JkwyPackagePrice();
        queryPrice.setJkwyPackageId(id);
        queryPrice.setStatus(0);
        List<JkwyPackagePrice> packagePrice = jkwyPackagePriceService.list(queryPrice);
        JkwyPackageContent queryContent = null;
        for (JkwyPackagePrice jkwyPackagePrice : packagePrice) {
            queryContent = new JkwyPackageContent();
            queryContent.setJkwyPackagePriceId(jkwyPackagePrice.getId());
            queryContent.setStatus(0);
            List<JkwyPackageContent> contentList = jkwyPackageContentService.list(queryContent);
            jkwyPackagePrice.setJkwyPackageContentList(contentList);
        }
        jkwyPackage.setPackagePrice(packagePrice);
        ServiceImages query = new ServiceImages();
        query.setSourceId(id);
        query.setStatus(1);
        List<ServiceImages> serviceImage = serviceImagesService.list(query);
        ServiceImages pc_image = new ServiceImages();
        ServiceImages web_image = new ServiceImages();
        ServiceImages qt_image = new ServiceImages();
        for (int a = 0; a < serviceImage.size(); a++) {
            if (serviceImage.get(a).getDevice_type() == 1) {
                pc_image = serviceImage.get(a);
            }
            else if (serviceImage.get(a).getDevice_type() == 2) {
                web_image = serviceImage.get(a);
            }
            else {
                qt_image = serviceImage.get(a);
            }
        }
        modelMap.put("pc_image", pc_image);
        modelMap.put("wap_image", web_image);
        modelMap.put("qt_image", qt_image);
        modelMap.put("jkwyPackage", jkwyPackage);
        return "jkwy/jkwy/package/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackage jkwyPackage, ImageType imageType) {
        SystemUser user = (SystemUser) hs.getAttribute("session_user");
        if (jkwyPackage.getId() != null && !jkwyPackage.getId().equals("")) {
            String b = jkwyPackageService.updatePackageAndImg(jkwyPackage, imageType);
            if (b.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/package/err.jhtml";
            }
        }
        else {
            jkwyPackage.setId(UUIDUtils.getId());
            jkwyPackage.setCreateTime(new Date());
            jkwyPackage.setCreatorId(user.getId());
            jkwyPackage.setCreatorName(user.getName());
            String result = jkwyPackageService.insertPackageAndImg(jkwyPackage, imageType);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/package/err.jhtml";
            }
        }
        return "redirect:/jkwy/package/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id,
            Integer status) {
        JkwyPackage user = jkwyPackageService.loadById(id);
        user.setStatus(status);
        boolean b = jkwyPackageService.update(user);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/package/err.jhtml";
        }

        return "redirect:/jkwy/package/index.jhtml";
    }

    @RequestMapping(name = "套餐列表", path = "/getPackageList.json")
    @ResponseBody
    public JSONObject getPackageList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap) {
        JSONObject message = new JSONObject();
        JkwyPackage jkwyPackage = new JkwyPackage();
        jkwyPackage.setStatus(0);
        jkwyPackage.setOrderby("create_time DESC");
        List<JkwyPackage> c = jkwyPackageService.query(jkwyPackage).getResult();
        message.put("jkwyPackage", c);
        return message;
    }

}
