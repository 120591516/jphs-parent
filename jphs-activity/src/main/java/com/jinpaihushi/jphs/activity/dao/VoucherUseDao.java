package com.jinpaihushi.jphs.activity.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;

/**
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
@Repository("voucherUseDao")
public interface VoucherUseDao extends BaseDao<VoucherUse> {

	Page<VoucherUse> getList(VoucherUse voucherUse);

	Page<VoucherUse> getDetailList(String id);

	VoucherUse getVoucherUse(String id);

	/*<!-- 查询该批次优惠券，某个用户的拥有量 -->*/
	Integer getVoucherBatchNoUserNumber(@Param("creatorId") String creatorId, @Param("batchNo") String batchNo);
}
