/**
 * 该js是用于设置多种对比数据的统计数据
 */
/**
 * 给echarts 设置 series
 * @param dataNameList
 * @param dataNum
 * @returns {Array}
 */
function pushSeriesData(dataNameList, dataNum) {
	var series = [];
	for (var i = 0; i < dataNameList.length; i++) {
		series.push({
			name : dataNameList[i],
			type : 'bar',
			data : dataNum[i],
			//最大值，最小值
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			//平均值
			   markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            },
		});
	}
	return series;
}

/**
 * 给echarts 设置option 
 * @param textStr title 标题
 * @param dataNameList 图标数据集合（图例）
 * @param xAxisList x轴的数据
 * @param seriesData 图表数据
 */
function pushOption(textStr, dataNameList, xAxisList, seriesData) {
	var pvOption = {
		title : {
			text : textStr,
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : dataNameList
		},
		//右上角工具条
		toolbox : {
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
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		//是否启用拖拽重计算特性，默认关闭
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : xAxisList
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value}次'
			}
		} ],
		series : seriesData
	};
	return pvOption;
}