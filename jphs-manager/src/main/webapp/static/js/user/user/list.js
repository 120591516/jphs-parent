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
	
	/*$('#save').click(function() {
		if(yzCode($("#verificattionCode").val())){
			
			$('#serach-form').attr('action','/user/getExcel.jhtml');
			$('#serach-form').submit();
			$('#serach-form').attr('action','/user/index.jhtml');
			
		}else{
			alert("验证码错误");
		}
	})*/
	
})

function getexcel(){
	if(yzCode($("#verificattionCode").val())){
		$('#serach-form').attr('action','/user/getExcel.jhtml');
		$('#serach-form').submit();
		$('#serach-form').attr('action','/user/index.jhtml');
	}else{
		alert("验证码错误");
	}
}

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/user/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#province10").empty();
		$("#city10").empty();
		$("#device").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/user/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/user/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确定删除吗")){
		window.location.href = "/user/delete.jhtml?id=" + id;
	}
}
