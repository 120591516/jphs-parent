package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackagePlatformDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePlatform;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackagePlatformService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-22 11:06:05
 * @version 1.0
 */
@Service("jkwyPackagePlatformService")
public class JkwyPackagePlatformServiceImpl extends BaseServiceImpl<JkwyPackagePlatform> implements JkwyPackagePlatformService{

	@Autowired
	private JkwyPackagePlatformDao jkwyPackagePlatformDao;
	
	@Override
	protected BaseDao<JkwyPackagePlatform> getDao(){
		return jkwyPackagePlatformDao;
	}

}