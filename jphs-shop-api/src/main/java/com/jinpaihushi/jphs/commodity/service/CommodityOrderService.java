package com.jinpaihushi.jphs.commodity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.service.BaseService;

import net.sf.json.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
public interface CommodityOrderService extends BaseService<CommodityOrder> {

    String createCommodityOrder(String userId, String commodityId, String userAddressId, String cpId, String guideId,
            Integer number, String remark, double payPrice, String code, Integer device, String platformId);

    String cancelShopOrder(String orderNo);

    List<CommodityOrder> getOrderList(String userId, String schedule);

    CommodityOrder getOrderDetail(String orderId);

    Integer updateShopOrderSchedule(CommodityOrder commodityOrder);

    Integer updateRemindTime(CommodityOrder commodityOrder);

    Integer confimOrder(CommodityOrder commodityOrder);

    Integer deleteOrder(CommodityOrder commodityOrder);

    List<CommodityOrder> getListByOrderNo(String OrderNo);

    List<HashMap<String, Object>> loadS(CommodityOrder commodityOrder);

    Integer toUpdatePayPrice(String id, double payPrice);

    List<CommodityOrder> getStatusByOrderNo(String orderNo);

    byte[] balancePayment(String orderId, String orderNo, Double payParice, String userId);

    boolean updateWechatCommodityOrderStutas(SortedMap<Object, Object> packageParams);

    Page<CommodityOrder> getList(CommodityOrder commodityOrder);

    Page<CommodityOrder> getTkList(CommodityOrder commodityOrder);

    /**
     * 
     * @param commodityOrder
     * @return boolean
     * 定时任务  自动结算护士收益
     * 
     */
    boolean commodityPayNurse(CommodityOrder commodityOrder);

    /**
     * 
     * @return boolean
     * 
     * 定时任务 未收货时满7天自动收货
     */
    boolean proxyRecipient();
    boolean proxyRecipientss();

    /**
     * 商品订单活动奖励
     * @return
     */
    List<Map<String, Object>> getSendTransaction();
    
    /**
	 * 新版商品下单
	 * @param userId
	 * @param recommendId
	 * @param userAddressId
	 * @param carArr
	 * @param remarkArr
	 * @param voucherArr
	 * @param siteId
	 * @param payPrice
	 * @param code
	 * @param device
	 * @param platformId
	 * @return
	 */
	JSONObject createCommodityOrder(String userId, String recommendId,String userAddressId,String carArr,String remarkArr,String voucherArr,String siteId,
			double payPrice, String code, Integer device, String platformId);
    
}