<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	var year =${year }; 
</script>
<div id="annual-quantity-year">
	<div class="marage_right_title">
		<div class="form-group" style="background: #fff">
			<button type="button" class="btn btn-info public_btn">导出</button>
			<jphs:hasPermission url="/order/statistics/quantityByAnnual.jhtml">
			<button id="annual-next-year" class="search_btn"
				value="/order/statistics/quantityByAnnual.jhtml" style="display: none;">下一年</button>
			</jphs:hasPermission>
			&nbsp;&nbsp;
			<button id="annual-this-year" class="search_btn" style="width: 70px;"></button>
			&nbsp;&nbsp;
			<jphs:hasPermission url="/order/statistics/quantityByAnnual.jhtml">
			<button id="annual-last-year" class="search_btn"
				value="/order/statistics/quantityByAnnual.jhtml">上一年</button>
			</jphs:hasPermission>
		</div>
	</div>
	<div class="marage_right_content">
		<table id="dateTable" class="data_table text-center"
			style="width: 100%;">
			<thead>
				<tr>
					<c:forEach items="${requestScope.months }" var="row" varStatus="s">
						<c:choose>
							<c:when test="${s.index!=0 }">
								<th >${row }月</th>
							</c:when>
							<c:otherwise>
								<th ></th>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<th>合计</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.allData }" var="row" varStatus="s">
					<tr class="bg_list_body">
						<c:forEach items="${row }" var="ele" varStatus="t">
							<c:choose>
								<c:when test="${t.index==0 }">
									<td>${ele }号</td>
								</c:when>
								<c:otherwise>
									<td class="month_${t.index } day_${s.index+1 }">${ele }</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<td id="dayTotal_${s.index+1 }" class="dayTotal">
					</tr>
				</c:forEach>
				<tr class="t_footer">
					<c:forEach items="${requestScope.months }" var="row" varStatus="s">
						<c:choose>
							<c:when test="${s.index==0 }">
								<td>合计</td>
							</c:when>
							<c:otherwise>
								<td id="monthTotal_${s.index }"></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<td id="total"></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>