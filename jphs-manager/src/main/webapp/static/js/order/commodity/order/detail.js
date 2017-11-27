function updateStatus(id,status){
	
	var coId = $("#coId").val();
	 
	if(status == -2){
		if(confirm("确定拒绝吗")){ 
			$.ajax({
				url : "/commodity/return/update.jhtml",
				type : "POST",
				data : {id:id,status:status},
				dataType : 'json',
				success : function(msg) { 
					window.location.reload();
				} 
			}) 
		 }
	}else if(status == 2){
		if(confirm("确定退款吗")){
			$.ajax({
				url : "/commodity/return/update.jhtml",
				type : "POST",
				data : {id:id,status:status,coId:coId},
				dataType : 'json',
				success : function(msg) { 
					window.location.reload();
				} 
			}) 
		}
	} 
}


function addLogistics(){
	
	var no = $("#no").val();
	var logisticsId = $("#logisticsId").val();
	 
	 
	if(no != "" && logisticsId != ""){
		if(confirm("确定添加吗？")){
			$.ajax({
			 	type: "get",
				url: "/commodity/order/addLogistics.jhtml",
				data:$('#logistics').serialize(),
				dataType: "json",
				success: function(data) {
					window.location.reload();
				}
		 }); 
		}
		
	}
}

function addRemark(){
	if(confirm("确定保存吗？")){
		$.ajax({
		 	type: "get",
			url: "/remark/addRemark.json",
			data:$('#addRemark').serialize(),
			dataType: "json",
			success: function(data) {
				window.location.reload();
			}
	 }); 
	}
}

function check(no){
	var count = $("#comCount").val();
	if(count>1){
		 if(confirm("已联系用户退款")){
			 var crId = $("#crId"+no).val();
			 var  orderId= $("#orderId"+no).val();
			 
			 $.ajax({
				 	type: "get",
					url: "/commodity/order/update.jhtml",
					data:{crId:crId,orderId:orderId},
					dataType: "json",
					success: function(data) {
						window.location.reload();
					}
			 });
		 }
	}else{
		 
		$.ajax({
		 	type: "get",
			url: "/commodity/order/refund.jhtml",
			data:$('#tkForm').serialize(),
			dataType: "json",
			success: function(data) {
				console.info(data);
				window.location.reload();
			}
	 });
	}
}