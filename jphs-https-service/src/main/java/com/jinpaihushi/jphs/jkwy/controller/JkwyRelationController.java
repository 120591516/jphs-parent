package com.jinpaihushi.jphs.jkwy.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.jkwy.model.JkwyRelation;
import com.jinpaihushi.jphs.jkwy.service.JkwyRelationService;
import com.jinpaihushi.model.ResultDTO;
import com.jinpaihushi.utils.CertificateNo;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(name = "用户亲属关系" ,path = "/jkwy/relation")
public class JkwyRelationController {

	@Autowired
	private JkwyRelationService jkwyRelationService;
	
	/**
	 * 查询亲属
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="查询" , path = "/query.json")
	public byte[] query(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,JkwyRelation jkwyRelation){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.relation.query.json");
            }
			if(StringUtils.isEmpty(jkwyRelation.getCreatorId())){
				return JSONUtil.toJSONResult(0, "参数不能为空！", null);
			}
			if(jkwyRelation.getId() == null){
				return JSONUtil.toJSONResult(1, "操作成功！", jkwyRelationService.getUserRelationOfOrder(jkwyRelation));
			}else{
				return JSONUtil.toJSONResult(1, "操作成功！", jkwyRelationService.getUserRelationOfOrder(jkwyRelation).get(0));
			}
		} catch (Exception e){
			Util.failLog.error("jkwy.relation.query.json", e);
		}
		return null;
	}
	
	/**
	 * 删除亲属关系
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="删除" , path = "/delete.json")
	public byte[] delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String id){
		try{
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("jkwy.relation.delete.json id="+id); 
			}
			if(StringUtils.isEmpty(id) || jkwyRelationService.loadById(id) == null){
				return JSONUtil.toJSONResult(0, "id不合法！", null);
			}
			if(jkwyRelationService.deleteById(id)){
				return JSONUtil.toJSONResult(0, "操作成功！",null);
			}
			return JSONUtil.toJSONResult(0, "操作失败",null);
		} catch (Exception e){
			Util.failLog.error("jkwy.relation.delete.json  id="+id);
		}
		return null;
	}
	
	/**
	 * 插入/更新 亲属关系
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name="插入/更新" , path = "/insert.json")
	public byte[] insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,JkwyRelation jkwyRelation){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("jkwy.relation.insert.json CreatorId="+jkwyRelation.getCreatorId()
                		+" name="+jkwyRelation.getName()
                		+" Phone="+jkwyRelation.getPhone()
                		+" Sex="+jkwyRelation.getSex()
                		+" Relation="+jkwyRelation.getRelation()
                		+" Sfz="+jkwyRelation.getSfz()
                		+" Birthday="+jkwyRelation.getBirthday());
            }
			if(StringUtils.isEmpty(jkwyRelation.getCreatorId()) || StringUtils.isEmpty(jkwyRelation.getName()) 
					|| StringUtils.isEmpty(jkwyRelation.getPhone())
					|| jkwyRelation.getSex() == null 
					|| StringUtils.isEmpty(jkwyRelation.getRelation())
					|| StringUtils.isEmpty(jkwyRelation.getSfz())){
				return JSONUtil.toJSONResult(0, "参数不能为空！", null);
			}
			if(jkwyRelation.getId() ==null ){
				jkwyRelation.setId(UUIDUtils.getId());
				jkwyRelation.setCreateTime(new Date());
				ResultDTO resultDTO = CertificateNo.parseCertificateNo(jkwyRelation.getSfz());
				if(resultDTO.getStatueMessage() != null){
					return JSONUtil.toJSONResult(0, "操作失败,"+resultDTO.getStatueMessage()+"!", null);
				}
				jkwyRelation.setBirthday(resultDTO.getBirthdayTime());
				String str_j = jkwyRelationService.insert(jkwyRelation);
				if(str_j ==null || str_j.length() < 1){
					return JSONUtil.toJSONResult(0, "操作失败！", null);
				}
			}else{
				int j_u = jkwyRelationService.updateRelation(jkwyRelation);
				if(j_u<1){
					if(j_u == -1)
					return JSONUtil.toJSONResult(0, "操作失败,该亲友购买过健康优护套餐，不能修改！", null);
					else
					return JSONUtil.toJSONResult(0, "操作失败！", null);
				}
			}
			
			return JSONUtil.toJSONResult(1, "操作成功！", null);
		} catch (Exception e){
			Util.failLog.error("jkwy.relation.insert.json  CreatorId="+jkwyRelation.getCreatorId()
                		+" name="+jkwyRelation.getName()
                		+" Phone="+jkwyRelation.getPhone()
                		+" Sex="+jkwyRelation.getSex()
                		+" Relation="+jkwyRelation.getRelation()
                		+" Sfz="+jkwyRelation.getSfz()
                		+" Birthday="+jkwyRelation.getBirthday(), e);
		}
		return null;
	}
	
	
}
