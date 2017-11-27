$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/order/relation/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/order/relation/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/order/relation/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jkwy/order/relation/delete.jhtml?id=" + id;
}
