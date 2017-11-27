package com.jinpaihushi.jphs.jkwy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(name = "订单相关" , path = "/jkwy/order")
public class JkwyOrderController {

	@Autowired
	private JkwyOrderService jkwyOrderService;
	
	/**
	 * 查询健康档案
	 * @param hs
	 * @param req
	 * @param resp
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="健康档案",path="/getHealthyArchives.json")
	public byte[] getHealthyArchives(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String userId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.order.getHealthyArchives.json userId="+userId);
            }
            if(StringUtils.isEmpty(userId)){
            	return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            JkwyOrder jkwyOrder = new JkwyOrder();
            jkwyOrder.setCreatorId(userId);
            List<JkwyPackagePrice> jkwyPackagePriceList = jkwyOrderService.getHealthyArchives(jkwyOrder);
            return JSONUtil.toJSONResult(1, "成功", jkwyPackagePriceList);
		} catch (Exception e) {
			Util.failLog.error("jkwy.order.getHealthyArchives.json userId="+userId, e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="创建单",path = "/createOrder.json")
	public byte[] createOrder(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,JkwyOrder jkwyOrder,String jkwyRelationId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.order.createOrder.json CreatorId="+jkwyOrder.getCreatorId()+
                		" jkwyRelationId="+jkwyRelationId+" Address="+jkwyOrder.getAddress()
                		+" Address="+jkwyOrder.getAddress()
                		+" DetailAddress="+jkwyOrder.getDetailAddress()
                		+" JkwyPackageId="+jkwyOrder.getJkwyPackageId()
                		+" JkwyPackagePriceId="+jkwyOrder.getJkwyPackagePriceId()
                		+" PayPrice="+jkwyOrder.getPayPrice()
                		+" VoucherUserId="+jkwyOrder.getVoucherUserId()
                		+" VoucherPrice="+jkwyOrder.getVoucherPrice()
                		+" ActivityPromotionId="+jkwyOrder.getActivityPromotionId()
                		+" getPlatformId="+jkwyOrder.getPlatformId() 
                		+" ActivityPromotionPrice="+jkwyOrder.getActivityPromotionPrice()
                		+" code="+jkwyOrder.getCode());
            }
			if(StringUtils.isEmpty(jkwyOrder.getCreatorId()) || StringUtils.isEmpty(jkwyRelationId) || 
					StringUtils.isEmpty(jkwyOrder.getAddress()) || 
					StringUtils.isEmpty(jkwyOrder.getJkwyPackageId()) || 
					StringUtils.isEmpty(jkwyOrder.getJkwyPackagePriceId()) || 
					StringUtils.isEmpty(jkwyOrder.getPlatformId()) || 
					jkwyOrder.getPayPrice() == null || 
					StringUtils.isEmpty(jkwyOrder.getDetailAddress())){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			JSONObject json = jkwyOrderService.createOrder(jkwyOrder, jkwyRelationId);
			if(json.containsKey("resultcode")){
				if(json.getInt("resultcode") == 0){
					return JSONUtil.toJSONResult(0,json.getString("msg"), json.getString("result"));
				}else{
					return JSONUtil.toJSONResult(1,json.getString("msg"), json.getJSONObject("result"));
				}
			}
			return JSONUtil.toJSONResult(0, "异常通知", null);
		} catch (Exception e) {
			Util.failLog.error("jkwy.order.createOrder.json CreatorId="+jkwyOrder.getCreatorId()+
                		" jkwyRelationId="+jkwyRelationId+" Address="+jkwyOrder.getAddress()
                		+" Address="+jkwyOrder.getAddress()
                		+" DetailAddress="+jkwyOrder.getDetailAddress()
                		+" JkwyPackageId="+jkwyOrder.getJkwyPackageId()
                		+" JkwyPackagePriceId="+jkwyOrder.getJkwyPackagePriceId()
                		+" PayPrice="+jkwyOrder.getPayPrice()
                		+" VoucherUserId="+jkwyOrder.getVoucherUserId()
                		+" VoucherPrice="+jkwyOrder.getVoucherPrice()
                		+" ActivityPromotionId="+jkwyOrder.getActivityPromotionId()
                		+" ActivityPromotionPrice="+jkwyOrder.getActivityPromotionPrice()
                		+" code="+jkwyOrder.getCode(), e);
		}
		return null;
	}
	
}
