<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	 var monthTimeList =${monthTimeList };
	 var monthDeviceName =${monthDeviceName};
	 var monthTotal=${monthTotal };
</script>
<div id ="quantity-month">
	<div class="marage_right_title">
		<div class="form-group" style="background: #fff">
			<jphs:hasPermission url="/order/statistics/quantityByMonth.jhtml">
			<button id="next-month" class="search_btn" value="/order/statistics/quantityByMonth.jhtml">下一月</button>
			</jphs:hasPermission>
			&nbsp;&nbsp;
			<button id="this-month" class="search_btn" style="width: 40px;"></button>
			&nbsp;&nbsp;
			<jphs:hasPermission url="/order/statistics/quantityByMonth.jhtml">
			<button id="last-month" class="search_btn" value="/order/statistics/quantityByMonth.jhtml">上一月</button>
			</jphs:hasPermission>
		</div>
	</div>
	<div class="marage_right_content_statis">
		<!-- ECharts单文件引入 -->
		<div id="quantityMonthChart" class="chart_parent"></div>
	</div>
</div>