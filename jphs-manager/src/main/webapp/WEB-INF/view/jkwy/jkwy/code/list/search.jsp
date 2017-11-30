<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/code/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>活动码CODE：</td>
			<td width="200">
				<input type="text" name="code" id="code" value="${jkwyCode.code}" />
			</td>
			<td>推广人ID：</td>
			<td width="200">
				<input type="text" name="recommendId" id="recommendId" value="${jkwyCode.recommendId}" />
			</td>
			</tr>
			<tr>
			
			<td>推广人姓名：</td>
			<td width="200">
				<input type="text" name="recommendName" id="recommendName" value="${jkwyCode.recommendName}" />
			</td>
			<td>推广人手机号：</td>
			<td width="200">
				<input type="text" name="recommendPhone" id="recommendPhone" value="${jkwyCode.recommendPhone}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

