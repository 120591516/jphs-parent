package com.jinpaihushi.jphs.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityReturnDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;
import com.jinpaihushi.jphs.commodity.model.CommoditySaleByNurse;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderInfoService;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:07:25
 * @version 1.0
 */
@Service("commodityOrderInfoService")
public class CommodityOrderInfoServiceImpl extends BaseServiceImpl<CommodityOrderInfo> implements CommodityOrderInfoService{

	@Autowired
	private CommodityOrderInfoDao commodityOrderInfoDao;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private CommodityReturnDao commodityReturnDao;
	
	@Override
	protected BaseDao<CommodityOrderInfo> getDao(){
		return commodityOrderInfoDao;
	}

	@Override
	public Integer updateByOrderNo(CommodityOrderInfo commodityOrderInfo) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.updateByOrderNo(commodityOrderInfo);
	}

	@Override
	public List<CommodityOrderInfo> getListByCoId(String coId) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.getListByCoId(coId);
	}

	@Override
	public List<CommoditySaleByNurse> judgeProfit(CommodityOrderInfo commodityOrderInfo) {
		 
		
		List<CommoditySaleByNurse> list = commodityOrderInfoDao.all(commodityOrderInfo);
		 
		if(commodityOrderInfo.getStatus() == null){
			 
			list = commodityOrderInfoDao.all(commodityOrderInfo);
			Transaction transaction = new Transaction();
			CommodityReturn commodityReturn = new CommodityReturn();
			for (int i = 0; i < list.size(); i++) {
				transaction.setOrderId(list.get(i).getId());
				Transaction tModel = transactionDao.load(transaction);
				if(tModel != null){
					list.get(i).setStatus(2);
				}else{
					commodityReturn.setCommodityOrderInfoId(list.get(i).getId());
					commodityReturn.setStatus(2);
					CommodityReturn cr = commodityReturnDao.load(commodityReturn);
					if(cr!=null){
						list.get(i).setStatus(-2);
					}else{
						list.get(i).setStatus(1);
					}
				}
			}
			
		}else{
		if(commodityOrderInfo.getStatus() == -2){
			 
			list = commodityOrderInfoDao.alreadyRefund(commodityOrderInfo);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStatus(-2);
			}
		}else if(commodityOrderInfo.getStatus() == 2){
			 
			list = commodityOrderInfoDao.alreadyBalance(commodityOrderInfo);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStatus(2);
			}
		}else if(commodityOrderInfo.getStatus() == 0){
			 
			list = commodityOrderInfoDao.turnOver(commodityOrderInfo);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStatus(1);
			}
		}else if(commodityOrderInfo.getStatus() == 1){
			 
			list = commodityOrderInfoDao.turnOver(commodityOrderInfo);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setStatus(1);
			}
		} 
			
		}
		
		
		return list;
	}

	@Override
	public Integer confimOrder(String comId) {
		 
		Integer result = 0;
		
		List<CommodityOrderInfo> confirmList = commodityOrderInfoDao.getConfirmList(comId);
		
		for (int i = 0; i < confirmList.size(); i++) {
			 
			if(confirmList.get(i).getStatus() == 2){
				result = commodityOrderInfoDao.confimOrder(confirmList.get(i).getId());
			}
		}
		
		
		return result;
	}

	@Override
	public List<CommodityOrderInfo> getList(String coId) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.getList(coId);
	}

	@Override
	public Integer getCountByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return commodityOrderInfoDao.getCountByOrderNo(orderNo);
	}

	
}