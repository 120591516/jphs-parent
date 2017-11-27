package com.jinpaihushi.jphs.user.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.export.model.ExportFile;
import com.jinpaihushi.jphs.export.service.ExportFileService;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.util.ExcelUtil;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
@Controller
@RequestMapping(name = "用户", path = "/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private AccountService accountService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, User user,
            Integer p, Integer n) {
        startPage(p, n);
        //只查询用户 
        user.setType(1);
        user.setStatus(1);
        if (null != user.getAddress() && user.getAddress().equals(",")) {
            user.setAddress("");
        }
        Page<User> list = userService.getUserList(user);
        // Page<User> list = userService.userList(user);
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getId();
            UserAddress userAddress = new UserAddress();
            Account account = new Account();
            account.setCreatorId(id);
            userAddress.setCreatorId(id);
            Page<UserAddress> addresslist = userAddressService.query(userAddress);
            if (addresslist.size() > 0) {
                list.get(i).setProvince(addresslist.get(0).getProvince());
                list.get(i).setCity(addresslist.get(0).getCity());
                list.get(i).setArea(addresslist.get(0).getArea());
                list.get(i).setDetailaddress(addresslist.get(0).getDetailaddress());
            }

            Page<Account> accountlist = accountService.query(account);
            if (accountlist.size() > 0) {
                list.get(i).setBalance(accountlist.get(0).getBalance());
                list.get(i).setScore(accountlist.get(0).getScore());

            }
        }
        if (StringUtils.isNotEmpty(user.getAddress())) {
            String[] areas = user.getAddress().split(",");
            if (areas.length == 1)
                user.setProvince(areas[0]);
            if (areas.length == 2)
                user.setCity(areas[1]);
        }
        PageInfos<User> pageInfo = new PageInfos<User>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);

        return "user/user/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        User user = userService.loadById(id);

        modelMap.put("user", user);
        return "user/user/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        return "user/user/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        User user = userService.getUserDetail(id);
        UserAddress userAddress = new UserAddress();
        userAddress.setCreatorId(id);
        NurseImages nurseImages = new NurseImages();
        nurseImages.setCreatorId(id);
        Page<NurseImages> query = nurseImagesService.query(nurseImages);
        if (query.size() > 0) {
            modelMap.put("images", query.get(0));
        }
        else {
            modelMap.put("images", null);
        }
        Page<UserAddress> list = userAddressService.query(userAddress);
        modelMap.put("user", user);
        modelMap.put("list", list);
        return "user/user/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            User user) {

        if (user.getId() != null && !user.getId().equals("")) {
            boolean b = userService.update(user);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/user/err.jhtml";
            }
        }
        else {
            user.setId(UUID.randomUUID().toString());
            String result = userService.insert(user);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/user/err.jhtml";
            }
        }
        return "redirect:/user/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        User user = userService.loadById(id);
        user.setStatus(-1);
        boolean b = userService.update(user);
        //boolean b = userService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/user/err.jhtml";
        }

        return "redirect:/user/index.jhtml";
    }

    @RequestMapping(name = "生成用户Excel", path = "/getExcel.jhtml")
    @ResponseBody
    public void getExcel(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            User user) {
        user.setType(1);
        user.setStatus(1);
        List<Map<String, Object>> list = userService.exportExcel(user);
        JSONArray ja = new JSONArray();
        for (Map<String, Object> map : list) {
            ja.add(map);
        }
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("name", "姓名");
        headMap.put("userSex", "性别");
        headMap.put("phone", "手机号");
        headMap.put("device", "注册端");
        headMap.put("address", "地址");
        headMap.put("u_name", "推荐人");
        headMap.put("u_phone", "推荐人手机号");
        headMap.put("balance", "账号余额");
        headMap.put("available_score", "剩余积分");
        headMap.put("create_time", "注册时间");

        String title = "用户信息";
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        String phone = systemUser.getPhone();
        String name = systemUser.getName();

        ExportFile exportFile = new ExportFile();
        exportFile.setId(UUIDUtils.getId());
        exportFile.setFileName(title + ".xlsx");
        exportFile.setCreatorIp(ip);
        exportFile.setCreatorName(name);
        exportFile.setCreatorPhone(phone);
        exportFile.setCreatorId(systemUser.getId());
        exportFile.setCreateTime(new Date());
        exportFile.setStatus(1);
        exportFileService.insert(exportFile);

        ExcelUtil.downloadExcelFile(title, headMap, ja, resp);
    }
}
