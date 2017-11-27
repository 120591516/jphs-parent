$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/register/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/register/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/register/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/family/register/delete.jhtml?id=" + id;
}
