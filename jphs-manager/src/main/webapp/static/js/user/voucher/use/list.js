
$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/voucher/use/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").empty();
		$("#type").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/voucher/use/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/voucher/use/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确认要删除吗?")){
		window.location.href = "/voucher/user/delete.jhtml?id=" + id+"&status=-1";
	}
}
