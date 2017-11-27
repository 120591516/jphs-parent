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
			<th>价格标题</th>
			<th>价原</th>
			<th>销售价</th>
			<th>服务时长</th>
			<th>单位</th>
			<th>支持套餐人数</th>
			<th>支持续费（0不支持，1支持）</th>
			<th>支持变更升级套餐（0不支持，1支持）</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.jkwyPackageId}"/></td>
							<td><c:out value="${e.title}"/></td>
							<td><c:out value="${e.oldPrice}"/></td>
							<td><c:out value="${e.price}"/></td>
							<td><c:out value="${e.serviceTime}"/></td>
							<td><c:out value="${e.unit}"/></td>
							<td><c:out value="${e.supportNumber}"/></td>
							<td><c:out value="${e.supportFee}"/></td>
							<td><c:out value="${e.supportChange}"/></td>
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