package com.jinpaihushi.jphs.bfm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.bfm.dao.BfmAwardLogDao;
import com.jinpaihushi.jphs.bfm.model.BfmAwardLog;
import com.jinpaihushi.jphs.bfm.service.BfmAwardLogService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-10-26 16:45:32
 * @version 1.0
 */
@Service("bfmAwardLogService")
public class BfmAwardLogServiceImpl extends BaseServiceImpl<BfmAwardLog> implements BfmAwardLogService{

	@Autowired
	private BfmAwardLogDao bfmAwardLogDao;
	
	@Override
	protected BaseDao<BfmAwardLog> getDao(){
		return bfmAwardLogDao;
	}

}