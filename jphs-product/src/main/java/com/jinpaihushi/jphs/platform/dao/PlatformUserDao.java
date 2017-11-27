package com.jinpaihushi.jphs.platform.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.model.PlatformUser;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Repository("platformUserDao")
public interface PlatformUserDao extends BaseDao<PlatformUser> {

    PlatformUser getUserRoleModule(PlatformUser platformUser);

}
