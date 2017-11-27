$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jkwy/relation/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jkwy/relation/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jkwy/relation/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jkwy/relation/delete.jhtml?id=" + id;
}
