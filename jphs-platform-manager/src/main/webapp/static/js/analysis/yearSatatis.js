/**
 * 该js 用于通用的统计按年查询的页面按钮
 */
$(document).ready(function(){
	/**
	 * 按年统计部分
	 */
	var updateYearButtonText= function(thisYear){
		if(thisYear.length>4){
			thisYear = thisYear.substring(0,4);
		}
		$("#this-year").text(thisYear+'年');
		var nowYear = format_year(new Date());
		if(thisYear < nowYear){
			$("#next-year").show();
		}
		if(flag){
			if(thisYear<=2017){
				$("#last-year").hide();
			}
		}
	};
	var thisYear =getDateTime(new Date(yearTimeList[0]));
	updateYearButtonText(thisYear);
	$("#last-year").click(function(par) {
		var url =par.currentTarget.value;
		var year=getPreYear(thisYear);
		updateYearButtonText(year);
		$('#quantity-year').load(url+"?year="+ year, "");
	});
		$("#next-year").click(function(par) {
		var url =par.currentTarget.value;
		var year=getNextYear(thisYear);
		updateYearButtonText(year);
		$('#quantity-year').load(url+"?year="+ year, "");
	});
})