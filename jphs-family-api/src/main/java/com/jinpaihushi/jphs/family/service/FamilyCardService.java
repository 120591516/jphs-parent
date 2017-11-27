package com.jinpaihushi.jphs.family.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.family.model.FamilyCard;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:53
 * @version 1.0
 */
public interface FamilyCardService extends BaseService<FamilyCard> {

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
	 * 生成一批兑换卡
	 * @param FamilyCard
	 * @param cardNumber
	 * @return
	 */
	int putAll(FamilyCard FamilyCard,int cardNumber);
	
}