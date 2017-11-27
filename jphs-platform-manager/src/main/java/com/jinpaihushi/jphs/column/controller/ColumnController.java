package com.jinpaihushi.jphs.column.controller;

import java.util.Date;
import java.util.List;
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
import com.jinpaihushi.jphs.column.model.Column;
import com.jinpaihushi.jphs.column.model.CsList;
import com.jinpaihushi.jphs.column.service.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.system.service.SystemUserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-10-19 09:24:03
 * @version 1.0
 */
@Controller
@RequestMapping(name = "栏目管理", path = "/column")
public class ColumnController extends BaseController<Column> {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private ColumnServiceService columnServiceService;

    @Autowired
    private SystemUserService systemUserService;

    @Override
    protected BaseService<Column> getService() {
        return columnService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Column column, Integer p, Integer n) {
        startPage(p, n);

        Page<Column> list = columnService.query(column);
        SystemUser su = new SystemUser();
        com.jinpaihushi.jphs.column.model.ColumnService cs = new com.jinpaihushi.jphs.column.model.ColumnService();
        for (int i = 0; i < list.size(); i++) {
            cs.setColumnId(list.get(i).getId());
            List<com.jinpaihushi.jphs.column.model.ColumnService> csList = columnServiceService.list(cs);
            list.get(i).setCsCount(csList.size());

            su.setId(list.get(i).getCreatorId());
            SystemUser systemUser = systemUserService.load(su);

            if (systemUser != null) {
                list.get(i).setCreatorName(systemUser.getName());
            }

        }
        PageInfos<Column> pageInfo = new PageInfos<Column>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "activity/column/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Column column = columnService.loadById(id);
        modelMap.put("column", column);

        com.jinpaihushi.jphs.column.model.ColumnService cs = new com.jinpaihushi.jphs.column.model.ColumnService();
        cs.setColumnId(id);
        SystemUser su = new SystemUser();
        List<com.jinpaihushi.jphs.column.model.ColumnService> csList = columnServiceService.list(cs);
        for (int i = 0; i < csList.size(); i++) {
            if (csList.get(i).getCreatorId() != null) {

                SystemUser systemUser = systemUserService.loadById(csList.get(i).getCreatorId());
                if (systemUser != null) {
                    csList.get(i).setCreatorName(systemUser.getName());
                }
            }
        }
        SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");

        modelMap.put("csList", csList);
        modelMap.put("session_user", session_user);

        return "activity/column/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(HttpServletRequest req, ModelMap modelMap) {

        SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");
        modelMap.put("session_user", session_user);
        return "activity/column/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Column column = columnService.loadById(id);
        modelMap.put("column", column);
        com.jinpaihushi.jphs.column.model.ColumnService cs = new com.jinpaihushi.jphs.column.model.ColumnService();
        cs.setColumnId(id);
        List<com.jinpaihushi.jphs.column.model.ColumnService> csList = columnServiceService.list(cs);
        for (int i = 0; i < csList.size(); i++) {
            if (csList.get(i).getCreatorId() != null) {

                SystemUser systemUser = systemUserService.loadById(csList.get(i).getCreatorId());
                if (systemUser != null) {
                    csList.get(i).setCreatorName(systemUser.getName());
                }
            }
        }
        modelMap.put("csList", csList);
        return "activity/column/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Column column, CsList csList) {
        com.jinpaihushi.jphs.column.model.ColumnService cs = new com.jinpaihushi.jphs.column.model.ColumnService();
        String result = null;
        SystemUser session_user = (SystemUser) req.getSession().getAttribute("session_user");
        if (column.getId() != null && !column.getId().equals("")) {
            boolean b = columnService.update(column);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/column/err.jhtml";
            }
        }
        else {

            column.setCreatorId(session_user.getId());
            column.setId(UUID.randomUUID().toString());
            result = columnService.insert(column);

            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/column/err.jhtml";
            }

        }

		if( csList.getCsList() != null){
			for (int i = 0; i < csList.getCsList().size(); i++) {
            cs.setImage(csList.getCsList().get(i).getImage());
            cs.setName(csList.getCsList().get(i).getName());
            cs.setBrief(csList.getCsList().get(i).getBrief());
            cs.setSort(csList.getCsList().get(i).getSort());
			cs.setLink(csList.getCsList().get(i).getLink());
            cs.setStatus(1);
            cs.setCreateTime(new Date());

            if (result != null) {
                cs.setColumnId(result);
            } else {
                cs.setColumnId(column.getId());
            }

            cs.setId(csList.getCsList().get(i).getId());
            if (csList.getCsList().get(i).getId() != null) {
                System.out.println("-----update--------");
				System.out.println("文件原名: " + cs.getLink());
                columnServiceService.update(cs);
            } else {
                System.out.println("-----insert--------");
                cs.setId(UUID.randomUUID().toString());
                cs.setCreatorId(session_user.getId());
                columnServiceService.insert(cs);
            }
        }
		}
        
        return "redirect:/column/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = columnService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/column/err.jhtml";
        }

        return "redirect:/column/index.jhtml";
    }

    @RequestMapping(name = "修改栏目状态", path = "/updateStatus.jhtml")
    @ResponseBody
    public JSONObject updateStatus(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Column column) {

        JSONObject message = new JSONObject();
        boolean result = columnService.update(column);
        message.put("result", result);

        return message;
    }

    @RequestMapping(name = "修改栏目服务状态", path = "/updateCsStatus.jhtml")
    @ResponseBody
    public JSONObject updateCsStatus(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, com.jinpaihushi.jphs.column.model.ColumnService cs) {

        JSONObject message = new JSONObject();
        boolean result = columnServiceService.update(cs);
        message.put("result", result);

        return message;
    }
}
