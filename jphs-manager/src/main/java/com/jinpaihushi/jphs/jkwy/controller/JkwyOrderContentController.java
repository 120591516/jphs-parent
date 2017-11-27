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
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderContent;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderContentService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "订单内套餐服务内容", path = "/jkwy/order/content")
public class JkwyOrderContentController extends BaseController<JkwyOrderContent> {

    @Autowired
    private JkwyOrderContentService jkwyOrderContentService;

    @Override
    protected BaseService<JkwyOrderContent> getService() {
        return jkwyOrderContentService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrderContent jkwyOrderContent, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyOrderContent> list = jkwyOrderContentService.query(jkwyOrderContent);
        PageInfos<JkwyOrderContent> pageInfo = new PageInfos<JkwyOrderContent>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/order/content/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrderContent jkwyOrderContent = jkwyOrderContentService.loadById(id);
        modelMap.put("jkwyOrderContent", jkwyOrderContent);
        return "jkwy/jkwy/order/content/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/order/content/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrderContent jkwyOrderContent = jkwyOrderContentService.loadById(id);
        modelMap.put("jkwyOrderContent", jkwyOrderContent);
        return "jkwy/jkwy/order/content/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrderContent jkwyOrderContent) {

        if (jkwyOrderContent.getId() != null && !jkwyOrderContent.getId().equals("")) {
            boolean b = jkwyOrderContentService.update(jkwyOrderContent);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/order/content/err.jhtml";
            }
        }
        else {
            jkwyOrderContent.setId(UUID.randomUUID().toString());
            String result = jkwyOrderContentService.insert(jkwyOrderContent);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/order/content/err.jhtml";
            }
        }
        return "redirect:/jkwy/order/content/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyOrderContentService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/order/content/err.jhtml";
        }

        return "redirect:/jkwy/order/content/index.jhtml";
    }

}
