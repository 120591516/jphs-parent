<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center">
	<thead>
		<tr >
			<th width="30">序号</th>
			<th>单号</th>
			<th>套餐</th>
			<th>原价￥</th>
			<th>销售价￥</th>
			<th>实付款￥</th>
			<th>下单人</th>
			<th>下单时间<br>结束时间</th>
			<th>剩余天数</th>
			<th>所在地区</th>
			<th>推广码<br><span  class="title_value">推广人ID</span></th>
			<th width="80">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.no}"/></td>
							<td><c:out value="${e.jkwyPackageName }"/></td>
							<td><c:out value="${e.oldPrice}"/></td>
							<td><c:out value="${e.price}"/></td>
							<td><c:out value="${e.payPrice}"/></td>
							<td>${e.creatorName }</td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm"/><br><fmt:formatDate value="${e.endTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td>
								<c:if test="${e.subDate>=0 }">
									<span title="剩余${e.subDate }天" style="color: green;">${e.subDate }</span>
								</c:if>
								<c:if test="${e.subDate<0 }">
									<span title="已过期${-e.subDate }天" style="color:red;">${-e.subDate }</span>
								</c:if>
							</td>
							<td><c:out value="${e.address}"/></td>
							<td><c:out value="${e.code}"/><br><span  class="title_value"><c:out value="${e.recommendId}"/></span></td>
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