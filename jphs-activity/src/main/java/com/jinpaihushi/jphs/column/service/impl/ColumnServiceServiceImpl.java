package com.jinpaihushi.jphs.column.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.business.dao.BusinessDao;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.column.dao.ColumnServiceDao;
import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.product.dao.ProductDao;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.service.impl.BaseServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @version 1.0
 */
@Service("columnServiceService")
public class ColumnServiceServiceImpl extends BaseServiceImpl<ColumnService> implements ColumnServiceService{

	@Autowired
	private ColumnServiceDao columnServiceDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	private GoodsDao goodsDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private CommodityDao commodityDao;
	
	@Override
	protected BaseDao<ColumnService> getDao(){
		return columnServiceDao;
	}

	/**
	 * 根据type查询 服务或者商品分类
	 * @param type
	 * @param link
	 * @return
	 */
	public JSONObject selectListProduct(String type,String link){
		JSONObject re_obj = new JSONObject();
		
		JSONArray re_arr = new JSONArray();
		int types = Integer.parseInt(type); 
		if(types == 1){
			Product product = new Product();
			product.setStatus(1);
			product.setOrderby("P.create_time DESC");
			List<Product> productList = productDao.list(product);
				Goods goods = new Goods();
				goods.setStatus(1);
				goods.setOrderby("G.create_time DESC");
			for(int p=0;p<productList.size();p++){
				JSONObject one_p_obj = new JSONObject();
				Product productOne = productList.get(p);
				goods.setProductId(productOne.getId());
				List<Map<String ,Object>> goodsList = goodsDao.listGoodsColumn(goods);
				one_p_obj.put("pId", productOne.getId());
				one_p_obj.put("pTitle", productOne.getTitle());
				one_p_obj.put("goodsList",goodsList);
				re_arr.add(one_p_obj);
			}
			if(!StringUtils.isEmpty(link)){
				Goods link_goods = goodsDao.loadById(link);
				re_obj.put("p", link_goods.getProductId());
			}
		}else{
			Business business = new Business();
			business.setStatus(1);
			business.setOrderby("B.create_time DESC");
			List<Business> businessList = businessDao.list(business);
			Commodity commodity = new Commodity();
			commodity.setStatus(1);
			commodity.setOrderby("C.create_time DESC");
			for(int b=0;b<businessList.size();b++){
				JSONObject one_b_obj = new JSONObject();
				Business businessOne = businessList.get(b);
				commodity.setBusinessId(businessOne.getId());
				List<Map<String,Object>> commodityList = commodityDao.listCommodityColumn(commodity);
				one_b_obj.put("pId", businessOne.getId());
				one_b_obj.put("pTitle", businessOne.getName());
				one_b_obj.put("goodsList",commodityList);
				re_arr.add(one_b_obj);
			}
			
			if(!StringUtils.isEmpty(link)){
				Commodity link_goods = commodityDao.loadById(link);
				re_obj.put("p", link_goods.getBusinessId());
			}
		}
		re_obj.put("goodsList", re_arr);
		return re_obj;
	}
	
}