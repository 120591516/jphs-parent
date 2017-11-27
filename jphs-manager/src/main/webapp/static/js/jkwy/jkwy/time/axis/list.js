$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/time/axis/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/time/axis/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/time/axis/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jkwy/time/axis/delete.jhtml?id=" + id;
}
