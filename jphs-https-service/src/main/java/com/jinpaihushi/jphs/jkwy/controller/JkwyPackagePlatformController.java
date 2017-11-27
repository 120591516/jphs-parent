package com.jinpaihushi.jphs.jkwy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(name="平台套餐",path="/jkwy/package/platform")
public class JkwyPackagePlatformController {

	@ResponseBody
	@RequestMapping(name = "根据平台id，查询套餐",path = "/packageList.json")
	public byte[] getPackagePlatformList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String platformId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.package.platform.packageList.json");
            }
			if(StringUtils.isEmpty(platformId)){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			
			
		} catch (Exception e) {
			Util.failLog.error("jkwy.package.platform.packageList.json", e);
		}
		
		return null;
	}
	
}
