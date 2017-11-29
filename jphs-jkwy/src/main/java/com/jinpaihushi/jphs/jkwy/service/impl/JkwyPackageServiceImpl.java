package com.jinpaihushi.jphs.jkwy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.model.ActivityPromotion;
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.jkwy.dao.JkwyOrderDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackageContentDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackageDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackagePlatformDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyPackagePriceDao;
import com.jinpaihushi.jphs.jkwy.dao.JkwyRelationDao;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackage;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackageContent;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePlatform;
import com.jinpaihushi.jphs.jkwy.model.JkwyPackagePrice;
import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyPackageService;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.jphs.service.service.ServiceImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 17:01:27
 * @version 1.0
 */
@Service("jkwyPackageService")
public class JkwyPackageServiceImpl extends BaseServiceImpl<JkwyPackage> implements JkwyPackageService {
    @Autowired
    private JkwyPackageDao jkwyPackageDao;

    @Autowired
    private JkwyPackagePlatformDao jkwyPackagePlatformDao;
    @Autowired
    private JkwyOrderDao jkwyOrderDao;
    @Autowired
    private JkwyRelationDao jkwyRelationDao;

    @Override
    protected BaseDao<JkwyPackage> getDao() {
        return jkwyPackageDao;
    }

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private ServiceImagesService serviceImagesService;

    @Autowired
    private JkwyPackageContentDao jkwyPackageContentDao;

    @Autowired
    private JkwyPackagePriceDao jkwyPackagePriceDao;

    @Override
    public String insertPackageAndImg(JkwyPackage jkwyPackage, ImageType imageType) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板

            public String doInTransaction(final TransactionStatus status) {
                int i = 0;
                try {
                    //插入套餐基本信息
                    String content = jkwyPackage.getContent();
                    String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
                    jkwyPackage.setContent(con);
                    i = jkwyPackageDao.insert(jkwyPackage);
                    if (i > 0) {
                        List<JkwyPackagePrice> list = jkwyPackage.getPackagePrice();
                        for (JkwyPackagePrice jkwyPackagePrice : list) {
                            if (null == jkwyPackagePrice.getId() && jkwyPackagePrice.getStatus() != -1) {
                                jkwyPackagePrice.setJkwyPackageId(jkwyPackage.getId());
                                jkwyPackagePrice.setId(UUIDUtils.getId());
                                jkwyPackagePrice.setStatus(0);
                                jkwyPackagePrice.setCreateTime(new Date());
                                jkwyPackagePrice.setCreatorId(jkwyPackage.getCreatorId());
                                jkwyPackagePrice.setCreatorName(jkwyPackage.getCreatorName());
                                jkwyPackagePriceDao.insert(jkwyPackagePrice);
                                List<JkwyPackageContent> priceContent = jkwyPackagePrice.getJkwyPackageContentList();
                                for (JkwyPackageContent jkwyPackagePriceContent : priceContent) {
                                    if (null == jkwyPackagePriceContent.getId()
                                            && jkwyPackagePriceContent.getStatus() != -1) {
                                        jkwyPackagePriceContent.setId(UUIDUtils.getId());
                                        jkwyPackagePriceContent.setJkwyPackagePriceId(jkwyPackagePrice.getId());
                                        jkwyPackagePriceContent.setStatus(0);
                                        jkwyPackagePriceContent.setCreatorId(jkwyPackage.getCreatorId());
                                        jkwyPackagePriceContent.setCreatorName(jkwyPackage.getCreatorName());
                                        jkwyPackagePriceContent.setCreateTime(new Date());
                                        jkwyPackageContentDao.insert(jkwyPackagePriceContent);
                                    }
                                }
                            }
                        }
                        ServiceImages serviceImages = new ServiceImages();
                        serviceImages.setSourceId(jkwyPackage.getId());
                        serviceImages.setSort(jkwyPackage.getSort());
                        serviceImages.setStatus(1);
                        serviceImages.setType(1);
                        serviceImages.setCreatorId(jkwyPackage.getCreatorId());
                        serviceImages.setCreatorName(jkwyPackage.getCreatorName());
                        /* pc图片信息 */
                        serviceImages.setId(UUID.randomUUID().toString());
                        serviceImages.setUrl(imageType.getPcurl());
                        serviceImages.setDevice_type(1);
                        serviceImages.setRemarks("套餐-" + jkwyPackage.getTitle() + "-pc图片");
                        // 添加
                        serviceImagesService.insert(serviceImages);
                        /* 移动端图片信息 */
                        serviceImages.setId(UUID.randomUUID().toString());
                        serviceImages.setUrl(imageType.getMoveurl());
                        serviceImages.setDevice_type(2);
                        serviceImages.setRemarks("套餐-" + jkwyPackage.getTitle() + "-移动图片");
                        // 添加
                        serviceImagesService.insert(serviceImages);
                    }
                    else {
                        status.setRollbackOnly();// 回滚
                    }
                    JkwyPackagePlatform packagePlatform = null;
                    //插入平台的信息
                    String[] platformId = jkwyPackage.getPlatformId().split(",");
                    for (String string : platformId) {
                        packagePlatform = new JkwyPackagePlatform();
                        packagePlatform.setId(UUIDUtils.getId());
                        packagePlatform.setPlatformId(string);
                        packagePlatform.setJkwyPackageId(jkwyPackage.getId());
                        packagePlatform.setStatus(0);
                        jkwyPackagePlatformDao.insert(packagePlatform);
                    }

                }
                catch (Exception e) {

                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return i + "";
            }
        });
        return rs;
    }

    @Override
    public String updatePackageAndImg(JkwyPackage jkwyPackage, ImageType imageType) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板

            public String doInTransaction(final TransactionStatus status) {
                int i = 0;
                try {
                    //插入套餐基本信息
                    String content = jkwyPackage.getContent();
                    String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
                    jkwyPackage.setContent(con);
                    i = jkwyPackageDao.update(jkwyPackage);
                    if (i > 0) {
                        List<JkwyPackagePrice> list = jkwyPackage.getPackagePrice();
                        for (JkwyPackagePrice jkwyPackagePrice : list) {
                            if (null == jkwyPackagePrice.getId() && jkwyPackagePrice.getStatus() != -1) {
                                jkwyPackagePrice.setJkwyPackageId(jkwyPackage.getId());
                                jkwyPackagePrice.setId(UUIDUtils.getId());
                                jkwyPackagePrice.setStatus(0);
                                jkwyPackagePrice.setCreateTime(new Date());
                                jkwyPackagePrice.setCreatorId(jkwyPackage.getCreatorId());
                                jkwyPackagePrice.setCreatorName(jkwyPackage.getCreatorName());
                                jkwyPackagePriceDao.insert(jkwyPackagePrice);
                                List<JkwyPackageContent> priceContent = jkwyPackagePrice.getJkwyPackageContentList();
                                for (JkwyPackageContent jkwyPackagePriceContent : priceContent) {
                                    if (null == jkwyPackagePriceContent.getId()
                                            && jkwyPackagePriceContent.getStatus() != -1) {
                                        jkwyPackagePriceContent.setId(UUIDUtils.getId());
                                        jkwyPackagePriceContent.setJkwyPackagePriceId(jkwyPackagePrice.getId());
                                        jkwyPackagePriceContent.setStatus(0);
                                        jkwyPackagePriceContent.setCreatorId(jkwyPackage.getCreatorId());
                                        jkwyPackagePriceContent.setCreatorName(jkwyPackage.getCreatorName());
                                        jkwyPackagePriceContent.setCreateTime(new Date());
                                        jkwyPackageContentDao.insert(jkwyPackagePriceContent);
                                    }
                                }
                            }
                            else if (null != jkwyPackagePrice.getId()) {
                                jkwyPackagePriceDao.update(jkwyPackagePrice);
                                List<JkwyPackageContent> priceContent = jkwyPackagePrice.getJkwyPackageContentList();
                                for (JkwyPackageContent jkwyPackagePriceContent : priceContent) {
                                    if (null == jkwyPackagePriceContent.getId()
                                            && jkwyPackagePriceContent.getStatus() != -1) {
                                        jkwyPackagePriceContent.setId(UUIDUtils.getId());
                                        jkwyPackagePriceContent.setJkwyPackagePriceId(jkwyPackagePrice.getId());
                                        jkwyPackagePriceContent.setStatus(0);
                                        jkwyPackagePriceContent.setCreatorId(jkwyPackage.getCreatorId());
                                        jkwyPackagePriceContent.setCreatorName(jkwyPackage.getCreatorName());
                                        jkwyPackagePriceContent.setCreateTime(new Date());
                                        jkwyPackageContentDao.insert(jkwyPackagePriceContent);
                                    }
                                    else {
                                        jkwyPackageContentDao.update(jkwyPackagePriceContent);
                                    }
                                }
                            }
                        }
                        boolean flag = false;
                        ServiceImages serviceImages = new ServiceImages();
                        // 添加
                        if (StringUtils.isNotEmpty(imageType.getPcid())) {
                            /* pc图片信息 */
                            serviceImages.setId(imageType.getPcid());
                            serviceImages.setUrl(imageType.getPcurl());
                            // 修改
                            flag = serviceImagesService.update(serviceImages);
                            if (flag) {
                                if (StringUtils.isNotEmpty(imageType.getMoveid())) {
                                    /* 移动端图片信息 */
                                    serviceImages.setId(imageType.getMoveid());
                                    serviceImages.setUrl(imageType.getMoveurl());
                                    // 修改
                                    flag = serviceImagesService.update(serviceImages);
                                }
                            }
                        }
                    }
                    else {
                        status.setRollbackOnly();// 回滚
                    }
                    //删除该套餐所在平台
                    jkwyPackagePlatformDao.deleteByPackageId(jkwyPackage.getId());
                    //插入信息的绑定数据
                    JkwyPackagePlatform packagePlatform = null;
                    //插入平台的信息
                    String[] platformId = jkwyPackage.getPlatformId().split(",");
                    for (String string : platformId) {
                        packagePlatform = new JkwyPackagePlatform();
                        packagePlatform.setId(UUIDUtils.getId());
                        packagePlatform.setPlatformId(string);
                        packagePlatform.setJkwyPackageId(jkwyPackage.getId());
                        packagePlatform.setStatus(0);
                        jkwyPackagePlatformDao.insert(packagePlatform);
                    }
                }
                catch (Exception e) {

                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return i + "";
            }
        });
        return rs;
    }

    @Override
    public JkwyPackage getDetail(String id) {
        return jkwyPackageDao.getDetail(id);
    }

    /**
     * 根据套餐id 和 平台id查询套餐详情
     */
    public JkwyPackage getJkwyPackageDetails(String platformId, String id, String userId, int deviceType) {
        JkwyPackage jkwyPackage = new JkwyPackage();
        jkwyPackage.setStatus(0);
        jkwyPackage.setId(id);
        jkwyPackage.setDeviceType(deviceType);
        jkwyPackage.setPlatformId(platformId);
        JkwyPackage jkwyPackage_re = jkwyPackageDao.getJkwyPackageDetails(jkwyPackage);
        if (!StringUtils.isEmpty(userId)) {
            JkwyRelation jkwyRelation = new JkwyRelation();
            jkwyRelation.setCreatorId(userId);
            List<JkwyRelation> jkwyRelationList = jkwyRelationDao.getUserRelationOfOrder(jkwyRelation);
            if (jkwyPackage_re != null) {
                jkwyPackage_re.setJkwyRelationList(jkwyRelationList);
            }
            List<JkwyPackagePrice> jkwyPackagePriceList = jkwyPackage_re.getJkwyPackagePriceList();
            for(int pp =0;pp<jkwyPackagePriceList.size();pp++){
            	JkwyPackagePrice jkwyPackagePriceOne = jkwyPackagePriceList.get(pp);
            	ActivityPromotion activityPromotion = jkwyPackagePriceOne.getActivityPromotion();
            	if(activityPromotion != null){
                        int num = jkwyOrderDao.getJkwyOrderNumber(activityPromotion.getId(), userId,
                        		activityPromotion.getBeginTime(), activityPromotion.getEndTime());
                        //2 首单立减
                        if (activityPromotion.getType() == 2) {
                        	if(num != 0){
                        		jkwyPackagePriceList.get(pp).getActivityPromotion().setPrice(0d);
                        	}
                        }
                        //3 第二单立减
                        if (activityPromotion.getType() == 3) {
                        	if(num != 1){
                        		jkwyPackagePriceList.get(pp).getActivityPromotion().setPrice(0d);
                        	}
                        }
                    }
            }
        }

        return jkwyPackage_re;
    }

    /**
     * 根据平台id，查询平台下所有健康无忧套餐
     * @param platformId
     * @return
     */
    public List<JkwyPackage> getJkwyPackagePlatformList(String platformId, int type) {
        JkwyPackage jkwyPackage = new JkwyPackage();
        jkwyPackage.setStatus(0);
        jkwyPackage.setPlatformId(platformId);
        jkwyPackage.setDeviceType(type);
        jkwyPackage.setOrderby("JP.sort ASC");
        List<JkwyPackage> jkwyPackageList = jkwyPackageDao.lists(jkwyPackage);
        return jkwyPackageList;
    }

    @Override
    public List<TreeNode> getTreeNode() {
        return jkwyPackageDao.getTreeNode();
    }

}