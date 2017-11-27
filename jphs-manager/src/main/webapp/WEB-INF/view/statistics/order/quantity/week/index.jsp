<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
var weekTimeList =${weekTimeList };
var weekDeviceName =${weekDeviceName};
var weekTotal=${weekTotal };
</script>
<div id="quantity-week">
	<div class="marage_right_title">
		<div style="display: none;">
			<table id="monitor" class="table table-striped">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<div class="form-group" style="background: #fff">
			<jphs:hasPermission url="/order/statistics/quantityByWeek.jhtml">
			<button id="next-week" class="search_btn" value="/order/statistics/quantityByWeek.jhtml">下一周</button>
			</jphs:hasPermission>
			&nbsp;&nbsp;
			<button id="this-week" class="search_btn" style="width: 110px;"></button>
			&nbsp;&nbsp;
			<jphs:hasPermission url="/order/statistics/quantityByWeek.jhtml">
			<button id="last-week" class="search_btn" value="/order/statistics/quantityByWeek.jhtml">上一周</button>
			</jphs:hasPermission>
		</div>
	</div>
	<div class="marage_right_content_statis">
		<!-- ECharts单文件引入 -->
		<div id="quantityWeekChart" class="chart_parent"></div>
	</div>
</div>
