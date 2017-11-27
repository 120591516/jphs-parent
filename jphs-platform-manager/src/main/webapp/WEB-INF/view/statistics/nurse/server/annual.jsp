<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	var year =${year }; 
	var flag =${flag};
</script>
<div id="annual-quantity-year">
	<div class="marage_right_content">
		<table id="dateTable" class="data_table text-center"
			style="width: 100%;">
			<thead>
				<tr>
					<th width="30"></th>
					<th>服务名称</th>
					<th>发布数量</th>
					<th>人数</th>
					<th>发布比例</th>
					<th>接单量</th>
					<th>丢单量</th>
					<th>丢单比例</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allData}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.title}" /></td>
						<td><c:out value="${e.total}" /></td>
						<td><c:out value="${e.totalPerson}" /></td>
						<td><fmt:formatNumber value="${e.total/e.totalPerson}"
								 type="percent" /></td>
						<td><c:out value="${e.successOrderNum}" /></td>
						<td><c:out value="${e.cancelNum}" /></td>
						<td><fmt:formatNumber value="${e.cancelNum/(e.successOrderNum+e.cancelNum)}"
								 type="percent" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>