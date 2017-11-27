$(function() {
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
		format: 'yyyy-mm-dd hh:ii:ss'
	});
	$('.form_dates').datetimepicker({
	    language:  'zh-CN',
		format: 'yyyy-mm-dd hh:ii:ss'
	});
	
	$('#save').click(function() {
		if(!valIsNotNull("validateCode")){
			alert("请获取验证编码~~");
			return false;
		}
		if(!valIsNotNull("beginTime")){
			alert("请选择开始时间~~");
			return false;
		}
		if(!valIsNotNull("endTime")){
			alert("请选择结束时间~~");
			return false;
		}
		if(!valIsNotNull("day")){
			alert("请输入有效期~~");
			return false;
		}
		if(!valIsNotNull("unit")){
			alert("请输入有效期单位~~");
			return false;
		}
		document.productForm.submit();
	});
		
});
function hqsjs(){
	var validateCode = randomWord(false,30);
	$('#validateCode').val(validateCode);
	$('#validateCodes').val(validateCode);
}

/*
** randomWord 产生任意长度随机字母数字组合
** randomFlag-是否任意长度 min-任意长度最小位[固定位数] max-任意长度最大位
** xuanfeng 2014-08-28
*/
 
function randomWord(randomFlag, min, max){
    var str = "",
        range = min,
        arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
 
    // 随机产生
    if(randomFlag){
        range = Math.round(Math.random() * (max-min)) + min;
    }
    for(var i=0; i<range; i++){
        pos = Math.round(Math.random() * (arr.length-1));
        str += arr[pos];
    }
    return str;
}