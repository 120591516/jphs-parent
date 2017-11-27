package com.jinpaihushi.jphs.family.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.model.FamilyCard;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:53
 * @version 1.0
 */
@Repository("familyCardDao")
public interface FamilyCardDao extends BaseDao<FamilyCard> {
	
	/**
	 * 兑换卡首页列表
	 * @param familyCard
	 * @return
	 */
	Page<Map<String,Object>> familyCardIndex(FamilyCard familyCard);
	
	/**
	 * 根据批次号查看详情
	 * @param familyCard
	 * @return
	 */
	List<Map<String,Object>> familyCardDetail(FamilyCard familyCard);
	
	/**
	 * 获取卡号最后一个
	 */
	FamilyCard loadByOneNo();
}
