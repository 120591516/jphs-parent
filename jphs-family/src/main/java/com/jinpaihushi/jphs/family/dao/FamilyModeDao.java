package com.jinpaihushi.jphs.family.dao;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.model.FamilyMode;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Repository("familyModeDao")
public interface FamilyModeDao extends BaseDao<FamilyMode> {
	
	/**
	 * 获取方式首页列表
	 * @param familyMode
	 * @return
	 */
	Page<FamilyMode> familyIndexList(FamilyMode familyMode);
	
	/**
	 * 查看详情
	 * @param familyMode
	 * @return
	 */
	FamilyMode detailPackageAndMode(FamilyMode familyMode);
	
}
