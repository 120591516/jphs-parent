package com.jinpaihushi.jphs.withdraw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.order.dao.OrderDao;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.withdraw.dao.WithdrawCashDao;
import com.jinpaihushi.jphs.withdraw.model.WithdrawCash;
import com.jinpaihushi.jphs.withdraw.service.WithdrawCashService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-07 11:16:39
 * @version 1.0
 */
@Service("withdrawCashService")
public class WithdrawCashServiceImpl extends BaseServiceImpl<WithdrawCash> implements WithdrawCashService {

    @Autowired
    private WithdrawCashDao withdrawCashDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    protected BaseDao<WithdrawCash> getDao() {
        return withdrawCashDao;
    }

    public List<Map<String, Object>> exportExcel(WithdrawCash withdrawCash) {
        return withdrawCashDao.exportWithdrawCashExcel(withdrawCash);
    }

    @Override
    public int withdrawals(WithdrawCash withdrawCash) {
        //事务模板
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    Double cash_withdrawal = 0.00;
                    //                    Transaction tr = new Transaction();
                    //                    tr.setWithdraw(0);
                    //                    tr.setOperate(4);
                    //                    tr.setCreatorId(withdrawCash.getCreatorId());
                    //查询该护士的可以提现的交易记录
                    List<Map<String, Object>> list = transactionDao.listWithdraw(withdrawCash.getCreatorId());
                    //判断提交的金额
                    String[] ids = new String[list.size()];
                    for (int k = 0; k < list.size(); k++) {
                        Object object = list.get(k).get("amount");
                        cash_withdrawal = DoubleUtils.add(cash_withdrawal, Double.parseDouble(object.toString()));
                        ids[k] = (String) list.get(k).get("id");
                    }
                    if (DoubleUtils.sub(cash_withdrawal, withdrawCash.getAmount()) < 0) {
                        return "0";
                    }
                    //插入提现记录
                    withdrawCash.setStatus(0);
                    withdrawCash.setId(UUIDUtils.getId());
                    withdrawCash.setCreateTime(new Date());
                    int i = withdrawCashDao.insert(withdrawCash);
                    //修改交易表状态
                    if (i > 0) {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("ids", ids);
                        params.put("userId", withdrawCash.getCreatorId());
                        params.put("withdrawCashId", withdrawCash.getId());
                        i = transactionDao.updateWithdrawals(params);
                    }
                    //扣除余额的值
                    Account account = new Account();
                    account.setCreatorId(withdrawCash.getCreatorId());
                    account = accountDao.load(account);
                    account.setBalance(DoubleUtils.sub(account.getBalance(), withdrawCash.getAmount()));
                    accountDao.update(account);
                    //插入提现的交易记录
                    com.jinpaihushi.jphs.transaction.model.Transaction tr = new com.jinpaihushi.jphs.transaction.model.Transaction();
                    tr.setWithdraw(1);
                    tr.setOperate(1);
                    tr.setCreatorId(withdrawCash.getCreatorId());
                    tr.setCreatorName(withdrawCash.getCreatorName());
                    tr.setCreateTime(new Date());
                    tr.setOperateSource(1);
                    tr.setWithdrawCashId(withdrawCash.getId());
                    tr.setRemark("提现");
                    tr.setScore(0);
                    tr.setAmount(withdrawCash.getAmount());
                    tr.setId(UUIDUtils.getId());
                    tr.setStatus(1);
                    transactionDao.insert(tr);

                    return i + "";
                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }

        });
        return (int) Integer.parseInt(rs);
    }

    public WithdrawCash withdrawCashTDetail(String id) {
        WithdrawCash withdrawCash = withdrawCashDao.withdrawCashTDetail(id);
        /*Transaction tr = new Transaction();
        tr.setWithdraw(2);
        tr.setCreatorId(withdrawCash.getCreatorId());
        tr.setStatus(1);
        List<Transaction> tr_list = transactionDao.list(tr);
        List<Order> order_list = new ArrayList<Order>();
        if(tr_list != null && !tr_list.equals("")){
        	for(int a= 0;a<tr_list.size();a++){
        		if(tr_list.get(a).getOrderId() != null && !tr_list.get(a).getOrderId().equals("")){
        			order_list.add(orderDao.loadById(tr_list.get(a).getOrderId()));
        		}
        	}
        	withdrawCash.setOrderList(order_list);
        }*/
        return withdrawCash;
    }

    public int withdrawCashTUpdate(String id, int statuss, String remark, String auditUserId, String auditUserName) {
        //事务模板
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    WithdrawCash withdrawCash = withdrawCashDao.withdrawCashTDetail(id);
                    WithdrawCash withdrawCash_up = new WithdrawCash();
                    withdrawCash_up.setId(id);
                    withdrawCash_up.setStatus(statuss);
                    withdrawCash_up.setRemark(remark);
                    withdrawCash_up.setAuditTime(new Date());
                    withdrawCash_up.setAuditUserId(auditUserId);
                    withdrawCash_up.setAuditUserName(auditUserName);
                    int wco = withdrawCashDao.update(withdrawCash_up);
                    if (wco < 1) {
                        status.setRollbackOnly();//回滚
                        return "2";
                    }
                    List<com.jinpaihushi.jphs.withdraw.model.Transaction> transactionList = withdrawCash
                            .getTransactionList();
                    if (transactionList == null || transactionList.size() < 1) {
                        status.setRollbackOnly();//回滚
                        return "3";
                    }

                    for (int i = 0; i < transactionList.size(); i++) {
                        Transaction transaction = new Transaction();
                        transaction.setId(transactionList.get(i).getId());
                        if (statuss == 1) {
                            transaction.setWithdraw(1);
                        }
                        if (statuss == -1) {
                            transaction.setWithdraw(0);
                        }
                        int td = transactionDao.update(transaction);
                        if (td < 1) {
                            status.setRollbackOnly();//回滚
                            return "4";
                        }
                    }
                    if (statuss == -1) {
                        Transaction transaction_in = new Transaction();
                        transaction_in.setId(UUIDUtils.getId());
                        transaction_in.setAmount(withdrawCash.getAmount());
                        transaction_in.setOperate(4);
                        transaction_in.setWithdraw(1);
                        transaction_in.setRemark("提现失败");
                        transaction_in.setWithdrawCashId(withdrawCash.getId());
                        transaction_in.setCreatorId(withdrawCash.getCreatorId());
                        transaction_in.setCreatorName(withdrawCash.getCreatorName());
                        transaction_in.setCreateTime(new Date());
                        transaction_in.setScore(1);
                        transaction_in.setStatus(1);
                        int ttd = transactionDao.insert(transaction_in);
                        if (ttd < 1) {
                            status.setRollbackOnly();//回滚
                            return "5";
                        }
                        Account account = new Account();
                        account.setCreatorId(withdrawCash.getCreatorId());
                        account = accountDao.load(account);
                        account.setBalance(DoubleUtils.add(account.getBalance(), withdrawCash.getAmount()));
                        int ad = accountDao.update(account);
                        if (ad < 1) {
                            status.setRollbackOnly();//回滚
                            return "6";
                        }
                    }

                    return "1";
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }

        });

        return (int) Integer.parseInt(rs);
    }

}