package com.jinpaihushi.jphs.evaluation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.evaluation.dao.EvaluationDao;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.jphs.order.dao.OrderDao;
import com.jinpaihushi.jphs.order.dao.OrderGoodsDao;
import com.jinpaihushi.jphs.order.model.Order;
import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 14:43:42
 * @version 1.0
 */
@Service("evaluationService")
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation> implements EvaluationService{

	@Autowired
	private EvaluationDao evaluationDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	
    @Autowired
    private PlatformTransactionManager txManager;
    
	@Override
	protected BaseDao<Evaluation> getDao(){
		return evaluationDao;
	}

	public int insertEvaluation(Evaluation evaluation){
		 TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
	        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
	            // 事务模板
	            public String doInTransaction(final TransactionStatus status) {
	                try {
	                	
	                	Order order = orderDao.loadById(evaluation.getOrderId());
	                	if(order == null){
	                		return "2";
	                	}
	                	if(order.getSchedule() != 4){
	                		return "3";
	                	}
	                	
	                	OrderGoods orderGoods = new OrderGoods();
	                	orderGoods.setOrderId(order.getId());
	                	OrderGoods orderGoodsOne = orderGoodsDao.load(orderGoods);
	                	if(orderGoodsOne == null){
	                		return "4";
	                	}
	                	evaluation.setId(UUIDUtils.getId());
	                	evaluation.setStatus(0);
	                	evaluation.setGoodsId(orderGoods.getGoodsId());
	                	evaluation.setNurseId(order.getAcceptUserId());
	                	int ed = evaluationDao.insert(evaluation);
	                	if(ed <=0){
	                		status.setRollbackOnly();// 回滚
	                		return "5";
	                	}
	                	
	                	order.setSchedule(5);
	                	int o = orderDao.update(order);
	                	if(o<=0){
	                		status.setRollbackOnly();// 回滚
	                		return "6";
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
		return Integer.parseInt(rs);
	}
	
	@Override
	public Page<Evaluation> getInfo(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Page<Evaluation> list = evaluationDao.getInfo(evaluation);
		return list;
	}

	@Override
	public Page<Evaluation> getList(Evaluation evaluation) {
		// TODO Auto-generated method stub
		return evaluationDao.getList(evaluation);
	}

	@Override
	public Integer getGoodLevel(String goodsId) {
		int i=evaluationDao.getGoodsLevel(goodsId);
		return i;
	}

	@Override
	public List<Evaluation> listInfo(Evaluation evaluation) {
		return evaluationDao.listInfo(evaluation);
	}
	
}