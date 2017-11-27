package com.jinpaihushi.jphs.column.service;

import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.service.BaseService;

import net.sf.json.JSONObject;

/**
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @version 1.0
 */
public interface ColumnServiceService extends BaseService<ColumnService> {

	/**
	 * 根据type查询 服务或者商品分类
	 * @param type
	 * @param link
	 * @return
	 */
	JSONObject selectListProduct(String type,String link);

}