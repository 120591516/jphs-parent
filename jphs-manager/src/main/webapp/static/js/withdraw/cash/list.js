$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/withdraw/cash/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("#status").get(0).selectedIndex=0;
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/withdraw/cash/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/withdraw/cash/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/withdraw/cash/delete.jhtml?id=" + id;
}
function getexcel(){
	if(yzCode($("#verificattionCode").val())){
		$('#serach-form').attr('action','/withdraw/cash/getExcel.jhtml');
		$('#serach-form').submit();
		$('#serach-form').attr('action','/withdraw/cash/index.jhtml');
	}else{
		alert("验证码错误");
	}
}