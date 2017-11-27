$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/healthy/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/healthy/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/healthy/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/family/healthy/delete.jhtml?id=" + id;
}
