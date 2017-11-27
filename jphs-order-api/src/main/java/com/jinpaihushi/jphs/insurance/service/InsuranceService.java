package com.jinpaihushi.jphs.insurance.service;

import com.jinpaihushi.jphs.insurance.model.Insurance;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:43
 * @version 1.0
 */
public interface InsuranceService extends BaseService<Insurance> {

	/**
	 * 获取最新一条保险记录
	 * @param id	用户id
	 * @return
	 */
	Insurance upInsuranceToOne(String id);

}