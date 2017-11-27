$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/member/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/member/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/member/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/family/member/delete.jhtml?id=" + id;
}
