package com.jinpaihushi.jphs.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
