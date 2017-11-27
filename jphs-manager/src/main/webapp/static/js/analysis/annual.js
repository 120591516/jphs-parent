/**
 * 该js 用于通用的全年统计
 */
$(document).ready(function(){
	/**
	 * 按年统计部分
	 */
	var updateYearButtonText= function(year){
		if(thisYear.length>4){
			thisYear = thisYear.substring(0,4);
		}
		$("#annual-this-year").text(thisYear+'年');
		var nowYear = format_year(new Date());
		console.info("当前年："+nowYear);
		if(thisYear < nowYear){
			$("#annual-next-year").show();
		}
		if(flag){
			if(thisYear<=2017){
				$("#annual-last-year").hide();
			}
		}
	};
	var thisYear =getDateTime(new Date(year));
	updateYearButtonText(thisYear);
	$("#annual-last-year").click(function(par) {
		var url =par.currentTarget.value;
		console.info(url);
		var year=getPreYear(thisYear);
		updateYearButtonText(year);
		$('#annual-quantity-year').load(url+"?year="+ year, "");
	});
		$("#annual-next-year").click(function(par) {
		var url =par.currentTarget.value;
		var year=getNextYear(thisYear);
		updateYearButtonText(year);
		$('#annual-quantity-year').load(url+"?year="+ year, "");
	});
		getTotal();
})
/**
 * 求和
 */
function getTotal() {
	for (var i = 1; i < 32; i++) {
		getTotalNumber("day_" + i, "dayTotal_" + i);
	}
	for (var i = 1; i < 13; i++) {
		getTotalNumber("month_" + i, "monthTotal_" + i);
	}
	getTotalNumber("dayTotal", "total");
}
/**
 * 获取
 * @param name
 */
function getTotalNumber(className,totalIdName){
	var box = document.getElementsByClassName(className);
	var totalUserPayPrice = 0;
	for(var i=0;i<box.length;i++){
		totalUserPayPrice +=parseFloat(box[i].innerHTML);
	}
	$("#"+totalIdName).html(totalUserPayPrice);
}