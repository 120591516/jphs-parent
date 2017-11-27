<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/relation/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>姓名：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${jkwyRelation.name}" />
			</td>
			<td>电话：</td>
			<td width="200">
				<input type="text" name="phone" id="phone" value="${jkwyRelation.phone}" />
			</td>
			<td>性别：</td>
			<td width="200">
				<input type="text" name="sex" id="sex" value="${jkwyRelation.sex}" />
			</td>
			<td>关系：</td>
			<td width="200">
				<input type="text" name="relation" id="relation" value="${jkwyRelation.relation}" />
			</td>
			<td>身份证：</td>
			<td width="200">
				<input type="text" name="sfz" id="sfz" value="${jkwyRelation.sfz}" />
			</td>
			<td>出生日期：</td>
			<td width="200">
				<input type="text" name="birthday" id="birthday" value="${jkwyRelation.birthday}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

