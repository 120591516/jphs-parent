package com.jinpaihushi.jphs.family.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.family.model.FamilyCard;
import com.jinpaihushi.jphs.family.service.FamilyCardService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-10-16 17:03:56
 * @version 1.0
 */
@Controller
@RequestMapping(name = "FamilyCard", path = "/family/card")
public class FamilyCardController extends BaseController<FamilyCard> {

	@Autowired
	private FamilyCardService familyCardService;

	@Override
	protected BaseService<FamilyCard> getService() {
		return familyCardService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			FamilyCard familyCard, Integer p, Integer n) {
		startPage(p, n);
		/*Page<FamilyCard> list = familyCardService.query(familyCard);
		PageInfos<FamilyCard> pageInfo = new PageInfos<FamilyCard>(list, req);*/
		Page<Map<String,Object>> list = familyCardService.familyCardIndex(familyCard);
		PageInfos<Map<String,Object>> pageInfo = new PageInfos<Map<String,Object>>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "family/card/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		FamilyCard familyCard = familyCardService.loadById(id);
		modelMap.put("familyCard", familyCard);
		return "family/card/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "family/card/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			FamilyCard familyCard, Integer p, Integer n) {
		List<Map<String,Object>> familyCards = familyCardService.familyCardDetail(familyCard);
		startPage(p, n);
		Page<FamilyCard> list = familyCardService.query(familyCard);
		PageInfos<FamilyCard> pageInfo = new PageInfos<FamilyCard>(list, req);
		
		modelMap.put("familyCard", familyCards.get(0));
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		
		return "family/card/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, FamilyCard familyCard,String cardNumber) {

		if (familyCard.getId() != null && !familyCard.getId().equals("")) {
			boolean b = familyCardService.update(familyCard);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/family/card/err.jhtml";
			}
		} else {
			int cardNumbers = 0;
			try {
                SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
                familyCard.setCreatorName(systemUser.getName());
                cardNumbers = Integer.parseInt(cardNumber);
            }
            catch (Exception e) {
            	return "redirect:/family/card/err.jhtml";
            }
			int	result = familyCardService.putAll(familyCard,cardNumbers);
			if (result <= 0) {
				// 跳转到错误页
				return "redirect:/family/card/err.jhtml";
			}
		}
		return "redirect:/family/card/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = familyCardService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/family/card/err.jhtml";
		}

		return "redirect:/family/card/index.jhtml";
	}
	
	

}
