/**
 * 该js 用于通用的统计按周查询的页面按钮
 */
$(document).ready(function(){
	/**
	 * 按周统计部分
	 */
	var cells = document.getElementById('monitor').getElementsByTagName('td');
	var clen = cells.length;
	var currentFirstDate;
	var addDate = function(date, n) {
		date.setDate(date.getDate() + n);
		return date;
	};
	$("#next-week").text('下一周');
	$("#last-week").text('上一周');
	var setDate = function(date) {
		var week = date.getDay() - 1;
		date = addDate(date, week * -1);
		currentFirstDate = new Date(date);
		for (var i = 0; i < clen; i++) {
			cells[i].innerHTML = getDateTime(i == 0 ? date : addDate(date, 1));
		}
	};
	var updateWeekButtonText=function(){
		var startDay = cells[0].innerHTML.substring(5,cells[0].innerHTML.length);
		var endDay = cells[clen-1].innerHTML.substring(5,cells[clen-1].innerHTML.length);
		startDay = startDay.replace('-','月');
		endDay = endDay.replace('-','月');
		$("#this-week").text(startDay+'~'+endDay);

	}
	setDate(new Date(weekTimeList[0]));
	updateWeekButtonText();
	$("#last-week").click(function(param) {
		setDate(addDate(currentFirstDate, -7));
		updateWeekButtonText();
		var url =param.currentTarget.value;
		var weekDays =cells[0].innerHTML+"T"+cells[6].innerHTML;
		$('#quantity-week').load(url+"?weeks="+ weekDays, "");
	});
	$("#next-week").click(function(param) {
		setDate(addDate(currentFirstDate, 7));
		updateWeekButtonText();
		var url =param.currentTarget.value;
		var weekDays =cells[0].innerHTML+"T"+cells[6].innerHTML;
		$('#quantity-week').load(url+"?weeks="+ weekDays, "");
	});
})