package com.jinpaihushi.jphs.voucher.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.business.dao.BusinessDao;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.car.dao.CarDao;
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.price.dao.PriceNurseDao;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.product.dao.ProductDao;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.voucher.dao.VoucherDao;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.ArraysUtils;
import com.jinpaihushi.utils.CollectionShort;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.RandomUtils;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Service("voucherService")
public class VoucherServiceImpl extends BaseServiceImpl<Voucher> implements VoucherService {
    @Autowired
    private GoodsDao goodsDao;

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private PricePartDao pricePartDao;

    @Autowired
    private VoucherUseDao voucherUseDao;

    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;

//    private JkwyPackagePriceDao jkwyPackagePriceDao;
    @Autowired
    private PriceNurseDao priceNurseDao;

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private CommodityPriceDao commodityPriceDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private CarDao carDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BusinessDao businessDao;

    @Override
    protected BaseDao<Voucher> getDao() {
        return voucherDao;
    }

    @Override
    public Page<Voucher> getList(Voucher voucher) {
        // TODO Auto-generated method stub
        return voucherDao.getList(voucher);
    }

    /**
     * 获取下单页用户可用的优惠券
     * 
     * @param pricePartId
     *            站点商品的价格id
     * @param goodsId
     *            商品id
     * @param userId
     *            用户id
     * @return
     */
    @SuppressWarnings("unused")
    @Override
    public List<Map<String, Object>> getUservoucher(String pricePartId, String goodsId, String userId, String nurseId,
            int type) {
        if (type == 1) {
            // 服务的销售价
            Double salePrice = null;
            if (!StringUtils.isEmpty(nurseId)) {
                PriceNurse priceNurse = new PriceNurse();
                priceNurse.setPricePartId(pricePartId);
                priceNurse.setCreatorId(nurseId);
                priceNurse = priceNurseDao.load(priceNurse);
                if (priceNurse == null) {
                    return null;
                }
                else {
                    salePrice = priceNurse.getPrice();
                }
            }
            else {
                // 获取商品的价格信息
                PricePart pricePart = pricePartDao.loadById(pricePartId);
                salePrice = pricePart.getPrice();
            }
            // 根据商品id获取商品的信息
            Goods goods = goodsDao.loadById(goodsId);
            // 根据pricePartId 下单商品的价格
            PricePart pricePart = pricePartDao.loadById(pricePartId);
            // 获取用户的优惠券
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("productType", type);
            map.put("goodsId", goodsId);
            map.put("productId", goods.getProductId());
            if (goods != null) {
                List<Map<String, Object>> vocherList = voucherRepertoryDao.getUserVocher(map);
                for (int i = 0; i < vocherList.size(); i++) {
                    BigDecimal bg = null;
                    // 优惠券的现金券是所有商品可用
                    // 如果是满减券
                    if (((Integer) vocherList.get(i).get("type")) == 2) {
                        bg = (BigDecimal) vocherList.get(i).get("condition_amount");
                        // 判断需要满足的金额是否达到要求
                        if (bg.doubleValue() > salePrice) {
                            // 不满足要求移除改优惠券
                            vocherList.remove(i);
                            i = -1;
                            break;
                        }
                    }
                    if (((Integer) vocherList.get(i).get("type")) == 3) {
                        bg = (BigDecimal) vocherList.get(i).get("discount_amount");
                        if (bg.doubleValue() > salePrice) {
                            // 不满足要求移除改优惠券
                            vocherList.remove(i);
                            i = -1;
                            break;
                        }
                    }
                }
                return vocherList;
            }
            return null;
        }
        else if (type == 2 || type == 3) {
            Commodity commodity = commodityDao.loadById(goodsId);
            if (commodity == null) {
                return null;
            }
            CommodityPrice commodityPrice = commodityPriceDao.loadById(pricePartId);
            if (commodityPrice == null) {
                return null;
            }

            Double salePrice = commodityPrice.getPrice();
            // 获取用户的优惠券
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("goodsId", goodsId);
            map.put("productType", 2);
            if (type == 3) {
                map.put("supportType", 3);
            }
            map.put("businessId", commodity.getBusinessId());
            List<Map<String, Object>> vocherList = voucherRepertoryDao.getUserVocher(map);
            for (int i = 0; i < vocherList.size(); i++) {
                BigDecimal bg = null;
                // 优惠券的现金券是所有商品可用
                // 如果是满减券
                if (((Integer) vocherList.get(i).get("type")) == 2) {
                    bg = (BigDecimal) vocherList.get(i).get("condition_amount");
                    // 判断需要满足的金额是否达到要求
                    if (bg.doubleValue() > salePrice) {
                        // 不满足要求移除改优惠券
                        vocherList.remove(i);
                        i = -1;
                        break;
                    }
                }
                if (((Integer) vocherList.get(i).get("type")) == 3) {
                    bg = (BigDecimal) vocherList.get(i).get("discount_amount");
                    if (bg.doubleValue() > salePrice) {
                        // 不满足要求移除改优惠券
                        vocherList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            return vocherList;
        }
        else if (type == 4) {
            String[] carArr = goodsId.split(",");
            List<Map<String, Object>> vocherLists = new ArrayList<Map<String, Object>>();
            for (int a = 0; a < carArr.length; a++) {
                if (StringUtils.isEmpty(carArr[a])) {
                    continue;
                }
                Car car = carDao.loadById(carArr[a]);
                Commodity commodity = commodityDao.loadById(car.getCommodityId());
                if (commodity == null) {
                    return null;
                }
                CommodityPrice commodityPrice = commodityPriceDao.loadById(car.getCommodityPriceId());
                if (commodityPrice == null) {
                    return null;
                }

                Double salePrice = commodityPrice.getPrice();
                // 获取用户的优惠券
                Map<String, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("goodsId", commodity.getId());
                map.put("productType", 2);
                if (type == 3) {
                    map.put("supportType", 3);
                }
                map.put("businessId", commodity.getBusinessId());
                List<Map<String, Object>> vocherList = voucherRepertoryDao.getUserVocher(map);
                for (int i = 0; i < vocherList.size(); i++) {
                    BigDecimal bg = null;
                    // 优惠券的现金券是所有商品可用
                    // 如果是满减券
                    if (((Integer) vocherList.get(i).get("type")) == 2) {
                        bg = (BigDecimal) vocherList.get(i).get("condition_amount");
                        // 判断需要满足的金额是否达到要求
                        if (bg.doubleValue() > salePrice) {
                            // 不满足要求移除改优惠券
                            vocherList.remove(i);
                            i = -1;
                            continue;
                        } /*else{
                          if(vocherLists.size()>0){
                          	for(int a1=0;a1<vocherLists.size();a1++){
                             		if(!vocherLists.get(a1).get("id").toString().equals(vocherList.get(i).get("id"))){
                             			vocherLists.add(vocherList.get(i));
                             		}
                             	}
                          }else{
                          	vocherLists.add(vocherList.get(i));
                          }
                          }*/
                    }
                    if (((Integer) vocherList.get(i).get("type")) == 3) {
                        bg = (BigDecimal) vocherList.get(i).get("discount_amount");
                        if (bg.doubleValue() > salePrice) {
                            // 不满足要求移除改优惠券
                            vocherList.remove(i);
                            i = -1;
                            continue;
                        } /*else{
                          if(vocherLists.size()>0){
                          	for(int a1=0;a1<vocherLists.size();a1++){
                             		if(!vocherLists.get(a1).get("id").toString().equals(vocherList.get(i).get("id"))){
                             			vocherLists.add(vocherList.get(i));
                             		}
                             	}
                          }else{
                          	vocherLists.add(vocherList.get(i));
                          }
                          }*/
                    }
                }
                vocherLists.addAll(vocherList);
            }
            Map<String, Map<String, Object>> msp = new HashMap<String, Map<String, Object>>();
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
            for (int i = vocherLists.size() - 1; i >= 0; i--) {
                Map<String, Object> map = vocherLists.get(i);
                String id = (String) map.get("id");
                map.remove("id");
                msp.put(id, map);
            }
            //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
            Set<String> mspKey = msp.keySet();
            for (String key : mspKey) {
                Map<String, Object> newMap = msp.get(key);
                newMap.put("id", key);
                listMap.add(newMap);
            }

            return listMap;
        } else if (type == 5){
        	
        	Map<String,Object> map_se = new HashMap<String,Object>();
        	map_se.put("id", pricePartId);
        	Map<String,Object> map_price = voucherRepertoryDao.getJkwyPackagePriceVoucher(map_se);
//        	pricePartId
//        	goodsId
        	if(!map_price.containsKey("price")){
        		return null;
        	}
        	Double salePrice = Double.parseDouble(map_price.get("price").toString());
        	
        	// 获取用户的优惠券
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("goodsId", goodsId);
            map.put("productType", 3);
            List<Map<String, Object>> vocherList = voucherRepertoryDao.getUserVocher(map);
            for (int i = 0; i < vocherList.size(); i++) {
                BigDecimal bg = null;
                // 优惠券的现金券是所有商品可用
                // 如果是满减券
                if (((Integer) vocherList.get(i).get("type")) == 2) {
                    bg = (BigDecimal) vocherList.get(i).get("condition_amount");
                    // 判断需要满足的金额是否达到要求
                    if (bg.doubleValue() > salePrice) {
                        // 不满足要求移除改优惠券
                        vocherList.remove(i);
                        i = -1;
                        break;
                    }
                }
                if (((Integer) vocherList.get(i).get("type")) == 3) {
                    bg = (BigDecimal) vocherList.get(i).get("discount_amount");
                    if (bg.doubleValue() > salePrice) {
                        // 不满足要求移除改优惠券
                        vocherList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            return vocherList;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getUserAllvoucher(String userId, Integer type, Integer status) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("type", type);
        map.put("status", status);
        long startTime = System.currentTimeMillis();
        logger.info(startTime);
        List<Map<String, Object>> list = voucherDao.getUserAllVocher(map);
        long endTime = System.currentTimeMillis();
        logger.info("sql执行的时间" + (endTime - startTime));
        for (Map<String, Object> map2 : list) {
            String productName = "";
            if (map2.get("product_type").equals("1")) {
                String productIds = (String) map2.get("product_id");
                if (productIds != null) {
                    String[] productId = productIds.split(",");
                    for (int i = 0; i < productId.length; i++) {
                        Product product = productDao.loadById(productId[i]);
                        if (product != null) {
                            productName += "、" + product.getTitle();
                        }
                    }
                }
                String goodsIds = (String) map2.get("goods_id");
                if (goodsIds != null) {
                    String[] goodsId = goodsIds.split(",");
                    for (int j = 0; j < goodsId.length; j++) {
                        Goods goods = goodsDao.loadById(goodsId[j]);
                        if (goods != null) {
                            productName += "、" + goods.getTitle();
                        }
                    }
                }
            }
            if (map2.get("product_type").equals("2")) {
                if (map2.get("support_type").equals("1")) {
                    String productIds = (String) map2.get("product_id");
                    if (productIds != null) {
                        String[] productId = productIds.split(",");
                        for (int i = 0; i < productId.length; i++) {
                            Commodity commodity = commodityDao.loadById(productId[i]);
                            if (commodity != null) {
                                productName += "、" + commodity.getTitle();
                            }
                        }
                    }
                }
                if (map2.get("support_type").equals("2")) {
                    String productIds = (String) map2.get("product_id");
                    if (productIds != null) {
                        String[] productId = productIds.split(",");
                        for (int i = 0; i < productId.length; i++) {
                            Business business = businessDao.loadById(productId[i]);
                            if (business != null) {
                                productName += "、" + business.getName();
                            }
                        }
                    }
                }
                if (map2.get("support_type").equals("3")) {
                    productName += "、" + "通用券";
                }
            }
            map2.put("productName", productName.substring(1));
        }
        long endTime2 = System.currentTimeMillis();
        logger.info("for循环" + (endTime2 - endTime));
        list = CollectionShort.testMapOrder(list, "status");
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserVoucherNum(String userId) {
        String[] s = { "已使用", "未使用", "已过期" };
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<Map<String, Object>> userAllVocher = voucherDao.getUserVoucherNum(map);
        String[] a = new String[userAllVocher.size()];
        for (int i = 0; i < userAllVocher.size(); i++) {
            a[i] = userAllVocher.get(i).get("status").toString();
        }
        s = ArraysUtils.arrContrast(s, a);
        Map<String, Object> add = null;
        for (int i = 0; i < s.length; i++) {
            add = new HashMap<>();
            add.put("status", s[i]);
            add.put("num", 0);
            userAllVocher.add(add);
        }
        return userAllVocher;
    }

	@Override
    public Double getGoodsPrice(String voucherUseId, String pricePartId, String nurseId, Integer type) {

        Double price = null;
        if (type == 1) {
            // 获取服务的价格信息
            PricePart pricePart = pricePartDao.loadById(pricePartId);
            price = pricePart.getPrice();
            if (!StringUtils.isEmpty(nurseId)) {
                PriceNurse priceNurse = new PriceNurse();
                priceNurse.setPricePartId(pricePartId);
                priceNurse.setCreatorId(nurseId);
                priceNurse.setStatus(0);
                priceNurse = priceNurseDao.load(priceNurse);
                if (priceNurse == null) {
                    return null;
                }
                else {
                    price = priceNurse.getPrice();
                }
            }
        } else if(type == 4){
        	Double prices = 0d;
        	String [] pricePartId_arr = pricePartId.split(",");
        	for(int p=0;p<pricePartId_arr.length;p++){
        		if(StringUtils.isEmpty(pricePartId_arr[p])){
        			continue;
        		}
        		Car car = carDao.loadById(pricePartId_arr[p]);
        		int carNumber = car.getNumber();
        		CommodityPrice commodityPrice = commodityPriceDao.loadById(car.getCommodityPriceId());
        		prices += commodityPrice.getPrice()*carNumber;
        	}
        	price = prices;
        }else if(type == 5){
        	Map<String,Object> map_se = new HashMap<String,Object>();
        	map_se.put("id", pricePartId);
        	Map<String,Object> map_price = voucherRepertoryDao.getJkwyPackagePriceVoucher(map_se);
        	if(map_price == null){
        		return null;
        	}
//        	pricePartId
//        	goodsId
        	if(!map_price.containsKey("price")){
        		return null;
        	}
        	price = Double.parseDouble(map_price.get("price").toString());
        }

        // 获取优惠券的信息
        if (StringUtils.isNotEmpty(voucherUseId)) {
            Voucher voucher = voucherDao.getVocherByUser(voucherUseId);
            // 判断优惠券类型
            if (voucher != null) {
                VoucherRepertory repertory = voucher.getVoucherRepertory();
                if (voucher.getType() == 1) {
                    return DoubleUtils.subToPositive(price, repertory.getAmount());
                }
                else if (voucher.getType() == 2) {
                    if (price > repertory.getConditionAmount()) {
                        return DoubleUtils.subToPositive(price, repertory.getAmount());
                    }
                }
                else if (voucher.getType() == 3) {
                    if (price > repertory.getDiscountAmount()) {
                        return DoubleUtils.mul(price, repertory.getAmount());
                    }
                }
            }
        }
        logger.info("支付金额：" + price);
        return price > 0 ? price : 0.0;
    }

    @Override
    public boolean verificationVoucher(String voucherUseId, String pricePartId) {
        // 商品信息
        Goods goods = goodsDao.getGoodsByPricePart(pricePartId);
        // 获取优惠券的信息
        Voucher voucher = voucherDao.getVocherByUser(voucherUseId);
        // 进行判断
        if (voucher != null) {
            if (voucher.getProductId() != null) {
                if (Arrays.asList(voucher.getProductId().split(",")).contains(goods.getProductId())) {
                    if (voucher.getGoodsId() != null) {
                        if (Arrays.asList(voucher.getProductId().split(",")).contains(goods.getProductId()))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isHaveVoucher(String voucherUseId, String userId) {
        VoucherUse voucherUse = new VoucherUse();
        voucherUse.setId(voucherUseId);
        voucherUse.setCreatorId(userId);
        VoucherUse result = voucherUseDao.load(voucherUse);
        if (result != null)
            return true;
        return false;
    }

    /**
     * 用户兑换优惠券
     */
    public byte[] userConvertVoucher(String userId, String code, String type) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {
                    User user = userDao.loadById(userId);
                    if (user == null) {
                        return "2";
                    }
                    VoucherRepertory voucherRepertory = new VoucherRepertory();
                    voucherRepertory.setCode(code);
                    List<VoucherRepertory> voucherRepertoryList = voucherRepertoryDao.list(voucherRepertory);
                    if (voucherRepertoryList == null || voucherRepertoryList.size() < 1) {
                        return "3";
                    }
                    VoucherRepertory voucherRepertoryOne = voucherRepertoryList.get(0);

                    if (voucherRepertoryOne.getStatus() == 1) {
                        return "9";
                    }
                    if (voucherRepertoryOne.getStatus() == -1) {
                        return "10";
                    }
                    if (voucherRepertoryOne.getStatus() != 0) {
                        return "11";
                    }
                    Voucher voucher = new Voucher();
                    voucher.setId(voucherRepertoryOne.getVoucherId());
                    Voucher vouchers = voucherDao.load(voucher);
                    if (vouchers == null) {
                        return "4";
                    }
                    if (vouchers.getStatus() == -1) {
                        return "12";
                    }
                    if (vouchers.getStatus() != 0) {
                        return "13";
                    }
                    if (DateUtils.isAfter(new Date(), vouchers.getStartTime())) {
                        return "5";
                    }
                    if (DateUtils.isAfter(vouchers.getEndTime(), new Date())) {
                        return "6";
                    }
                    voucherRepertoryOne.setStatus(1);
                    int i = voucherRepertoryDao.update(voucherRepertoryOne);
                    if (i < 1) {
                        // 日志打印区
                        status.setRollbackOnly();// 回滚
                        return "7";
                    }
                    VoucherUse voucherUse = new VoucherUse();
                    voucherUse.setId(UUIDUtils.getId());
                    voucherUse.setVoucherRepertoryId(voucherRepertoryOne.getId());
                    voucherUse.setPhone(user.getPhone());
                    voucherUse.setAmount(voucherRepertoryOne.getAmount());
                    voucherUse.setStartTime(vouchers.getStartTime());
                    voucherUse.setEndTime(vouchers.getEndTime());
                    voucherUse.setCreatorName(user.getName());
                    voucherUse.setCreatorId(user.getId());
                    voucherUse.setCreateTime(new Date());
                    voucherUse.setStatus(0);
                    voucherUse.setGrantName(type);
                    int vu = voucherUseDao.insert(voucherUse);
                    if (vu < 1) {
                        // 日志打印区
                        status.setRollbackOnly();// 回滚
                        return "8";
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return "1";
            }
        });
        int re = Integer.parseInt(rs);
        String msg = "恭喜，兑换成功！";
        if (re == 0) {
            msg = "兑换失败，请重试...";
        }
        if (re == 2) {
            msg = "兑换失败，请登录后重试...";
        }
        if (re == 3) {
            msg = "兑换码不正确！";
        }
        if (re == 4) {
            msg = "兑换失败，兑换码已失效...";
        }
        if (re == 5) {
            msg = "兑换失败，未到开始兑换时间...";
        }
        if (re == 6) {
            msg = "兑换失败，兑换已结束...";
        }
        if (re == 7) {
            msg = "兑换失败，请刷新后重试...";
        }
        if (re == 8) {
            msg = "兑换失败，请刷新后重试...";
        }
        if (re == 9) {
            msg = "兑换码已使用！";
        }
        if (re == 10) {
            msg = "兑换码不正确！";
        }
        if (re == 11) {
            msg = "兑换码不正确！";
        }
        if (re == 12) {
            msg = "兑换码不正确！";
        }
        if (re == 13) {
            msg = "兑换码不正确！";
        }
        try {
            return JSONUtil.toJSONResult(re, msg, null);
        }
        catch (IOException e) {
            Util.failLog.error("voucher.userConvertVoucher.json,userId=" + userId + " code=" + code + " rs=" + rs, e);
        }
        return null;
    }

    /**
     * 2017 11 9 领取优惠请活动
     * @param userId
     * @return
     */
    public int getActiveCenterVoucher(String userId, String grantName) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板
            public String doInTransaction(final TransactionStatus status) {
                try {
                    User user = userDao.loadById(userId);
                    if (user == null) {
                        return "2";
                    }
                    String[] voucherArr = "XJS20171110195801,XJS20171110195910,XJS20171110200025".split(",");
                    for (int a = 0; a < voucherArr.length; a++) {
                        Voucher voucher = new Voucher();
                        voucher.setBatchNo(voucherArr[a]);
                        voucher.setStatus(0);
                        Voucher vouchers = voucherDao.load(voucher);
                        if (vouchers == null) {
                            return "3";
                        }
                        VoucherUse voucherUseSelect = new VoucherUse();
                        voucherUseSelect.setCreatorId(user.getId());
                        List<VoucherUse> voucherUseList = voucherUseDao.list(voucherUseSelect);
                        if (voucherUseList != null && voucherUseList.size() > 0) {
                            for (int v = 0; v < voucherUseList.size(); v++) {
                                VoucherRepertory voucherRepertorySelecrt = voucherRepertoryDao
                                        .loadById(voucherUseList.get(v).getVoucherRepertoryId());
                                Voucher voucherSelecrt = voucherDao.loadById(voucherRepertorySelecrt.getVoucherId());
                                if (voucherSelecrt.getBatchNo().equals(voucherArr[a])) {
                                    return "7";
                                }
                            }
                        }

                        VoucherRepertory voucher_repertory = new VoucherRepertory();
                        voucher_repertory.setVoucherId(vouchers.getId());
                        voucher_repertory.setStatus(0);
                        List<VoucherRepertory> VoucherRepertoryList = voucherRepertoryDao.list(voucher_repertory);
                        if (VoucherRepertoryList == null || VoucherRepertoryList.size() < 1) {
                            return "4";
                        }
                        int vro = 0;
                        if (VoucherRepertoryList.size() - 1 > 1) {
                            vro = RandomUtils.randomCommon(0, VoucherRepertoryList.size() - 1, 1)[0];
                        }
                        VoucherRepertory voucherRepertoryOne = VoucherRepertoryList.get(vro);
                        VoucherUse voucherUse = new VoucherUse();
                        voucherUse.setId(UUIDUtils.getId());
                        voucherUse.setVoucherRepertoryId(voucherRepertoryOne.getId());
                        voucherUse.setPhone(user.getPhone());
                        voucherUse.setAmount(voucherRepertoryOne.getAmount());
                        voucherUse.setStartTime(vouchers.getStartTime());
                        voucherUse.setEndTime(vouchers.getEndTime());
                        voucherUse.setCreatorName(user.getName());
                        voucherUse.setCreatorId(user.getId());
                        voucherUse.setCreateTime(new Date());
                        voucherUse.setStatus(0);
                        voucherUse.setGrantName(grantName);
                        int vud = voucherUseDao.insert(voucherUse);
                        if (vud < 1) {
                            status.setRollbackOnly();// 回滚
                            return "5";
                        }
                        voucherRepertoryOne.setStatus(1);
                        int vrou = voucherRepertoryDao.update(voucherRepertoryOne);
                        if (vrou < 1) {
                            status.setRollbackOnly();// 回滚
                            return "6";
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return "1";
            }
        });
        return Integer.parseInt(rs);
    }

}