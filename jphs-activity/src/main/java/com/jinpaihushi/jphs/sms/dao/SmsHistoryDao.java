package com.jinpaihushi.jphs.sms.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sms.model.SmsHistory;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Repository("smsHistoryDao")
public interface SmsHistoryDao extends BaseDao<SmsHistory> {
	
	
	
}
