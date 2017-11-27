package com.jinpaihushi.jphs.remark.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.remark.dao.RemarkDao;
import com.jinpaihushi.jphs.remark.model.Remark;
import com.jinpaihushi.jphs.remark.service.RemarkService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-09-29 16:55:54
 * @version 1.0
 */
@Service("remarkService")
public class RemarkServiceImpl extends BaseServiceImpl<Remark> implements RemarkService{

	@Autowired
	private RemarkDao remarkDao;
	
	@Override
	protected BaseDao<Remark> getDao(){
		return remarkDao;
	}

}