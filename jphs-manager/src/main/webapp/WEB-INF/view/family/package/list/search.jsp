<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/family/package/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>套餐名称：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${familyPackage.title}" />
			</td>
			<td>价格：</td>
			<td width="200">
				<input type="text" name="price" id="price" value="${familyPackage.price}" />
			</td>
		</tr>
		<tr>
			<td>套餐类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;" id="type" name="type">
					<option value="">全部</option>
					<c:if test="${familyPackage.type == 1}">
						<option value="1" selected="selected">单人版</option>
						<option value="2" >三人版</option>		
					</c:if>
					<c:if test="${familyPackage.type == 2}">
						<option value="1" >单人版</option>
						<option value="2" selected="selected">三人版</option>		
					</c:if>
					<c:if test="${familyPackage.type == null}">
						<option value="1" >单人版</option>
						<option value="2" >三人版</option>		
					</c:if>
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