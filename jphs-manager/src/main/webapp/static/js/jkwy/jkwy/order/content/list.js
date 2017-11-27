$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/order/content/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/order/content/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/order/content/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jkwy/order/content/delete.jhtml?id=" + id;
}
