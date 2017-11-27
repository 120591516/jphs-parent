window.onload = function() {
	// 实例化编辑器

	$(".btn_addsss")
			.click(
					function() {
						var trlength = Number($('.price_gradeAdd').length);
						/* alert(trlength); */
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
}

function addTd(id) {
	 
	var no = $("#csList").val();
	$("#csList").val(no * 1 + 1);

	var number = '';

	for (var a = 1; a < 61; a++) {
		number += '<option>' + a + '</option>'
	}
	// <input type="hidden" id="grade" name="grade" value="1" />
	/*
	 * var grade; if(id=='chuji'){ grade = 1; } if(id=='zhongji'){ grade = 2; }
	 * if(id=='gaoji'){ grade = 3; } if(id=='biaozhun'){ grade = 0; }
	 */
	/*
	 * var trlength = Number($('#addClassprice tr').length) -
	 * Number($('.headClass').length);
	 */
	// var trlength = Number($('.addClassprice').length);
	var trlength = no;
	var xh = no * 1 + 1;
	var tr = '<tr class="addClassprice" id="delete'
			+ trlength
			+ '"><td><input style="width:100%" class="form-control" id="title'
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].title" value = '+xh+'></td>'
			+ '		<td> '
			+'<img alt="" height="200" width="200" id="urls"'+trlength
			+'	src="${url}" /><span>'
			+'</span> <input class="input-file" type="file" name="myfiles" id="pcurl_s"'
			+'	onchange=ajaxFileUpload("pcurl_s","url"); /> <input'
			+'	class="input-file" type="hidden" id="url"'+trlength+' name="csList['
			+ trlength
			+'].image" value="${url}" />'
			
			+'</td>'
			+ '		<td><input style="width:100%" class="form-control" id="costPrice'
			+ trlength
			+ ''
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].name"  value=""/></td>'
			+ '		<td><input style="width:100%" class="form-control" id="price'
			+ trlength
			+ ''
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].link" value=""/> </td>'
			+ '		<td><input style="width:100%" class="form-control" id="profit'
			+ trlength
			+ ''
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].brief" value=""/> </td>'
			/*+ '		<td><input style="width:100%" class="form-control" id="profit'
			+ trlength
			+ ''
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].profit" value=""/> </td>'*/
			+ '		<td><input style="width:100%" class="form-control" id="number'
			+ trlength
			+ ''
			+ trlength
			+ '" name="csList['
			+ trlength
			+ '].sort" value=""/>  </td><td>'
			+ '<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle(\''
			+ trlength
			+ ''
			+ trlength
			+ '\');" data-target="#myModal" />'
			+ '<input type="hidden" id="aptitudeIdArr'
			+ trlength
			+ ''
			+ trlength
			+ '" name="priceGrade['
			+ trlength
			+ '].price['
			+ trlength
			+ '].aptitudeIdArr" value=""/>'
			+ '</td>'
			+ '<td>'
			+ '	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'
			+ id + '' + trlength + '" onclick="deleteTr(' + trlength + ');">'
			+ '</td>' + '	<input type="hidden" id="grade' + trlength
			+ '" name="priceGrade[' + id + '].price[' + trlength
			+ '].grade" value="' + id + '" />' + '	</tr>';
	$("#addtrprice0").append(tr);
	/*
	 * <img style="width: 20px;height: 20px;"
	 * src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png"
	 * data-toggle="modal" onclick="setJobtitle('${priceOne.id }');"
	 * data-target="#myModal" />
	 *//*
		 * <input type="button" style="width:100%" class="form-control"
		 * value="删除" id="delete'+id+''+trlength+'"
		 * onclick="deleteTr('+trlength+');" /> <button type="button"
		 * class="public-info public_btn public_btn_center" data-toggle="modal"
		 * onclick="setJobtitle(\''+id+''+trlength+'\');" data-target="#myModal"
		 * >修改</button>
		 */}

function deleteTr(id) {
	if (confirm('确实要删除吗？')) {
		$("#delete" + id).hide();
		$('#status' + id).val('-1');
		var no = $("#csList").val();
		$("#csList").val(no * 1-1);
	}
}

function updateCsStatus(id,status){
	if(status == 1){
		status = -1;
	}else{
		status = 1;
	}
	
	if(confirm("确定执行吗？")){
		$.ajax({
			type: "get",
			url: "/column/updateCsStatus.jhtml",
			data: {id:id,status:status},
			dataType: "json",
			complete: function(data) { 
				location.reload();
			}
		});
	}
}