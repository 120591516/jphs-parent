package com.jinpaihushi.jphs.platform.service;

import java.util.List;

import com.jinpaihushi.jphs.platform.model.PlatformUser;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
public interface PlatformUserService extends BaseService<PlatformUser> {

    List<String> getUserModule(String id);

}