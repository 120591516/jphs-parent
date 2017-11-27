<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	 var yearTimeList =${yearTimeList };
	 var yearDeviceName =${yearDeviceName};
	 var yearTotal=${yearTotal };
	 var flag =${flag};
</script>
<div id ="quantity-year">
	<div class="marage_right_title">
		<div class="form-group" style="background: #fff">
			<jphs:hasPermission url="/order/statistics/quantityByYear.jhtml">
			<button id="next-year" class="search_btn" value="/order/statistics/quantityByYear.jhtml">下一年</button>
			</jphs:hasPermission>	
			&nbsp;&nbsp;
			<button id="this-year" class="search_btn" style="width: 70px;"></button>
			&nbsp;&nbsp;
			<jphs:hasPermission url="/order/statistics/quantityByYear.jhtml">
			<button id="last-year" class="search_btn" value="/order/statistics/quantityByYear.jhtml">上一年</button>
			</jphs:hasPermission>
		</div>
	</div>
	<div class="marage_right_content_statis">
		<!-- ECharts单文件引入 -->
		<div id="quantityYearChart" class="chart_parent"></div>
	</div>
</div>