$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/business/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/business/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/business/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确定执行吗？")){
		window.location.href = "/business/delete.jhtml?id=" + id;
	}
}
