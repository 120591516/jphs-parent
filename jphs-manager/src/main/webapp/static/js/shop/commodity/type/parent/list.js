$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/commodity/type/parent/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/commodity/type/parent/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/commodity/type/parent/detail.jhtml?id=" + id;
}
function deleteById(id,count) {
	if(confirm("确定执行吗？")){
		if(count == 0){
			window.location.href = "/commodity/type/parent/delete.jhtml?id=" + id;
		}else{
			alert("拒绝删除，当前分类下存在二级分类！");
		}
	}
}
