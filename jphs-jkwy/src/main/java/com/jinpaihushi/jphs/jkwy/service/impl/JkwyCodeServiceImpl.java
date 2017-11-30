package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyCodeDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyCode;
import com.jinpaihushi.jphs.jkwy.service.JkwyCodeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-30 09:24:38
 * @version 1.0
 */
@Service("jkwyCodeService")
public class JkwyCodeServiceImpl extends BaseServiceImpl<JkwyCode> implements JkwyCodeService{

	@Autowired
	private JkwyCodeDao jkwyCodeDao;
	
	@Override
	protected BaseDao<JkwyCode> getDao(){
		return jkwyCodeDao;
	}

}