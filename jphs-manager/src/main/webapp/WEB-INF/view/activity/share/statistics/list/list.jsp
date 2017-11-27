<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
	<thead>
		<tr >
			<th width="40"></th>
			<th width="100">资源类型</th>
			<th width="220">资源名称</th>
			<th width="280">分享名称</th>
			<th width="120">分享目标平台</th>
			<th width="120">分享终端</th>
			<th width="120">分享时间</th>
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
								<c:if test="${e.type == 1}">服务</c:if>
								<c:if test="${e.type == 2}">商品</c:if>
								<c:if test="${e.type == 3}">其他</c:if>
							</td>
							<td><c:out value="${e.goodsId}"/></td>
							<td><c:out value="${e.name}"/></td>
							<td><!-- 分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台 -->
								<c:if test="${e.sharePlatform == 1}">微信好友</c:if>
								<c:if test="${e.sharePlatform == 2}">微信朋友圈</c:if>
								<c:if test="${e.sharePlatform == 3}">QQ好友</c:if>
								<c:if test="${e.sharePlatform == 4}">QQ空间</c:if>
								<c:if test="${e.sharePlatform == 5}">微博</c:if>
								<c:if test="${e.sharePlatform == 6}">其他平台</c:if>
							</td>
							<td><!-- 1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端 -->
								<c:if test="${e.shareDevice == 1}">ios用户端</c:if>
								<c:if test="${e.shareDevice == 2}">ios护士端</c:if>
								<c:if test="${e.shareDevice == 3}">Android用户端</c:if>
								<c:if test="${e.shareDevice == 4}">Android护士端</c:if>
								<c:if test="${e.shareDevice == 5}">wap站</c:if>
								<c:if test="${e.shareDevice == 6}">其他终端</c:if>
							</td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /></td>
							<td>
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
								<%-- <a onclick="redirectUpdatePage('${e.id}')">
									<img  src="/static/images/xiugai.png">
								</a>									
								<a onclick="deleteById('${e.id}')">
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
	</div>
	
</div>