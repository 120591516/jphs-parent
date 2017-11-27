$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/consultation/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/consultation/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/consultation/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/family/consultation/delete.jhtml?id=" + id;
}
