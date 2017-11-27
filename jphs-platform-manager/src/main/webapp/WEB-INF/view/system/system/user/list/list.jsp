<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable"  class="data_table text-center" width="960">
	<thead>
		<tr >
			<th width="30"></th>
			<th width="80">姓名</th>
			<th>邮箱</th>
			<th width="110">手机号</th>
			<th width="80">创建人</th>
			<th width="110">创建时间</th>
			<th width="60">状态</th>
			<th width="120">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.name}"/></td>
							<td style="text-align:left"><c:out value="${e.email}"/></td>
							<td><c:out value="${e.phone}"/></td>
							<td><c:out value="${e.creatorName}"/></td>
							<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
							<td><c:if test="${e.status==0}">开启</c:if>
								<c:if test="${e.status==-1}">关闭</c:if></td>
							<td>
							<jphs:hasPermission url="/system/user/detail.jhtml">
							<a title="详情" onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							</jphs:hasPermission>
							<jphs:hasPermission url="/system/user/redirectUpdate.jhtml">
							<a title="编辑" onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							</jphs:hasPermission>
							<jphs:hasPermission url="/system/user/delete.jhtml">
							<a title="禁用" onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/system/user/getUserRoles.jhtml">
							<a title="分配角色" onclick="getUserRoles('${e.id}')">
								<img  src="/static/images/role.png">
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