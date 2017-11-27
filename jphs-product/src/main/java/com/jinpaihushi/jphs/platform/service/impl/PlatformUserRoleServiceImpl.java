package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformUserRoleDao;
import com.jinpaihushi.jphs.platform.model.PlatformUserRole;
import com.jinpaihushi.jphs.platform.service.PlatformUserRoleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Service("platformUserRoleService")
public class PlatformUserRoleServiceImpl extends BaseServiceImpl<PlatformUserRole> implements PlatformUserRoleService{

	@Autowired
	private PlatformUserRoleDao platformUserRoleDao;
	
	@Override
	protected BaseDao<PlatformUserRole> getDao(){
		return platformUserRoleDao;
	}

}