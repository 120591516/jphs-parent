$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/package/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/package/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/package/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	window.location.href = "/jkwy/package/delete.jhtml?id=" + id+"&status="+status;
}
