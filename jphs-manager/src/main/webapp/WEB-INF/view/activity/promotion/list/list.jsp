<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width: 100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th>资源名称</th>
			<th>资源类型</th>
			<th>优惠价格</th>
			<th>活动类型</th>
			<th>活动起止日期</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>创建人</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:if test="${e.resourceType == 1}">${e.goodsTitle}</c:if>
							<c:if test="${e.resourceType == 2}">${e.commodityTitle}</c:if></td>
						<td><c:if test="${e.resourceType == 1}">服务</c:if> <c:if
								test="${e.resourceType == 2}">商品</c:if></td>
						<td><c:out value="${e.price}" /></td>
						<td><c:if test="${e.type == 1}">立减</c:if> <c:if
								test="${e.type == 2}">首单立减</c:if> <c:if test="${e.type == 3}">第二件立减</c:if>
						</td>
						<td><fmt:formatDate value="${e.beginTime}"
								pattern="yy-MM-dd HH:mm" />
							<p>
								<fmt:formatDate value="${e.endTime}"
									pattern="yy-MM-dd HH:mm" />
							</p></td>
						<td>
							<c:set var="nowDate">
								<fmt:formatDate value="<%=new Date()%>"
									pattern="yyyy-MM-dd HH:mm" type="date" />
							</c:set>
							<c:set var="beginDate">
								<fmt:formatDate value="${e.beginTime}"
									pattern="yyyy-MM-dd HH:mm" type="date" />
							</c:set>
							<c:set var="endDate">
								<fmt:formatDate value="${e.endTime}"
									pattern="yyyy-MM-dd HH:mm" type="date" />
							</c:set>  
							<c:if test="${beginDate gt nowDate}"><span style="color: #FFCC22">未开始</span></c:if>
							<c:if test="${endDate lt nowDate}">已结束</c:if>
							<c:if test="${beginDate le nowDate and nowDate le endDate}"><span style="color: #00DD00">进行中</span></c:if>
						</td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td>${e.creatorName }</td>
						<td><jphs:hasPermission
								url="/activity/promotion/detail.jhtml">
								<a onclick="redirectDetailPage('${e.id}')"> <img
									src="/static/images/chakan.png">
								</a>
							</jphs:hasPermission> <jphs:hasPermission
								url="/activity/promotion/redirectUpdate.jhtml">
								<a onclick="redirectUpdatePage('${e.id}')"> <img
									src="/static/images/xiugai.png">
								</a>
							</jphs:hasPermission> <jphs:hasPermission url="/activity/promotion/delete.jhtml">
								<a onclick="deleteById('${e.id}')"> <img
									src="/static/images/shanchu.png">
								</a>
							</jphs:hasPermission></td>
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