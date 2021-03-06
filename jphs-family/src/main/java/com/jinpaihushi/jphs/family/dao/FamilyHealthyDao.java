package com.jinpaihushi.jphs.family.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.model.FamilyHealthy;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Repository("familyHealthyDao")
public interface FamilyHealthyDao extends BaseDao<FamilyHealthy> {
	
	/**
	 * 查看健康计划详情
	 * @param familyHealthy
	 * @return
	 */
	FamilyHealthy getDetailMemberAndHealthy(FamilyHealthy familyHealthy);
	
}
