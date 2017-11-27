window.onload=function(){
	$('#save').click(function() {
		
		if(!valIsNotNull("title")){
			alert("标题不能为空~~");
			return false;
		}
		if(!valIsNotNull("price")){
			alert("价格不能为空~~");
			return false;
		}
		if(!valIsNotNull("image")){
			alert("头图不能为空~~");
			return false;
		}
		if(!valIsNotNull("subTitle")){
			alert("介绍不能为空~~");
			return false;
		}
		if(!valIsNotNull("content")){
			alert("服务详情不能为空~~");
			return false;
		}
		document.productForm.submit();
	});



	// 实例化编辑器
	var um = UM.getEditor('myEditor');
	um.addListener('blur', function() {
		$('#focush2').html('编辑器失去焦点了');
	});
	um.addListener('focus', function() {
		$('#focush2').html('');
	});
}