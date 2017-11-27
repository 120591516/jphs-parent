package com.jinpaihushi.jphs.share.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.share.model.ShareStatistics;
import com.jinpaihushi.jphs.share.service.ShareStatisticsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author scj
 * @date 2017-11-20 15:23:08
 * @version 1.0
 */
@Controller
@RequestMapping(name = "ShareStatistics", path = "/share/statistics")
public class ShareStatisticsController extends BaseController<ShareStatistics> {

	@Autowired
	private ShareStatisticsService shareStatisticsService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private UserService userService;
	@Override
	protected BaseService<ShareStatistics> getService() {
		return shareStatisticsService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			ShareStatistics shareStatistics, Integer p, Integer n) {
		startPage(p, n);
		Page<ShareStatistics> list = shareStatisticsService.query(shareStatistics);
		for(int i=0;i<list.size();i++){
			
			if(list.get(i).getType() == 1){
				Goods goods = goodsService.loadById(list.get(i).getGoodsId());
				if(goods != null){
					list.get(i).setGoodsId(goods.getTitle());
				}
			}
			if(list.get(i).getType() == 1){
				Commodity commodity = commodityService.loadById(list.get(i).getGoodsId());
				if(commodity != null){
					list.get(i).setGoodsId(commodity.getTitle());
				}
			}
		}
		
		
		PageInfos<ShareStatistics> pageInfo = new PageInfos<ShareStatistics>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "share/statistics/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		ShareStatistics shareStatistics = shareStatisticsService.loadById(id);
		modelMap.put("shareStatistics", shareStatistics);
		return "share/statistics/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "share/statistics/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		ShareStatistics shareStatistics = shareStatisticsService.loadById(id);
		if(shareStatistics.getType() == 1){
			Goods goods = goodsService.loadById(shareStatistics.getGoodsId());
			if(goods != null){
				shareStatistics.setGoodsId(goods.getTitle());
			}
		}
		if(shareStatistics.getType() == 1){
			Commodity commodity = commodityService.loadById(shareStatistics.getGoodsId());
			if(commodity != null){
				shareStatistics.setGoodsId(commodity.getTitle());
			}
		}
		if(!StringUtils.isEmpty(shareStatistics.getCreatorId())){
			User user = userService.loadById(shareStatistics.getCreatorId());
			if(user!=null){
				shareStatistics.setCreatorName(user.getName());
			}
		}
		modelMap.put("shareStatistics", shareStatistics);
		return "share/statistics/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, ShareStatistics shareStatistics) {

		if (shareStatistics.getId() != null && !shareStatistics.getId().equals("")) {
			boolean b = shareStatisticsService.update(shareStatistics);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/share/statistics/err.jhtml";
			}
		} else {
			shareStatistics.setId(UUID.randomUUID().toString());
			String result = shareStatisticsService.insert(shareStatistics);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/share/statistics/err.jhtml";
			}
		}
		return "redirect:/share/statistics/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = shareStatisticsService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/share/statistics/err.jhtml";
		}

		return "redirect:/share/statistics/index.jhtml";
	}
	
	

}
