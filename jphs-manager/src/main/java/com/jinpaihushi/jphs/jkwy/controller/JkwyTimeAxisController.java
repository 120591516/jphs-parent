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
import com.jinpaihushi.jphs.jkwy.model.JkwyTimeAxis;
import com.jinpaihushi.jphs.jkwy.service.JkwyTimeAxisService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "时间轴", path = "/jkwy/time/axis")
public class JkwyTimeAxisController extends BaseController<JkwyTimeAxis> {

    @Autowired
    private JkwyTimeAxisService jkwyTimeAxisService;

    @Override
    protected BaseService<JkwyTimeAxis> getService() {
        return jkwyTimeAxisService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyTimeAxis jkwyTimeAxis, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyTimeAxis> list = jkwyTimeAxisService.query(jkwyTimeAxis);
        PageInfos<JkwyTimeAxis> pageInfo = new PageInfos<JkwyTimeAxis>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/time/axis/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyTimeAxis jkwyTimeAxis = jkwyTimeAxisService.loadById(id);
        modelMap.put("jkwyTimeAxis", jkwyTimeAxis);
        return "jkwy/jkwy/time/axis/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/time/axis/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyTimeAxis jkwyTimeAxis = jkwyTimeAxisService.loadById(id);
        modelMap.put("jkwyTimeAxis", jkwyTimeAxis);
        return "jkwy/jkwy/time/axis/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyTimeAxis jkwyTimeAxis) {

        if (jkwyTimeAxis.getId() != null && !jkwyTimeAxis.getId().equals("")) {
            boolean b = jkwyTimeAxisService.update(jkwyTimeAxis);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/time/axis/err.jhtml";
            }
        }
        else {
            jkwyTimeAxis.setId(UUID.randomUUID().toString());
            String result = jkwyTimeAxisService.insert(jkwyTimeAxis);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/time/axis/err.jhtml";
            }
        }
        return "redirect:/jkwy/time/axis/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyTimeAxisService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/time/axis/err.jhtml";
        }

        return "redirect:/jkwy/time/axis/index.jhtml";
    }

}
