<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<!-- <th></th> -->
			<th width="200px">获取方式<!-- (1实付购买，2兑换码，3免费识别) --></th>
			<th width="200px">所属套餐</th>
			<th width="100px">套餐类型</th>
			<th width="200px">开始时间</th>
			<th width="200px">结束时间</th>
			<th width="100px">有效天数</th>
			<th width="100px">单位</th>
			<th width="200px">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:if test="${e.accessMode == 1}">
									实付购买
								</c:if> <c:if test="${e.accessMode == 2}">
									兑换码
								</c:if> <c:if test="${e.accessMode == 3}">
									免费识别
								</c:if></td>
						<td><c:out value="${e.familyPackage.title}"/></td>
						<td>
							<c:if test="${e.familyPackage.type ==1 }">
								单人版
							</c:if>
							<c:if test="${e.familyPackage.type ==2 }">
								三人版
							</c:if>
						</td>
						<%-- <td><c:out value="${e.validateCode}"/></td> --%>
						<td><fmt:formatDate value="${e.beginTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${e.endTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:out value="${e.day}" /></td>
						<td><c:out value="${e.unit}" /></td>
						<td>
							<jphs:hasPermission url="/family/mode/delete.jhtml">									
								<c:if test="${e.status == 0}">
									<a onclick="deleteById('${e.id}','1')"> 
										<img style="width: 20px;height: 20px;" src="/static/images/blockup.png">
									</a>
								</c:if>
								<c:if test="${e.status == 1}">
									<a onclick="deleteById('${e.id}','0')">
										<img style="width: 20px;height: 20px;" src="/static/images/startup.png">
									</a>
								</c:if>
							</jphs:hasPermission>
							<jphs:hasPermission url="/family/mode/detail.jhtml">
								<a onclick="redirectDetailPage('${e.id}')">
									<img src="/static/images/chakan.png">
								</a>
							</jphs:hasPermission>	
							<jphs:hasPermission url="/family/mode/insert.jhtml">					
								<a onclick="redirectUpdatePage('${e.id}')">
									<img  src="/static/images/xiugai.png">
								</a>		
							</jphs:hasPermission>	
							<jphs:hasPermission url="/family/mode/delete.jhtml">									
								<a onclick="deleteById('${e.id}','-1')">
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
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>