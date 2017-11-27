<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/commodity/type/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>类别名称：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${commodityType.name}" />
			</td>
			<td>所属类别：</td>
			<td width="200">
				<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="commodityTypeParentId"
							name="commodityTypeParentId">
							<option value="">全部</option>
							<c:forEach items="${ctpList}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${commodityType.commodityTypeParentId==e.id}">selected="selected"</c:if>>${e.name }</option>
							</c:forEach>
						</select>
					</div>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

