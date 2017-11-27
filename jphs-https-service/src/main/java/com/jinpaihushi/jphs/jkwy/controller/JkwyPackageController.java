package com.jinpaihushi.jphs.jkwy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(name="探访服务套餐",path="/jkwy/package")
public class JkwyPackageController {

	@Autowired
	private JkwyPackageService jkwyPackageService;
	@Autowired
	private UserService userService;
	/**
	 * 根据平台id和套餐id，查询套餐详情
	 * @param hs
	 * @param req
	 * @param resp
	 * @param platformId
	 * @param id
	 * @return
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(name = "详情", path = "/details.json")
	public byte[] getPackageDetails(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String platformId,String id,String userId,
			@RequestParam(value = "type", defaultValue = "1", required = true) Integer type){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.package.details.json");
            }
			if(StringUtils.isEmpty(platformId) || StringUtils.isEmpty(id)){
				return JSONUtil.toJSONResult(0, "参数不能为空！", null);
			}
			if(!StringUtils.isEmpty(userId)){
	        	User user =new User();
	        	user.setId(userId);
	        	user.setStatus(1);
	        	User user_one = userService.load(user);
	        	if(user_one==null){
	        		return JSONUtil.toJSONResult(0, "用户id不合法！", null);
	        	}
	        }
			JkwyPackage jkwyPackage = jkwyPackageService.getJkwyPackageDetails(platformId, id,userId,type);
			if(jkwyPackage.getJkwyRelationList() !=null ){
				// json解析会出现递归异常。因为bean对象是互相循环调用了。所以去除格式化以后再放入，解决问题
				JSONArray arr = new JSONArray().fromObject(jkwyPackage.getJkwyRelationList());
				jkwyPackage.setJkwyRelationList(null);
				JSONObject obj = new JSONObject().fromObject(jkwyPackage);
				obj.put("jkwyRelationList", arr);
	            return JSONUtil.toJSONResult(1, "操作成功！", obj);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", jkwyPackage);
		} catch (Exception e) {
			Util.failLog.error("jkwy.package.details.json", e);
		}
		
		return null;
	}
	
	/**
	 * 根据平台id查询套餐列表
	 * @param hs
	 * @param req
	 * @param resp
	 * @param platformId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name = "获取套餐列表",	path = "/list.json")
	public byte[] getJkwyPackageList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String platformId,
			@RequestParam(value = "type", defaultValue = "1", required = true) Integer type){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.package.list.json");
            }
            if(StringUtils.isEmpty(platformId)){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
            List<JkwyPackage> jkwyPackageList = jkwyPackageService.getJkwyPackagePlatformList(platformId,type);
            
            return JSONUtil.toJSONResult(1, "操作成功！", jkwyPackageList);
		} catch (Exception e) {
			Util.failLog.error("jkwy.package.list.json", e);
		}
		
		return null;
	}
	
}
