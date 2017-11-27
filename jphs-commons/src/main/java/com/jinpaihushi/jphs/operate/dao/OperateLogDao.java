package com.jinpaihushi.jphs.operate.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.operate.model.OperateLog;

/**
 * 
 * @author scj
 * @date 2017-10-16 14:10:15
 * @version 1.0
 */
@Repository("operateLogDao")
public interface OperateLogDao extends BaseDao<OperateLog> {
	
	
	
}
