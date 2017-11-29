<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" class="data_table"
	style="width: 950px; margin-top: 10px">
	<thead>
		<tr>
			<th width="30">序号</th>
			<th>模板ID</th>
			<th>模板名称</th>
			<th>短信类型</th>
			<th>添加时间</th>
			<th>创建人</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.smsId}" /></td>
						<td><c:out value="${e.title}" /></td>
						<td><c:if test="${e.type == 1}">
								<c:out value="标准短信" />
							</c:if> 
							<c:if test="${e.type == 2}">
							<c:out value="推广短信 "/>
								</c:if>
						</td>
							<td><fmt:formatDate value="${e.createTime}"
								pattern="yyyy-MM-dd HH:mm" />
							</td>
							<td><c:out value="${e.creatorName}" /></td>
							<td>
							<jphs:hasPermission url="/sms/template/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/sms/template/redirectUpdate.jhtml">								
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/sms/template/delete.jhtml">									
							<a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
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
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
	
</div>