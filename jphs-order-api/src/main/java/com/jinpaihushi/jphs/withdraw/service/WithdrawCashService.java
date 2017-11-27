package com.jinpaihushi.jphs.withdraw.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
public interface WithdrawCashService extends BaseService<WithdrawCash> {

    int withdrawals(WithdrawCash withdrawCash);

    WithdrawCash withdrawCashTDetail(String id);
    
    int withdrawCashTUpdate(String id,int status,String remark,String auditUserId,String auditUserName);
    
    /**
     * 下载提现记录-excel
     * @param user
     * @return
     */
    List<Map<String,Object>> exportExcel(WithdrawCash withdrawCash);
    
}