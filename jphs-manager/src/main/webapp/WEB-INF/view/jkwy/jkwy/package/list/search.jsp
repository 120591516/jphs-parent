<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/package/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>标题：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${jkwyPackage.title}" />
			</td>
			<td>副标题：</td>
			<td width="200">
				<input type="text" name="subTitle" id="subTitle" value="${jkwyPackage.subTitle}" />
			</td>
			<%-- <td>超文本介绍内容：</td>
			<td width="200">
				<input type="text" name="content" id="content" value="${jkwyPackage.content}" />
			</td> --%>
		</tr>
		<%-- <tr>
			<td>排序：</td>
			<td width="200">
				<input type="text" name="sort" id="sort" value="${jkwyPackage.sort}" />
			</td>
			<td>排序：</td>
			<td width="200">
				<input type="text" name="sort" id="sort" value="${jkwyPackage.sort}" />
			</td>
			<td>排序：</td>
			<td width="200">
				<input type="text" name="sort" id="sort" value="${jkwyPackage.sort}" />
			</td>
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

