package com.jinpaihushi.jphs.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.dao.PlatformUserDao;
import com.jinpaihushi.jphs.platform.model.PlatformModel;
import com.jinpaihushi.jphs.platform.model.PlatformRole;
import com.jinpaihushi.jphs.platform.model.PlatformUser;
import com.jinpaihushi.jphs.platform.service.PlatformUserService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-01 16:25:41
 * @version 1.0
 */
@Service("platformUserService")
public class PlatformUserServiceImpl extends BaseServiceImpl<PlatformUser> implements PlatformUserService {

    @Autowired
    private PlatformUserDao platformUserDao;

    @Override
    protected BaseDao<PlatformUser> getDao() {
        return platformUserDao;
    }

    @Override
    public List<String> getUserModule(String id) {
        List<String> list = new ArrayList<>();
        PlatformUser platformUser = new PlatformUser();
        platformUser.setId(id);
        PlatformUser user = platformUserDao.getUserRoleModule(platformUser);
        if (user != null) {
            for (PlatformRole role : user.getRoleList()) {
                for (PlatformModel module : role.getModuleList()) {
                    if (!list.contains(module.getUrl())) {
                        list.add(module.getUrl());
                    }
                }
            }
        }
        return list;
    }

}