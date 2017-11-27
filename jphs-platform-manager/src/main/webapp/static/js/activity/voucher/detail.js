function search(){
	
	var name = $("#name").val();
	var phone = $("#phone").val();
	document.getElementById('userTable').innerHTML = '<th>编号</th>'
		+'<th>昵称</th>'
		+'<th>电话</th>'
		+'<th>选择</th>';
	 $.ajax({
		 	type: "get",
			url: "/voucher/getUserList.jhtml",
			data: {
					name :name,
					phone:phone
			},
			dataType: "json",
			success: function(data) {
				var userList = data.userList;
				for(var i = 0;i<userList.length;i++){
					$('#userTable').append("<tr><td>"+[i+1]+"</td>"
							+ "<td>"+userList[i].name+"</td>" 
							+ "<td>"+userList[i].phone+"</td>" 
							+ "<td><a onclick='choice("+userList[i].id+")'>选择此人</a></td></tr>");
				}
			}
	 });
}


function  showDiv(voucherId,amount){

	var voucherRepertoryIds = '';
	var voucherRepertoryId = $('input[name="repertoryId"]:checked');
	if (voucherRepertoryId.length > 0) {
		for (var i = 0; i < voucherRepertoryId.length; i++) {
			if ((i + 1) == voucherRepertoryId.length) {
				voucherRepertoryIds += voucherRepertoryId[i].value;
			} else {
				voucherRepertoryIds += voucherRepertoryId[i].value + ",";
			}
		}
	}else{
		alert("请选择优惠券！！");
		return false;
	}
	window.location.href = "/voucher/addUser.jhtml?voucherRepertoryIds=" + voucherRepertoryIds+"&voucherId="+voucherId+"&amount="+amount;
	
}
function choice(creatorId,creatorName,phone){
	var voucherRepertoryIds = '';
	var voucherRepertoryId =GetRequest()['voucherRepertoryIds'];
	var voucherId =GetRequest()['voucherId'];
	var amount =GetRequest()['amount'];
	if(confirm("确定给此人添加优惠券吗？")){
	 $.ajax({
		 	type: "get",
			url: "/voucher/addVoucherUser.jhtml",
			data: {
				creatorId :creatorId,
				creatorName :creatorName,
				voucherRepertoryId : voucherRepertoryId,
				voucherId : voucherId,
				phone : phone,
				amount : amount,
			},
			dataType: "json",
			success: function(data) { 
				window.location.href = "/voucher/detail.jhtml?id=" + data.result;
			}
	 }); 
	}
}

function getPhone(imgid,inputid){
	
	   var filename = document.getElementById("myfiles").value;
	    // 这时的filename不是 importFile 框中的值
	    alert(filename);
	/* $.ajax({
		 	type: "post",
			url: "/voucher/fileUpload.jhtml",
			 secureuri:false,                           //是否启用安全提交,默认为false 
		      fileElementId :imgid,               //文件选择框的id属性
		     dataType:'text', 
			success: function(data) { 
				 alert(data);
			}
	 });*/
}


function sendVoucher(){
	
	var phones = $("#phones").val();
	 
	var reg = /^.*[~!@#\$%\^&\*\(\)_+\-=\[\]\{\}\\\|\'\";，；:\<\.\>\/\?\s+].*$/;
	var voucherId =GetRequest()['id'];
	 if(!(reg.test(phones))){
			$.ajax({
			 	type: "post",
				url: "/voucher/sendVoucher.json",
				data:{phones:phones,voucherId:voucherId},
			    dataType:'json', 
				success: function(data) { 
					 alert(data.result);
					 if(data.code == 1){
						 location.reload();
					 }
				}
		 });
	}else{
		 alert("含有非法字符");
	} 

}