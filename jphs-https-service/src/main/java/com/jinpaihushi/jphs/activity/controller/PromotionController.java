package com.jinpaihushi.jphs.activity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.activity.service.ActivityPromotionService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/activity/promotion")
public class PromotionController {
	
	@Autowired
    private ActivityPromotionService activityPromotionService;
	
	/**
	 *	根据服务/商品id，priceId(规格id)查询该商品是否存在特价。
	 * @param hs
	 * @param req
	 * @param resp
	 * @param type
	 * @param goodsId
	 * @param priceId
	 * @return
	 */
	@RequestMapping(name="获取服务or商品特价",path="/getGoodsPromotion.json")
	@ResponseBody
	public byte[] getGoodsPromotion(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String userId,String platformId,String goodsId,String priceId,
			 @RequestParam(value = "resourceType", defaultValue = "1", required = true) Integer resourceType){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("activity/promotion.getGoodsPromotion.json,resourceType=" + resourceType+" goodsId="+goodsId);
            }
            // 查空
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(priceId) || StringUtils.isEmpty(platformId)||StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            
            ActivityPromotion activityPromotion = activityPromotionService.ifPromotion(userId, platformId, goodsId, priceId, resourceType);
            if(activityPromotion == null){
            	 return JSONUtil.toJSONResult(0, "未达成！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功!!!!!", activityPromotion);
		} catch (Exception e) {
			  Util.failLog.error("activity/promotion.getGoodsPromotion.json,resourceType=" + resourceType+" goodsId="+goodsId, e);
		}
		return null;
	}
	
}
