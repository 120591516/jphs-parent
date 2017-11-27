<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/package/price/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>：</td>
			<td width="200">
				<input type="text" name="jkwyPackageId" id="jkwyPackageId" value="${jkwyPackagePrice.jkwyPackageId}" />
			</td>
			<td>价格标题：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${jkwyPackagePrice.title}" />
			</td>
			<td>价原：</td>
			<td width="200">
				<input type="text" name="oldPrice" id="oldPrice" value="${jkwyPackagePrice.oldPrice}" />
			</td>
			<td>销售价：</td>
			<td width="200">
				<input type="text" name="price" id="price" value="${jkwyPackagePrice.price}" />
			</td>
			<td>服务时长：</td>
			<td width="200">
				<input type="text" name="serviceTime" id="serviceTime" value="${jkwyPackagePrice.serviceTime}" />
			</td>
			<td>单位：</td>
			<td width="200">
				<input type="text" name="unit" id="unit" value="${jkwyPackagePrice.unit}" />
			</td>
			<td>支持套餐人数：</td>
			<td width="200">
				<input type="text" name="supportNumber" id="supportNumber" value="${jkwyPackagePrice.supportNumber}" />
			</td>
			<td>支持续费（0不支持，1支持）：</td>
			<td width="200">
				<input type="text" name="supportFee" id="supportFee" value="${jkwyPackagePrice.supportFee}" />
			</td>
			<td>支持变更升级套餐（0不支持，1支持）：</td>
			<td width="200">
				<input type="text" name="supportChange" id="supportChange" value="${jkwyPackagePrice.supportChange}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

