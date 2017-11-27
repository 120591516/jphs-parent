package com.jinpaihushi.jphs.jkwy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackageContentDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackageContent;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageContentService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyPackageContentService")
public class JkwyPackageContentServiceImpl extends BaseServiceImpl<JkwyPackageContent> implements JkwyPackageContentService{

	@Autowired
	private JkwyPackageContentDao jkwyPackageContentDao;
	
	@Override
	protected BaseDao<JkwyPackageContent> getDao(){
		return jkwyPackageContentDao;
	}

}