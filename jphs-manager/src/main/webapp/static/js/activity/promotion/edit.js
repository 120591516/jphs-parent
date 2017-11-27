$(function() {
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
		format: 'yyyy-mm-dd hh:ii:ss'
	});
	
	var resourceTypes = $('#resourceTypes').val();
	if(resourceTypes == 2){
		$('#resourceTypes').val(2);
		$('.product_class').hide();//隐藏div  
		getCommodityList();
	}else if(resourceTypes == 1){
		$('#resourceTypes').val(1);
		getProductList();
	}else{
		$('#resourceTypes').val(3);
		$('.product_class').hide();//隐藏div  
		getPackageList();
	}
	getPlatformList();
	getSiteList();
	//checked
	if($('#resourceIds').val() != null && $('#resourceIds').val() != '' ){
		if($('#resourceTypes').val() == 1){
			$.ajax({
			 	type: "get",
				url: "/activity/promotion/getIfGoodsIdPriceList.json",
				data: {
					id:$('#resourceIds').val(),
					type: 1,
				},
				dataType: "json",
				success: function(data) {
					var priceList = '';
					$.each(data.price, function(i, item) {
						if(item.goodsId == $('#resourceIds').val()){
							$("#productTypes").val(item.productId);
							$("#productType").find("option[id='product"+item.productId+"']").attr("selected",true); 
						}
						var priceIds_arr = $('#priceIds').val().split(',');
						
						var checkedasd = "";
						
						for(var a =0;a<priceIds_arr.length ;a++){
							if(priceIds_arr[a] == item.id){
								checkedasd= "checked='checked'";
								break;
							}
						}
						priceList+='<label class="checkbox inline" > <input type="checkbox" '+checkedasd+' name="priceId" value="'+item.id+'">'+item.title+'</label>';
					});
					$("#priceList").html(priceList);
				}
			});
			
			 $.ajax({
				 	type: "get",
					url: "/goods/getIfProductGoodsList.json",
					data: {
						id:$("#productTypes").val(),
					},
					dataType: "json",
					success: function(data) {
						var goodsHtml = '<option value="0" id="goods0" >请选择服务/商品/套餐</option>';
						$.each(data.goods, function(i, item) {
							if($('#resourceIds').val() == item.id){
								goodsHtml+='<option value="'+item.id+'" id="goods'+item.id+'" selected="selected" >'+item.title+'</option>';
							}else{
								goodsHtml+='<option value="'+item.id+'" id="goods'+item.id+'" >'+item.title+'</option>';
							}
						});
						$("#resourceId").html(goodsHtml);
					}
			 });
			
		}else{
			 $.ajax({
				 	type: "get",
					url: "/activity/promotion/getIfGoodsIdPriceList.json",
					data: {
						id:$('#resourceIds').val(),
						type: 2,
					},
					dataType: "json",
					success: function(data) {
						var priceList = '';
						$.each(data.price, function(i, item) {
							var priceIds_arr = $('#priceIds').val().split(',');
							
							var checkedasd = "";
							
							for(var a =0;a<priceIds_arr.length ;a++){
								if(priceIds_arr[a] == item.id){
									checkedasd= "checked='checked'";
									break;
								}
							}
							
							priceList+='<label class="checkbox inline" > <input type="checkbox" name="priceId" '+checkedasd+' value="'+item.id+'">'+item.title+'</label>';
						});
						$("#priceList").html(priceList);
					}
			 });
		}
	}
	
})

/**
 * 选择服务、商品
 * @param type
 */
function getGoodsShop(type){
	var types=type.options[type.selectedIndex];
	$("#productType").html("");
	$("#resourceId").html("");
	$("#priceList").html("");
	if(types.value == 1){
		$('#resourceTypes').val(1);
		$(".product_class").show();//显示div  
		getProductList();
	}
	if(types.value == 2){
		$('#resourceTypes').val(2);
		$('.product_class').hide();//隐藏div  
		getCommodityList();
	}
	if(types.value == 3){
		$('#resourceTypes').val(3);
		$('.product_class').hide();//隐藏div  
		getPackageList();
	}
}

/**
 * 获取商品
 */
function getCommodityList(){
	 $.ajax({
		 	type: "get",
			url: "/commodity/getAllCtList.json",
			data: {},
			dataType: "json",
			success: function(data) {
				var commodityHtml = '<option value="0" id="commodity0" >请选择服务/商品/套餐</option>';
				$.each(data.commodity, function(i, item) {
					if($('#resourceIds').val() == item.id){
						commodityHtml+='<option value="'+item.id+'" selected="selected" id="commodity'+item.id+'" >'+item.title+'</option>';
					}else{
						commodityHtml+='<option value="'+item.id+'" id="commodity'+item.id+'" >'+item.title+'</option>';
					} 
				});
				$("#resourceId").html(commodityHtml);
			}
	 });
	 if($('#resourceIds').val() != null && $('#resourceIds').val() != '' ){
			if($('#resourceTypes').val() == 1){
			}else{
				 $.ajax({
					 	type: "get",
						url: "/activity/promotion/getIfGoodsIdPriceList.json",
						data: {
							id:$('#resourceIds').val(),
							type: 2,
						},
						dataType: "json",
						success: function(data) {
							var priceList = '';
							$.each(data.price, function(i, item) {
								var priceIds_arr = $('#priceIds').val().split(',');
								
								var checkedasd = "";
								
								for(var a =0;a<priceIds_arr.length ;a++){
									if(priceIds_arr[a] == item.id){
										checkedasd= "checked='checked'";
										break;
									}
								}
								
								priceList+='<label class="checkbox inline" > <input type="checkbox" name="priceId" '+checkedasd+' value="'+item.id+'">'+item.title+'</label>';
							});
							$("#priceList").html(priceList);
						}
				 });
			}
			}
	 
}

/**
 * 获取服务品类
 */
function getProductList(){
	 $.ajax({
		 	type: "get",
			url: "/product/getProductAllList.json",
			data: {},
			dataType: "json",
			success: function(data) {
				var productHtml='<option value="0" id="product0" >请选择品类</option>';
				$.each(data.product, function(i, item) {
					productHtml+='<option value="'+item.id+'" id="product'+item.id+'" >'+item.title+'</option>';
				});
				$("#productType").html(productHtml);
			}
	 });
	 if($('#resourceIds').val() != null && $('#resourceIds').val() != '' ){
			if($('#resourceTypes').val() == 1){
				$.ajax({
				 	type: "get",
					url: "/activity/promotion/getIfGoodsIdPriceList.json",
					data: {
						id:$('#resourceIds').val(),
						type: 1,
					},
					dataType: "json",
					success: function(data) {
						var priceList = '';
						$.each(data.price, function(i, item) {
							if(item.goodsId == $('#resourceIds').val()){
								$("#productTypes").val(item.productId);
								$("#productType").find("option[id='product"+item.productId+"']").attr("selected",true); 
							}
							var priceIds_arr = $('#priceIds').val().split(',');
							
							var checkedasd = "";
							
							for(var a =0;a<priceIds_arr.length ;a++){
								if(priceIds_arr[a] == item.id){
									checkedasd= "checked='checked'";
									break;
								}
							}
							priceList+='<label class="checkbox inline" > <input type="checkbox" name="priceId" '+checkedasd+' value="'+item.id+'">'+item.title+'</label>';
						});
						$("#priceList").html(priceList);
					}
				});
				
				 $.ajax({
					 	type: "get",
						url: "/goods/getIfProductGoodsList.json",
						data: {
							id:$("#productTypes").val(),
						},
						dataType: "json",
						success: function(data) {
							var goodsHtml = '<option value="0" id="goods0" >请选择服务/商品/套餐</option>';
							$.each(data.goods, function(i, item) {
								if($('#resourceIds').val() == item.id){
									goodsHtml+='<option value="'+item.id+'" id="goods'+item.id+'" selected="selected" >'+item.title+'</option>';
								}else{
									goodsHtml+='<option value="'+item.id+'" id="goods'+item.id+'" >'+item.title+'</option>';
								}
							});
							$("#resourceId").html(goodsHtml);
						}
				 });
				
			}else{
			}
		}
}
/**
 * 获取商品
 * @param productId
 */
function getIfGoodsasd(productId){
		$("#resourceId").html("");
		$("#priceList").html("");
	 var productIds=productId.options[productId.selectedIndex];    
	 $.ajax({
		 	type: "get",
			url: "/goods/getIfProductGoodsList.json",
			data: {
				id:productIds.value,
			},
			dataType: "json",
			success: function(data) {
				var goodsHtml = '<option value="0" id="goods0" >请选择服务/商品/套餐</option>';
				$.each(data.goods, function(i, item) {
					goodsHtml+='<option value="'+item.id+'" id="goods'+item.id+'" >'+item.title+'</option>';
				});
				$("#resourceId").html(goodsHtml);
			}
	 });
}
/**
 * 获取商品或者服务规格
 * @param goodsId
 */
function getIfPriceasd(goodsId){
	$("#priceList").html("");
	var resourceTypes = $('#resourceTypes').val();
	 var goodsIds=goodsId.options[goodsId.selectedIndex];    
	 $.ajax({
		 	type: "get",
			url: "/activity/promotion/getIfGoodsIdPriceList.json",
			data: {
				id:goodsIds.value,
				type: resourceTypes,
			},
			dataType: "json",
			success: function(data) {
				var priceList = '';
				$.each(data.price, function(i, item) {
					priceList+='<label class="checkbox inline" > <input type="checkbox" name="priceId" value="'+item.id+'">'+item.title+'</label>';
				});
				$("#priceList").html(priceList);
			}
	 });
}

/**
 * 获取平台
 */
function getPlatformList(){
	 $.ajax({
		 	type: "get",
			url: "/platform/getPlatformAllList.json",
			data: {},
			dataType: "json",
			success: function(data) {
				var platformHtml = '<option value="0" id="platform0" >请选择平台</option>';
				var platformIds = $('#platformIds').val();
				$.each(data.platform, function(i, item) {
					if(platformIds == item.id){
						platformHtml+='<option value="'+item.id+'" id="platform'+item.id+'" selected="selected" >'+item.name+' / '+item.company+'</option>';
					}else{
						platformHtml+='<option value="'+item.id+'" id="platform'+item.id+'" >'+item.name+' / '+item.company+'</option>';
					}
				});
				$("#platformId").html(platformHtml);
			}
	 });
}

/**
 * 获取站点
 */
function getSiteList(){
	 $.ajax({
		 	type: "get",
			url: "/site/getSiteAllList.json",
			data: {},
			dataType: "json",
			success: function(data) {
				var siteHtml = '<option value="0" id="platform0" >请选择站点</option>';
				var siteIds = $('#siteIds').val();
				$.each(data.site, function(i, item) {
					if(siteIds == item.id){
						siteHtml+='<option value="'+item.id+'" id="platform'+item.id+'" selected="selected" >'+item.name+' / '+item.company+'</option>';
					}else{
						siteHtml+='<option value="'+item.id+'" id="platform'+item.id+'" >'+item.name+' / '+item.company+'</option>';
					}
				});
				$("#siteId").html(siteHtml);
			}
	 });
}
/**
 * 获取商品
 */
function getPackageList(){
	 $.ajax({
		 	type: "get",
			url: "/jkwy/package/getPackageList.json",
			data: {},
			dataType: "json",
			success: function(data) {
				var commodityHtml = '<option value="0" id="jkwyPackage0" >请选择服务/商品/套餐</option>';
				console.info(data.jkwyPackage);
				$.each(data.jkwyPackage, function(i, item) {
					if($('#resourceIds').val() == item.id){
						commodityHtml+='<option value="'+item.id+'" selected="selected" id="jkwyPackage'+item.id+'" >'+item.title+'</option>';
					}else{
						commodityHtml+='<option value="'+item.id+'" id="jkwyPackage'+item.id+'" >'+item.title+'</option>';
					} 
				});
				
				$("#resourceId").html(commodityHtml);
			}
	 });
	 if($('#resourceIds').val() != null && $('#resourceIds').val() != '' ){
			if($('#resourceTypes').val() == 3){
				 $.ajax({
					 	type: "get",
						url: "/activity/promotion/getIfGoodsIdPriceList.json",
						data: {
							id:$('#resourceIds').val(),
							type: 3,
						},
						dataType: "json",
						success: function(data) {
							var priceList = '';
							$.each(data.price, function(i, item) {
								var priceIds_arr = $('#priceIds').val().split(',');
								
								var checkedasd = "";
								
								for(var a =0;a<priceIds_arr.length ;a++){
									if(priceIds_arr[a] == item.id){
										checkedasd= "checked='checked'";
										break;
									}
								}
								
								priceList+='<label class="checkbox inline" > <input type="checkbox" name="priceId" '+checkedasd+' value="'+item.id+'">'+item.title+'</label>';
							});
							$("#priceList").html(priceList);
						}
				 });
			}
			}
	 
}
