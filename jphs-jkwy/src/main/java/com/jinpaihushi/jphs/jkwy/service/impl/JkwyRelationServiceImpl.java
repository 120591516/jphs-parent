package com.jinpaihushi.jphs.jkwy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderRelationDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyRelationDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderRelation;
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyRelationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyRelationService")
public class JkwyRelationServiceImpl extends BaseServiceImpl<JkwyRelation> implements JkwyRelationService {

    @Autowired
    private JkwyRelationDao jkwyRelationDao;

    @Autowired
    private JkwyOrderRelationDao jkwyOrderRelationDao;

    @Override
    protected BaseDao<JkwyRelation> getDao() {
        return jkwyRelationDao;
    }

    
    /**
     * 查询未购买套餐的亲属
     * @param jkwyRelation
     * @return
     */
    public List<JkwyRelation> getUserRelationIsNotOrder(JkwyRelation jkwyRelation){
    	return jkwyRelationDao.getUserRelationIsNotOrder(jkwyRelation);
    }
    
    /**
     * 查询用户亲属
     * @param jkwyRelation
     * @return
     */
    public List<JkwyRelation> getUserRelationOfOrder(JkwyRelation jkwyRelation) {
        return jkwyRelationDao.getUserRelationOfOrder(jkwyRelation);
    }

    /**
     * 更新亲友关系，如果该亲友已经绑定套餐，则无法修改该亲友信息
     * @param jkwyRelation
     * @return
     */
    public int updateRelation(JkwyRelation jkwyRelation) {
        JkwyOrderRelation jkwyOrderRelation = new JkwyOrderRelation();
        jkwyOrderRelation.setJkwyRelation(jkwyRelation.getId());
        List<JkwyOrderRelation> jkwyOrderRelationList = jkwyOrderRelationDao.list(jkwyOrderRelation);
        if (jkwyOrderRelationList != null && jkwyOrderRelationList.size() > 0) {
            return -1;
        }
        int j_u = jkwyRelationDao.update(jkwyRelation);
        return j_u;
    }

    /**
     * 查询用户亲属
     * @param jkwyRelation
     * @return
     */
    public List<JkwyRelation> getUserRelationByOrderId(String id) {
        return jkwyRelationDao.getUserRelationByOrderId(id);
    }

}