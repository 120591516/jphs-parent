package com.jinpaihushi.jphs.person.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    
    //    @Autowired
    //    private UserService userService;
    @RequestMapping(path = "/userConvertVoucher.json", name = "用户使用优惠券")
    @ResponseBody
    public byte[] userConvertVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String code, @RequestParam(name = "type", defaultValue = "1", required = true) String type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("voucher.userConvertVoucher.json,userId=" + userId + " code=" + code + " type=" + type);
            }
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(code.replace(" ", ""))) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            return voucherService.userConvertVoucher(userId, code.replace(" ", ""), type);
        }
        catch (Exception e) {
            Util.failLog.error("voucher.userConvertVoucher.json,userId=" + userId + " code=" + code + " type=" + type,
                    e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserVoucher.json", name = "获取用户可用的优惠券")
    @ResponseBody
    public byte[] getUserVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String goodsId, String pricePartId, String nurseId,
            @RequestParam(name = "type", defaultValue = "1", required = true) Integer type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId
                        + " pricePartId=" + pricePartId);
            }
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(pricePartId) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            /**
             * type = 1		查询服务优惠券
             * type = 2		查询商品优惠券-单个查询
             * type = 3		查询商品通用优惠券
             * type = 4		根据多个商品购物车id 查询优惠券
             * type = 5		查询健康无忧 产品优惠券
             */
            List<Map<String, Object>> list = voucherService.getUservoucher(pricePartId, goodsId, userId, nurseId,type);
            if (list == null) {
                return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId + " pricePartId="
                    + pricePartId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserVoucherNum.json", name = "获取用户优惠券分类个数")
    @ResponseBody
    public byte[] getUserVoucherNum(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String goodsId, String pricePartId, String nurseId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("voucher.getUserVoucherNum.json,userId=" + userId );
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            List<Map<String, Object>> list = voucherService.getUserVoucherNum(userId);
            if (list == null) {
                return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("voucher.getUserVoucher.json,userId=" + userId + " goodsId=" + goodsId + " pricePartId="
                    + pricePartId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserAllVoucher.json", name = "我的的优惠券")
    @ResponseBody
    public byte[] getUserAllVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer type, Integer status) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "voucher.getUserAllVoucher.json,userId=" + userId + " type=" + type + " status=" + status);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            List<Map<String, Object>> list = voucherService.getUserAllvoucher(userId, type, status);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error(
                    "voucher.getUserAllVoucher.json,userId=" + userId + " type=" + type + " status=" + status, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "计算使用优惠券之后的商品价格", path = "/getGoodsPrice.json")
    public byte[] getGoodsPrice(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String voucherUseId,
            String pricePartId, String userId, String nurseId,
            @RequestParam(name = "type", defaultValue = "1", required = true) Integer type) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("计算使用优惠券之后的商品价格 ---voucher.getGoodsPrice.json,voucherUseId=" + voucherUseId
                        + " pricePartId=" + pricePartId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(voucherUseId) || StringUtils.isEmpty(pricePartId) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            // 判断该用户是否拥有此优惠券
            boolean flag = voucherService.isHaveVoucher(voucherUseId, userId);
            if (!flag) {
                return JSONUtil.toJSONResult(2, "该用户没有改优惠券", null);
            }
            // 计算使用优惠券之后的价格
            Double price = voucherService.getGoodsPrice(voucherUseId, pricePartId, nurseId, type);
            return JSONUtil.toJSONResult(1, "操作成功！", price);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("计算使用优惠券之后的商品价格 ---voucher.getGoodsPrice.json,voucherUseId=" + voucherUseId
                    + " pricePartId=" + pricePartId + " userId=" + userId, e);
        }
        return null;
    }
}
