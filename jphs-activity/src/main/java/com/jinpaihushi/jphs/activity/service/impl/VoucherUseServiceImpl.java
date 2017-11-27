package com.jinpaihushi.jphs.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
@Service("voucherUseService")
public class VoucherUseServiceImpl extends BaseServiceImpl<VoucherUse> implements VoucherUseService {

    @Autowired
    private VoucherUseDao voucherUseDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;

    @Override
    protected BaseDao<VoucherUse> getDao() {
        return voucherUseDao;
    }

    @Override
    public Page<VoucherUse> getList(VoucherUse voucherUse) {
        // TODO Auto-generated method stub
        Page<VoucherUse> list = voucherUseDao.getList(voucherUse);

        return list;
    }

    @Override
    public Page<VoucherUse> getDetailList(String id) {
        // TODO Auto-generated method stub
        Page<VoucherUse> list = voucherUseDao.getDetailList(id);
        /*for (int i = 0;list.size();i++ ){
        	System.out.println(list.get(i).toString());
        }*/
        return list;
    }

    @Override
    public VoucherUse getVoucherUse(String id) {
        // TODO Auto-generated method stub
        return voucherUseDao.getVoucherUse(id);
    }

    @Override
    public String addVoucherUser(VoucherUse voucherUse, String voucherRepertoryIds) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板

            public String doInTransaction(final TransactionStatus status) {
                int j = 0;
                try {
                    //功能区
                    //插入数据
                    String[] voucherRepertoryId = voucherRepertoryIds.split(",");
                    for (int i = 0; i < voucherRepertoryId.length; i++) {
                        voucherUse.setId(UUIDUtils.getId());
                        voucherUse.setVoucherRepertoryId(voucherRepertoryId[i]);
                        voucherUseDao.insert(voucherUse);
                    }
                    //修改仓库的数据
                    j = voucherRepertoryDao.updateVoucherRepertory(voucherRepertoryId);
                    return j + "";

                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }
        });
        return null;
    }

}