package com.jinpaihushi.jphs.voucher.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherRepertoryService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.commodity.model.CommodityTreeNode;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.business.service.BusinessService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

import org.springframework.web.multipart.MultipartFile;
import com.jinpaihushi.util.ExcelUtil;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Controller
@RequestMapping(name = "优惠券", path = "/voucher")
public class VoucherController extends BaseController<Voucher> {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherRepertoryService voucherRepertoryService;

    @Autowired
    private VoucherUseService voucherUseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private UserService userService;

	@Autowired
    private CommodityService commodityService;

	@Autowired
	private BusinessService businessService;


    @Override
    protected BaseService<Voucher> getService() {
        return voucherService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Voucher voucher, String comId,String busId,String Ctype ,Integer p, Integer n) {
		String typeFlag = voucher.getProductType();	

		 
			
		if(!("1".equals(voucher.getProductType()))){
			 
			voucher.setProductType(Ctype);
			if("3".equals(Ctype)){
			if(busId != null){
				voucher.setProductId(busId);
			}
			 
			voucher.setProductType("2");
		}
		
		if("2".equals(Ctype)){
		 if(comId != null){
		 	voucher.setProductId(comId);
		 }
		}

		if("4".equals(Ctype)){
			if(busId != null){
				voucher.setProductId("T");
			}
			 
			voucher.setProductType("2");
		}
			
		}else{
			voucher.setSupportType("");
		}
	 
		
		 
        //Page<Voucher> list = voucherService.query(voucher);

        Product productModel = new Product();
        productModel.setStatus(1);

		String productIdRecord = voucher.getProductId();
		if(voucher.getGoodsId()!= null){
			voucher.setProductId("");
		}

        Page<Product> productList = productService.query(productModel);

        Goods goodsModel = new Goods();
			
        if (voucher != null && voucher.getGoodsId() != null) {
            goodsModel.setProductId(productIdRecord);
            goodsModel.setStatus(1);
            Page<Goods> goodsList = goodsService.query(goodsModel);
            modelMap.put("goodsList", goodsList);
        }
        else {
            modelMap.put("goodsList", null);
        }

        startPage(p, n);
        voucher.setOrderby("create_time DESC");
        Page<Voucher> list = voucherService.getList(voucher);
		voucher.setProductId(productIdRecord);
        for (int i = 0; i < list.size(); i++) {
            String productName = "";
            String goodsName = "";
			if(list.get(i).getProductType() != "2"){
				String productIds = list.get(i).getProductId();

				if (productIds != null) {
                String[] productId = productIds.split(",");
                for (int j = 0; j < productId.length; j++) {
                    Product product = productService.loadById(productId[j]);
                    if (product != null) { 
                        productName += "," + product.getTitle();
                    }else{
						Commodity com = commodityService.loadById(productId[j]);
						if(com!=null){
							 
							if(com.getTitle()!=null){
								 productName += ","+com.getTitle();
							}
						}else{
							Business bus = businessService.loadById(productId[j]);
							if(bus!= null){
								 
								if(bus.getName() != null){
									productName += ","+bus.getName();
								}
							}
						}
					}
                }
				 
				if(productName!=""){
					list.get(i).setProductName(productName.substring(1));
				} 
            }
			}
            String goodsIds = list.get(i).getGoodsId();

            

            if (goodsIds != null) {
                String[] goodsId = goodsIds.split(",");
                for (int j = 0; j < goodsId.length; j++) {
                    Goods goods = goodsService.loadById(goodsId[j]);
                    if (goods != null) {
                        goodsName += "," + goods.getTitle();
                    }
                }
                list.get(i).setGoodsName(goodsName.substring(1));
            }
        }

        PageInfos<Voucher> pageInfo = new PageInfos<Voucher>(list, req);

		Business busModel = new Business();
		busModel.setStatus(1);
		List<Business> busList= businessService.list(busModel);
		Commodity comModel = new Commodity();
		comModel.setStatus(1);
		List<Commodity> comList= commodityService.list(comModel);
		voucher.setProductType(typeFlag);
		modelMap.put("busList",busList);
		modelMap.put("comList",comList);
        modelMap.put("list", list);
		modelMap.put("comId",comId);
		modelMap.put("busId",busId);
		modelMap.put("Ctype",Ctype);
        modelMap.put("productList", productList);

        modelMap.put("pageInfo", pageInfo);
		 
        return "activity/voucher/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Voucher voucher = voucherService.loadById(id);
        modelMap.put("voucher", voucher);
        return "activity/voucher/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        /*Page<Product> list = productService.query(new Product());
        modelMap.put("list", list);*/
        JSONObject message = new JSONObject();
        List<TreeNode> list = platformService.getGoodsList(null);
		List<CommodityTreeNode> shopList = commodityService.getTreeNode(null);
		List<CommodityTreeNode> busList = businessService.getTreeNode(null);
        List<Site> site = platformService.getSelectSite(null);
		List<CommodityTreeNode> noList = new ArrayList<CommodityTreeNode>();
		message.put("shopList", shopList);
        message.put("treeData", list);
		message.put("busList", busList);
		message.put("noList", noList);
        modelMap.put("data", message);
        modelMap.put("site", site);
        return "activity/voucher/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id,
            Integer p, Integer n) {

        Voucher voucherModel = new Voucher();
        VoucherRepertory voucherRepertory = new VoucherRepertory();
        VoucherUse voucherUse = new VoucherUse();
        voucherModel.setId(id);
        Voucher voucher = voucherService.getList(voucherModel).get(0);

        String productName = "";
        String productIds = voucher.getProductId();
      
		if(voucher.getProductType() != "2"){
				 
				if (productIds != null) {
                String[] productId = productIds.split(",");
                for (int j = 0; j < productId.length; j++) {
                    Product product = productService.loadById(productId[j]);
                    if (product != null) { 
                        productName += "," + product.getTitle();
                    }else{
						Commodity com = commodityService.loadById(productId[j]);
						if(com!=null){
							 
							if(com.getTitle()!=null){
								 productName += ","+com.getTitle();
							}
						}else{
							Business bus = businessService.loadById(productId[j]);
							if(bus!= null){
								 
								if(bus.getName() != null){
									productName += ","+bus.getName();
								}
							}
						}
					}
                }
				 
				if(productName!=""){
					voucher.setProductName(productName.substring(1));
				} 
            }
		
		}
				
        String goodsName = "";
        String goodsIds = voucher.getGoodsId();
        if (goodsIds != null) {
            String[] goodsId = goodsIds.split(",");
            for (int j = 0; j < goodsId.length; j++) {
                Goods goods = goodsService.loadById(goodsId[j]);
                if (goods != null) {
                    goodsName += "," + goods.getTitle();
                }
            }
            voucher.setGoodsName(goodsName.substring(1));
        }

        voucherRepertory.setVoucherId(voucher.getId());
        Integer count = voucherRepertoryService.count(voucherRepertory);
        Page<VoucherRepertory> voucherRepertorylist = voucherRepertoryService.query(voucherRepertory);
        startPage(p, n);
        Page<VoucherUse> list = voucherUseService.getDetailList(id);

        PageInfos<VoucherUse> pageInfo = new PageInfos<VoucherUse>(list, req);
        modelMap.put("voucher", voucher);
        modelMap.put("count", count);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "activity/voucher/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Voucher voucher, Integer count, Double CAmount, Double DAmount) {
		
		System.out.println("Hello World!开始测试数据");
		System.out.println("Hello World!开始测试数据 == productType："+voucher.getProductType() == "1");
		System.out.println("Hello World!开始测试数据 == supportType："+voucher.getSupportType());

        if (voucher.getType() != null) {
            String id = UUID.randomUUID().toString();
            SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
            voucher.setId(id);

            voucher.setCreateTime(new Date());
            voucher.setCreatorId(systemUser.getId());
            voucher.setCreatorName(systemUser.getName());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String batchNo = sdf.format(new Date());

            //优惠券批次号生成规则
			if(voucher.getProductType() == "1"){
				if (voucher.getType() == 1) {
					//现金券批次号生成规则
					voucher.setBatchNo("XJS" + batchNo);
				}else if (voucher.getType() == 2) {
					//满减券批次号生成规则
					voucher.setBatchNo("MJS" + batchNo);
				}else if (voucher.getType() == 3) {
					//折扣券批次号生成规则
					voucher.setBatchNo("ZKS" + batchNo);
				}
			}else{
				if (voucher.getType() == 1) {
					//现金券批次号生成规则
					voucher.setBatchNo("XJC" + batchNo);
					if("3".equals(voucher.getSupportType())){
						voucher.setProductId("T");
					}
				}else if (voucher.getType() == 2) {
					//满减券批次号生成规则
					voucher.setBatchNo("MJC" + batchNo);
					if("3".equals(voucher.getSupportType())){
						voucher.setProductId("T");
					}
				}else if (voucher.getType() == 3) {
					//折扣券批次号生成规则
					voucher.setBatchNo("ZKC" + batchNo);
					if("3".equals(voucher.getSupportType())){
						voucher.setProductId("T");
					}
					
				}
			}
            String result = voucherService.insert(voucher);

            VoucherRepertory voucherRepertory = new VoucherRepertory();
            for (int i = 0; i < count; i++) {

                if (voucher.getType() == 1) {
                    voucherRepertory.setAmount(voucher.getAmount());
                }
                else if (voucher.getType() == 2) {
                    voucherRepertory.setAmount(CAmount);
                }
                else if (voucher.getType() == 3) {
                    voucherRepertory.setAmount(DAmount);
                }

                voucherRepertory.setId(UUID.randomUUID().toString());
                voucherRepertory.setVoucherId(id);

                voucherRepertory.setNo(voucher.getBatchNo() + i);

                voucherRepertory.setConditionAmount(voucher.getConditionAmount());
                voucherRepertory.setDiscountAmount(voucher.getDiscountAmount());
                voucherRepertory.setCode(getStringRandom(8));
                voucherRepertoryService.insert(voucherRepertory);
            }

            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/voucher/err.jhtml";
            }
        }
        return "redirect:/voucher/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = voucherService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/voucher/err.jhtml";
        }

        return "redirect:/voucher/index.jhtml";
    }

    @RequestMapping(name = "查询商品", path = "/getGoodsList.jhtml")
    @ResponseBody
    public JSONObject getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        JSONObject message = new JSONObject();
        Goods goods = new Goods();
        goods.setProductId(id);
        goods.setStatus(1);
        Page<Goods> goodsList = goodsService.query(goods);
        message.put("goodsList", goodsList);

        return message;
    }

    @RequestMapping(name = "查询用户", path = "/getUserList.jhtml")
    public ModelMap getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            User user, Integer p, Integer n) {
        user.setStatus(0);
        user.setType(1);

        Page<User> userList = userService.query(user);

        modelMap.put("userList", userList);

        return modelMap;
    }

    @RequestMapping(name = "添加优惠券使用", path = "/addVoucherUser.jhtml")
    @ResponseBody
    public JSONObject addVoucherUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, VoucherUse voucherUse, String voucherId) {

        SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
        Voucher voucher = voucherService.loadById(voucherId);
        voucherUse.setStartTime(voucher.getStartTime());
        voucherUse.setEndTime(voucher.getEndTime());
        voucherUse.setCreateTime(new Date());
        voucherUse.setStatus(0);
        voucherUse.setGrantName(systemUser.getName());
        JSONObject message = new JSONObject();
        String result = voucherUseService.addVoucherUser(voucherUse, voucherUse.getVoucherRepertoryId());
        message.put("result", voucher.getId());

        return message;
    }

    @RequestMapping(name = "跳转到添加页", path = "/addUser.jhtml")
    public String addUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherRepertory voucherRepertory, User user, Integer p, Integer n, String voucherRepertoryIds,
            String amount) {

        user.setStatus(1);
        user.setType(1);
        user.setId("");
        startPage(p, n);
        Page<User> userList = userService.query(user);
        PageInfos<User> pageInfo = new PageInfos<User>(userList, req);
        modelMap.put("userList", userList);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("user", user);
        modelMap.put("voucherRepertoryIds", voucherRepertoryIds);
        modelMap.put("amount", amount);
        return "activity/voucher/detail/addUser";
    }

    private String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            }
            else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

	
	@RequestMapping(name = "跳转到添加页", path = "/fileUpload.jhtml")
	@ResponseBody
    public JSONObject fileUpload(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
          @RequestParam MultipartFile myfiles) {
		
		 JSONObject message = new JSONObject();
		 String originalFilename = null;
		/*	for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				 
			} else {
				originalFilename = myfile.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				 
			}*/
			//List<String> strList = ExcelUtil.readExcel(2,myfiles);
			//message.put("strList",strList);
        return message;
    
	}


	@RequestMapping(name = "跳转到添加页", path = "/sendVoucher.json")
	@ResponseBody
    public JSONObject sendVoucher(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
         String phones,String voucherId) {
		
		 JSONObject message = new JSONObject();
		  
		 SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
		 String[] phoneArr = phones.split(",");

		  List<String> tmp = new ArrayList<String>();
        for(String str:phoneArr){
            if(str!=null && str.length()!=0){
                tmp.add(str.trim());
            }
        }
        phoneArr = tmp.toArray(new String[0]);
          


		 VoucherRepertory voucherRepertory = new VoucherRepertory();
		 voucherRepertory.setVoucherId(voucherId);
		  voucherRepertory.setStatus(0);
		 List<VoucherRepertory> vrList= voucherRepertoryService.list(voucherRepertory);
		  
		 
		if(phoneArr.length <= vrList.size()){
			int count = 0;
			VoucherUse voucherUse = new VoucherUse();
		
			 Voucher voucher = voucherService.loadById(voucherId);
			 voucherUse.setStartTime(voucher.getStartTime());
			 voucherUse.setEndTime(voucher.getEndTime());
			 voucherUse.setCreateTime(new Date());
			 voucherUse.setStatus(0);
			
			
			for (int i = 0; i<phoneArr.length;i++ ){
				User user = new User();
				user.setPhone(phoneArr[i]);
				user.setType(1);
				User u = userService.queryUser(user);
				
				 
				if(u!=null){
					System.out.println("vrList"+i+"："+vrList.get(i).getId());
					voucherUse.setId(UUID.randomUUID().toString());
					voucherUse.setVoucherRepertoryId(vrList.get(i).getId());
					voucherUse.setPhone(phoneArr[i]);
					voucherUse.setAmount(vrList.get(i).getAmount());
					voucherUse.setCreatorName(u.getName());
					voucherUse.setCreatorId(u.getId());
					voucherUse.setCreateTime(new Date());
					voucherUse.setGrantName(systemUser.getName());
					String result = voucherUseService.insert(voucherUse);
					VoucherRepertory voucherRepertoryModel = vrList.get(i);
					voucherRepertoryModel.setStatus(1);
					 
					if(result.length()>0){
						boolean b = voucherRepertoryService.update(voucherRepertoryModel);
						if(b == true){
							count++;
						}
					}	  
				}
			}
			message.put("result","成功发放"+count+"张优惠券");
			message.put("code",1);
		}else{
			message.put("result","优惠券数量不足");
			message.put("code",0);
		}
	 
        return message;
    
	}

}
