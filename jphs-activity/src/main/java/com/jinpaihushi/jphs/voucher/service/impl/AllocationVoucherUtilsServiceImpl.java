package com.jinpaihushi.jphs.voucher.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.product.dao.ProductDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.voucher.dao.VoucherDao;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.model.AllocationVoucher;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.AllocationVoucherUtilsService;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.RandomUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Service("allocationVoucherUtilsService")
public class AllocationVoucherUtilsServiceImpl implements AllocationVoucherUtilsService{

	@Autowired
    private PlatformTransactionManager txManager;
	@Autowired
    private VoucherDao voucherDao;
    @Autowired
    private VoucherUseDao voucherUseDao;
    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GoodsDao goodsDao;
    

    /**
     * 送优惠券
     * @param userId		用户id
     * @param type			发放优惠请类型	1：注册发放优惠券
     * 									2：
     * 									
     */
	public void setVoucher(String userId,int type,String goodsId,String grantName){
		// 		批次号
		String batchNo_arr = "";
		//		发放数量
		String number_arr = "";
		//		发放类型	1:原始数据复制；2：在当前时间叠加天数
		String type_arr = "";
		//		有效天数
		String day_arr = "";
		//		是否允许重复领取 0:不允许重复领取；1：允许重复领取
		String ifRepeat_arr = "";
		
		int types = 1;
		
		//	五个数组length必须一致
		if(types == 1){
			batchNo_arr = "XJS20171129152500,";
			number_arr = "3";
			type_arr = "2";
			day_arr = "30,30,20,";
			ifRepeat_arr = "2";
		}
		if(types == 2){
			batchNo_arr = "XJS20171129152500,XJS20171129152102";
			number_arr = "1,1";
			type_arr = "1,2";
			day_arr = "0,20";
			ifRepeat_arr = "1,0";
		}
		
		/**
		 * 	母婴护理-下单立送优惠券
		 */
		if(types == 133){
			batchNo_arr = "XJS20171130135403";
			number_arr = "1";
			type_arr = "1";
			day_arr = "30";
			ifRepeat_arr = "1";
		}
		/**
		 * 	护士上门-下单立送优惠券
		 */
		if(types == 137){
			batchNo_arr = "XJS20171130135336";
			number_arr = "1";
			type_arr = "1";
			day_arr = "30";
			ifRepeat_arr = "1";
		}
		/**
		 * 居家康复-下单立送优惠券
		 */
		if(types == 135){
			batchNo_arr = "XJS20171130135448";
			number_arr = "1";
			type_arr = "1";
			day_arr = "30";
			ifRepeat_arr = "1";
		}
		/**
		 * 医疗体检-下单立送优惠券
		 */
		if(types == 139){
			batchNo_arr = "XJS20171130135520";
			number_arr = "1";
			type_arr = "1";
			day_arr = "30";
			ifRepeat_arr = "1";
		}
		/**
		 * 挂号陪诊-下单立送优惠券
		 */
		if(types == 141){
			batchNo_arr = "XJS20171130135558";
			number_arr = "1";
			type_arr = "1";
			day_arr = "30";
			ifRepeat_arr = "1";
		}

		// 	四个参数长度必须一致
		String [] batchNo_arrs = batchNo_arr.split(",");
		String [] number_arrs = number_arr.split(",");
		String [] type_arrs = type_arr.split(",");
		String [] day_arrs = day_arr.split(",");
		String [] ifRepeat_arrs = ifRepeat_arr.split(",");
		
		List<AllocationVoucher> allocationVoucherList = new ArrayList<AllocationVoucher>();
		for(int bntd = 0; bntd < batchNo_arrs.length;bntd++){
			AllocationVoucher allocationVoucherOne = new AllocationVoucher();
			allocationVoucherOne.setBatchNo(batchNo_arrs[bntd]);
			allocationVoucherOne.setNumber(Integer.parseInt(number_arrs[bntd]));
			allocationVoucherOne.setType(Integer.parseInt(type_arrs[bntd]));
			allocationVoucherOne.setDay(Integer.parseInt(day_arrs[bntd]));
			allocationVoucherOne.setIfRepeat(Integer.parseInt(ifRepeat_arrs[bntd]));
			
			allocationVoucherList.add(allocationVoucherOne);
		}
		int rs = putUserVoucher(allocationVoucherList,userId,grantName);
		if (Util.debugLog.isDebugEnabled()) {
            Util.debugLog.debug("给用户发放优惠请 ·	 rs=" + rs);
        }
	}
	
	public int putUserVoucher(List<AllocationVoucher> allocationVoucherList,String userId,String grantName){
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {
                	User user = userDao.loadById(userId);
                	if(user == null){
                		return "2";
                	}
                	
                	for(int a=0;a<allocationVoucherList.size();a++){
                		AllocationVoucher allocationVoucherOne = allocationVoucherList.get(a);
                		
                		if(allocationVoucherOne.getIfRepeat() == 0){
                			/*<!-- 查询该批次优惠券，某个用户的拥有量 -->*/
                			if(voucherUseDao.getVoucherBatchNoUserNumber(userId, allocationVoucherOne.getBatchNo()) > 0){
                				  return "3";
                					// continue;
                			}
                		}
                        Voucher voucher = voucherDao.getVoucherVoucherRepertorylist(allocationVoucherOne.getBatchNo());
                        if (voucher == null) {
                        	 status.setRollbackOnly();// 回滚
                            return "4";
                        }
                        if(voucher.getStatus() == -1){
                        	 status.setRollbackOnly();// 回滚
                        	// 优惠券已失效
                        	return "5";
                        }
                        
                        if(new Date().getTime() < voucher.getActivationStartTime().getTime()){
                        	 status.setRollbackOnly();// 回滚
                        	// 未到优惠券领取时间
                        	return "6";
                        }
                        if(new Date().getTime() > voucher.getActivationEndTime().getTime()){
                        	 status.setRollbackOnly();// 回滚
                        	//	领取时间已过
                        	return "7";
                        }
                        
                        List<VoucherRepertory> VoucherRepertoryList = voucher.getVoucherRepertoryList();
                        if (VoucherRepertoryList == null || VoucherRepertoryList.size() < 1) {
                        	status.setRollbackOnly();// 回滚
                            return "8";
                        }
                        
                        if(VoucherRepertoryList.size() < allocationVoucherOne.getNumber()){
                        	 status.setRollbackOnly();// 回滚
                        	return "9";
                        }
                        
                        for(int v = 0; v< allocationVoucherOne.getNumber();v++){
                            int vro = 0;
                            if (VoucherRepertoryList.size() - 1 > 1) {
                                vro = RandomUtils.randomCommon(0, VoucherRepertoryList.size() - 1, 1)[0];
                            }
                            VoucherRepertory voucherRepertoryOne = VoucherRepertoryList.get(vro);
                            voucherRepertoryOne.setStatus(1);
                            int vrou = voucherRepertoryDao.update(voucherRepertoryOne);
                            if (vrou < 1) {
                                status.setRollbackOnly();// 回滚
                                return "10";
                            }
                            VoucherRepertoryList.remove(vro);
                            
                            VoucherUse voucherUse = new VoucherUse();
                            voucherUse.setId(UUIDUtils.getId());
                            voucherUse.setVoucherRepertoryId(voucherRepertoryOne.getId());
                            voucherUse.setPhone(user.getPhone());
                            voucherUse.setAmount(voucherRepertoryOne.getAmount());
                            if(allocationVoucherOne.getType() == 1){
                            	voucherUse.setStartTime(voucher.getStartTime());
                                voucherUse.setEndTime(voucher.getEndTime());
                            } else if(allocationVoucherOne.getType() == 2){
                            	voucherUse.setStartTime(new Date());
                                voucherUse.setEndTime(DateUtils.addDate(new Date(),(long)allocationVoucherOne.getDay()));
                            }
                            
                            voucherUse.setCreatorName(user.getName());
                            voucherUse.setCreatorId(user.getId());
                            voucherUse.setCreateTime(new Date());
                            voucherUse.setStatus(0);
                            voucherUse.setGrantName(grantName);
                            int vud = voucherUseDao.insert(voucherUse);
                            if (vud < 1) {
                                status.setRollbackOnly();// 回滚
                                return "11";
                            }
                        }
                	}
                } catch (Exception e) {
                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return "1";
            }
        });
		return Integer.parseInt(rs);
	}
}
