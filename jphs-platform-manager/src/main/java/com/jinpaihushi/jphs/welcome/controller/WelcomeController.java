package com.jinpaihushi.jphs.welcome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;

@Controller
@RequestMapping(name = "顶级菜单栏", path = "/welcome")
public class WelcomeController extends BaseController<BaseModel> {

    @Override
    protected BaseService<BaseModel> getService() {
        // TODO Auto-generated method stub
        return null;
    }

    @RequestMapping(name = "首页", path = "/index.jhtml")
    public String toLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/index";
    }

    @RequestMapping(name = "会员管理", path = "/user.jhtml")
    public String toUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/user";
    }

    @RequestMapping(name = "护士管理", path = "/nurse.jhtml")
    public String toNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/nurse";
    }

    @RequestMapping(name = "订单管理", path = "/order.jhtml")
    public String toOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/order";
    }

    @RequestMapping(name = "产品管理", path = "/product.jhtml")
    public String toProduct(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/product";
    }

    @RequestMapping(name = "活动管理", path = "/activity.jhtml")
    public String toActivity(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/activity";
    }

    @RequestMapping(name = "信息管理", path = "/information.jhtml")
    public String toInformation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/information";
    }

    @RequestMapping(name = "数据管理", path = "/statistics.jhtml")
    public String toStatistics(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/statistics";
    }

    @RequestMapping(name = "账户管理", path = "/withdraw.jhtml")
    public String toWithdraw(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/withdraw";
    }

    @RequestMapping(name = "系统管理", path = "/system.jhtml")
    public String toSystem(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        //纯跳转后登录页面
        return "/welcome/system";
    }
}
