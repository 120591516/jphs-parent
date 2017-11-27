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
import com.jinpaihushi.jphs.jkwy.model.JkwyPackageContent;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageContentService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Controller
@RequestMapping(name = "套餐内容", path = "/jkwy/package/content")
public class JkwyPackageContentController extends BaseController<JkwyPackageContent> {

    @Autowired
    private JkwyPackageContentService jkwyPackageContentService;

    @Override
    protected BaseService<JkwyPackageContent> getService() {
        return jkwyPackageContentService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackageContent jkwyPackageContent, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyPackageContent> list = jkwyPackageContentService.query(jkwyPackageContent);
        PageInfos<JkwyPackageContent> pageInfo = new PageInfos<JkwyPackageContent>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/package/content/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyPackageContent jkwyPackageContent = jkwyPackageContentService.loadById(id);
        modelMap.put("jkwyPackageContent", jkwyPackageContent);
        return "jkwy/jkwy/package/content/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/package/content/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyPackageContent jkwyPackageContent = jkwyPackageContentService.loadById(id);
        modelMap.put("jkwyPackageContent", jkwyPackageContent);
        return "jkwy/jkwy/package/content/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyPackageContent jkwyPackageContent) {

        if (jkwyPackageContent.getId() != null && !jkwyPackageContent.getId().equals("")) {
            boolean b = jkwyPackageContentService.update(jkwyPackageContent);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/package/content/err.jhtml";
            }
        }
        else {
            jkwyPackageContent.setId(UUID.randomUUID().toString());
            String result = jkwyPackageContentService.insert(jkwyPackageContent);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/package/content/err.jhtml";
            }
        }
        return "redirect:/jkwy/package/content/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = jkwyPackageContentService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/package/content/err.jhtml";
        }

        return "redirect:/jkwy/package/content/index.jhtml";
    }

}
