package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderRelationDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderRelationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyOrderRelationService")
public class JkwyOrderRelationServiceImpl extends BaseServiceImpl<JkwyOrderRelation> implements JkwyOrderRelationService{

	@Autowired
	private JkwyOrderRelationDao jkwyOrderRelationDao;
	
	@Override
	protected BaseDao<JkwyOrderRelation> getDao(){
		return jkwyOrderRelationDao;
	}

}