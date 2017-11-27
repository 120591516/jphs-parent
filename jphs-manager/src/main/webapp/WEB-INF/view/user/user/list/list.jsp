<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" class="data_table text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="80">用户ID</th>
			<th width="110">昵称</th>
			<th width="110">手机号</th>
			<th>积分</th>
			<th>所在地</th>
			<th>CODE</th>
			<th>注册端</th>
			<th width="120">注册时间</th>
			<th width="50">状态</th>
			<th width="100">操作</th>
		</tr>
	</thead>
	<c:choose>
		<c:when test="${fn:length(list) >0}">
			<c:forEach items="${list}" var="e" varStatus="s">
				<tr class="bg_list_body">
					<td>${s.index+1}</td>
					<td><c:out value="${e.id}" /></td>
					<td><c:out value="${e.name}" /></td>
					<td><c:out value="${e.phone}" /></td>
					<td> <c:out value="${e.score}" /></td>
					<td><c:out value="${e.address}" /></td>
					<td>${e.code}</td>
					<td>
						<c:if test="${e.device==1}">IOS</c:if>
						<c:if test="${e.device==2}">安卓</c:if>
						<c:if test="${e.device==3}">微信</c:if>
						<c:if test="${e.device==4}">114等注册端</c:if>
						<c:if test="${e.device==5}">PC端</c:if>
						<c:if test="${e.device==6}">WAP站</c:if>
						<c:if test="${e.device==7}">呼啦圈</c:if>
					</td>
					<td><fmt:formatDate value="${e.createTime}"
							pattern="yy-MM-dd HH:mm" />
					<td  ><c:if test="${e.status == 1}"><span style="color: #34BC2C;">正常</span></c:if> <c:if
							test="${e.status == -1}"><span style="color: #F0BB1C;">已注销</span></c:if></td>
					<td>
					<jphs:hasPermission url="/user/detail.jhtml">
					<a onclick="redirectDetailPage('${e.id}')"> <img
							src="/static/images/chakan.png">
					</a> 
					</jphs:hasPermission>
					<jphs:hasPermission url="/user/redirectUpdate.jhtml">
					<a onclick="redirectUpdatePage('${e.id}')"> <img
							src="/static/images/xiugai.png">
					</a> 
					</jphs:hasPermission>
					<jphs:hasPermission url="/user/delete.jhtml">
					<a onclick="deleteById('${e.id}')"> <img
							src="/static/images/shanchu.png">
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
</table>
<div class="page">
       <jphs:page pageInfos="${pageInfo}" ></jphs:page>    
</div>
