package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackagePriceDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackagePriceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyPackagePriceService")
public class JkwyPackagePriceServiceImpl extends BaseServiceImpl<JkwyPackagePrice> implements JkwyPackagePriceService{

	@Autowired
	private JkwyPackagePriceDao jkwyPackagePriceDao;
	
	@Override
	protected BaseDao<JkwyPackagePrice> getDao(){
		return jkwyPackagePriceDao;
	}

}