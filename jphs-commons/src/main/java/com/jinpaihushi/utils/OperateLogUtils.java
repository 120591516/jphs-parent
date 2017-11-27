package com.jinpaihushi.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jinpaihushi.jphs.operate.model.OperateLog;
import com.jinpaihushi.jphs.operate.service.OperateLogService;

public class OperateLogUtils {

	@Autowired
	private OperateLogService operateLogService;
	
	/**
	 * 管理端操作日志插入
	 */
	public static void putLog(HttpServletRequest req,int type,int result,String remark,String sql,String module_one_name,String module_two_name){
		try{
			String strBackUrl =  req.getContextPath() + req.getServletPath();//请求页面或其他地址  
			if(StringUtils.isEmpty(module_one_name)){
				module_one_name = strBackUrl.split("/")[1];
			}
			if(StringUtils.isEmpty(module_one_name)){
				module_two_name = strBackUrl.split("/")[2];
			}
			
			
			OperateLog operateLog = new OperateLog();
			operateLog.setId(UUIDUtils.getId());
			operateLog.setType(type);
			operateLog.setModuleOneName(module_one_name);
			operateLog.setModuleTwoName(module_two_name);
			operateLog.setSql(sql);
			operateLog.setPath(strBackUrl);
			
			
		}catch (Exception e){
		}
	}
	//获取全路径3=/product/index.jhtml
	
	public static void main(String[] args) {
		String str = "/product/index.jhtml";
		String [] str_arr = str.split("/");
		System.out.println(str_arr[1]);
		
	}
	
}
