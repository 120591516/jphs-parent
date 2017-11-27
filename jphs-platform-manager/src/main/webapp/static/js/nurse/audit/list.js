$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/audit/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").get(0).selectedIndex=0;
		$("#departmentId").get(0).selectedIndex=0;
	});
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
		format: 'yyyy-mm-dd hh:ii:ss'
	});
});

function redirectUpdatePage(id,creatorId) {
	window.location.href = "/audit/redirectUpdate.jhtml?id=" + id+"&creatorId="+creatorId;
}
function redirectDetailPage(id) {
	window.location.href = "/audit/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/audit/delete.jhtml?id=" + id;
}
