<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/column/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>栏目名称：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${column.name}" />
			</td>
			<td>状态：</td>
			<td width="200">
				<select class="marage_select" id="status"
					name="status">
					<option value="">全部</option>
						<option value="1"
							<c:if test="${column.status==1}">selected="selected"</c:if>>已启用</option>
							<option value="0"
							<c:if test="${column.status==0}">selected="selected"</c:if>>未启用</option>
							<option value="-1"
							<c:if test="${column.status==-1}">selected="selected"</c:if>>已停用</option>
				</select>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

