package com.jinpaihushi.jphs.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.model.User;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
@Repository("userDao")
public interface UserDao extends BaseDao<User> {

	User getUserDetail(@Param("id") String id);

	Page<User> userList(User user);

	User findUser(User user);

	User queryUser(User user);
	List<Map<String,Object>> exportUserExcel(User user);
	
	Page<User> getUserList(User user);
	
	/**
	 * 查询用户优惠券
	 * @param map
	 * @return
	 */
	User commodityUserVoucherIfUsable(Map<String ,Object> map);
	
	/**
	 * 修改优惠券，已使用状态
	 */
	int updateCommodityUserVoucher(String id);
}
