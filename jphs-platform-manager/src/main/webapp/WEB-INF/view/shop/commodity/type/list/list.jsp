<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable"
	class="data_table" style="width:950px;margin-top:10px">
	<thead>
		<tr >
			<th width="30"></th>
			<th>类别名称</th>
			<th>所属类别</th>
			<th>创建人</th>
			<th>创建时间</th>
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
							<td><c:out value="${e.name}"/></td>
							<td><c:out value="${e.commodityTypeParentId}"/></td>
							<td><c:out value="${e.creatorName}"/></td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /></td>
							<td> 
								<c:if test="${e.status==1}"><span style="color: #34BC2C;">启用</span></c:if>  
								<c:if test="${e.status==-1}">停用</c:if>
							</td>
							<td>
							<jphs:hasPermission url="/commodity/type/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>
							</jphs:hasPermission>		
							<jphs:hasPermission url="/commodity/type/redirectUpdate.jhtml">						
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>	
							</jphs:hasPermission>
							<jphs:hasPermission url="/commodity/type/delete.jhtml">								
							<a onclick="deleteById('${e.id}','${e.count }')">
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