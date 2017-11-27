<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/activity/promotion/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>活动类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;"  name="type" id="type">
					<option value="">全部</option>
					<option value="1" <c:if test="${activityPromotion.type==1}">selected="selected"</c:if>>立减</option>
					<option value="2" <c:if test="${activityPromotion.type==2}">selected="selected"</c:if>>首单立减</option>
					<option value="3" <c:if test="${activityPromotion.type==3}">selected="selected"</c:if>>第二件立减</option>
				</select>
			</td>
			<td>资源类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;"  name="resourceType" id="resourceType">
					<option value="">全部</option>
					<option value="1" <c:if test="${activityPromotion.resourceType==1}">selected="selected"</c:if>>服务</option>
					<option value="2" <c:if test="${activityPromotion.resourceType==2}">selected="selected"</c:if>>商品</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>资源名称：</td>
			<td width="200">
				<input type="text" name="goodsTitle" id="goodsTitle" value="${activityPromotion.goodsTitle}" />
			</td>
		</tr>
		<%-- <tr>
			<td>活动开始时间：</td>
			<td width="200">
				<input type="text" name="beginTime" id="beginTime" value="${activityPromotion.beginTime}" />
			</td>
			<td>活动束结时间：</td>
			<td width="200">
				<input type="text" name="endTime" id="endTime" value="${activityPromotion.endTime}" />
			</td>
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

