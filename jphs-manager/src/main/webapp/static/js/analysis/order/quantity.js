$(document).ready(
		function() {
			/**
			 * 周统计图
			 */
			var quantityWeekChart = echarts.init(document.getElementById("quantityWeekChart"));
			var weekSeries = pushSeriesData(weekDeviceName, weekTotal);
			var weekOption = pushOption('订单量周统计', weekDeviceName, weekTimeList, weekSeries)
			// 为echarts对象加载数据
			if(weekDeviceName.length>0){
				quantityWeekChart.setOption(weekOption);
			}else{
				$('#quantityWeekChart').text('暂无数据！！！');
			}
			/**
			 * 月统计图
			 */
			var quantityMonthChart = echarts.init(document.getElementById("quantityMonthChart"));
			var monthSeries = pushSeriesData(monthDeviceName, monthTotal);
			var monthOption = pushOption('订单量月统计', monthDeviceName, monthTimeList, monthSeries)
			// 为echarts对象加载数据
			if(monthDeviceName.length>0){
				quantityMonthChart.setOption(monthOption);
			}else{
				$('#quantityMonthChart').text('暂无数据！！！');
			}
			/**
			 * 年统计图
			 */
			var quantityYearChart = echarts.init(document.getElementById("quantityYearChart"));
			var yearSeries = pushSeriesData(yearDeviceName, yearTotal);
			var yearOption = pushOption('订单量年统计', yearDeviceName, yearTimeList, yearSeries)
			// 为echarts对象加载数据
			if(yearDeviceName.length>0){
				quantityYearChart.setOption(yearOption);
			}else{
				$('#quantityYearChart').text('暂无数据！！！');
			}
		})
function getexcel(){
	if(yzCode($("#verificattionCode").val())){
		$('#getexcel').submit();
	}else{
		alert("验证码错误");
	}
}