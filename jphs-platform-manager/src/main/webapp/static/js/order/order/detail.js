$(document).ready(function() {
	var date = new Date();
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss',
		 startDate:date
	});

		$("#timeModalData").load("/order/edit.jhtml",function(){});
		
		
});

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}



function addRemark(){

	
	if(confirm("确定保存吗？")){
		$.ajax({
		 	type: "get",
			url: "/remark/addRemark.jhtml",
			data:$('#addRemark').serialize(),
			dataType: "json",
			success: function(data) {
				location.reload();
			}
	 }); 
	}
	

}