$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/sms/template/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#type").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/sms/template/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/sms/template/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/sms/template/delete.jhtml?id=" + id;
}
