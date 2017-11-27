$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/activity/promotion/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#resourceType").get(0).selectedIndex=0;
		$("#type").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/activity/promotion/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/activity/promotion/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确认要删除吗?")){
	window.location.href = "/activity/promotion/delete.jhtml?id=" + id;
	}
}
