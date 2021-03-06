﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center" style="width:100%;">
	<thead>
		<tr >
			<th width="30"></th>
			<th>主订单id</th>
			<th>单次价格</th>
			<th>执行人</th>
			<th>患者姓名</th>
			<th>患者电话</th>
			<th>指定医生</th>
			<th>进度</th>
			<th>预约时间</th>
			<th>出发时间</th>
			<th>开始服务时间</th>
			<th>结束服务时间</th>
			<th>提醒时间</th>
			<th>完成订单时间</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.orderId}"/></td>
							<td><c:out value="${e.price}"/></td>
							<td><c:out value="${e.nurseId}"/></td>
							<td><c:out value="${e.patientName}"/></td>
							<td><c:out value="${e.patientPhone}"/></td>
							<td><c:out value="${e.expectorDoctor}"/></td>
							<td><c:out value="${e.schedule}"/></td>
							<td><fmt:formatDate value="${e.appointmentTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.setoutTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.startServiceTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.endServiceTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.remindTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.confirmTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td>
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							<a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a>
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