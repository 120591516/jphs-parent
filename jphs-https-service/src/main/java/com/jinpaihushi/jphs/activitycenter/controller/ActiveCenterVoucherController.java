package com.jinpaihushi.jphs.activitycenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(name="活动中心",path="/activeCenter/voucher")
public class ActiveCenterVoucherController {

	 @Autowired
	 private VoucherService voucherService;
	
	@RequestMapping(name="获取下单流程",path="/getVoucher.json")
	@ResponseBody
	public byte[] getVoucher(HttpServletRequest req, HttpServletResponse resp,String userId,String code){
		try{
			if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("/activeCenter/voucher.getVoucher.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId) ) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int r = voucherService.getActiveCenterVoucher(userId,code);
            String msg = "领取成功！！！";
            if(r == 2){
            	msg = "领取失败，非用户！";
            }
            if(r == 3){
            	msg = "领取失败，该活动已结束！";
            }
            if(r == 4){
            	msg = "领取失败，优惠券领取已完毕！";
            }
            if(r == 5){
            	msg = "领取失败优惠券！";
            }
            if(r == 6){
            	msg = "领取失败！";
            }
            if(r == 7){
            	msg = "已领取过，不能重复领取！";
            }
            return JSONUtil.toJSONResult(r, msg, null);
		} catch (Exception e) {
			Util.failLog.error("familyMode.getMode.json,userId=" + userId , e);
		}
		
		return null;
	}
	
}
