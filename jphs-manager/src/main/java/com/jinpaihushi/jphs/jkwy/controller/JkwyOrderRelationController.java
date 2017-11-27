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
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderRelationService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "订单亲友关系中间表", path = "/jkwy/order/relation")
public class JkwyOrderRelationController extends BaseController<JkwyOrderRelation> {

    @Autowired
    private JkwyOrderRelationService jkwyOrderRelationService;

    @Override
    protected BaseService<JkwyOrderRelation> getService() {
        return jkwyOrderRelationService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrderRelation jkwyOrderRelation, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyOrderRelation> list = jkwyOrderRelationService.query(jkwyOrderRelation);
        PageInfos<JkwyOrderRelation> pageInfo = new PageInfos<JkwyOrderRelation>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/order/relation/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrderRelation jkwyOrderRelation = jkwyOrderRelationService.loadById(id);
        modelMap.put("jkwyOrderRelation", jkwyOrderRelation);
        return "jkwy/jkwy/order/relation/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/order/relation/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyOrderRelation jkwyOrderRelation = jkwyOrderRelationService.loadById(id);
        modelMap.put("jkwyOrderRelation", jkwyOrderRelation);
        return "jkwy/jkwy/order/relation/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyOrderRelation jkwyOrderRelation) {

        if (jkwyOrderRelation.getId() != null && !jkwyOrderRelation.getId().equals("")) {
            boolean b = jkwyOrderRelationService.update(jkwyOrderRelation);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/order/relation/err.jhtml";
            }
        }
        else {
            jkwyOrderRelation.setId(UUID.randomUUID().toString());
            String result = jkwyOrderRelationService.insert(jkwyOrderRelation);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/order/relation/err.jhtml";
            }
        }
        return "redirect:/jkwy/order/relation/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyOrderRelationService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/order/relation/err.jhtml";
        }

        return "redirect:/jkwy/order/relation/index.jhtml";
    }

}
