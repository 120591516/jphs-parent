<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p> <span>资源类型：</span><span class="title_value">
			<c:if test="${shareStatistics.type == 1}">服务</c:if>
			<c:if test="${shareStatistics.type == 2}">商品</c:if>
			<c:if test="${shareStatistics.type != 2 && shareStatistics.type != 1}">其他</c:if>
		</span> </p>
		<p>
			<span>资源名称：</span>
			<span class="title_value">${shareStatistics.goodsId}</span>
		</p>
		
		<p>
			<span>分享名称：</span><span class="title_value">${shareStatistics.name}</span>
		</p>
		<p>
			<span>链接地址：</span><span class="title_value">${shareStatistics.url}</span>
		</p>
		<p> <span>分享目标平台：</span><span class="title_value">
		<!-- 	分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台 -->
			<c:if test="${shareStatistics.sharePlatform == 1}">微信好友</c:if>
			<c:if test="${shareStatistics.sharePlatform == 2}">微信朋友圈</c:if>
			<c:if test="${shareStatistics.sharePlatform == 3}">QQ好友</c:if>
			<c:if test="${shareStatistics.sharePlatform == 4}">QQ空间</c:if>
			<c:if test="${shareStatistics.sharePlatform == 5}">微博</c:if>
			<c:if test="${shareStatistics.sharePlatform == 6}">其他平台</c:if>
		</span> </p>
		<p> <span>分享终端：</span><span class="title_value">
		<!-- 1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端 -->
			<c:if test="${shareStatistics.type == 1}">ios用户端</c:if>
			<c:if test="${shareStatistics.type == 2}">ios护士端</c:if>
			<c:if test="${shareStatistics.type == 3}">Android用户端</c:if>
			<c:if test="${shareStatistics.type == 4}">Android护士端</c:if>
			<c:if test="${shareStatistics.type == 5}">wap站</c:if>
			<c:if test="${shareStatistics.type == 6}">其他终端</c:if>
		</span> </p>
	</div>
	<div class="col-md-6">
		<c:if test="${shareStatistics.creatorId != null}">
			<p>
				<span>创建人：</span><span class="title_value">${shareStatistics.creatorName}</span>
			</p>
		</c:if>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${shareStatistics.createTime}" pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
</div>