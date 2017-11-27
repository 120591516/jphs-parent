package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformRoleModuleDao;
import com.jinpaihushi.jphs.platform.model.PlatformRoleModule;
import com.jinpaihushi.jphs.platform.service.PlatformRoleModuleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Service("platformRoleModuleService")
public class PlatformRoleModuleServiceImpl extends BaseServiceImpl<PlatformRoleModule> implements PlatformRoleModuleService{

	@Autowired
	private PlatformRoleModuleDao platformRoleModuleDao;
	
	@Override
	protected BaseDao<PlatformRoleModule> getDao(){
		return platformRoleModuleDao;
	}

}