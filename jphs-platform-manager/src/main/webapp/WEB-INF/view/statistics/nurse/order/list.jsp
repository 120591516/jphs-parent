<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form method="get" action="/nurse/statistics/orderRank.jhtml" id="serach-form">

<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>区域</th>
			<th>接单量</th>
			<th>丢单量</th>
			<th>丢单比例</th>
			<th>区域排名</th>
			<th>全国排名</th>
		</tr>
	</thead>
	 <tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td>${s.index+1}</td>
						<td>${e.name}</td>
						<td>${e.age}</td>
						<td>${e.addressN}</td>
						<td>${e.total}</td>
						<td>${e.cancelNum}</td>
						<td>${e.cancelpPercent}</td>
						<td>${e.cityRank}</td>
						<td>${e.ranking-18}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="20" align="center">没有可显示的记录。</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody> 
</table>
<div class="page">
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>
</form>