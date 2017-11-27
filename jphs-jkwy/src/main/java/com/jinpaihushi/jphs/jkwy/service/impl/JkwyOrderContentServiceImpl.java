package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderContentDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderContent;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderContentService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyOrderContentService")
public class JkwyOrderContentServiceImpl extends BaseServiceImpl<JkwyOrderContent> implements JkwyOrderContentService{

	@Autowired
	private JkwyOrderContentDao jkwyOrderContentDao;
	
	@Override
	protected BaseDao<JkwyOrderContent> getDao(){
		return jkwyOrderContentDao;
	}

}