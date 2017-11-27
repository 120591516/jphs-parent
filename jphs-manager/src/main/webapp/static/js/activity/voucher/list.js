$(document).ready(function() {
	$('.form_date').datetimepicker({
		language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	
	$("#product").hide();
	$("#goods").hide();
	$("#com").hide();
	$("#bus").hide();
	var productType = $("#productTypeFlag").val();
	var CtypeValue = $("#CtypeValue").val();
	 var goodsId = $("#goodsId").val();
	if(productType != ""){
		if(productType != "1"){
			if(CtypeValue != ""){
				changeType(CtypeValue);
			}else{
				changeType(productType);
			}
		}else{
			changeType(productType);
			if(goodsId != ""){
				//change(goodsId);
			}
		}
	}
})

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/voucher/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").empty();
		$("#type").empty();
		$("#productId").empty();
		$("#goodsId").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/voucher/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/voucher/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/voucher/delete.jhtml?id=" + id;
}
function change(id){
	document.getElementById("goodsId").innerHTML=""; 
	 
	if(id != ""){
		$.ajax({
			type: "get",
			url: "/voucher/getGoodsList.jhtml",
			data: {id:id},
			dataType: "json",
			success: function(data) {
				var goodsList = data.goodsList;
				 var parentId = $("#productId").val() 
				$("#goodsId").append("<option value=''>请选择</option>");
				 for(var i = 0;i<goodsList.length;i++ ){
					 if(parentId == goodsList[i].productId){
						 $("#goodsId").append("<option value='"+goodsList[i].id+"' >"+goodsList[i].title+"</option>");
					 }
				
				 };
			}
		});
	}else{
		$("#goodsId").append("<option value=''>请选择</option>");
	}
	
}

function changeType(type){ 
	  
	if(type =="2"){
		
		$("#product").hide();
		$("#goods").hide();
		$("#com").show();
		$("#bus").hide();
		$("#CtypeTd").show();
		 
		$("#supportType").val(1);
		$("#goodsId").val("");
		 
	}else if(type =="1"){
		$("#product").show();
		$("#goods").show();
		$("#bus").hide();
		$("#com").hide();
		$("#CtypeTd").hide();
	}else if(type =="3"){
		 
		$("#product").hide();
		$("#goods").hide();
		$("#com").hide();
		$("#bus").show();
		$("#supportType").val(2);
		$("#goodsId").val("");
		 
	}else if(type =="4"){
		 
		$("#product").hide();
		$("#goods").hide();
		$("#com").hide();
		$("#bus").hide();
		$("#supportType").val(3);
		$("#goodsId").val("");
		 
	}
}