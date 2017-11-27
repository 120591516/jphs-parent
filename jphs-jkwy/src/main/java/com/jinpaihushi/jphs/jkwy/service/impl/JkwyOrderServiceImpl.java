package com.jinpaihushi.jphs.jkwy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.ActivityPromotionDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderContentDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderRelationDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackageContentDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackageDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackagePriceDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyRelationDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrder;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderContent;
import com.jinpaihushi.jphs.jkwy.model.JkwyOrderRelation;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackageContent;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyOrderService;
import com.jinpaihushi.jphs.statistics.model.StatisticsModel;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.voucher.dao.VoucherDao;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.IntToSmallChineseNumber;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:26
 * @version 1.0
 */
@Service("jkwyOrderService")
public class JkwyOrderServiceImpl extends BaseServiceImpl<JkwyOrder> implements JkwyOrderService {

    @Autowired
    private JkwyOrderDao jkwyOrderDao;

    @Autowired
    private JkwyPackagePriceDao jkwyPackagePriceDao;

    @Autowired
    private JkwyPackageDao jkwyPackageDao;

    @Autowired
    private JkwyRelationDao jkwyRelationDao;

    @Autowired
    private JkwyPackageContentDao jkwyPackageContentDao;

    @Autowired
    private JkwyOrderContentDao jkwyOrderContentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VoucherUseDao voucherUseDao;

    @Autowired
    private VoucherRepertoryDao voucherRepertoryDao;

    @Autowired
    private JkwyOrderRelationDao jkwyOrderRelationDao;

    @Autowired
    private ActivityPromotionDao activityPromotionDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private PlatformTransactionManager txManager;// 创建事务管理器

    @Override
    protected BaseDao<JkwyOrder> getDao() {
        return jkwyOrderDao;
    }

    /**
     * 查询用户订单列表
     * @param jkwyOrder
     * @return
     */
    public List<JkwyPackagePrice> getHealthyArchives(JkwyOrder jkwyOrder) {
        return jkwyPackagePriceDao.getHealthyArchives(jkwyOrder);
    }

    /**
     * 
     * @param type					回调类型1，支付宝，2微信，3余额
     * @param no					支付宝回调订单号-自己平台定义回调返回结果
     * @param trade_no				支付宝交易号-支付宝生成
     * @param total_fee				支付宝回调返回支付金额
     * @param packageParams			微信回调返回内容
     * @return
     */
    public boolean jkwyNotify(int type, String no, String trade_no, String total_fee,
            SortedMap<Object, Object> packageParams) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    String out_trade_no = ""; //	自己平台订单号
                    String total_fees = ""; //	支付金额
                    String transaction_id = ""; //	第三方支付交易号
                    if (type == 1) {
                        out_trade_no = no;
                        total_fees = total_fee;
                        transaction_id = trade_no;
                    }
                    if (type == 2) {
                        String mch_id = (String) packageParams.get("mch_id");
                        String openid = (String) packageParams.get("openid");
                        String attach = (String) packageParams.get("attach");
                        String is_subscribe = (String) packageParams.get("is_subscribe");
                        out_trade_no = (String) packageParams.get("out_trade_no"); //	订单号
                        total_fees = (String) packageParams.get("total_fee"); //	支付金额
                        transaction_id = (String) packageParams.get("transaction_id"); //	交易号
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("支付回调：mch_id=" + mch_id + " openid=" + openid + " attach=" + attach
                                    + " is_subscribe=" + is_subscribe);
                        }
                    }
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("支付回调：out_trade_no=" + out_trade_no + " total_fees=" + total_fees
                                + " transaction_id=" + transaction_id);
                    }
                    JkwyOrder jkwyOrder = new JkwyOrder();
                    jkwyOrder.setNo(out_trade_no);
                    jkwyOrder.setSchedule(0);
                    jkwyOrder.setStatus(0);
                    //	修改订单状态
                    JkwyOrder jkwyOrder_up = jkwyOrderDao.load(jkwyOrder);
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("支付回调：jkwyOrder_up=" + jkwyOrder_up);
                    }
                    if (jkwyOrder_up == null) {
                        return "0";
                    }
                    jkwyOrder_up.setSchedule(1);
                    jkwyOrder_up.setPayTime(new Date());
                    jkwyOrder_up.setPayPrice(Double.parseDouble(total_fees));
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("支付回调：jkwyOrder_up_up=" + jkwyOrder_up);
                    }
                    int jod = jkwyOrderDao.update(jkwyOrder_up);
                    if (Util.debugLog.isDebugEnabled()) {
                        Util.debugLog.debug("支付回调：jod=" + jod);
                    }
                    if (jod <= 0) {
                        return "2";
                    }
                    // 修改亲友订单关系表
                    JkwyOrderRelation jkwyOrderRelation = new JkwyOrderRelation();
                    jkwyOrderRelation.setJkwyOrderId(jkwyOrder_up.getId());
                    jkwyOrderRelation.setStatus(0);
                    List<JkwyOrderRelation> jkwyOrderRelationList = jkwyOrderRelationDao.list(jkwyOrderRelation);
                    if (jkwyOrderRelationList == null || jkwyOrderRelationList.size() < 1) {
                        for (int j = 0; j < jkwyOrderRelationList.size(); j++) {
                            JkwyOrderRelation jkwyOrderRelation_one = jkwyOrderRelationList.get(j);
                            jkwyOrderRelation_one.setStatus(1);
                            if (Util.debugLog.isDebugEnabled()) {
                                Util.debugLog.debug("支付回调：jkwyOrderRelation_one" + j + "=" + jkwyOrderRelation_one);
                            }
                            int jord = jkwyOrderRelationDao.update(jkwyOrderRelation_one);
                            if (jord <= 0) {
                                return "3";
                            }
                        }
                    }
                    //	修改优惠券状态
                    if (!StringUtils.isEmpty(jkwyOrder_up.getVoucherUserId())) {
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("支付回调：VoucherUserId=" + jkwyOrder_up.getVoucherUserId());
                        }
                        VoucherUse voucherUse_re = voucherUseDao.loadById(jkwyOrder_up.getVoucherUserId());
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("支付回调：voucherUse_re=" + voucherUse_re);
                        }
                        voucherUse_re.setUseTime(new Date());
                        int vud = voucherUseDao.update(voucherUse_re);
                        if (Util.debugLog.isDebugEnabled()) {
                            Util.debugLog.debug("支付回调：voucherUse_re=" + voucherUse_re);
                        }
                        if (vud <= 0) {
                            return "3";
                        }
                    }

                    JkwyPackage jkwyPackage = jkwyPackageDao.loadById(jkwyOrder_up.getJkwyPackageId());
                    JkwyPackagePrice jkwyPackagePrice = jkwyPackagePriceDao
                            .loadById(jkwyOrder_up.getJkwyPackagePriceId());

                    Transaction transaction = new Transaction();
                    transaction.setId(UUID.randomUUID().toString());
                    transaction.setOrderId(out_trade_no);
                    transaction.setAmount(Double.parseDouble(total_fees));
                    transaction.setScore((new Double(transaction.getAmount())).intValue());
                    transaction.setOperate(3);
                    transaction.setOperateSource(4);
                    transaction.setRemark(jkwyPackage.getTitle() + "-" + jkwyPackagePrice.getTitle());
                    transaction.setWithdraw(0);
                    transaction.setPayType(type);
                    transaction.setOutTradeNo(trade_no);
                    transaction.setCreatorId(jkwyOrder_up.getCreatorId());
                    transaction.setCreateTime(new Date());
                    transaction.setStatus(1);
                    transaction.setType(3);
                    transactionDao.insert(transaction);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    status.setRollbackOnly();// 回滚
                    return "4";
                }
                return "1";
            }
        });
        if (rs.equals("1")) {
            return true;
        }

        return false;
    }

    public JSONObject createOrder(JkwyOrder jkwyOrder, String jkwyRelationId) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            JSONObject re_json = new JSONObject();

            Double payPrice = 0d;

            String jkwyName = "";

            String orderId = UUIDUtils.getId();

            String no = Common.getOrderJKNumber();

            public String doInTransaction(final TransactionStatus status) {
                try {

                    if (userDao.loadById(jkwyOrder.getCreatorId()) == null) {
                        re_json.put("msg", "下单失败，getCreatorId=" + jkwyOrder.getCreatorId());
                        re_json.put("resultcode", 0);
                        re_json.put("result", 0);
                        return re_json.toString();
                    }
                    String jkwyPackageId = jkwyOrder.getJkwyPackageId();
                    String jkwyPackagePriceId = jkwyOrder.getJkwyPackagePriceId();
                    JkwyPackagePrice jkwyPackagePrice = new JkwyPackagePrice();
                    jkwyPackagePrice.setJkwyPackageId(jkwyPackageId);
                    jkwyPackagePrice.setId(jkwyPackagePriceId);
                    JkwyPackagePrice jkwyPackagePriceOne = jkwyPackagePriceDao.load(jkwyPackagePrice);
                    if (jkwyPackagePriceOne == null) {
                        re_json.put("msg", "下单失败，套餐id=" + jkwyPackageId + "或规格id=" + jkwyPackagePriceId + "不正确");
                        re_json.put("resultcode", 0);
                        re_json.put("result", 0);
                        return re_json.toString();
                    }
                    JkwyPackage jkwyPackageOne = jkwyPackageDao.loadById(jkwyPackageId);
                    if (jkwyPackageOne == null) {
                        re_json.put("msg", "下单失败，套餐id=" + jkwyPackageId + "或规格id=" + jkwyPackagePriceId + "不正确");
                        re_json.put("resultcode", 0);
                        re_json.put("result", 0);
                        return re_json.toString();
                    }

                    // 提出套餐绑定的亲友
                    int supportNumber = jkwyPackagePriceOne.getSupportNumber();
                    String[] jkwyRelationId_arr = jkwyRelationId.split(",");
                    List<String> jkwyRelationId_list = new ArrayList<String>();
                    for (int a = 0; a < jkwyRelationId_arr.length; a++) {
                        if (!StringUtils.isEmpty(jkwyRelationId_arr[a])) {
                            jkwyRelationId_list.add(jkwyRelationId_arr[a]);
                        }
                    }
                    if (supportNumber != jkwyRelationId_list.size()) {
                        re_json.put("resultcode", 0);
                        re_json.put("msg", "创建订单失败，套餐人数 supportNumber=" + supportNumber + "和 选择人数jkwyRelationId="
                                + jkwyRelationId + "不一致");
                        re_json.put("result", 0);
                        return re_json.toString();
                    }
                    //	 订单绑定亲友
                    for (int j = 0; j < jkwyRelationId_list.size(); j++) {
                        JkwyRelation jkwyRelation = jkwyRelationDao.loadById(jkwyRelationId_list.get(j));
                        if (jkwyRelation == null) {
                            status.setRollbackOnly();// 回滚
                            re_json.put("resultcode", 0);
                            re_json.put("msg", "生成订单失败,亲友id不正确 =" + jkwyRelationId_list.get(j));
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                        JkwyOrderRelation jkwyOrderRelation_se = new JkwyOrderRelation();
                        jkwyOrderRelation_se.setJkwyRelation(jkwyRelation.getId());
                        jkwyOrderRelation_se.setCreatorId(jkwyOrder.getCreatorId());
                        jkwyOrderRelation_se.setStatus(1);
                        List<JkwyOrderRelation> jkwyOrderRelationList = jkwyOrderRelationDao.list(jkwyOrderRelation_se);
                        if (jkwyOrderRelationList != null && jkwyOrderRelationList.size() > 0) {
                            for (int jorl = 0; jorl < jkwyOrderRelationList.size(); jorl++) {
                                JkwyOrder jkwy_order_R = new JkwyOrder();
                                jkwy_order_R.setId(jkwyOrderRelationList.get(jorl).getJkwyOrderId());
                                jkwy_order_R.setStatus(0);
                                jkwy_order_R.setSchedule(1);
                                JkwyOrder jkwyOrder_s = jkwyOrderDao.load(jkwy_order_R);
                                if (DateUtils.isAfter(jkwyOrder_s.getEndTime(), new Date())) {
                                    if (!jkwyOrder_s.getJkwyPackageId().equals(jkwyPackageId)
                                            || !jkwyOrder_s.getJkwyPackagePriceId().equals(jkwyPackagePriceId)) {
                                        status.setRollbackOnly();// 回滚
                                        re_json.put("resultcode", 0);
                                        re_json.put("msg", "下订单失败,亲友 " + jkwyRelation.getName() + " 已购买"
                                                + jkwyPackageDao.loadById(jkwyOrder_s.getJkwyPackageId()).getTitle()
                                                + "-" + jkwyPackagePriceDao
                                                        .loadById(jkwyOrder_s.getJkwyPackagePriceId()).getTitle()
                                                + "套餐。");
                                        re_json.put("result", 0);
                                        return re_json.toString();
                                    }
                                }
                            }
                        }

                        JkwyOrderRelation jkwyOrderRelation_in = new JkwyOrderRelation();
                        jkwyOrderRelation_in.setId(UUIDUtils.getId());
                        jkwyOrderRelation_in.setJkwyOrderId(orderId);
                        jkwyOrderRelation_in.setJkwyRelation(jkwyRelationId_list.get(j));
                        jkwyOrderRelation_in.setStatus(0);
                        jkwyOrderRelation_in.setCreateTime(new Date());
                        jkwyOrderRelation_in.setCreatorId(jkwyOrder.getCreatorId());

                        int jord = jkwyOrderRelationDao.insert(jkwyOrderRelation_in);
                        if (jord <= 0) {
                            status.setRollbackOnly();// 回滚
                            re_json.put("resultcode", 0);
                            re_json.put("msg", "生成订单失败,添加订单绑定亲友失败");
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                    }

                    jkwyName = jkwyPackageOne.getTitle() + jkwyPackagePriceOne.getTitle();
                    // 使用完优惠券之后的价格
                    Double payPrice_one = jkwyPackagePriceOne.getPrice();
                    if (!StringUtils.isEmpty(jkwyOrder.getVoucherUserId())) {
                        VoucherUse voucherUse = voucherUseDao.loadById(jkwyOrder.getVoucherUserId());
                        if (voucherUse == null) {
                            re_json.put("msg", "下单失败，优惠券VoucherUserId=" + jkwyOrder.getVoucherUserId() + "不正确");
                            re_json.put("resultcode", 0);
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                        VoucherRepertory voucherRepertoryOne = voucherRepertoryDao
                                .loadById(voucherUse.getVoucherRepertoryId());
                        if (voucherRepertoryOne == null) {
                            re_json.put("msg",
                                    "下单失败，优惠券VoucherRepertoryId=" + voucherUse.getVoucherRepertoryId() + "不正确");
                            re_json.put("resultcode", 0);
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                        Voucher voucherOne = voucherDao.loadById(voucherRepertoryOne.getVoucherId());
                        if (voucherOne == null) {
                            re_json.put("msg", "下单失败，优惠券VoucherId=" + voucherRepertoryOne.getVoucherId() + "不正确");
                            re_json.put("resultcode", 0);
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                        /*VoucherUse voucherUse_re = new VoucherUse();
                        voucherUse_re.setId(voucherUse.getId());
                        voucherUse_re.setUseTime(new Date());*/
                        if (voucherOne.getType() == 1) {
                            payPrice_one = DoubleUtils.subToPositive(jkwyPackagePriceOne.getPrice(),
                                    voucherRepertoryOne.getAmount());

                            jkwyOrder.setVoucherPrice(voucherRepertoryOne.getAmount());
                            jkwyOrder.setVoucherUserId(voucherUse.getId());
                            /*int ucuv = voucherUseDao.update(voucherUse_re);
                             * if(ucuv <=0){
                            	status.setRollbackOnly();// 回滚
                            	re_json.put("resultcode", 0);
                            	re_json.put("msg", "修改优惠券状态失败");
                            	re_json.put("result", 0);
                            	return re_json.toString();
                            }*/
                        }
                        else if (voucherOne.getType() == 2) {
                            if (jkwyPackagePriceOne.getPrice() > voucherRepertoryOne.getConditionAmount()) {
                                payPrice_one = DoubleUtils.subToPositive(jkwyPackagePriceOne.getPrice(),
                                        voucherRepertoryOne.getAmount());
                                jkwyOrder.setVoucherPrice(voucherRepertoryOne.getAmount());
                                jkwyOrder.setVoucherUserId(voucherUse.getId());
                                /*int ucuv = voucherUseDao.update(voucherUse_re);
                                if(ucuv <=0){
                                	status.setRollbackOnly();// 回滚
                                	re_json.put("resultcode", 0);
                                	re_json.put("msg", "修改优惠券状态失败");
                                	re_json.put("result", 0);
                                	return re_json.toString();
                                }*/
                            }
                        }
                        else if (voucherOne.getType() == 3) {
                            if (jkwyPackagePriceOne.getPrice() > voucherRepertoryOne.getDiscountAmount()) {
                                payPrice_one = DoubleUtils.mul(jkwyPackagePriceOne.getPrice(),
                                        voucherRepertoryOne.getAmount());
                                jkwyOrder.setVoucherPrice(voucherRepertoryOne.getAmount());
                                jkwyOrder.setVoucherUserId(voucherUse.getId());
                                //		                		int ucuv = voucherUseDao.update(voucherUse_re);
                                /*if(ucuv <=0){
                                	status.setRollbackOnly();// 回滚
                                	re_json.put("resultcode", 0);
                                	re_json.put("msg", "修改优惠券状态失败");
                                	re_json.put("result", 0);
                                	return re_json.toString();
                                }*/
                            }
                        }
                    }
                    // 查询特价优惠
                    ActivityPromotion activityPromotion = new ActivityPromotion();
                    activityPromotion.setResourceId(jkwyPackageId);
                    activityPromotion.setPriceId(jkwyPackagePriceId);
                    activityPromotion.setResourceType(3);
                    activityPromotion.setPlatformId(jkwyOrder.getPlatformId());
                    ActivityPromotion activityPromotion_one = activityPromotionDao
                            .getActivityForGoods(activityPromotion);
                    if (activityPromotion_one != null && payPrice_one > 0) {

                        boolean flag = false;
                        //活动类型 1、立减
                        if (activityPromotion_one.getType() == 1) {
                            payPrice_one = DoubleUtils.subToPositive(payPrice_one, activityPromotion_one.getPrice());
                            flag = true;
                        }
                        else {
                            int num = 0;/*orderDao.countByActivity(activityPromotion_one.getId(), activityPromotion_one.getCreatorId(),
                                        activityPromotion_one.getBeginTime(), activityPromotion_one.getEndTime());*/
                            //2 首单立减
                            if (activityPromotion_one.getType() == 2) {
                                if (num == 0) {
                                    payPrice_one = DoubleUtils.subToPositive(payPrice_one,
                                            activityPromotion_one.getPrice());
                                    flag = true;
                                }
                            }
                            //3 第二单立减
                            if (activityPromotion_one.getType() == 3) {
                                if (num == 1) {
                                    payPrice_one = DoubleUtils.subToPositive(payPrice_one,
                                            activityPromotion_one.getPrice());
                                    flag = true;
                                }
                            }
                        }
                        if (flag) {
                            jkwyOrder.setActivityPromotionId(activityPromotion_one.getId());
                            jkwyOrder.setActivityPromotionPrice(activityPromotion_one.getPrice());
                            if (DoubleUtils.subToPositive(payPrice_one, activityPromotion_one.getPrice()) == 0) {
                                payPrice_one = 0d;
                            }
                            else {
                                payPrice_one = DoubleUtils.sub(payPrice_one, activityPromotion_one.getPrice());
                            }
                        }

                    }
                    if (DoubleUtils.subToPositive(payPrice_one, jkwyOrder.getPayPrice()) != 0) {
                        re_json.put("resultcode", 0);
                        re_json.put("msg", "创建订单失败，支付金额计算结果异常 payPrice_one=" + payPrice_one + " jkwyOrder.payPrice="
                                + jkwyOrder.getPayPrice());
                        re_json.put("result", 0);
                        return re_json.toString();
                    }

                    // 套餐内容赋值
                    JkwyPackageContent jkwyPackageContent = new JkwyPackageContent();
                    jkwyPackageContent.setJkwyPackagePriceId(jkwyPackagePriceId);
                    jkwyPackageContent.setStatus(0);
                    List<JkwyPackageContent> jkwyPackageContent_list = jkwyPackageContentDao.list(jkwyPackageContent);
                    if (jkwyPackageContent_list == null || jkwyPackageContent_list.size() == 0) {
                        status.setRollbackOnly();// 回滚
                        re_json.put("resultcode", 0);
                        re_json.put("msg", "生成订单失败,查询服务项目失败 ，jkwyPackagePriceId=" + jkwyPackagePriceId);
                        re_json.put("result", 0);
                        return re_json.toString();
                    }
                    for (int jc = 0; jc < jkwyPackageContent_list.size(); jc++) {
                        JkwyPackageContent jkwyPackageContentOne = jkwyPackageContent_list.get(jc);

                        JkwyOrderContent jkwyOrderContent = new JkwyOrderContent();
                        jkwyOrderContent.setId(UUIDUtils.getId());
                        jkwyOrderContent.setJkwyOrderId(orderId);
                        jkwyOrderContent.setTitle(jkwyPackageContentOne.getTitle());
                        jkwyOrderContent.setContent(jkwyPackageContentOne.getContent());
                        jkwyOrderContent.setNumber(jkwyPackageContentOne.getNumber());
                        jkwyOrderContent.setSurplusNumber(jkwyPackageContentOne.getSurplusNumber());
                        jkwyOrderContent.setStatus(0);
                        jkwyOrderContent.setCreateTime(new Date());
                        jkwyOrderContent.setCreatorId(jkwyOrder.getCreatorId());
                        int jocd = jkwyOrderContentDao.insert(jkwyOrderContent);
                        if (jocd <= 0) {
                            status.setRollbackOnly();// 回滚
                            re_json.put("resultcode", 0);
                            re_json.put("msg", "生成订单失败,添加order—countent失败");
                            re_json.put("result", 0);
                            return re_json.toString();
                        }
                    }
                    // 生成订单
                    jkwyOrder.setId(orderId);
                    jkwyOrder.setNo(no);
                    jkwyOrder.setOldPrice(jkwyPackagePriceOne.getOldPrice());
                    jkwyOrder.setPrice(jkwyPackagePriceOne.getPrice());
                    jkwyOrder.setPayPrice(payPrice_one);
                    jkwyOrder.setSchedule(0);
                    jkwyOrder.setEndTime(
                            DateUtils.addDate(new Date(), new Long((long) jkwyPackagePriceOne.getServiceTime())));
                    jkwyOrder.setCreateTime(new Date());
                    jkwyOrder.setStatus(0);
                    int jod = jkwyOrderDao.insert(jkwyOrder);
                    if (jod <= 0) {
                        status.setRollbackOnly();// 回滚
                        re_json.put("resultcode", 0);
                        re_json.put("msg", "生成订单失败");
                        re_json.put("result", 0);
                        return re_json.toString();
                    }

                    if (DoubleUtils.subToPositive(payPrice_one, jkwyOrder.getPrice()) != 0) {
                        re_json.put("msg",
                                "下单失败，支付金额=" + jkwyOrder.getPayPrice() + "跟实际需要支付金额id=" + payPrice_one + "不一致");
                        re_json.put("resultcode", 0);
                        re_json.put("result", 0);
                        return re_json.toString();
                    }

                    payPrice = payPrice_one;

                }
                catch (Exception e) {
                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    re_json.put("msg", "下单失败，");
                    re_json.put("resultcode", 0);
                    re_json.put("result", 0);
                    return re_json.toString();
                }
                JSONObject json_re = new JSONObject();
                json_re.put("payPrice", payPrice);
                json_re.put("no", no);
                json_re.put("orderId", orderId);
                json_re.put("goodsName", jkwyName);
                re_json.put("msg", "下单成功");
                re_json.put("resultcode", 1);
                re_json.put("result", json_re);
                return re_json.toString();
            }
        });
        return JSONObject.fromObject(rs);
    }

    @Override
    public List<Map<String, Object>> getQuantityByWeek(String weeks) {
        String startTime = weeks.split("T")[0];
        String endTime = weeks.split("T")[1];
        List<StatisticsModel> list = jkwyOrderDao.getQuantityByWeek(startTime, endTime);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            //获取两个日期内的所有日期
            List<String> time = CycleTimeUtils.getDatesBetweenTwoDate(startTime, endTime);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] getDeviceNameList(List<StatisticsModel> deviceList) {
        for (int i = 0; i < deviceList.size(); i++) {
            for (int j = deviceList.size() - 1; j > i; j--) {
                if (deviceList.get(i).getDevice().equals(deviceList.get(j).getDevice())) {
                    deviceList.remove(j);
                }
            }
        }
        String[] names = new String[deviceList.size()];
        for (int i = 0; i < deviceList.size(); i++) {
            names[i] = "'" + deviceList.get(i).getDevice() + "'";
        }
        /*String[] names = { "'官网'", "'wap站'", "'IOS'", "'安卓'", "'其他'" };*/
        return names;
    }

    @Override
    public List<Map<String, Object>> quantityByMonth(String month) {
        List<StatisticsModel> list = jkwyOrderDao.quantityByMonth(month);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(month);
            List<String> time = CycleTimeUtils.getAllDaysMonthByDate(date);
            for (int i = 0; i < time.size(); i++) {
                String str = "'" + time.get(i) + "'";
                time.set(i, str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> quantityByYear(String year) {
        List<StatisticsModel> list = jkwyOrderDao.quantityByYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = null;
        try {
            List<String> time = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                String str = "'" + year + "-";
                if (i < 10) {
                    str += "0" + i + "'";
                }
                else {
                    str += i + "'";
                }
                time.add(str);
            }
            Object[] array = time.toArray();
            map = new HashMap<>();
            map.put("time", array);
            result.add(map);
            List<StatisticsModel> deviceList = new ArrayList<>(list);
            String[] names = getDeviceNameList(deviceList);
            map.put("deviceName", names);
            result.add(map);
            //某个平台的数据集合
            getDviceData(list, result, time, names);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> getAllNumByYear(String year) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        List<StatisticsModel> allNumByYear = jkwyOrderDao.getAllNumByYear(map);
        Map<String, Object> result = getAnnualData(year, allNumByYear);
        return result;
    }

    protected Map<String, Object> getAnnualData(String year, List<StatisticsModel> allNumByYear) {
        Map<String, Object> result = new HashMap<>();
        String[] month = new String[13];
        String[] chMonth = new String[13];
        for (int i = 0; i < 13; i++) {
            if (i < 10) {
                if (i == 0)
                    month[i] = "  ";
                else
                    month[i] = "0" + i;
            }
            else {
                month[i] = i + "";
            }
            chMonth[i] = IntToSmallChineseNumber.ToCH(i);

        }
        result.put("months", chMonth);
        String[] colData = getDeviceNameList(allNumByYear);
        result.put("days", Arrays.toString(colData));
        String[][] allData = getDataByAllData(allNumByYear, month, chMonth, colData);
        result.put("allData", allData);
        result.put("year", "'" + year + "-01'");
        return result;
    }
}