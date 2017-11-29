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
			<th>昵称<br>手机号</th>
			<!-- <th>手机号</th> -->
			<!-- <th>优惠券编号</th> -->
			<th>批次号</th>
			<th>卡号</th>
			<th>优惠券类型</th>
			<th>优惠券金额</th>
			<th>领取时间<br>到期时间</th>
			<!-- <th>到期时间</th> -->
			<th>使用时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.creatorName}" /><br><c:out value="${e.phone}" /></td>
						<%-- <td><c:out value="${e.phone}" /></td> --%>
						<td><c:out value="${e.voucherRepertory.voucher.batchNo}" /></td>
						<td><c:out value="${e.voucherRepertory.no}" /></td>
						<td>
							<c:if test="${e.voucherRepertory.voucher.type ==1 }">现金券</c:if>
							<c:if test="${e.voucherRepertory.voucher.type ==2 }">满减券</c:if>
							<c:if test="${e.voucherRepertory.voucher.type ==3 }">折扣券</c:if>
						</td>
						<td>
							<c:if test="${e.voucherRepertory.voucher.type ==1 }"><fmt:formatNumber value="${e.voucherRepertory.amount }" type="currency"/></c:if>
							<c:if test="${e.voucherRepertory.voucher.type ==2 }"><fmt:formatNumber value="${e.voucherRepertory.amount }" type="currency"/></c:if>
							<c:if test="${e.voucherRepertory.voucher.type ==3 }">打${e.voucherRepertory.amount*10 }折</c:if>
						</td>
						<td>
							<fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /><br>
							<fmt:formatDate value="${e.endTime}" pattern="yy-MM-dd HH:mm" />
						</td>
						<td><fmt:formatDate value="${e.useTime}" pattern="yy-MM-dd HH:mm" /></td>
						<td>
							<c:if test="${e.status ==0 }"><span style="color: #F0BB1C;">未使用</span></c:if>
							<c:if test="${e.status ==1 }"><span style="color: #34BC2C;">已使用</span></c:if>
							<c:if test="${e.status ==-1 }"><span >已停用</span></c:if>
						</td>
						<td>
							<jphs:hasPermission url="/voucher/user/delete.jhtml">
								<c:if test="${e.status ==0 }">
									<a onclick="deleteById('${e.id}')">
										<img src="/static/images/shanchu.png">
									</a>
								</c:if>
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
