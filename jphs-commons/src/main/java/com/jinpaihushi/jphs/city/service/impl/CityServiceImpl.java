package com.jinpaihushi.jphs.city.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.city.dao.CityDao;
import com.jinpaihushi.jphs.city.model.City;
import com.jinpaihushi.jphs.city.service.CityService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-11-13 16:28:56
 * @version 1.0
 */
@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<City> implements CityService{

	@Autowired
	private CityDao cityDao;
	
	@Override
	protected BaseDao<City> getDao(){
		return cityDao;
	}

}