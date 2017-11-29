package com.jinpaihushi.jphs.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(name = "订单评价", path = "/order/evaluation")
public class OrderEvaluationController {

	@Autowired
	private EvaluationService evaluationService;
	
	@ResponseBody
	@RequestMapping(name="查询评价记录",path = "/list.json")
	public byte[] list(HttpServletRequest req, HttpServletResponse resp,Evaluation evaluation,Integer p){
		try{
			// 记录日志-debug
	        if (Util.debugLog.isDebugEnabled()) {
	            Util.debugLog.debug("order.evaluation.list.json, getCreatorId=" + evaluation.getCreatorId() + " getNurseId=" + evaluation.getNurseId()
	                    + " getLevel=" + evaluation.getLevel() );
	        }
	        if(StringUtils.isEmpty(evaluation.getNurseId())){
				  return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
	        if (p == null)
                p = 1;
	        evaluation.setStatus(0);
	        evaluation.setOrderby("E.create_time DESC");
	        PageHelper.startPage(p, 10);
	        List<Evaluation> evaluationList = evaluationService.list(evaluation);
	        PageInfo<Evaluation> list = new PageInfo<Evaluation>(evaluationList);
	               
	        return JSONUtil.toJSONResult(1, "查询成功", list);
		} catch (Exception e) {
			// 记录日志-fail
            Util.failLog.error("order.evaluation.list.json,  getCreatorId=" + evaluation.getCreatorId() + " getNurseId=" + evaluation.getNurseId()
                        + " getLevel=" + evaluation.getLevel() , e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name="查询评价数量",path = "/evaluationCount.json")
	public byte[] count(HttpServletRequest req, HttpServletResponse resp,Evaluation evaluation){
		try{
			// 记录日志-debug
	        if (Util.debugLog.isDebugEnabled()) {
	            Util.debugLog.debug("order.evaluation.evaluationCount.json,  getNurseId=" + evaluation.getNurseId());
	        }
	        if(StringUtils.isEmpty(evaluation.getNurseId())){
				  return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
	        Evaluation evaluationCount= evaluationService.getListCountLevel(evaluation);	
	        return JSONUtil.toJSONResult(1, "查询成功", evaluationCount);
		} catch (Exception e) {
			// 记录日志-fail
            Util.failLog.error("order.evaluation.evaluationCount.json,  getNurseId=" + evaluation.getNurseId(), e);
		}
		return null;
	}
	
	/**
	 * 完成服务，添加评价记录
	 * @param req
	 * @param resp
	 * @param evaluation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="添加评论",path="/insert.json")
	public byte[] insert(HttpServletRequest req, HttpServletResponse resp,Evaluation evaluation){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("order.evaluation.insert.json, OrderId=" + evaluation.getOrderId() + " getLevel=" + evaluation.getLevel()
                        + " getContent=" + evaluation.getContent() );
            }
			if(StringUtils.isEmpty(evaluation.getOrderId()) ||
					StringUtils.isEmpty(evaluation.getContent()) || 
						StringUtils.isEmpty(evaluation.getCreatorId()) || 
							evaluation.getLevel() == null){
				  return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
            
			int rs = evaluationService.insertEvaluation(evaluation);
			if(rs == 0){
				return JSONUtil.toJSONResult(0, "数据异常", null);
			}else if( rs == 2){
				return JSONUtil.toJSONResult(0, "根据订单id查询不到订单数据，请检查订单id是否正确", null);
			}else if( rs == 3){
				return JSONUtil.toJSONResult(0, "订单状态不正确", null);
			}else if( rs == 4){
				return JSONUtil.toJSONResult(0, "查询商品信息失败", null);
			}else if( rs == 5){
				return JSONUtil.toJSONResult(0, "插入评价信息失败", null);
			}else if( rs == 6){
				return JSONUtil.toJSONResult(0, "修改订单状态失败", null);
			}
			
            return JSONUtil.toJSONResult(1, "添加成功", null);
		} catch (Exception e) {
			 // 记录日志-fail
            Util.failLog.error("order.evaluation.insert.json,  OrderId=" + evaluation.getOrderId() + " getLevel=" + evaluation.getLevel()
                        + " getContent=" + evaluation.getContent() , e);
		}
		return null;
	}
	
}
