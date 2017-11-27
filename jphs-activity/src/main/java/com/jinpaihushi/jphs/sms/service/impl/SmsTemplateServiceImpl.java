package com.jinpaihushi.jphs.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sms.dao.SmsTemplateDao;
import com.jinpaihushi.jphs.sms.model.SmsTemplate;
import com.jinpaihushi.jphs.sms.service.SmsTemplateService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-11-23 15:40:14
 * @version 1.0
 */
@Service("smsTemplateService")
public class SmsTemplateServiceImpl extends BaseServiceImpl<SmsTemplate> implements SmsTemplateService{

	@Autowired
	private SmsTemplateDao smsTemplateDao;
	
	@Override
	protected BaseDao<SmsTemplate> getDao(){
		return smsTemplateDao;
	}

}