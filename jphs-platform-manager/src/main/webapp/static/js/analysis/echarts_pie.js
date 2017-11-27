/**
 * 用于设置单一饼图的数据
 */
/**
 * 封装饼图series Data数据
 * 
 * @param keyArray
 *            名称的数组
 * @param valueArray
 *            值得数组
 */
function echart_pie_series(keyArray, valueArray) {
	var data = new Array();
	for (var i = 0; i < keyArray.length; i++) {
		// data.push("{value:"+valueArray[i]+",name:'"+keyArray[i]+"}");
		var a = "{value:" + valueArray[i] + ",name:'" + keyArray[i] + "'}";
		data[i] = (eval('(' + a + ')'));
	}
	return data;
}
/**
 * @param textStr
 *            标题
 * @param xAxisData
 *            图例内容
 * @param pieDataName
 *            扇形数据简称
 * @param pieData
 *            扇形数据
 * @returns {___anonymous76_1474}
 */

function echart_pie(textStr, legendData, pieDataName, pieData) {
	var pieOption = {
		title : {
			text : textStr,
			x : 'left'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : '10px',
			y : '70px',
			data : legendData
		},
		toolbox : {
			x : '350px',
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 425
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : pieDataName,
			type : 'pie',
			// 半径
			radius : '50%',
			// 饼图的偏移量，以及圆心的位置
			center : [ '70%', '60%' ],
			data :pieData
		} ]
	};
	return pieOption
}