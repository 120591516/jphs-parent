
$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
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
				url: "/commodity/order/addLogistics.jhtml",
				data:$('#logistics').serialize(),
				dataType: "json",
				success: function(data) {
					location.reload();
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
