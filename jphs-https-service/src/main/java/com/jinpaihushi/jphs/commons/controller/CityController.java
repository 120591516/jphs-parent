package com.jinpaihushi.jphs.commons.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.city.model.City;
import com.jinpaihushi.jphs.city.service.CityService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityService cityService;

	@ResponseBody
	@RequestMapping(name="获取城市列表",path = "/getAll.json")
	public byte[] getAllList(HttpServletRequest req, HttpServletResponse resp){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("city.getAllList.json");
            }
            // 查空
            /*if (StringUtils.isEmpty(userId)|| StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }*/
           /* String str = "北京市,上海市,广州市,深圳市,成都市,大连市,福州市,广州市,哈尔滨市,杭州市,济南市,南京市,青岛市,厦门市,上海市,深圳市,沈阳市,苏州市,天津市,武汉市,西安市,长春市,长沙市,郑州市,重庆市";
            String [] strArr = str.split(",");
            
            for(int a=0;a<strArr.length;a++){
            	if(!StringUtils.isEmpty(strArr[a])){

                	City citys = new City();
                	citys.setId(UUIDUtils.getId());
                	citys.setName(strArr[a]);
                	citys.setCreateTime(new Date());
                	citys.setCreatorName("java-开发");
                	citys.setCreatorId("66666");
                	citys.setStatus(0);
                	
                	if(strArr[a].equals("北京市") || strArr[a].equals("上海市") || strArr[a].equals("广州市") || strArr[a].equals("深圳市")){
                		citys.setType(1);
                	}else{
                		citys.setType(2);
                	}
                	cityService.insert(citys);
            	}
            }
            */
            City city = new City();
            city.setStatus(0);
            List<City> cityList = cityService.list(city);
            if(cityList == null ){
            	return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", cityList);
		}catch(Exception e){
			 // 记录日志-fail
            Util.failLog.error("city.getAllList.json" , e);
		}
		return null;
	}
	
}
