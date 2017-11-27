package com.jinpaihushi.jphs.sms.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sms.model.SmsTemplate;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Repository("smsTemplateDao")
public interface SmsTemplateDao extends BaseDao<SmsTemplate> {
	
	
	
}
