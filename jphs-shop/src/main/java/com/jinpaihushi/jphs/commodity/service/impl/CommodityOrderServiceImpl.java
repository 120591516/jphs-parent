package com.jinpaihushi.jphs.commodity.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.car.dao.CarDao;
import com.jinpaihushi.jphs.car.model.ActivityPromotion;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityLogisticsDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityLogistics;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.logistics.dao.LogisticsDao;
import com.jinpaihushi.jphs.logistics.model.Logistics;
import com.jinpaihushi.jphs.nurse.dao.NurseCommodityDao;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.model.Voucher;
import com.jinpaihushi.jphs.user.model.VoucherRepertory;
import com.jinpaihushi.logistics.KdniaoTrackQueryAPI;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Service("commodityOrderService")
public class CommodityOrderServiceImpl extends BaseServiceImpl<CommodityOrder> implements CommodityOrderService {

	@Autowired
	private CommodityOrderDao commodityOrderDao;

	@Autowired
	private CommodityDao commodityDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CommodityOrderInfoDao commodityOrderInfoDao;

	@Autowired
	private CommodityPriceDao commodityPriceDao;

	@Autowired
	private UserAddressDao userAddressDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CarDao carDao;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private DoPostSmsService doPostSmsService;

	@Autowired
	private CommodityLogisticsDao commodityLogisticsDao;

	@Autowired
	private LogisticsDao logisticsDao;

	@Autowired
	private NurseCommodityDao nurseCommodityDao;

	@Value("${SMS_pay_success}")
	private String SMS_pay_success;

	@Override
	protected BaseDao<CommodityOrder> getDao() {
		return commodityOrderDao;
	}

	public List<HashMap<String, Object>> loadS(CommodityOrder commodityOrder) {
		return commodityOrderDao.loadS(commodityOrder);
	}

	@Autowired
	private PlatformTransactionManager txManager;// 创建事务管理器
	
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
	public JSONObject createCommodityOrder(String userId, String recommendId,String userAddressId,String carArr,String remarkArr,String voucherArr,String siteId,
			double payPrice, String code, Integer device, String platformId){
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
			@SuppressWarnings("static-access")
			public String doInTransaction(final TransactionStatus status) {
				JSONObject re_json = new JSONObject();
				// 订单号
				String orderNo = "";
				// 订单号
				String goodsName = "";
				// 支付总金额
				double totePrice=0d;
				try {
					
					// 按时间格式生成orderNo
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					orderNo = sdf.format(date);
					orderNo = "CM" + orderNo + (Math.random() * 9000 + 1000 + "").substring(0, 4);
					
					String[] idArr = carArr.split(",");
			        Map<String ,Object> map = new HashMap<String,Object>();
			        map.put("ids", idArr);
			        map.put("siteId", siteId);
			        map.put("platformId", platformId);
			        List<Business>	businessList =  carDao.getCarBusinessCommodity(map);
			        if(businessList == null || businessList.size() < 1){
			        	re_json.put("resultcode", 0);
						re_json.put("msg", "根据购物车id查询数据错误");
						re_json.put("result", 0);
						return re_json.toString();
			        }
			        /**
			         * 根据特价type判断事都满足该特价活动
			         */
			        for(int a=0;a<businessList.size();a++){
			        	List<CommodityPrice> commodityPriceList = businessList.get(a).getCommodityPriceList();
			        	for(int b=0;b<commodityPriceList.size();b++){
			        		if(commodityPriceList.get(b).getActivityPromotion() == null){
			        			continue;
			        		}
			        		ActivityPromotion activityPromotion = commodityPriceList.get(b).getActivityPromotion();
			        		if(activityPromotion.getType() == 1){
			        			continue;
			        		}else if(activityPromotion.getType() == 2){
			        			CommodityOrderInfo commodityOrderInfo =new CommodityOrderInfo();
			        			commodityOrderInfo.setCommodityId(activityPromotion.getResourceId());
			        			commodityOrderInfo.setCreatorId(userId);
			        			List<CommodityOrderInfo> commodityOrderInfoList = commodityOrderInfoDao.list(commodityOrderInfo);
			        			if(commodityOrderInfoList == null){
			        				continue;
			        			}else{
			        				commodityPriceList.get(b).getActivityPromotion().setPrice(0d);
			        			}
			        		}else if(activityPromotion.getType() == 3){
			        			CommodityOrderInfo commodityOrderInfo =new CommodityOrderInfo();
			        			commodityOrderInfo.setCommodityId(activityPromotion.getResourceId());
			        			commodityOrderInfo.setCreatorId(userId);
			        			List<CommodityOrderInfo> commodityOrderInfoList = commodityOrderInfoDao.list(commodityOrderInfo);
			        			if(commodityOrderInfoList !=null && commodityOrderInfoList.size() == 1){
			        				continue;
			        			}else{
			        				commodityPriceList.get(b).getActivityPromotion().setPrice(0d);
			        			}
			        		}
			        	}
			        }
			       
			        /**
			         * 遍历多个商家
			         */
					for(int b=0; b<businessList.size(); b++){
						String commodityOrderOneId = UUIDUtils.getId();
						Business businessOne = businessList.get(b);
						
						CommodityOrder commodityOrderOne = new CommodityOrder();
						
						if(businessOne == null){
							re_json.put("resultcode", 0);
							re_json.put("msg", "查询出错，此商家内容为空");
							re_json.put("result", 0);
							return re_json.toString();
						}
						if(businessOne.getCommodityPriceList() == null){
							re_json.put("resultcode", 0);
							re_json.put("msg", "查询出错，此商家商品内容为空");
							re_json.put("result", 0);
							return re_json.toString();
						}
						
						String remarkOne = "";
						
						try {
							JSONObject re_remarkArr = new JSONObject().fromObject(remarkArr);
							String remarkKey ="remark_"+businessOne.getId()+"_";
							if(re_remarkArr.containsKey(remarkKey)){
								remarkOne = re_remarkArr.getString(remarkKey);
							}
						} catch (Exception e) {
						}
						
//						orderNo
						double businessTotePayPrice = 0d;
						double activityPromotionTotePayPrice = 0d;
						// 商家下的商品，多个规格商品遍历
						List<CommodityPrice> commodityPriceList = businessOne.getCommodityPriceList();
						for(int cp =0;cp<commodityPriceList.size();cp++){
							// 单个规格
							CommodityPrice commodityPriceOne = commodityPriceList.get(cp);
								if(commodityPriceOne == null){
									re_json.put("resultcode", 0);
									re_json.put("msg", "查询出错，此商家商品价格内容为空");
									re_json.put("result", 0);
									return re_json.toString();
								}
							//  此规格商品参加的优惠内容
							ActivityPromotion activityPromotion = commodityPriceOne.getActivityPromotion();
							// 
							Commodity commodity = commodityPriceOne.getCommodity();
							Car car = commodityPriceOne.getCar();
							
							goodsName+=commodity.getTitle();
							CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
							commodityOrderInfo.setId(UUIDUtils.getId());
							commodityOrderInfo.setCommodityOrderId(commodityOrderOneId);
							commodityOrderInfo.setCommodityId(commodity.getId());
							commodityOrderInfo.setCommodityPriceId(commodityPriceOne.getId());
							commodityOrderInfo.setUserId(recommendId);
							commodityOrderInfo.setProfit((commodityPriceOne.getPrice()-commodityPriceOne.getCostPrice())*commodityPriceOne.getProfit());
							commodityOrderInfo.setTitle(commodity.getTitle());
							commodityOrderInfo.setOldPrice(commodityPriceOne.getPrice());
							commodityOrderInfo.setCommodityPriceName(commodityPriceOne.getName());
							commodityOrderInfo.setCommodityModel(commodity.getModel());
							commodityOrderInfo.setNumber(car.getNumber());
							commodityOrderInfo.setRemark(remarkOne);
							commodityOrderInfo.setStatus(1);
							commodityOrderInfo.setCreateTime(new Date());
							
							commodityOrderInfo.setCreatorId(userId);
							commodityOrderInfo.setCode(code);
							double coiPrice = 0d;
							if(activityPromotion != null){
								commodityOrderInfo.setActivityPromotionId(activityPromotion.getId());
								commodityOrderInfo.setActivityPromotionPrice(activityPromotion.getPrice());
								activityPromotionTotePayPrice += activityPromotion.getPrice()*commodityOrderInfo.getNumber();
								if(DoubleUtils.subToPositive(commodityPriceOne.getPrice(), activityPromotion.getPrice()) == 0){
									coiPrice = 0d;
								}else{
									coiPrice = DoubleUtils.sub(commodityPriceOne.getPrice(), activityPromotion.getPrice());
								}
							}else{
								coiPrice = commodityPriceOne.getPrice();
							}
							
							commodityOrderInfo.setPrice(coiPrice);
							businessTotePayPrice += commodityPriceOne.getPrice()*commodityOrderInfo.getNumber();
							// 每条商品添加一条orderinfo
							int coid = commodityOrderInfoDao.insert(commodityOrderInfo);
							if(coid<=0){
								status.setRollbackOnly();// 回滚
								re_json.put("resultcode", 0);
								re_json.put("msg", "创建orderInfo错误");
								re_json.put("result", 0);
								return re_json.toString();
							}
							car.setStatus(0);
							// 修改购物车状态
							int c =carDao.update(car);
							if(c <=0){
								status.setRollbackOnly();// 回滚
								re_json.put("resultcode", 0);
								re_json.put("msg", "修改购物车状态失败");
								re_json.put("result", 0);
								return re_json.toString();
							}
							// 修改商品销售量
							Commodity commodity_up = new Commodity();
							commodity_up.setId(commodity.getId());
							commodity_up.setCount(commodity.getCount()+car.getNumber());
							commodityDao.update(commodity_up);
						}
						if(voucherArr!=null){
							try {
								JSONObject voucher_json = new JSONObject().fromObject(voucherArr);
								String voucherKey ="voucher_"+businessOne.getId()+"_";
								if(voucher_json.containsKey(voucherKey)){
									String voucherId = voucher_json.getString(voucherKey);
									Map<String,Object> cuvifu_map = new HashMap<String,Object>();
									cuvifu_map.put("vuId", voucherId);
									cuvifu_map.put("id", userId);
									User user = userDao.commodityUserVoucherIfUsable(cuvifu_map);
									if(user == null ||user.getVoucher() == null  ||user.getVoucher().getVoucherRepertory() == null||user.getVoucher().getVoucherRepertory().getVoucherUser() == null){
										status.setRollbackOnly();// 回滚
										re_json.put("resultcode", 0);
										re_json.put("msg", "查询用户优惠券异常");
										re_json.put("result", 0);
										return re_json.toString();
									}
									Voucher voucher =user.getVoucher();
									VoucherRepertory repertory = user.getVoucher().getVoucherRepertory();
					                if (voucher.getType() == 1) {
					                		businessTotePayPrice = DoubleUtils.subToPositive(businessTotePayPrice, repertory.getAmount());
					                		commodityOrderOne.setVoucherUseId(voucherId);
					                		commodityOrderOne.setVoucherPrice(repertory.getAmount());
					                		int ucuv = userDao.updateCommodityUserVoucher(voucherId);
					                		if(ucuv <=0){
					                			status.setRollbackOnly();// 回滚
												re_json.put("resultcode", 0);
												re_json.put("msg", "修改优惠券状态失败");
												re_json.put("result", 0);
												return re_json.toString();
					                		}
					                } else if (voucher.getType() == 2) {
					                    if (businessTotePayPrice > repertory.getConditionAmount()) {
					                    	businessTotePayPrice =  DoubleUtils.subToPositive(businessTotePayPrice, repertory.getAmount());
					                    	commodityOrderOne.setVoucherUseId(voucherId);
					                		commodityOrderOne.setVoucherPrice(repertory.getAmount());
					                		int ucuv = userDao.updateCommodityUserVoucher(voucherId);
					                		if(ucuv <=0){
					                			status.setRollbackOnly();// 回滚
												re_json.put("resultcode", 0);
												re_json.put("msg", "修改优惠券状态失败");
												re_json.put("result", 0);
												return re_json.toString();
					                		}
					                    }
					                } else if (voucher.getType() == 3) {
					                    if (businessTotePayPrice > repertory.getDiscountAmount()) {
					                    	businessTotePayPrice = DoubleUtils.mul(businessTotePayPrice, repertory.getAmount());
					                    	commodityOrderOne.setVoucherUseId(voucherId);
					                		commodityOrderOne.setVoucherPrice(repertory.getAmount());
					                		int ucuv = userDao.updateCommodityUserVoucher(voucherId);
					                		if(ucuv <=0){
					                			status.setRollbackOnly();// 回滚
												re_json.put("resultcode", 0);
												re_json.put("msg", "修改优惠券状态失败");
												re_json.put("result", 0);
												return re_json.toString();
					                		}
					                    }
					                }
					                
					                
								}
							} catch (Exception e) {
								status.setRollbackOnly();// 回滚
								re_json.put("resultcode", 0);
								re_json.put("msg", "优惠券异常");
								re_json.put("result", 0);
								return re_json.toString();
							}
						}
						commodityOrderOne.setId(commodityOrderOneId);
						commodityOrderOne.setOrderNo(orderNo+"-"+(b+1));
						commodityOrderOne.setPayPrice(DoubleUtils.subToPositive(businessTotePayPrice, activityPromotionTotePayPrice));
						commodityOrderOne.setSchedule(0);
						commodityOrderOne.setStatus(1);
						commodityOrderOne.setDevice(device);
						commodityOrderOne.setPlatformId(platformId);
						commodityOrderOne.setCreateTime(new Date());
						commodityOrderOne.setCreatorId(userId);
						UserAddress user_address = userAddressDao.loadById(userAddressId);
						commodityOrderOne.setAddress(user_address.getProvince()+"-"+user_address.getCity());
						commodityOrderOne.setDetailAddress(user_address.getArea()+"-"+user_address.getDetailaddress());
						commodityOrderOne.setPhone(user_address.getPhone());
						commodityOrderOne.setReceiveName(user_address.getName());
						// 算入总价
						totePrice+=commodityOrderOne.getPayPrice();
						
						int cod = commodityOrderDao.insert(commodityOrderOne);
						if(cod <= 0){
							status.setRollbackOnly();// 回滚
							re_json.put("resultcode", 0);
							re_json.put("msg", "创建订单失败");
							re_json.put("result", 0);
							return re_json.toString();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 日志打印区
					status.setRollbackOnly();// 回滚
					re_json.put("resultcode", 0);
					re_json.put("msg", "异常");
					re_json.put("result", 0);
					return re_json.toString();
				}
				
				if(DoubleUtils.subToPositive(payPrice, totePrice) == 0){
					JSONObject json_o = new JSONObject();
					json_o.put("orderNo", orderNo);
					json_o.put("payPrice", totePrice);
					if(goodsName.length() > 21)
						json_o.put("goodsName", goodsName.substring(0, 19)+"...");
					else
						json_o.put("goodsName", goodsName);
					
					re_json.put("resultcode", 1);
					re_json.put("msg", "创建订单成功");
					re_json.put("result", json_o);
					return re_json.toString();
				}
				re_json.put("resultcode", 0);
				re_json.put("msg", "创建订单失败");
				re_json.put("result", 0);
				return re_json.toString();
			}
		});
		
		return JSONObject.fromObject(rs);
	}

	@Override
	public String createCommodityOrder(String userId, String commodityIds, String userAddressId, String cpIds,
			String guideId, Integer number, String remark, double payPrice, String code, Integer device,
			String platformId) {
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
			// 事务模板
			String orderNo = "";

			public String doInTransaction(final TransactionStatus status) {
				try {

					try {
						String[] commodityIdArr = commodityIds.split(",");
						String[] cpIdArr = cpIds.split(",");
						Set<String> set = new HashSet<String>();
						Map<String, List<String>> map = new HashMap<String, List<String>>();
						for (int i = 0; i < commodityIdArr.length; i++) {
							if (!("".equals(commodityIdArr[i]))) {

								Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
								set.add(commodity.getBusinessId());
							}

						}

						for (String businessId : set) {
							List<String> list = new ArrayList<String>();
							for (int i = 0; i < commodityIdArr.length; i++) {

								if (!("".equals(commodityIdArr[i]))) {

									Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
									if (commodity.getBusinessId().equals(businessId)) {
										System.out.println(commodityIdArr[i] + "," + cpIdArr[i]);
										list.add(commodityIdArr[i] + "," + cpIdArr[i]);

									}
								}
							}
							map.put(businessId, list);
						}

						Set<String> keys = map.keySet();
						Iterator<String> iterator = keys.iterator();
						// 按时间格式生成orderNo
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						orderNo = sdf.format(date);
						orderNo = "CM" + orderNo + (Math.random() * 9000 + 1000 + "").substring(0, 4);
						Integer i = 0;
						while (iterator.hasNext()) {

							String id = UUID.randomUUID().toString();

							String key = iterator.next();
							List<String> arrayList = map.get(key);
							UserAddress userAddress = userAddressDao.loadById(userAddressId);
							User user = userDao.loadById(userId);

							CommodityOrder comObj = null;

							for (String comAndCp : arrayList) {
								String[] comAndCpArr = comAndCp.split(",");

								Commodity commodity = commodityDao.loadById(comAndCpArr[0]);
								i++;

								/*
								 * Car car = null; if(number ==null){ Car
								 * carModel = new Car();
								 * carModel.setCommodityId(comAndCpArr[0]);
								 * carModel.setCommodityPriceId(comAndCpArr[1]);
								 * carModel.setStatus(1);
								 * carModel.setCreatorId(userId); car =
								 * carDao.lookup(carModel); }
								 */

								CommodityPrice commodityPrice = commodityPriceDao.loadById(comAndCpArr[1]);
								CommodityOrder commodityOrder = new CommodityOrder();
								commodityOrder.setId(id);
								commodityOrder.setOrderNo(orderNo + "-" + i);
								System.out.println(commodityPrice.getPrice());
								System.out.println(number);
								commodityOrder.setPayPrice(payPrice);
								commodityOrder.setProtectDay(commodity.getProtectDay());
								commodityOrder.setVoucherUseId("");
								System.out.println("===================================");
								System.out.println(device);
								commodityOrder.setDevice(device);
								commodityOrder.setPlatformId(platformId);
								// commodityOrder.setVoucherPrice(0);
								commodityOrder.setSchedule(0);
								commodityOrder.setStatus(1);
								commodityOrder.setAddress(userAddress.getProvince() + "，" + userAddress.getCity() + "，"
										+ userAddress.getArea() + "，");
								commodityOrder.setDetailAddress(userAddress.getDetailaddress());
								commodityOrder.setCreatorId(userId);
								if (user.getName() != null) {
									commodityOrder.setCreatorName(user.getName());
								}

								commodityOrder.setCreateTime(date);
								commodityOrder.setPhone(userAddress.getPhone());
								commodityOrder.setReceiveName(userAddress.getName());
								int insert = commodityOrderDao.insert(commodityOrder);
								Map<String, Object> modelMap = new HashMap<>();
								modelMap.put("orderNo", orderNo + "-" + i);
								comObj = commodityOrderDao.getObjectByOrder(modelMap);

								if (insert < 0) {
									return "创建失败";
								} else {
									break;
								}
							}

							for (String comAndCp : arrayList) {
								String[] comAndCpArr = comAndCp.split(",");
								Car car = null;
								Commodity commodity2 = commodityDao.loadById(comAndCpArr[0]);
								Integer count = commodity2.getCount();
								if (number == null) {
									Car carModel = new Car();
									carModel.setCommodityId(comAndCpArr[0]);
									carModel.setCommodityPriceId(comAndCpArr[1]);
									carModel.setStatus(1);
									carModel.setCreatorId(userId);
									car = carDao.lookup(carModel);
								}

								Commodity commodity = commodityDao.loadById(comAndCpArr[0]);
								CommodityPrice commodityPrice = commodityPriceDao.loadById(comAndCpArr[1]);
								// 创建详细订单
								CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();

								commodityOrderInfo.setId(UUID.randomUUID().toString());
								commodityOrderInfo.setCommodityOrderId(comObj.getId());
								commodityOrderInfo.setCommodityId(comAndCpArr[0]);
								commodityOrderInfo.setCommodityPriceId(comAndCpArr[1]);
								double profit = 0.0;

								if (commodityPrice.getProfit() >= 1) {
									profit = commodityPrice.getProfit();
								} else {
									profit = (commodityPrice.getPrice() - commodityPrice.getCostPrice())
											* commodityPrice.getProfit();
								}

								commodityOrderInfo.setProfit(profit);
								commodityOrderInfo.setTitle(commodity.getTitle());
								commodityOrderInfo.setOldPrice(commodityPrice.getOldPrice());
								commodityOrderInfo.setPrice(commodityPrice.getPrice());
								commodityOrderInfo.setCommodityPriceName(commodityPrice.getName());
								commodityOrderInfo.setCommodityModel(commodity.getModel());
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
								System.out.println(car != null);
								if (car != null) {
									Integer number2 = car.getNumber();
									if (number2 == null) {
										status.setRollbackOnly();// 回滚
										return "1";
									}
									System.out.println(number2);
									commodityOrderInfo.setNumber(number2);
									commodityOrderInfo.setUserId(car.getUserId());
									commodityOrderInfo.setCode(car.getCode());
									count += number2;
									commodity2.setCount(count);
								} else {
									commodityOrderInfo.setNumber(number);
									commodityOrderInfo.setUserId(guideId);
									commodityOrderInfo.setCode(code);
									count += number;
									commodity2.setCount(count);
								}
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
								commodityOrderInfo.setRemark(remark);
								commodityOrderInfo.setStatus(1);
								commodityOrderInfo.setCreatorId(userId);
								commodityOrderInfo.setCreatorName(user.getName());
								commodityOrderInfo.setCreateTime(date);
								int insert2 = commodityOrderInfoDao.insert(commodityOrderInfo);
								commodityDao.update(commodity2);
								if (insert2 < 0) {
									return "创建失败";
								}

							}

						}

					} catch (Exception e) {
						orderNo = "";
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
					// 日志打印区
					status.setRollbackOnly();// 回滚
					return "0";
				}

				return orderNo;
			}
		});

		return rs;
	}

	@Override
	public String cancelShopOrder(String id) {

		CommodityOrder comObj = commodityOrderDao.loadById(id);

		Date date = new Date();
		comObj.setStatus(0);
		comObj.setSchedule(-1);
		comObj.setConfirmTime(date);
		int result = commodityOrderDao.update(comObj);

		CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
		commodityOrderInfo.setStatus(-1);
		commodityOrderInfo.setCommodityOrderId(id);
		result = commodityOrderInfoDao.updateByOrderNo(commodityOrderInfo);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}

	}

	@Override
	public List<CommodityOrder> getOrderList(String userId, String schedule) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("schedule", schedule);
		List<CommodityOrder> orderList = commodityOrderDao.getOrderList1(map);
		System.out.println(orderList.size());
		for (int i = 0; i < orderList.size(); i++) {
			System.out.println(orderList.get(i).getId());
			String id = orderList.get(i).getId();

			int allNumber = commodityOrderInfoDao.getAllNumber(id);
			orderList.get(i).setCount(allNumber);

			/*
			 * List<CommodityOrderInfo> coiList = orderList.get(i).getCoiList();
			 * Double payment = 0.0; for (int j = 0; j < coiList.size(); j++) {
			 * Integer number = coiList.get(j).getNumber(); Double price =
			 * coiList.get(j).getPrice(); payment += (price * number); }
			 * orderList.get(i).setPayment(payment);
			 */
			List<CommodityOrderInfo> setCoiList = commodityOrderInfoDao.getOrderInfo(id);

			orderList.get(i).setCoiList(setCoiList);
		}

		return orderList;
	}

	@Override
	public CommodityOrder getOrderDetail(String orderId) {

		CommodityOrder commodityOrder = commodityOrderDao.getOrderDetail(orderId);

		List<CommodityOrderInfo> coiList = commodityOrder.getCoiList();
		for (int i = 0; i < coiList.size(); i++) {
			if (coiList.get(i).getCrStatus() != null) {
				if (coiList.get(i).getCrStatus() == -1) {
					coiList.remove(i);
				}
			}

		}

		return commodityOrder;
	}

	@Override
	public Integer updateShopOrderSchedule(CommodityOrder commodityOrder) {

		Integer result = commodityDao.updateShopOrderSchedule(commodityOrder);

		// 修改状态成功且为退货，取消订单
		if (result > 0) {
			if (commodityOrder.getSchedule() < 0) {
				CommodityOrderInfo coi = new CommodityOrderInfo();
				coi.setCommodityOrderId(commodityOrder.getId());
				coi.setStatus(-1);
				result = commodityOrderInfoDao.updateByOrderNo(coi);
			}
		}

		return result;
	}

	@Override
	public Integer updateRemindTime(CommodityOrder commodityOrder) {
		commodityOrder.setRemindTime(new Date());
		Integer result = commodityOrderDao.updateRemindTime(commodityOrder);
		return result;
	}

	@Override
	public Integer confimOrder(CommodityOrder commodityOrder) {

		Integer result = 0;
		Date date = new Date();
		commodityOrder.setTakeTime(date);
		result = commodityOrderDao.confimOrder(commodityOrder);

		return result;
	}

	@Override
	public Integer deleteOrder(CommodityOrder commodityOrder) {

		Integer result = 0;
		Date date = new Date();

		commodityOrder.setConfirmTime(date);
		result = commodityOrderDao.deleteOrder(commodityOrder);

		return result;
	}

	@Override
	public List<CommodityOrder> getListByOrderNo(String OrderNo) {

		return commodityOrderDao.getListByOrderNo(OrderNo);
	}

	@Override
	public Integer toUpdatePayPrice(String id, double payPrice) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("payPrice", payPrice);
		return commodityOrderDao.toUpdatePayPrice(map);
	}

	/**
	 * 商品余额支付
	 * 
	 * @return
	 */
	public byte[] balancePayment(String orderId, String orderNo, Double payParice, String userId) {
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
			// 事务模板
			public String doInTransaction(final TransactionStatus status) {
				try {

					Account model = new Account();
					model.setCreatorId(userId);
					Account account = accountDao.load(model);
					if (null == account || "".equals(account)) {
						return "10";
					}
					if (null == account.getBalance() || "".equals(account.getBalance())) {
						return "11";
					}
					/**
					 * 用于余额不足
					 */
					if (DoubleUtils.sub(account.getBalance(), payParice) < 0) {
						return "12";
					}
					/**
					 * 用户余额减去-订单金额 更新用户余额
					 */
					Double balance = DoubleUtils.sub(account.getBalance(), payParice);
					account.setBalance(balance);
					int ad = accountDao.update(account);
					if (ad < 1) {
						status.setRollbackOnly();// 回滚
						return "13";
					}

					CommodityOrder commodityOrder = new CommodityOrder();
					commodityOrder.setOrderNo(orderNo);
					commodityOrder.setStatus(1);
					commodityOrder.setSchedule(0);
					List<HashMap<String, Object>> commodityOrders_list = commodityOrderDao.loadS(commodityOrder);

					if (commodityOrders_list == null || "".equals(commodityOrders_list)
							|| commodityOrders_list.size() < 1) {
						status.setRollbackOnly();// 回滚
						return "2";
					}

					for (int a = 0; a < commodityOrders_list.size(); a++) {
						HashMap<String, Object> map_c = commodityOrders_list.get(a);
						CommodityOrder commodityOrder_up = new CommodityOrder();
						commodityOrder_up.setPayTime(new Date());
						commodityOrder_up.setSchedule(1);
						commodityOrder_up.setId(map_c.get("id").toString());
						int cod = commodityOrderDao.update(commodityOrder_up);
						if (cod < 1) {
							status.setRollbackOnly();// 回滚
							return "5";
						}
						CommodityOrderInfo coi_up = new CommodityOrderInfo();
						coi_up.setCommodityOrderId(map_c.get("id").toString());
						List<CommodityOrderInfo> coi_list = commodityOrderInfoDao.list(coi_up);
						String remark = "";
						for (int y = 0; y < coi_list.size(); y++) {
							CommodityOrderInfo coi = coi_list.get(y);
							coi_up.setId(coi.getId());
							coi_up.setStatus(2);
							int coid = commodityOrderInfoDao.update(coi_up);
							if (coi.getTitle() != null && !coi.getTitle().equals("")) {
								remark += coi.getTitle();
							}
							if (coid < 1) {
								status.setRollbackOnly();// 回滚
								return "3";
							}
						}

						Transaction transaction = new Transaction();
						transaction.setId(UUID.randomUUID().toString());
						transaction.setOrderId(map_c.get("id").toString());
						transaction.setAmount(Double.parseDouble(map_c.get("pay_price").toString()));
						transaction.setScore((new Double(transaction.getAmount())).intValue());
						transaction.setOperate(3);
						transaction.setOperateSource(2);
						transaction.setRemark(remark);
						transaction.setWithdraw(0);
						transaction.setPayType(3);
						transaction.setOutTradeNo(orderNo);
						transaction.setCreatorId(map_c.get("creator_id").toString());
						if(map_c.containsKey("creator_name") && !StringUtils.isEmpty(map_c.get("creator_name").toString()) ){
							transaction.setCreatorName(map_c.get("creator_name").toString());
						}
						transaction.setCreateTime(new Date());
						transaction.setStatus(1);
						transaction.setType(2);
						int tto = transactionDao.insert(transaction);
						if (tto < 1) {
							status.setRollbackOnly();// 回滚
							return "4";
						}
					}
					try {
						if (userId != null && !userId.equals("")) {
							User user = new User();
							user.setId(userId);
							User orderUser = userDao.load(user);
							if (orderUser != null) {
								// 记录日志-debug
								if (Util.debugLog.isDebugEnabled()) {
									Util.debugLog.debug("updateWechatCommodityOrderStutas;订单用户信息orderUser="
											+ JSONObject.fromObject(orderUser).toString());
								}
								// 发送验证码
								doPostSmsService.sendSms(orderUser.getPhone(), SMS_pay_success,
										"{\"out_trade_no\":\"" + orderNo + "\"}");
							}
						}
					} catch (Exception e) {
					}

				} catch (Exception e) {
					e.printStackTrace();
					// 日志打印区
					status.setRollbackOnly();// 回滚
					return "0";
				}
				return "1";
			}
		});

		String msg = "支付成功";
		int rsi = Integer.parseInt(rs);
		if (rsi == 0) {
			msg = "支付失败，请刷新重试";
		} else if (rsi == 2) {
			msg = "支付失败，订单异常";
		} else if (rsi == 3) {
			msg = "支付失败，余额不足";
		} else if (rsi == 4) {
			msg = "支付失败，订单异常info";
		} else if (rsi == 5) {
			msg = "支付失败，订单异常order";
		} else if (rsi == 10) {
			msg = "支付失败，用户信息异常";
		} else if (rsi == 11) {
			msg = "支付失败，用户余额不足";
		} else if (rsi == 12) {
			msg = "支付失败，用户余额不足";
		} else if (rsi == 13) {
			msg = "支付失败，更新用户余额异常";
		}

		try {
			return JSONUtil.toJSONResult(rsi, msg, null);
		} catch (IOException e) {
		}
		return null;
	}

	// 商品支付回调
	public boolean updateWechatCommodityOrderStutas(SortedMap<Object, Object> packageParams) {
		try {
			// 这里是支付成功
			////////// 执行自己的业务逻辑////////////////
			String mch_id = (String) packageParams.get("mch_id");
			String openid = (String) packageParams.get("openid");
			String attach = (String) packageParams.get("attach");
			String is_subscribe = (String) packageParams.get("is_subscribe");
			String out_trade_no = (String) packageParams.get("out_trade_no");
			String total_fee = (String) packageParams.get("total_fee");
			String transaction_id = (String) packageParams.get("transaction_id");
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("wechat.updateWechatCommodityOrderStutas:mch_id=" + mch_id + ",openid=" + openid
						+ ",is_subscribe=" + is_subscribe + ",out_trade_no=" + out_trade_no + ",total_fee=" + total_fee
						+ "-------attach=" + attach);
			}

			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setOrderNo(out_trade_no);
			commodityOrder.setStatus(1);
			commodityOrder.setSchedule(0);
			List<HashMap<String, Object>> commodityOrders_list = commodityOrderDao.loadS(commodityOrder);

			if (commodityOrders_list != null && !"".equals(commodityOrders_list) && commodityOrders_list.size() > 0) {
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("updateWechatCommodityOrderStutas;根据微信返回out_trade_no=" + out_trade_no
							+ "平台内部订单号查询订单结果：orders=" + JSONArray.fromObject(commodityOrders_list).toString());
				}
				String userId = "";
				for (int a = 0; a < commodityOrders_list.size(); a++) {
					HashMap<String, Object> map_c = commodityOrders_list.get(a);
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("updateWechatCommodityOrderStutas;" + a + "-map_c="
								+ JSONObject.fromObject(map_c).toString());
					}
					if (a == 0) {
						userId = map_c.get("creator_id").toString();
					}
					CommodityOrder commodityOrder_up = new CommodityOrder();
					commodityOrder_up.setPayTime(new Date());
					commodityOrder_up.setSchedule(1);
					commodityOrder_up.setId(map_c.get("id").toString());
					commodityOrderDao.update(commodityOrder_up);
					CommodityOrderInfo coi_up = new CommodityOrderInfo();
					coi_up.setCommodityOrderId(map_c.get("id").toString());
					List<CommodityOrderInfo> coi_list = commodityOrderInfoDao.list(coi_up);
					String remark = "";
					for (int y = 0; y < coi_list.size(); y++) {
						CommodityOrderInfo coi = coi_list.get(y);
						coi_up.setId(coi.getId());
						coi_up.setStatus(2);
						commodityOrderInfoDao.update(coi_up);
						if (coi.getTitle() != null && !coi.getTitle().equals("")) {
							remark += coi.getTitle();
						}
					}

					Transaction transaction = new Transaction();
					transaction.setId(UUID.randomUUID().toString());
					transaction.setOrderId(map_c.get("id").toString());
					transaction.setAmount(Double.parseDouble(map_c.get("pay_price").toString()));
					transaction.setScore((new Double(transaction.getAmount())).intValue());
					transaction.setOperate(3);
					transaction.setOperateSource(2);
					transaction.setRemark(remark);
					transaction.setWithdraw(0);
					transaction.setPayType(2);
					transaction.setOutTradeNo(transaction_id);
					transaction.setCreatorId(map_c.get("creator_id").toString());
					transaction.setCreatorName(map_c.get("creator_name").toString());
					transaction.setCreateTime(new Date());
					transaction.setStatus(1);
					transaction.setType(2);
					// 记录日志-debug
					if (Util.debugLog.isDebugEnabled()) {
						Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
								+ JSONObject.fromObject(transaction).toString());
					}
					transactionDao.insert(transaction);
				}
				try {
					if (userId != null && !userId.equals("")) {
						User user = new User();
						user.setId(userId);
						User orderUser = userDao.load(user);
						if (orderUser != null) {
							// 记录日志-debug
							if (Util.debugLog.isDebugEnabled()) {
								Util.debugLog.debug("updateWechatCommodityOrderStutas;订单用户信息orderUser="
										+ JSONObject.fromObject(orderUser).toString());
							}
							// 发送验证码
							/*
							 * doPostSmsService.sendSms(orderUser.getPhone(),
							 * SMS_pay_success, "{\"out_trade_no\":\"" +
							 * out_trade_no + "\"}");
							 */
						}
					}
				} catch (Exception e) {
				}
				return true;
			} else {
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("alipay.notify.json;下单失败！查询不到该订单数据-查看status状态以及schedule字段是否正确");
				}
				return false;
			}
		} catch (Exception e) {
			Util.failLog.error("updateWechatCommodityOrderStutas：1e=", e);
		}
		return false;
	}

	@Override
	public List<CommodityOrder> getStatusByOrderNo(String orderNo) {
		return commodityOrderDao.getStatusByOrderNo(orderNo);
	}

	@Override
	public Page<CommodityOrder> getList(CommodityOrder commodityOrder) {

		return commodityOrderDao.getList(commodityOrder);
	}

	@Override
	public Page<CommodityOrder> getTkList(CommodityOrder commodityOrder) {

		return commodityOrderDao.getTkList(commodityOrder);
	}

	@Override
	public boolean commodityPayNurse(CommodityOrder commodityOrder) {
		boolean flag = false;

		// 查询已收货的

		commodityOrder.setSchedule(4);
		List<CommodityOrder> list = commodityOrderDao.list(commodityOrder);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTakeTime() != null) {
				Double balance = 0.0;
				long day = (new Date().getTime() - list.get(i).getTakeTime().getTime()) / (24 * 60 * 60 * 1000);

				if (day > 0) {
					List<CommodityOrderInfo> coiList = commodityOrderInfoDao.getList(list.get(i).getId());
					for (int j = 0; j < coiList.size(); j++) {
						if (coiList.get(j).getStatus() == 2) {

							if (!("0".equals(coiList.get(j).getUserId()))) {

								Double profit = coiList.get(j).getProfit();
								Integer number = coiList.get(j).getNumber();
								balance = number * profit;
								Transaction transaction = new Transaction();

								transaction.setOrderId(coiList.get(j).getId());
								transaction.setOperateSource(1);
								transaction.setCreatorId(coiList.get(j).getUserId());
								transaction.setScore(0);
								transaction.setStatus(1);
								transaction.setType(2);
								transaction.setOperate(4);
								Transaction tModel = transactionDao.load(transaction);
								int result = 0;
								if (tModel == null) {
									transaction.setId(UUID.randomUUID().toString());
									transaction.setCreateTime(new Date());
									transaction.setAmount(balance);

									transaction.setRemark("推广" + number + "件" + coiList.get(j).getTitle());
									transaction.setWithdraw(0);
									result = transactionDao.insert(transaction);
								}

								if (result != 0) {
									Account account = new Account();

									account.setCreatorId(coiList.get(j).getUserId());
									Account a = accountDao.load(account);
									Double balance2 = a.getBalance();
									// System.out.println(balance2+"======================");
									// System.out.println(balance+"---------------------------");
									balance2 += balance;
									a.setBalance(balance2);
									int update = accountDao.update(a);
									// int update =0;

									if (update > 0) {
										NurseCommodity nurseCommodity = new NurseCommodity();
										nurseCommodity.setCommodityId(coiList.get(j).getCommodityId());
										nurseCommodity.setCreatorId(coiList.get(j).getUserId());
										NurseCommodity nurseCommodityModel = nurseCommodityDao.load(nurseCommodity);

										Integer count = nurseCommodityModel.getCount();
										// System.out.println(count+"-------------count1--------------");
										count += number;
										// System.out.println(count+"----------count2-----------------");
										nurseCommodityModel.setCount(count);
										nurseCommodityDao.update(nurseCommodityModel);

									}

								}
							}
						}
					}
				}

			}
		}
		return flag;
	}

	/**
	 * 定时执行-更新已发货7天以上的商品默认改为已完成
	 * @return
	 */
	public boolean proxyRecipientss() {
		boolean flag = false;
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setSchedule(2);
			List<CommodityOrder> list = commodityOrderDao.list(commodityOrder);
			for (int i = 0; i < list.size(); i++) {
				CommodityOrder commodityOrders = list.get(i);
				
				long day = (new Date().getTime() - commodityOrders.getSendTime().getTime()) / (24 * 60 * 60 * 1000);
				if (day > 7) {
					CommodityOrder commodityOrder2 = list.get(i);
					commodityOrder2.setSchedule(4);
					if (commodityOrder2.getTakeTime() == null) {
						commodityOrder2.setTakeTime(new Date());
						commodityOrderDao.update(commodityOrder2);
					}
				}
				/*
				CommodityLogistics clModel = new CommodityLogistics();
				clModel.setCommodityOrderId(list.get(i).getId());
				CommodityLogistics cl = commodityLogisticsDao.load(clModel);
				if (cl != null) {
					KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
					Logistics loadById = logisticsDao.loadById(cl.getLogisticsId());
					String result = api.getOrderTracesByJson(loadById.getCode(), cl.getNo());
					String state = JSONObject.fromObject(result).getString("State");
					if ("3".equals(state)) {
						JSONArray arry = JSONObject.fromObject(result).getJSONArray("Traces");
						ArrayList<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
						for (int j = 0; j < arry.size(); j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							String AcceptStation = arry.getJSONObject(j).getString("AcceptStation");
							String AcceptTime = arry.getJSONObject(j).getString("AcceptTime");
							map.put("AcceptTime", AcceptTime);
							map.put("AcceptStation", AcceptStation);
							arrList.add(map);
						}
						String dateString = arrList.get(arrList.size() - 1).get("AcceptTime").toString();
						SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
						Date beginDate = format.parse(dateString);
						long day = (new Date().getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

						if (day > 7) {
							CommodityOrder commodityOrder2 = list.get(i);
							commodityOrder2.setSchedule(4);
							if (commodityOrder2.getTakeTime() == null) {
								commodityOrder2.setTakeTime(new Date());
								commodityOrderDao.update(commodityOrder2);
							}

						}
					}
				}
			*/}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean proxyRecipient() {
		boolean flag = false;
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setSchedule(2);
			List<CommodityOrder> list = commodityOrderDao.list(commodityOrder);
			for (int i = 0; i < list.size(); i++) {
				CommodityLogistics clModel = new CommodityLogistics();
				clModel.setCommodityOrderId(list.get(i).getId());
				CommodityLogistics cl = commodityLogisticsDao.load(clModel);
				if (cl != null) {
					KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
					Logistics loadById = logisticsDao.loadById(cl.getLogisticsId());
					String result = api.getOrderTracesByJson(loadById.getCode(), cl.getNo());
					String state = JSONObject.fromObject(result).getString("State");
					if ("3".equals(state)) {
						JSONArray arry = JSONObject.fromObject(result).getJSONArray("Traces");
						ArrayList<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
						for (int j = 0; j < arry.size(); j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							String AcceptStation = arry.getJSONObject(j).getString("AcceptStation");
							String AcceptTime = arry.getJSONObject(j).getString("AcceptTime");
							map.put("AcceptTime", AcceptTime);
							map.put("AcceptStation", AcceptStation);
							arrList.add(map);
						}
						String dateString = arrList.get(arrList.size() - 1).get("AcceptTime").toString();
						SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
						Date beginDate = format.parse(dateString);
						long day = (new Date().getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

						if (day > 7) {
							CommodityOrder commodityOrder2 = list.get(i);
							commodityOrder2.setSchedule(4);
							if (commodityOrder2.getTakeTime() == null) {
								commodityOrder2.setTakeTime(new Date());
								commodityOrderDao.update(commodityOrder2);
							}

						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> getSendTransaction() {
		return commodityOrderDao.getSendTransaction();
	}
}