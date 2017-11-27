$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/commodity/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").empty();
		$("#businessId").empty();
		$("#commodityTypeId").empty();
		$("#ctpId").empty();
		$("#ctId").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/commodity/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/commodity/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确定删除吗")){
		
		window.location.href = "/commodity/delete.jhtml?id=" + id;
	}
	
}


function updateStatus(id,status){
	if(status == 1){
		status = -1;
	}else{
		status = 1;
	}
	if(confirm("确定执行吗？")){
		$.ajax({
			type: "get",
			url: "/commodity/updateStatus.jhtml",
			data: {id:id,status:status},
			dataType: "json",
			complete: function(data) { 
				location.reload();
			}
		});
	}
}

function change(id){
	document.getElementById("ctId").innerHTML=""; 
	if(id != ""){
		$.ajax({
			type: "get",
			url: "/commodity/getCtList.json",
			data: {id:id},
			dataType: "json",
			success: function(data) {
				var ctList = data.ctList;
				$("#ctId").append("<option value=''>请选择</option>");
				 for(var i = 0;i<ctList.length;i++ ){
				 $("#ctId").append("<option value='"+ctList[i].id+"' >"+ctList[i].name+"</option>");
				 };
			}
		});
	}else{
		$("#ctId").append("<option value=''>请选择</option>");
	}

}