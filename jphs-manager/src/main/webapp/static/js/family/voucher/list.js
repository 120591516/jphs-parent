$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/voucher/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/voucher/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/voucher/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/family/voucher/delete.jhtml?id=" + id;
}
