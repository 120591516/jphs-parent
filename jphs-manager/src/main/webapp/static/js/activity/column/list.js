$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/column/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/column/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/column/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确定删除吗")){
		window.location.href = "/column/delete.jhtml?id=" + id;
	}
}

function updateStatus(id,status){
	if(status == 1){
		status = 0;
	}else{
		status = 1;
	}
	
	if(confirm("确定执行吗？")){
		$.ajax({
			type: "get",
			url: "/column/updateStatus.jhtml",
			data: {id:id,status:status},
			dataType: "json",
			complete: function(data) { 
				location.reload();
			}
		});
	}
}

