package com.jinpaihushi.jphs.car.service;

import java.util.List;
import java.util.Map;

import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 17:39:23
 * @version 1.0
 */
public interface CarService extends BaseService<Car> {

	List<Car> getList(String creatorId);

	Car lookup(Car car);
	
	boolean updateNumber(Car car);
	
	boolean successOrder(String Ids);
	
	List<Business> getCarBusinessCommodity(Map<String ,Object> map);
	
}