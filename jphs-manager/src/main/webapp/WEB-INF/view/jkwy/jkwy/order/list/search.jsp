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
			<td>推广码：</td>
			<td width="200">
				<input type="text" name="code" id="code" value="${jkwyOrder.code}" />
			</td>
		</tr>
		<tr>
			<td>上门地址：</td>
			<td width="200">
				<input type="text" name="address" id="address" value="${jkwyOrder.address}" />
			</td>
			<td>详细地址：</td>
			<td width="200">
				<input type="text" name="detailAddress" id="detailAddress" value="${jkwyOrder.detailAddress}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

