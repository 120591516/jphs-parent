/**
 * 用于设置单一柱状图的数据
 */
/**
 * @param textStr
 *            标题
 * @param xAxisData
 *            X轴数据
 * @param name
 *            简称
 * @param barData
 *            y周数据
 * @returns {___anonymous76_1474}
 */
function echart_bar(textStr, xAxisData, name, barData) {
	var barOption = {
		title : {
			text : textStr
		},
		color : [ '#3398DB' ],
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : xAxisData,
			axisTick : {
				alignWithLabel : true
			}
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : name,
			type : 'bar',
			barWidth : '60%',
			data : barData
		} ]

	};
	return barOption
}