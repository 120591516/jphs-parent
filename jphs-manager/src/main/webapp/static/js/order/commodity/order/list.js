
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
})


$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/commodity/order/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#schedule").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/commodity/order/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/commodity/order/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/commodity/order/delete.jhtml?id=" + id;
}

function fh(id){
	$("#commodityOrderId").val(id);
	$("#no").val("");
}
function addLogistics(id){
	
	var no = $("#"+id).val();
	var logisticsId = $("#logisticsId").val();
	 
	 
	if(no != "" && logisticsId != ""){
		if(confirm("确定添加吗？")){
			$.ajax({
			 	type: "get",
				url: "/commodity/order/addLogistics.json",
				data:$('#logistics').serialize(),
				dataType: "json",
				success: function(data) {
					window.location.reload();
				}
		 }); 
		}	
	}
}


function getExcel(){
	
	if(yzCode($("#verificattionCode").val())){
		$('#serach-form').attr('action','/commodity/order/getExcel.jhtml');
		$('#serach-form').submit();
		
		$('#serach-form').attr('action','/commodity/order/index.jhtml');
	}else{
		alert("验证码错误");
	}
}
