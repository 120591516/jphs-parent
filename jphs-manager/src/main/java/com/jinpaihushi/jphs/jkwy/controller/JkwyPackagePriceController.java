package com.jinpaihushi.jphs.jkwy.controller;

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
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackagePriceService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "套餐价格", path = "/jkwy/package/price")
public class JkwyPackagePriceController extends BaseController<JkwyPackagePrice> {

    @Autowired
    private JkwyPackagePriceService jkwyPackagePriceService;

    @Override
    protected BaseService<JkwyPackagePrice> getService() {
        return jkwyPackagePriceService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackagePrice jkwyPackagePrice, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyPackagePrice> list = jkwyPackagePriceService.query(jkwyPackagePrice);
        PageInfos<JkwyPackagePrice> pageInfo = new PageInfos<JkwyPackagePrice>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/package/price/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyPackagePrice jkwyPackagePrice = jkwyPackagePriceService.loadById(id);
        modelMap.put("jkwyPackagePrice", jkwyPackagePrice);
        return "jkwy/jkwy/package/price/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/package/price/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyPackagePrice jkwyPackagePrice = jkwyPackagePriceService.loadById(id);
        modelMap.put("jkwyPackagePrice", jkwyPackagePrice);
        return "jkwy/jkwy/package/price/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackagePrice jkwyPackagePrice) {

        if (jkwyPackagePrice.getId() != null && !jkwyPackagePrice.getId().equals("")) {
            boolean b = jkwyPackagePriceService.update(jkwyPackagePrice);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/package/price/err.jhtml";
            }
        }
        else {
            jkwyPackagePrice.setId(UUID.randomUUID().toString());
            String result = jkwyPackagePriceService.insert(jkwyPackagePrice);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/package/price/err.jhtml";
            }
        }
        return "redirect:/jkwy/package/price/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyPackagePriceService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/package/price/err.jhtml";
        }

        return "redirect:/jkwy/package/price/index.jhtml";
    }

}
