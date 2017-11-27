<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jkwy/package/content/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>：</td>
			<td width="200">
				<input type="text" name="jkwyPackagePriceId" id="jkwyPackagePriceId" value="${jkwyPackageContent.jkwyPackagePriceId}" />
			</td>
			<td>服务标题：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${jkwyPackageContent.title}" />
			</td>
			<td>超文本服务内容介绍：</td>
			<td width="200">
				<input type="text" name="content" id="content" value="${jkwyPackageContent.content}" />
			</td>
			<td>总服务次数：</td>
			<td width="200">
				<input type="text" name="number" id="number" value="${jkwyPackageContent.number}" />
			</td>
			<td>剩余服务次数：</td>
			<td width="200">
				<input type="text" name="surplusNumber" id="surplusNumber" value="${jkwyPackageContent.surplusNumber}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

