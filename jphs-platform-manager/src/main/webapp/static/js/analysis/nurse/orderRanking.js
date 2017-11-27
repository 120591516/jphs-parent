$(document).ready(function() {
			var quantityYearChart = echarts.init(document
					.getElementById("quantityYearChart"));
			var year = $('#this-year').text();
			var yearOption = echart_bar(year + '服务人员订单量排名TOP10', xAxisData, '订单量',
					yAxisData);
			// 为echarts对象加载数据
			if(xAxisData.length>0){
				quantityYearChart.setOption(yearOption);
			}else{
				$('.marage_right_content_statis').text('暂无数据！！！');
			}
			$("#search").click(function() {
				var city=$("#city").val();
				var goodsId=$("#goodsId").val();
				var year=$("#this-year").text().substring(0,4);
				var url ="/nurse/statistics/orderRankSearch.jhtml";
				$('#quantity-year').load(url+"?year="+ year+"&goodsId="+goodsId+"&city="+city,"");
			});
			// 清除
			$("#clear").on("click", function() {
				$("#city").val("");
				$("#goodsId").val("");
			});
});