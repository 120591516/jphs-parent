package com.jinpaihushi.jphs.jkwy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.platform.model.TreeNode;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Repository("jkwyPackageDao")
public interface JkwyPackageDao extends BaseDao<JkwyPackage> {

    /**
     * 根据平台id和套餐id查询详情
     * @param jkwyPackage
     * @return
     */
    JkwyPackage getJkwyPackageDetails(JkwyPackage jkwyPackage);

    /**
     * 详情
     * @param id
     * @return
     */
    JkwyPackage getDetail(String id);

    /**
     * 查询所有内容
     * @param jkwyPackage
     * @return
     */
    List<JkwyPackage> lists(JkwyPackage jkwyPackage);

    List<TreeNode> getTreeNode();

}
