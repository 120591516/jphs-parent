package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyTimeAxisDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyTimeAxis;
import com.jinpaihushi.jphs.jkwy.service.JkwyTimeAxisService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyTimeAxisService")
public class JkwyTimeAxisServiceImpl extends BaseServiceImpl<JkwyTimeAxis> implements JkwyTimeAxisService{

	@Autowired
	private JkwyTimeAxisDao jkwyTimeAxisDao;
	
	@Override
	protected BaseDao<JkwyTimeAxis> getDao(){
		return jkwyTimeAxisDao;
	}

}