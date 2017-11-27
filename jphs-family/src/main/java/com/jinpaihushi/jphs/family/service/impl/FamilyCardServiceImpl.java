package com.jinpaihushi.jphs.family.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyCardDao;
import com.jinpaihushi.jphs.family.model.FamilyCard;
import com.jinpaihushi.jphs.family.service.FamilyCardService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:53
 * @version 1.0
 */
@Service("familyCardService")
public class FamilyCardServiceImpl extends BaseServiceImpl<FamilyCard> implements FamilyCardService{

	@Autowired
	private FamilyCardDao familyCardDao;
	
	@Autowired
    private PlatformTransactionManager txManager;
	
	@Override
	protected BaseDao<FamilyCard> getDao(){
		return familyCardDao;
	}

	/**
	 * 兑换卡首页列表
	 * @param familyCard
	 * @return
	 */
	public Page<Map<String,Object>> familyCardIndex(FamilyCard familyCard){
		return familyCardDao.familyCardIndex(familyCard);
	}
	
	/**
	 * 根据批次号查看详情
	 * @param familyCard
	 * @return
	 */
	public List<Map<String,Object>> familyCardDetail(FamilyCard familyCard){
		return familyCardDao.familyCardDetail(familyCard);
	}
	
	/**
	 * 生成一批兑换卡
	 * @param FamilyCard
	 * @param cardNumber
	 * @return
	 */
	public int putAll(FamilyCard familyCard,int cardNumber){
		FamilyCard familyCardOneOn = familyCardDao.loadByOneNo();
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            // 事务模板
            public String doInTransaction(final TransactionStatus status) {
                int i = 1;
                try {
						familyCard.setCreateTime(new Date());
						familyCard.setStatus(0);
						int no = Integer.parseInt(familyCardOneOn.getNo())+1;
						for(int a=0;a<cardNumber;a++){
							familyCard.setId(UUIDUtils.getId());
							familyCard.setNo(no+"");
							familyCard.setCode(UUIDUtils.getRandomString(8));
							int f =familyCardDao.insert(familyCard);
							if(f<=0){
								// 日志打印区
			                    status.setRollbackOnly();// 回滚
								return "0";
							}
							no++;
						}
                } catch (Exception e) {
                    e.printStackTrace();
                    // 日志打印区
                    status.setRollbackOnly();// 回滚
                    return "0";
                }
                return i + "";
            }
        });
		return Integer.parseInt(rs);
	}
	
}