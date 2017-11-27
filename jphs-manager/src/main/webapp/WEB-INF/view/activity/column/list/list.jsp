<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable"
	class="data_table text-center" style="width:950px;margin-top:10px">
	<thead>
		<tr >
			<th width="30"></th>
			<th>栏目名称</th>
			<th>生成接口地址</th>
			<th width="118">创建时间</th>
			<th width="70">服务个数</th>
			<th>创建人</th>
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
							<td style="text-align:left"><c:out value="${e.name}"/></td>
							<td  style="width: 80px;height: 100px;">
							<%
							String URL = request.getHeader("host");
							URL = URL.substring(0,1);
							if(URL.equals("l"))
							{
								%>
								<c:out value="http://localhost:8080/column/getColumnService.json?columnId=${e.id }"/>
								<% 
							}else if(URL.equals("1"))
							{
								%>
								<c:out value="https://s.goldnurse.com/column/getColumnService.json?columnId=${e.id }"/>
								<% 
							}else
							{
								%>
								<c:out value="/column/getColumnService.json?columnId=${e.id }"/>
								<% 
							}
							%>
							</td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /></td>
							<td><c:out value="${e.csCount}"/></td>
							<td style="width: 80px;"><c:out value="${e.creatorName}"/></td>
							<td  style="width: 80px;">
								<c:if test="${e.status==1}"><span style="color: #34BC2C;">已启用</span></c:if>  
								 
								<c:if test="${e.status==0}"><span style="color: #F0BB1C;">未启用</span></c:if>
								 
								<c:if test="${e.status==-1}">已停用</c:if>
							</td>
							<td style="width: 80px;">
							<a onclick="updateStatus('${e.id}','${e.status }')">
								<c:if test="${e.status==1}"><span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">停用</span></c:if>  
								 
								<c:if test="${e.status==0}"><span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">启用</span></c:if>
								 
								<c:if test="${e.status==-1}"><span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">启用</span></c:if>
							</a><br>
							<jphs:hasPermission url="/column/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/column/redirectUpdate.jhtml">								
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/column/delete.jhtml">									
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