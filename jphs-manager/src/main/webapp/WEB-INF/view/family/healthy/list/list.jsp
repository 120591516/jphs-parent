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
			<th></th>
			<th>健康计划标题</th>
			<th>总周期</th>
			<th>相关病史</th>
			<th>病史内容，编辑器</th>
			<th>病史周期</th>
			<th>治疗计划</th>
			<th>计划内容，编辑器</th>
			<th>计划周期</th>
			<th>饮食安排</th>
			<th>饮食内容，编辑器</th>
			<th>饮食周期</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.familyMemberId}"/></td>
							<td><c:out value="${e.title}"/></td>
							<td><c:out value="${e.cycle}"/></td>
							<td><c:out value="${e.history}"/></td>
							<td><c:out value="${e.historyContent}"/></td>
							<td><c:out value="${e.historyCycle}"/></td>
							<td><c:out value="${e.plan}"/></td>
							<td><c:out value="${e.planContent}"/></td>
							<td><c:out value="${e.planCycle}"/></td>
							<td><c:out value="${e.diet}"/></td>
							<td><c:out value="${e.dietContent}"/></td>
							<td><c:out value="${e.dietCycle}"/></td>
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