$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/card/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#type").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/card/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/card/detail.jhtml?batchNo=" + id;
}
function deleteById(id) {
	window.location.href = "/family/card/delete.jhtml?id=" + id;
}
