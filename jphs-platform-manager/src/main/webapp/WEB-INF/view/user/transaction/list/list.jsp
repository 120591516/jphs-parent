<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="110">昵称</th>
			<th width="110">手机号</th>
			<th width="255">订单编号</th>
			<th>金额</th>
			<th>积分</th>
			<th width="125">操作</th>
			<th width="110">支付方式</th>
			<th>交易号</th>
			<th width="120">交易时间</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.name}" /></td>
						<td><c:out value="${e.phone}" /></td>
						<td><c:out value="${e.orderId}" /></td>
						<td>
							<c:if test="${e.amount == null}">￥0.00</c:if>
							<c:if test="${e.amount != null}"><fmt:formatNumber value="${e.amount }" type="currency"/></c:if>
						</td>
						<td><c:out value="${e.score}" /></td>
						<td><c:if test="${e.operate == 2}">充值</c:if> <c:if
								test="${e.operate == 3}">消费</c:if></td>
						<td><c:if test="${e.payType == 1}">支付宝</c:if> <c:if
								test="${e.payType == 2}">微信</c:if> <c:if
								test="${e.payType == 3}">余额宝</c:if> <c:if
								test="${e.payType == 4}">银联</c:if> <c:if
								test="${e.payType == 5}">vip卡支付</c:if></td>
						<td><c:out value="${e.outTradeNo}" /></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
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