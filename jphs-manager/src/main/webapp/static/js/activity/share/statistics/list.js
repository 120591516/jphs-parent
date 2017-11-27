$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/share/statistics/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#type").get(0).selectedIndex=0;
		$("#sharePlatform").get(0).selectedIndex=0;
		$("#shareDevice").get(0).selectedIndex=0;
	});

	$('.form_date').datetimepicker({
		language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	
});

function redirectUpdatePage(id) {
	window.location.href = "/share/statistics/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/share/statistics/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/share/statistics/delete.jhtml?id=" + id;
}
