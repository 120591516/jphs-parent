package com.jinpaihushi.jphs.withdraw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
@Repository("withdrawCashDao")
public interface WithdrawCashDao extends BaseDao<WithdrawCash> {
	
	WithdrawCash withdrawCashTDetail(String id);
	
	
	List<Map<String,Object>> exportWithdrawCashExcel(WithdrawCash withdrawCash);
}
