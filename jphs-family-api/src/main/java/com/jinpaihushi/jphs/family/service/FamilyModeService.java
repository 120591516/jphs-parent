package com.jinpaihushi.jphs.family.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
public interface FamilyModeService extends BaseService<FamilyMode> {

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