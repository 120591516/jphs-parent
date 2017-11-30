package com.jinpaihushi.jphs.voucher.service;

public interface AllocationVoucherUtilsService {

	  /**
	 * 送优惠券
	 * @param userId
	 * @param type
	 */
	void setVoucher(String userId,int type,String goodsId,String grantName);
	
}
