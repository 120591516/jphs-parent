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
			<jphs:hasPermission url="/order/statistics/getExcel.jhtml">
				<!-- <button type="button" class="btn btn-info public_btn" data-toggle="modal" data-target="#myModal">导出</button> -->
			</jphs:hasPermission>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="border:0">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<p class="text-center" style="font-size: 18px">
			安全验证
			</p>
				<div class="form-group">
						<label class="control-label col-md-3 text-right">手机号:</label>
						<div class="controls  col-md-6">
							${fn:substring(session_user.phone, 0, 3)}****
							   ${fn:substring(session_user.phone, 7, 11)}
						</div>
					</div> 
					<div class="clearfix"></div>
			 	<div class="form-group">
						<label class="control-label col-md-3 text-right">验证码:</label>
						<div class="controls  col-md-4">
							<input type="text" value="" id="verificattionCode" name="verificattionCode" class="form-control">
						</div>
						<div class="controls  col-md-2">
						<button style="width: 95px;" type="button" class="public-info" onclick="putcode();">发送验证码</button>
						</div>
					</div> 
		<div class="clearfix"></div>
			<div class="modal-footer" style="border:0;text-align:center">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button> -->
				<form id="getexcel" method="get" action="/order/statistics/getExcel.jhtml">
					<input type="hidden" name="year" value="${fn:substring(year,1,5) }">
				</form>
				<button class="btn btn-primary" onclick="getexcel();">导出</button>
			</div>
		</div>
	</div>
</div>