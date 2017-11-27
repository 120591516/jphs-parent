package com.jinpaihushi.jphs.jkwy.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePlatform;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-22 11:06:05
 * @version 1.0
 */
@Repository("jkwyPackagePlatformDao")
public interface JkwyPackagePlatformDao extends BaseDao<JkwyPackagePlatform> {

    /**
     * 根据套餐id删除绑定平台信息
     * @param id 套餐id·
     */
    void deleteByPackageId(String id);

}
