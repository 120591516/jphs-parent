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
			<jphs:hasPermission url="/person/statistics/nurseRegisterByAnnual.jhtml">
			<button id="annual-next-year" class="search_btn"
				value="/person/statistics/nurseRegisterByAnnual.jhtml" style="display: none;">下一年</button>
			</jphs:hasPermission>
			&nbsp;&nbsp;
			<button id="annual-this-year" class="search_btn" style="width: 70px;"></button>
			&nbsp;&nbsp;
			<jphs:hasPermission url="/person/statistics/nurseRegisterByAnnual.jhtml">
			<button id="annual-last-year" class="search_btn"
				value="/person/statistics/nurseRegisterByAnnual.jhtml">上一年</button>
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
									<td>
										<c:if test="${ele=='0' }"><span class="month_${t.index }_red day_${s.index+1 }_red" title="审核量" style="color:red">0</span>/<span class="month_${t.index }_blue day_${s.index+1 }_blue blue" title="注册量" style="color:blue;">0</span></c:if>
										<c:if test="${ele!='0' }">
											<c:set value="${ fn:split(ele, '/') }" var="names" />
											<span class="month_${t.index }_red day_${s.index+1 }_red" title="审核量" style="color: red;">${names[0] }</span>/
											<span class="month_${t.index }_blue day_${s.index+1 }_blue" title="注册量" style="color: blue;">${names[1] }</span>
										</c:if>
										
									</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<td>
							<span class="dayTotal_red dayTotal_${s.index+1 }_red" title="审核量" style="color: red;"></span>/
							<span class="dayTotal_blue dayTotal_${s.index+1 }_blue" title="注册量" style="color: blue;"></span>
						</td>
					</tr>
				</c:forEach>
				<tr class="t_footer">
					<c:forEach items="${requestScope.months }" var="row" varStatus="s">
						<c:choose>
							<c:when test="${s.index==0 }">
								<td>合计</td>
							</c:when>
							<c:otherwise>
								<td>
									<span class="monthTotal_red monthTotal_${s.index }_red" title="审核量" style="color: red;"></span>/
									<span class="monthTotal_blue monthTotal_${s.index }_blue" title="注册量" style="color: blue;"></span>
								</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<td>
						<span class="total_red" title="审核量" style="color: red;"></span>/
						<span class="total_blue" title="注册量" style="color: blue;"></span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>