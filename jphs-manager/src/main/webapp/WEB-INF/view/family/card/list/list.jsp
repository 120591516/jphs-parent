<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
	<thead>
		<tr >
			<th width="30"></th>
			<th width="100">类型</th>
			<th width="200">批次号</th>
			<th width="160">总数</th>
			<th width="160">已兑换</th>
			<th width="160">未兑换</th>
			<th width="100">创建人</th>
			<th width="120">创建时间</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td>
								<c:if test="${e.type == 0}">虚拟卡</c:if>
								<c:if test="${e.type == 1}">实体卡</c:if>
							</td>
							<td><c:out value="${e.batch_no}"/></td>
							<td><c:out value="${e.fmsum}"/></td>
							<td><c:out value="${e.fmsumw}"/></td>
							<td><c:out value="${e.fmsumy}"/></td>
							<td><c:out value="${e.creator_name}"/></td>
							<td><fmt:formatDate value="${e.create_time}" pattern="yy-MM-dd HH:mm"/></td>
							<td>
							<a onclick="redirectDetailPage('${e.batch_no}')">
								<img src="/static/images/chakan.png">
							</a>								
							<%-- <a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a> --%>									
							<%-- <a onclick="deleteById('${e.id}')">
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
