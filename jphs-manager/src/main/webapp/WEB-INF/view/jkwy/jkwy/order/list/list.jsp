<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center">
	<thead>
		<tr >
			<th width="30"></th>
			<th>单号</th>
			<th>优惠金额</th>
			<th>活动id</th>
			<th>活动优惠价格</th>
			<th>推广码</th>
			<th>原价</th>
			<th>销售价</th>
			<th>支付价</th>
			<th>支付时间</th>
			<th>套餐结束日期</th>
			<th>上门地址</th>
			<th>详细地址</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.no}"/></td>
							<td><c:out value="${e.voucherPrice}"/></td>
							<td><c:out value="${e.activityPromotionId}"/></td>
							<td><c:out value="${e.activityPromotionPrice}"/></td>
							<td><c:out value="${e.code}"/></td>
							<td><c:out value="${e.oldPrice}"/></td>
							<td><c:out value="${e.price}"/></td>
							<td><c:out value="${e.payPrice}"/></td>
							<td><fmt:formatDate value="${e.payTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.endTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><c:out value="${e.address}"/></td>
							<td><c:out value="${e.detailAddress}"/></td>
							<td>
							<jphs:hasPermission url="/jkwy/order/detail.jhtml">
								<a onclick="redirectDetailPage('${e.id}')">
									<img src="/static/images/chakan.png">
								</a>
							</jphs:hasPermission>								
							<%-- <a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							<a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a> --%>
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
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
	
</div>