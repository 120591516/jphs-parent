package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyModeDao;
import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.jphs.family.service.FamilyModeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyModeService")
public class FamilyModeServiceImpl extends BaseServiceImpl<FamilyMode> implements FamilyModeService{

	@Autowired
	private FamilyModeDao familyModeDao;
	
	@Override
	protected BaseDao<FamilyMode> getDao(){
		return familyModeDao;
	}

	/**
	 * 获取方式首页列表
	 * @param familyMode
	 * @return
	 */
	public Page<FamilyMode> familyIndexList(FamilyMode familyMode){
		return familyModeDao.familyIndexList(familyMode);
	}
	
	/**
	 * 查看详情
	 * @param familyMode
	 * @return
	 */
	public FamilyMode detailPackageAndMode(FamilyMode familyMode){
		return familyModeDao.detailPackageAndMode(familyMode);
	}
}