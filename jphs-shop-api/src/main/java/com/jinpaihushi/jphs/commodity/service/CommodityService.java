package com.jinpaihushi.jphs.commodity.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityMap;
import com.jinpaihushi.jphs.commodity.model.CommodityTreeNode;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
public interface CommodityService extends BaseService<Commodity> {

    List<CommodityMap> getCommodityList(String columnId, String nurseId, String sort);

    Commodity getCommodityDetail(String columnId, String commodityId, String siteId);

    List<Commodity> getSaleByNurse(String nurseId);

    List<Commodity> getNurseSale(String nurseId, String commodityId, String schedule);

    List<Business> getListByCar(String ids,String siteId,String platformId,String userId);

    Commodity getOneDetail(String commodityId, String cpId);

    boolean updateBrowser(String commodityId);

    boolean updateShareNumber(String commodityId);

    boolean updateCount(Map<String, Object> map);

    Page<Commodity> getPageList(Commodity commodity);

    /**
     * 获取商品树
     * @param commodity
     * @return
     */
    List<CommodityTreeNode> getTreeNode(Commodity commodity);

    /**
     * 查询所有商品，只返回id，title，品类id
     * @param commodity
     * @return
     */
    List<Map<String, Object>> getAllCtList(Commodity commodity);

}