$(document)
		.ready(
				function() {
					$('#myTabs li').removeClass("active");
					/**
					 * 周统计图
					 */
					var quantityWeekChart = echarts.init(document
							.getElementById("quantityWeekChart"));
					var weekSeries = pushSeriesData(weekDeviceName, weekTotal);
					var weekOption = pushOption('护士注册量及审核量周统计', weekDeviceName,
							weekTimeList, weekSeries)
					// 为echarts对象加载数据
					quantityWeekChart.setOption(weekOption);
					/**
					 * 月统计图
					 */
					var quantityMonthChart = echarts.init(document
							.getElementById("quantityMonthChart"));
					var monthSeries = pushSeriesData(monthDeviceName,
							monthTotal);
					var monthOption = pushOption('护士注册量及审核量月统计',
							monthDeviceName, monthTimeList, monthSeries)
					// 为echarts对象加载数据
					quantityMonthChart.setOption(monthOption);
					/**
					 * 年统计图
					 */
					var quantityYearChart = echarts.init(document
							.getElementById("quantityYearChart"));
					var yearSeries = pushSeriesData(yearDeviceName, yearTotal);
					var yearOption = pushOption('护士注册量及审核量年统计', yearDeviceName,
							yearTimeList, yearSeries)
					// 为echarts对象加载数据
					quantityYearChart.setOption(yearOption);
					console.info(context);
					if (context == '护士') {
						$('#nurse').addClass("active");
					}
					if (context == '康复师') {
						$('#kfs').addClass("active");
					}
					if (context == '母婴师') {
						$('#mys').addClass("active");
					}
					if (context == '') {
						$('#all').addClass("active");
					}
					$('#myTabs a')
							.click(
									function(e) {
										var context = $(this).text();
										var type = '';
										if (context == '护士') {
											type = 1;
										}
										if (context == '母婴师') {
											type = 3;
										}
										if (context == '康复师') {
											type = 2;
										}
										window.location.href = "/person/statistics/nurseRegister.jhtml?type="
												+ type
									})
				})
