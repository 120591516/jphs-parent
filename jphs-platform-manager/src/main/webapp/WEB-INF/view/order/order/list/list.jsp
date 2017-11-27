<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="135">订单编号</th>
			<th width="130">下单人</th>
			<!-- <th>手机号</th> -->
			<th>服务名称/规格次数</th>
			<th width="100">订单金额</th>
			<!-- <th>下单时间</th> -->
			<th width="110">预约时间</th>
			<!-- <th>类型</th> -->
			<th width="70">接单人</th>
			<th width="80">状态</th>
			<th width="50">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td>
							<c:if test="${e.type==2}">
								<p style="color:red;" title="抢单"><c:out value="${e.orderNo}" /></p>						
							</c:if>
							<c:if test="${e.type==1}">
								<p style="color:blue;" title="指定单"><c:out value="${e.orderNo}" /></p>
							</c:if>
						</td>
						<td style="text-align:left">
							<c:out value="${e.userName}" />
							<p>
							<c:out value="${e.phone}" /><p>
							<fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" />
						</td>
						<%-- <td><c:out value="${e.phone}" /></td> --%>
						<td style="text-align:left"><c:out value="${e.title}" /><p><c:out value="${e.spec}"/></td>
						<td><fmt:formatNumber value="${e.payPrice }" type="currency"/></td>
						<%-- <td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /> </td> --%>
						<td><fmt:formatDate value="${e.appointmentTime}"
								pattern="yy-MM-dd HH:mm" /></td>
					<%-- 	<td>
							<c:if test="${e.type==2}">抢单</c:if>
							<c:if test="${e.type==1}">指定单</c:if>
						</td> --%>
						<td><c:out value="${e.nurseName}" /></td>
						<td><c:if test="${e.schedule == 0}">待支付</c:if> <c:if
								test="${e.schedule == 1}">待接单</c:if> <c:if
								test="${e.schedule == 2}">已接单</c:if> <c:if
								test="${e.schedule == 3}">执行中</c:if> <c:if
								test="${e.schedule == 4}">待确定</c:if> <c:if
								test="${e.schedule == 5}">已完成</c:if> <c:if
								test="${e.schedule == 8}">已失效</c:if> <c:if
								test="${e.schedule == 6}"><c:if test="${e.otherStatus!=null}">
									<c:if test="${e.otherStatus==0}"><span style="color: #F0BB1C;">待处理</span></c:if>
									<c:if test="${e.otherStatus==1}"><span style="color: #34BC2C;">已退款</span></c:if>
								</c:if>
								<c:if test="${e.otherStatus==null}">已取消</c:if>
								</c:if> <c:if
								test="${e.schedule == 7}">申诉中</c:if></td>
						<td>
							<jphs:hasPermission url="/order/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')"> <img
									src="/static/images/chakan.png">
							</a>
							</jphs:hasPermission>
						 </td>
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