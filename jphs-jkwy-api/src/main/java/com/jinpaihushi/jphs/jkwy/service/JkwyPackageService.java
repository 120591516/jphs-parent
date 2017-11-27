package com.jinpaihushi.jphs.jkwy.service;

import java.util.List;

import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
public interface JkwyPackageService extends BaseService<JkwyPackage> {

    /**
     * 保存套餐及套餐价格
     * @param jkwyPackage
     * @param imageType
     * @return
     */
    String insertPackageAndImg(JkwyPackage jkwyPackage, ImageType imageType);

    /**
     * 编辑套餐及套餐价格
     * @param jkwyPackage
     * @param imageType
     * @return
     */
    String updatePackageAndImg(JkwyPackage jkwyPackage, ImageType imageType);

    /**
     * 根据平台id，查询平台下所有健康无忧套餐
     * @param platformId
     * @return
     */
    List<JkwyPackage> getJkwyPackagePlatformList(String platformId, int type);

    /**
     * 根据平台id 和 套餐id查询套餐详情
     * @param platformId
     * @param id
     * @return
     */
    JkwyPackage getJkwyPackageDetails(String platformId, String id, String userId, int deviceType);

    /**
     *  查询详情
     * @param id
     * @return
     */
    JkwyPackage getDetail(String id);

    List<TreeNode> getTreeNode();

}