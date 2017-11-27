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
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyRelationService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "亲友关系", path = "/jkwy/relation")
public class JkwyRelationController extends BaseController<JkwyRelation> {

    @Autowired
    private JkwyRelationService jkwyRelationService;

    @Override
    protected BaseService<JkwyRelation> getService() {
        return jkwyRelationService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyRelation jkwyRelation, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyRelation> list = jkwyRelationService.query(jkwyRelation);
        PageInfos<JkwyRelation> pageInfo = new PageInfos<JkwyRelation>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/relation/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyRelation jkwyRelation = jkwyRelationService.loadById(id);
        modelMap.put("jkwyRelation", jkwyRelation);
        return "jkwy/jkwy/relation/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/relation/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyRelation jkwyRelation = jkwyRelationService.loadById(id);
        modelMap.put("jkwyRelation", jkwyRelation);
        return "jkwy/jkwy/relation/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyRelation jkwyRelation) {

        if (jkwyRelation.getId() != null && !jkwyRelation.getId().equals("")) {
            boolean b = jkwyRelationService.update(jkwyRelation);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/relation/err.jhtml";
            }
        }
        else {
            jkwyRelation.setId(UUID.randomUUID().toString());
            String result = jkwyRelationService.insert(jkwyRelation);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/relation/err.jhtml";
            }
        }
        return "redirect:/jkwy/relation/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyRelationService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/relation/err.jhtml";
        }

        return "redirect:/jkwy/relation/index.jhtml";
    }

}
