package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformRoleDao;
import com.jinpaihushi.jphs.platform.model.PlatformRole;
import com.jinpaihushi.jphs.platform.service.PlatformRoleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Service("platformRoleService")
public class PlatformRoleServiceImpl extends BaseServiceImpl<PlatformRole> implements PlatformRoleService{

	@Autowired
	private PlatformRoleDao platformRoleDao;
	
	@Override
	protected BaseDao<PlatformRole> getDao(){
		return platformRoleDao;
	}

}