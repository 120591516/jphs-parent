$(function() {
	
	$(".arrProd").click(function(){
		alert($(this).text());
	})
	setTimeout(setCSelectCallback,1500);//1000毫秒=1.5秒后执行test方法
})

/*window.onload = function () {
	$(".arrProd").click(function(){
		alert(2)
		alert($(this).text());
	})
	alert(3)
};*/
function setCSelectCallback(){
	
	/*var linkArrC = $("#linkArrC").val();
	alert(linkArrC);*/
}

function productSelect(){
	var productId = $('#product option:selected').val();//选中的值
	$('.classProduct').hide();
	$('#p_'+productId).show();
	$('#p_'+productId).removeClass("hide");
}