<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" /> 数据统计 <i
		class="public1-horn-45"></i>服务人员统计<i class="public1-horn-45"></i>人员属性分析
</div>
<script type="text/javascript">
var nurseJob = ${changeJob.nurseJob};
var nurseJobValue = ${changeJob.nurseJobValue};
var nurseAddress = ${changeAddress.nurseAddress};
var nurseAddressValue = ${changeAddress.nurseAddressValue};
var nurseAge = ${changeAge.nurseAge};
var nurseAgeValue = ${changeAge.nurseAgeValue};
var nurseJobtitle = ${changeJobtitle.nurseJobtitle};
var nurseJobtitleValue = ${changeJobtitle.nurseJobtitleValue};
</script>
<div class="marage_right_content_statis" style="height: 440px">
	<div id="nurse-city" class="marage_right_content_pie">
		<!-- ECharts单文件引入 -->
		<div id="nurse-city-chart" class="chart_parent"></div>
	</div>
	<div id="nurse-job" style="float: left;"
		class="marage_right_content_pie">
		<!-- ECharts单文件引入 -->
		<div id="nurse-job-chart" class="chart_parent"></div>
	</div>
</div>
<div class="marage_right_content_statis" style="height: 440px">
	<div id="nurse-city" class="marage_right_content_pie">
		<!-- ECharts单文件引入 -->
		<div id="nurse-age-chart" class="chart_parent"></div>
	</div>
	<div id="nurse-job" style="float: left;"
		class="marage_right_content_pie">
		<!-- ECharts单文件引入 -->
		<div id="nurse-jobType-chart" class="chart_parent"></div>
	</div>
</div>