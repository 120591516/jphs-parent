$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/package/price/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/package/price/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/package/price/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jkwy/package/price/delete.jhtml?id=" + id;
}
