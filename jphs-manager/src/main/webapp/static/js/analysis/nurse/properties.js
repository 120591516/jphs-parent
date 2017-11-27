$(document).ready(function() {
	/**
	 * 服务人员区域分布
	 */
	var nurse_city_chart = echarts.init(document.getElementById("nurse-city-chart"));
	var addressData = echart_pie_series(nurseAddress, nurseAddressValue);
	var addressOption = echart_pie('服务人员区域分布', nurseAddress,'区域分布', addressData);
	// 为echarts对象加载数据
	nurse_city_chart.setOption(addressOption);
	/**
	 * 服务人员职业分布
	 */
	var nurse_job_chart = echarts.init(document.getElementById("nurse-job-chart"));
	var jobData = echart_pie_series(nurseJob, nurseJobValue);
	var jobOption = echart_pie('服务人员职业分布', nurseJob,'职业分布', jobData);
	// 为echarts对象加载数据
	nurse_job_chart.setOption(jobOption);
	/**
	 * 服务人员年龄分布
	 */
	var nurse_age_chart = echarts.init(document.getElementById("nurse-age-chart"));
	var ageData = echart_pie_series(nurseAge, nurseAgeValue);
	var ageOption = echart_pie('服务人员年龄分布', nurseAge,'年龄分布', ageData);
	// 为echarts对象加载数据
	nurse_age_chart.setOption(ageOption);
	/**
	 * 服务人员年龄分布
	 */
	var nurse_jobType_chart = echarts.init(document.getElementById("nurse-jobType-chart"));
	var jobTypeData = echart_pie_series(nurseJobtitle, nurseJobtitleValue);
	var jobTypeOption = echart_pie('护士级别分析', nurseJobtitle,'级别分布', jobTypeData);
	// 为echarts对象加载数据
	nurse_jobType_chart.setOption(jobTypeOption);
})
