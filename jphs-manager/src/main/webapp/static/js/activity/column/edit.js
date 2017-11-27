/*window.onload = function() {
	// 实例化编辑器

	$(".btn_addsss")
			.click(
					function() {
						var trlength = Number($('.price_gradeAdd').length);
						 alert(trlength); 
						var a = '<div class="price_gradeAdd"><span class="price_add_title">'
								+ '		<input type="hidden" id="csList'
								+ trlength
								+ '" name="csList['
								+ trlength
								+ '].id" value="'
								+ trlength
								+ '" />'
								+ '等级名称：<input style="width: 200px;" type="text" id="gradeName'
								+ trlength
								+ '" name="csList['
								+ trlength
								+ '].gradeName" /></span>'
								+ '	<div class="marage_right_content" style="margin-top: 0; padding-top: 0">'
								+ '<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">'
								+ '<tbody id="addtrprice'
								+ trlength
								+ '"> <tr class="headClass">'
								+ '		<td width="100px">序号</td>'
								+ '		<td width="60px">图片</td>'
								+ '		<td width="60px">名称</td>'
								 
								+ '		<td width="60px">链接</td>'
								+ '		<td width="60px">介绍</td>'
								+ '		<td width="60px">排序</td>'
								+ '		<td width="60px">状态</td>'
								+ '		<td width="60px">操作</td>'
								+ '	</tr> </tbody> </table>'
								+ '<div class="btn_add" onclick="addTd('
								+ trlength + ');">+</div></div></div>';

						$(".norm_service").append(a);
					})
}*/

$(function() {
/* 等级服务 */
	$('#saveLink').click(function(){
		var numberLen= $('#numberLen').val();
		var productType = $('#product option:selected').val();//选中的值
		var goodsType = $('#p_'+productType+' option:selected').val();//选中的值
		var goodsTypeName = $('#p_'+productType+' option:selected').html();//选中的值
		$('#link'+numberLen).val(goodsType);
		$('#linkName'+numberLen).val(goodsTypeName);
	})
	
	$('#save').click(function() {
		
		var name=$('#name').val();
		if(name == null || name == ''){
			alert("名称不能为空~~");
			return false;
		}
		var remark=$('#remark').val();
		if(remark == null || remark == ''){
			alert("说明不能为空~~");
			return false;
		}
		var trlength = Number($('.addClassprice').length);
		
		if(trlength == 0){
			alert("服务项不能为空~~");
			return false;
		}
		for(var a=0;a<trlength ;a++){
			/*var image=$('#image_'+Number(a+1)).val();
			if(image == null || image == ''){
				alert("第"+Number(a+1)+"行，图片不能为空~~");
				return false;
			}*/
			/*var names=$('#name'+a).val();
			if(names == null || names == ''){
				alert("第"+Number(a+1)+"行，名称不能为空~~");
				return false;
			}*/
			var columnType = $('#type'+a+' option:selected').val();//选中的值
			if(columnType == 0 ){
				alert("第"+Number(a+1)+"行，请选择类型~~");
				return false;
			}
			var link=$('#link'+a).val();
			if(link == null || link == ''){
				alert("第"+Number(a+1)+"行，链接不能为空~~");
				return false;
			}
		/*	var brief=$('#brief'+a).val();
			if(brief == null || brief == ''){
				alert("第"+Number(a+1)+"行，介绍不能为空~~");
				return false;
			}*/
			/*var sort=$('#sort'+a).val();
			if(sort == null || sort == ''){
				alert("第"+Number(a+1)+"行，排序不能为空~~");
				return false;
			}*/
		}
		
		document.goodsForm.submit();
	});
	
})
function addTd(id) {
	 
	var trlength = Number($('.addClassprice').length);
	var trlengthImage = trlength +1;
	/*+'<td>'
	+'	<img style="width: 20px;height: 20px;" src="" data-toggle="modal" onclick="setJobtitle(\''+ trlength + '\');" data-target="#myModal" />'
	+'	<input type="hidden" id="aptitudeIdArr' + trlength + '" name="priceGrade[' + trlength + '].price[' + trlength + '].aptitudeIdArr" value=""/>'
	+'</td>'*/

	var tr = '<tr class="bg_list_body addClassprice" id="delete' + trlength + '">'
				+'<td>'
				+'	<input style="width:100%" class="form-control" id="number'+ trlength + '" value = '+trlengthImage+'>'
				+'</td>'
				+'<td>'
				+'	<label for="image_'+trlengthImage+'_s"><img alt="" height="50" width="50" id="image_'+trlengthImage+'s" src="" /></label>'
				+'	<input class="input-file" type="file" name="myfiles" id="image_'+trlengthImage+'_s"	onchange="ajaxFileUpload(\'image_'+trlengthImage+'_s\',\'image_'+trlengthImage+'\');" style="display:none" />'
				+'	<input class="input-file" type="hidden" id="image_'+trlengthImage+'" name="csList['+ trlength +'].image" value="" />'
				+'</td>'
				+'<td>'
				+'	<input style="width:100%" class="form-control" id="name' + trlength + '" name="csList[' + trlength + '].name"  value=""/>'
				+'</td>'
				+'<td>'
				+'	<select style="width: 100%;" class="form-control input-xlarge" id="type'+trlength+'" name="csList['+trlength+'].type" onchange="getTypeSelect('+trlength+');" >'
				+'		<option value="0" >全部</option>'
				+'		<option value="1" >服务</option>'
				+'		<option value="2" >商品</option>'
				+'		<option value="3" >自定义</option>'
				+'<input type="hidden" id="linkType'+ trlength+'"  value="0"/>'
				+'	</select>'
				+'</td>'
				+'<td id="setTypeSelect'+trlength+'">'
				+'</td>'
				+'<td>'
				+'	<input style="width:100%" class="form-control" id="brief' + trlength + '" name="csList[' + trlength + '].brief" value=""/>'
				+'</td>'
				+'<td>'
				+'	<input style="width:100%" class="form-control" id="sort' + trlength + '" name="csList[' + trlength + '].sort" value=""/>'
				+'</td>'
				+'<td>'
				+'	<select style="width: 100%;" class="form-control input-xlarge" id="status'+trlength+'" name="csList['+trlength+'].status">'
				+'		<option value="1">开启</option>'
				+'		<option value="0">关闭</option>'
				+'	</select>'
				+'</td>'
				+'<td>'
				+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete' + id + '' + trlength + '" onclick="deleteTr(' + trlength + ');">'
				+'</td>' 
			+'</tr>';
	$("#addtrprice0").append(tr);
}

function getTypeSelect(trlengthNumber){

	var linkHtml = "";
	var columnType = $('#type'+trlengthNumber+' option:selected').val();//选中的值
	if(columnType == 0){
		$("#setTypeSelect"+trlengthNumber).html("");
		return false;
	}
	
	if(columnType == 1 || columnType == 2){
		var imageIlc= "https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png";
		linkHtml = '<img style="width: 20px;height: 20px;" src="'+imageIlc+'" data-toggle="modal" onclick="setRelevance(\''+ trlengthNumber + '\',\''+columnType+'\');" data-target="#myModal" />'
				+'<input type="hidden" id="link' + trlengthNumber + '" name="csList[' + trlengthNumber + '].link" value=""/>'
				+'<input style="width:100%" class="form-control" id="linkName' + trlengthNumber + '"  value=""/>';
	}else{
		linkHtml ='	<input style="width:100%" class="form-control" id="link' + trlengthNumber + '" name="csList[' + trlengthNumber + '].link"  value=""/>';
	}
	if($('#linkType'+trlengthNumber).val() == 0){
		$('#linkType'+trlengthNumber).val(columnType);
	}
	$("#setTypeSelect"+trlengthNumber).html(linkHtml);
	
}

function setRelevance(numberLen,type){
	var columnType = $('#type'+numberLen+' option:selected').val();//选中的值
	var linkArr =  "";
	if(columnType == $('#linkType'+numberLen).val()){
		linkArr = $('#link'+numberLen).val();
	}
	$(".linkList").load("/column/selectListProduct.jhtml", {numberLen: numberLen,type: columnType,link:linkArr} , function(){
	});
}

function deleteTr(id) {
	if (confirm('确实要删除吗？')) {
		$.ajax({
			type: "get",
			url: "/column/updateCsStatus.jhtml",
			data: {id:id,status:-1},
			dataType: "json",
			complete: function(data) { 
				location.reload();
			}
		});
	}
}

function updateCsStatus(indNum,id){
	var statusY = $('#status'+indNum+' option:selected').val();//选中的值
	if(confirm("确定执行吗？")){
		$.ajax({
			type: "get",
			url: "/column/updateCsStatus.jhtml",
			data: {id:id,status:statusY},
			dataType: "json",
			complete: function(data) { 
				location.reload();
			}
		});
	}
}