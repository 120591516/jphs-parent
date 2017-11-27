$(document).ready(function() {
			var quantityYearChart = echarts.init(document
					.getElementById("quantityYearChart"));
			var year = $('#this-year').text();
			console.info(year);
			var yearOption = echart_bar(year + '服务人员发布的服务排名TOP10', xAxisData, '发布量',
					yAxisData);
			// 为echarts对象加载数据
			quantityYearChart.setOption(yearOption);
			/*$(".annual_select").change(function() {
				var goodsId =$(this).val();
				var url ="/order/statistics/serviceRankingAnnual.jhtml";
				var year=$('#this-year').text().substring(0,4);
				$('#annual-quantity-year').load(url+"?year="+ year+"&goodsId="+goodsId, getTotal());
			});*/
});