package com.jinpaihushi.jphs.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformModelDao;
import com.jinpaihushi.jphs.platform.model.PlatformModel;
import com.jinpaihushi.jphs.platform.service.PlatformModelService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Service("platformModelService")
public class PlatformModelServiceImpl extends BaseServiceImpl<PlatformModel> implements PlatformModelService{

	@Autowired
	private PlatformModelDao platformModelDao;
	
	@Override
	protected BaseDao<PlatformModel> getDao(){
		return platformModelDao;
	}

}