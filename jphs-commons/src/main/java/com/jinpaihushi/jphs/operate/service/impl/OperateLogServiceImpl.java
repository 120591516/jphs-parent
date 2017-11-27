package com.jinpaihushi.jphs.operate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.operate.dao.OperateLogDao;
import com.jinpaihushi.jphs.operate.model.OperateLog;
import com.jinpaihushi.jphs.operate.service.OperateLogService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-10-16 14:10:15
 * @version 1.0
 */
@Service("operateLogService")
public class OperateLogServiceImpl extends BaseServiceImpl<OperateLog> implements OperateLogService{

	@Autowired
	private OperateLogDao operateLogDao;
	
	@Override
	protected BaseDao<OperateLog> getDao(){
		return operateLogDao;
	}

}