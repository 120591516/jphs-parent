$(function() {
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/order/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {

		$("input[type='text']").val("");
		$("#device").empty();
	});

	$(".btn_add")
			.click(
					function() {
						var tr = "<tr><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td></tr>";
						$("tbody").append(tr);
					})

});

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/order/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/delete.jhtml?id=" + id;
}

function getExcel() {

	if (yzCode($("#verificattionCode").val())) {
		$('#serach-form').attr('action', '/order/getExcel.jhtml');
		$('#serach-form').submit();

		$('#serach-form').attr('action', '/order/index.jhtml');
	} else {
		alert("验证码错误");
	}

}
