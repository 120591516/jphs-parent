<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/order/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>单号：</td>
			<td width="200">
				<input type="text" name="no" id="no" value="${jkwyOrder.no}" />
			</td>
			<td>下单人：</td>
			<td width="200">
				<input type="text" name="creatorName" id="creatorName" value="${jkwyOrder.creatorName}" />
			</td>
			<td>所在地区：</td>
			<td width="200">
				<input type="text" name="address" id="address" value="${jkwyOrder.address}" />
			</td>
		</tr>
		<tr>
				<td>下单时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="bgTime"
								name="bgTime"
								value="${jkwyOrder.bgTime}"
								size="14" placeholder="请选择搜索下单开始时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar" title="选择日期"></span></span>  -->
						</div>
						</td><td>--
							</td><td>
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="enTime"
								name="enTime"
								value="${jkwyOrder.enTime}"
								size="14" placeholder="请选择搜索下单结束时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar" title="选择日期"></span></span>  -->
						</div>
					</div></td> 
			</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

