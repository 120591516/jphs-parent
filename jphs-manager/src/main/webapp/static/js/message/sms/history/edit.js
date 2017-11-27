
function showCom(){
	$("#d2").hide();
	$("#d1").show();
	$("#m_title").val("");
	$("#m_content").val("");
	$("#smsTemplateId").get(0).selectedIndex=0;
	$("#phone").attr('placeholder','请输入接收者手机号  最多不超过200个手机号');

}
function showCom2(){
	$("#d1").hide();
	$("#d2").show();
	$("#m_title").val("");
	$("#m_content").val("");
	$("#smsTemplateId2").get(0).selectedIndex=0;
	$("#phone").attr('placeholder','请输入接收者手机号  最多不超过5000个手机号');
	
	
}
function sl(){
	 var title=$('#smsTemplateId').find("option:selected").attr("title");
	 var content=$('#smsTemplateId').find("option:selected").attr("id");
	 $("#m_title").val(title);
	 $("#m_content").val(content);
}
function sl2(){
	 var title=$('#smsTemplateId2').find("option:selected").attr("title");
	 var content=$('#smsTemplateId2').find("option:selected").attr("id");
	 $("#m_title").val(title);
	 $("#m_content").val(content);
}
	
