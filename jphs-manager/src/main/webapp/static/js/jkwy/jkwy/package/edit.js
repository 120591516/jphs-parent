$(function() {
	// 实例化编辑器
	var um = UM.getEditor('myEditor');
	um.addListener('blur', function() {
		$('#focush2').html('编辑器失去焦点了');
	});
	um.addListener('focus', function() {
		$('#focush2').html('');
	});
	$('#counter').html	(110-($('#subTitle').val().length));
	$("#jkwyPackageForm").validate(
			{
				rules : {
					title : {
						required : true
					},
					subTitle : {
						required : true
					},
					sort : {
						required : true
					},
					platformId:{
						required : true
					}
				},
				messages : {
					title : {
						required : "请输入套餐名称",
					},
					subTitle : {
						required : "请输入套餐简介",
					},
					sort : "请输入套餐排序",
					platformId:"请至少选择一个平台"
				},
				submitHandler : function(form) {
					var id = $('#id').val();
					var moveurl= $('#moveurl').val();
					var pcurl= $('#pcurl').val();
					var content =$('#myEditor').val();
					var trlength = Number($('.addClassprice').length);
					if(!isNotNull(moveurl)){
						alert("请上传此套餐手机端图片！！！");
						return false;
					}
					if(!isNotNull(pcurl)){
						alert("请上传此套餐PC端图片！！！");
						return false;
					}
					if(!isNotNull(content)){
						alert("请完善套餐详情！！！");
						return false;
					}
					if(trlength<1){
						alert("请完善套餐内容！！");
						return false;
					}
					form.submit();
				}
			});
})

function countChar(textareaName, spanName) {
	document.getElementById(spanName).innerHTML = 110 - document
			.getElementById(textareaName).value.length;

	if ((110 - document.getElementById(textareaName).value.length) == 0) {
		$("#counter").css("color","red");
	}
}

function addTable(){
	var length = Number($('.price').length);
	var tr = '<div id="price'+length+'" class="price"><table id="dateTable" cellpadding="0" cellspacing="0" class="data_table  text-center" style="width: 900px; margin: 0 auto">'
			+'<thead id="addtrprice">'
			+'<tr class="headClass">'
			+'<td width="50">规格名称</td>'
			+'<td width="40">原价</td>'
			+'<td width="40">销售价</td>'
			+'<td width="60">服务时长</td>'
			+'<td width="60">单位</td>'
			+'<td width="50">支持套餐人数</td>'
			+'<td width="50">支持续费</td>'
			+'<td width="50">支持变更升级套餐</td>'
			+'<td style="width: 40px;">操作</td>'
			+'</tr>'
			+'<tr class="addClassprice" id="delete'+length+'"><input type="hidden" id="status'+length+'" name="packagePrice['+length+'].status" value="0"/><td><input  class="form-control" required="required" id="title'+length+'" name="packagePrice['+length+'].title" /></td>'
			+'		<td><input style="width:100%" required="required" class="form-control" id="oldPrice'+length+'" name="packagePrice['+length+'].oldPrice" /></td>'
			+'		<td><input style="width:100%" required="required" class="form-control" id="price'+length+'" name="packagePrice['+length+'].price"  /></td>'
			+'		<td>'
			+'			<input type="number" required="required" style="width:100%;" max="365" min="1" class="form-control input-xlarge" id="serviceTime'+length+'"name="packagePrice['+length+'].serviceTime"/>'
			+'		</td>'
			+'		<td>'
			+'			<select style="width: 100%;" required="required" class="form-control input-xlarge" id="unit'+length+'" name="packagePrice['+length+'].unit">'
			+'				<option>天</option>'
			+'			</select>'
			+'		</td>'
			+'		<td>'
			+'<input type="number" required="required" style="width:100%;" max="100" min="1" class="form-control input-xlarge" id="unit'+length+'"name="packagePrice['+length+'].supportNumber"/>'
			+'</td>'
			+'		<td>'
			+'<select style="width:100%;" required="required" class="form-control input-xlarge" id="supportFee'+length+'"name="packagePrice['+length+'].supportFee">'
			+'				<option value="0">否</option>'
			+'				<option value="1">是</option>'
			+'			</select>'
			+'	</td>'
			+'		<td>'
			+'<select style="width:100%;" required="required" class="form-control input-xlarge" id="supportChange'+length+'"name="packagePrice['+length+'].supportChange">'
			+'				<option value="0">否</option>'
			+'				<option value="1">是</option>'
			+'			</select>'
			+'</td>'
			+'<td>'
			+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+length+'" onclick="deleteTable('+length+');">'
			+'</td>'
			+'	</tr>'
			+'</thead>'
			+'</table>'
			+'<table class="data_table  text-center"  style="width: 900px; margin: 0 auto">'
			+'<thead><tr>'
			+'<td>服务模块</td>'
			+'<td>服务内容</td>'
			+'<td>服务说明</td>'
			+'<td width="60">总服务次数</td>'
			+'<td width="60">剩余服务次数</td>'
			+'<td style="width: 40px;">操作</td>'
			+'</tr></thead>'
			+'<tbody id="addPrice'+length+'Content"><tr class="packagePrice'+length+'addPriceContent" >'
			+'<td>'
			+'<input type="hidden" class="status'+length+'" id="packagePrice'+length+'jkwyPackageContentList0status" name="packagePrice['+length+'].jkwyPackageContentList[0].status" value="0"/>'
			+'<input name="packagePrice['+length+'].jkwyPackageContentList[0].title"  required="required" /></td>'
			+'<td><input name="packagePrice['+length+'].jkwyPackageContentList[0].subTitle"  required="required" /></td>'
			+'<td><input name="packagePrice['+length+'].jkwyPackageContentList[0].content"  required="required" /></td>'
			+'<td><input type="number" id="packagePrice'+length+'jkwyPackageContentList0number" onkeydown="parallel('+length+',0);" onkeyup="parallel('+length+',0);" style="width: 60px;" min="1" name="packagePrice['+length+'].jkwyPackageContentList[0].number"  required="required" /></td>'
			+'<td><input type="number" id="packagePrice'+length+'jkwyPackageContentList0surplusNumber" style="width: 60px;" min="1" name="packagePrice['+length+'].jkwyPackageContentList[0].surplusNumber"  required="required" readonly="readonly" /></td>'
			+'<td>'
			+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+length+'" onclick="deleteTr('+length+');">'
			+'</td>'
			+'</tr></tbody></table>'
			+'<div class="btn_add" onclick="addTr('+length+');" title="添加服务内容" style="width: 100px">+</div></div>'
			;
	$("#dateTableList").append(tr);
}
function addTr(length){
	var trlength = Number($('.packagePrice'+length+'addPriceContent').length);
	var tr = '<tr id="packagePrice'+length+'addPriceContent'+trlength+'" class="packagePrice'+length+'addPriceContent">'
			+'<td><input type="hidden" class="status'+length+'" id="packagePrice'+length+'jkwyPackageContentList'+trlength+'status" name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].status" value="0"/><input name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].title"  required="required" /></td>'
			+'<td><input name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].subTitle"  required="required" /></td>'
			+'<td><input name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].content"  required="required" /></td>'
			+'<td><input type="number" id="packagePrice'+length+'jkwyPackageContentList'+trlength+'number" onkeydown="parallel('+length+','+trlength+');" onkeyup="parallel('+length+','+trlength+');" style="width: 60px;" min="1" name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].number"  required="required" /></td>'
			+'<td><input type="number" id="packagePrice'+length+'jkwyPackageContentList'+trlength+'surplusNumber" style="width: 60px;" min="1" name="packagePrice['+length+'].jkwyPackageContentList['+trlength+'].surplusNumber"  required="required" readonly="readonly" /></td>'
			+'<td>'
			+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+length+'" onclick="deleteTr('+length+','+trlength+');">'
			+'</td>'
			+'</tr>';
	console.info(length+','+trlength);
	$("#addPrice"+length+"Content").append(tr);
}
function deleteTable(id){
	if(confirm('确实要删除吗？')){
		 $("#price"+id).hide();
		 $('#status'+id).val('-1');
		 $('.status'+id).val('-1');
	}
}
function deleteTr(length,trlength){
	if(confirm('确实要删除吗？')){
		$('#packagePrice'+length+'addPriceContent'+trlength).hide();
		$('#packagePrice'+length+'jkwyPackageContentList'+trlength+'status').val('-1');
	}
}

/**
 * 同步总次数 剩余次数
 * @param num1 price 的下标
 * @param num2 content 的下标
 */
function parallel(num1,num2){
	var number = $('#packagePrice'+num1+'jkwyPackageContentList'+num2+'number').val();
	$('#packagePrice'+num1+'jkwyPackageContentList'+num2+'surplusNumber').val(number)
}