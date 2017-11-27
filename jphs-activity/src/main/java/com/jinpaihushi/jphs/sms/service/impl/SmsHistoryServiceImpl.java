package com.jinpaihushi.jphs.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sms.dao.SmsHistoryDao;
import com.jinpaihushi.jphs.sms.model.SmsHistory;
import com.jinpaihushi.jphs.sms.service.SmsHistoryService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Service("smsHistoryService")
public class SmsHistoryServiceImpl extends BaseServiceImpl<SmsHistory> implements SmsHistoryService{

	@Autowired
	private SmsHistoryDao smsHistoryDao;
	
	@Override
	protected BaseDao<SmsHistory> getDao(){
		return smsHistoryDao;
	}

}