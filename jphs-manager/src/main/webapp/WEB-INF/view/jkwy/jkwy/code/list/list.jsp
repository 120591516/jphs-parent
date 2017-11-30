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
			<th>活动码CODE</th>
			<th>推广人ID</th>
			<th>推广人姓名</th>
			<th>推广人手机号</th>
			<th>添加时间</th>
			<th>状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.code}"/></td>
							<td><c:out value="${e.recommendId}"/></td>
							<td><c:out value="${e.recommendName}"/></td>
							<td><c:out value="${e.recommendPhone}"/></td>
							<td><fmt:formatDate value="${e.createTime }" pattern="yy-MM-dd HH:mm" /></td>
							<td><c:if test="${e.status == 0}">
									<span style="color: #34BC2C;"><c:out value="使用中" /></span>
								</c:if> <c:if test="${e.status == -1}">
									<span style="color: #F0BB1C;"><c:out value="已停用" /></span>
								</c:if></td>
							<td>
							<jphs:hasPermission url="/jkwy/code/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							</jphs:hasPermission>
							<jphs:hasPermission url="/jkwy/code/redirectUpdate.jhtml">
								<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/jkwy/code/delete.jhtml">
							<c:if test="${e.status==0}">
								<a title="禁用" onclick="deleteById('${e.id}',-1)">
								<img style="width: 19px;height: 17px;"  src="/static/images/shanchu.png">
							</a>
							</c:if>
							<c:if test="${e.status==-1}">
								<a title="激活" onclick="deleteById('${e.id}',0)">
								<img style="width: 19px;height: 17px;" src="/static/images/activation.png">
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
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
	
</div>