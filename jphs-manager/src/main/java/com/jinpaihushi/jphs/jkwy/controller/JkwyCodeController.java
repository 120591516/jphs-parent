package com.jinpaihushi.jphs.jkwy.controller;

import java.util.Date;

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
import com.jinpaihushi.jphs.jkwy.model.JkwyCode;
import com.jinpaihushi.jphs.jkwy.service.JkwyCodeService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-30 09:24:38
 * @version 1.0
 */
@Controller
@RequestMapping(name = "销售人员", path = "/jkwy/code")
public class JkwyCodeController extends BaseController<JkwyCode> {

    @Autowired
    private JkwyCodeService jkwyCodeService;

    @Override
    protected BaseService<JkwyCode> getService() {
        return jkwyCodeService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyCode jkwyCode, Integer p, Integer n) {
        startPage(p, n);
        Page<JkwyCode> list = jkwyCodeService.query(jkwyCode);
        PageInfos<JkwyCode> pageInfo = new PageInfos<JkwyCode>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "jkwy/jkwy/code/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyCode jkwyCode = jkwyCodeService.loadById(id);
        modelMap.put("jkwyCode", jkwyCode);
        return "jkwy/jkwy/code/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "jkwy/jkwy/code/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        JkwyCode jkwyCode = jkwyCodeService.loadById(id);
        modelMap.put("jkwyCode", jkwyCode);
        return "jkwy/jkwy/code/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            JkwyCode jkwyCode) {
        SystemUser user = (SystemUser) hs.getAttribute("session_user");
        if (jkwyCode.getId() != null && !jkwyCode.getId().equals("")) {
            boolean b = jkwyCodeService.update(jkwyCode);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/jkwy/code/err.jhtml";
            }
        }
        else {
            jkwyCode.setId(UUIDUtils.getId());
            jkwyCode.setCreateTime(new Date());
            jkwyCode.setCreatorId(user.getId());
            jkwyCode.setCreatorName(user.getName());
            String result = jkwyCodeService.insert(jkwyCode);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/jkwy/code/err.jhtml";
            }
        }
        return "redirect:/jkwy/code/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id,
            Integer status) {

        JkwyCode jkwyCode = jkwyCodeService.loadById(id);
        jkwyCode.setStatus(status);
        boolean b = jkwyCodeService.update(jkwyCode);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/jkwy/code/err.jhtml";
        }

        return "redirect:/jkwy/code/index.jhtml";
    }

}
