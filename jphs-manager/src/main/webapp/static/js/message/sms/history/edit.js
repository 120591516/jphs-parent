function showCom() {
	$("#d2").hide();
	$("#d1").show();
	$("#m_title").val("");
	$("#m_content").val("");
	$("#smsTemplateId").get(0).selectedIndex = 0;
	$("#phone").attr('placeholder', '请输入接收者手机号  最多不超过200个手机号');

}
function showCom2() {
	$("#d1").hide();
	$("#d2").show();
	$("#m_title").val("");
	$("#m_content").val("");
	$("#smsTemplateId2").get(0).selectedIndex = 0;
	$("#phone").attr('placeholder', '请输入接收者手机号  最多不超过5000个手机号');

}
function sl() {
	var title = $('#smsTemplateId').find("option:selected").attr("title");
	var content = $('#smsTemplateId').find("option:selected").attr("id");
	$("#m_title").val(title);
	$("#m_content").val(content);
}
function sl2() {
	var title = $('#smsTemplateId2').find("option:selected").attr("title");
	var content = $('#smsTemplateId2').find("option:selected").attr("id");
	$("#m_title").val(title);
	$("#m_content").val(content);
}

function subValidate() {
	var s = $('#smsTemplateId').find("option:selected").val();
	var s2 = $('#smsTemplateId2').find("option:selected").val();
	var phone = $("#phone").val();

	if (s == "" && s2 == "") {
		alert("请选择模板ID");
		return false;
	}
	if (phone == null || phone == "") {
		alert("请输入接收者手机号");
		return false;
	}
	 if (!confirm("确认发送？")) {
		 return false;
     }
	return true;

}

function doUpload() {
	var formData = new FormData();
	formData.append("file", $("#file")[0].files[0]);
	//var formData = new FormData($("#uploadForm")[0]);
	$.ajax({
		url : '/sms/history/readFile.json',
		type : 'POST',
		dataType : "JSON",
		data : formData,
		// fileElementId : 'file', //这里对应html中上传file的id
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(returndata) {
			
			alert(returndata.message);
			$("#phone").val(returndata.phone);
			
		},
		error : function(returndata) {
			alert("获取出错！");
		}
	});
}

function F_Open_dialog() 
{ 
     //document.getElementById("btn_file").click(); 
     $("#file").click();
} 
