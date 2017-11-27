$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/family/order/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#type").get(0).selectedIndex=0;
		$("#accessMode").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/family/order/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/family/order/detail.jhtml?id=" + id;
}

function putFamilyConsultation(id){
	window.location.href = "/family/consultation/redirectAddPage.jhtml?id=" + id;
}

function putFamilyRegister(id){
	window.location.href = "/family/register/redirectAddPage.jhtml?id=" + id;
}

function putFamilyHealthy(id){
	window.location.href = "/family/healthy/redirectAddPage.jhtml?id=" + id;
}

function deleteById(id) {
	window.location.href = "/family/order/delete.jhtml?id=" + id;
}
