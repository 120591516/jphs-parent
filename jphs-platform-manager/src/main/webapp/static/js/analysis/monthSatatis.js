/**
 * 该js 用于通用的统计按月查询的页面按钮
 */
$(document).ready(function(){
	/**
	 * 按月统计部分 
	 */
	var updateMonthButtonText= function(thisMonth){
		console.info(thisMonth);
		thisMonth = thisMonth.substring(5,7);
		$("#this-month").text(thisMonth+'月');
	};
	var thisMonth =monthTimeList[0];
	updateMonthButtonText(thisMonth);
	$("#last-month").click(function(param) {
		var url =param.currentTarget.value;
		var month=getPreMonth(thisMonth);
		updateMonthButtonText(month);
		$('#quantity-month').load(url+"?month="+ month, "");
	});
	$("#next-month").click(function(param) {
		var url =param.currentTarget.value;
		var month=getNextMonth(thisMonth);
		updateMonthButtonText(month);
		$('#quantity-month').load(url+"?month="+ month, "");
	});
})