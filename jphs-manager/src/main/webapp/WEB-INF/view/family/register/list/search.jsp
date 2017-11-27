<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/family/register/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>医院：</td>
			<td width="200">
				<input type="text" name="hospital" id="hospital" value="${familyRegister.hospital}" />
			</td>
			<td>科室：</td>
			<td width="200">
				<input type="text" name="department" id="department" value="${familyRegister.department}" />
			</td>
			<td>行程名称：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${familyRegister.title}" />
			</td>
			<td>拨打电话：</td>
			<td width="200">
				<input type="text" name="phone" id="phone" value="${familyRegister.phone}" />
			</td>
			<td>预约时间：</td>
			<td width="200">
				<input type="text" name="appointmentTime" id="appointmentTime" value="${familyRegister.appointmentTime}" />
			</td>
			<td>星期：</td>
			<td width="200">
				<input type="text" name="week" id="week" value="${familyRegister.week}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

