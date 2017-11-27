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
			<c:if test="${activityPromotion.resourceType == 1}">服务</c:if>
			<c:if test="${activityPromotion.resourceType == 2}">商品</c:if>
		</span> </p>
		<p>
			<span>活动类型：</span>
			<span class="title_value">
				<c:if test="${activityPromotion.type == 1}">立减</c:if>
				<c:if test="${activityPromotion.type == 2}">首单立减</c:if>
				<c:if test="${activityPromotion.type == 3}">第二单立减</c:if>
			</span>
		</p>
		
		<p>
			<span>优惠价格：</span><span class="title_value">${activityPromotion.price}</span>
		</p>
		<p>
			<span>开始时间：</span>
			<span class="title_value"><fmt:formatDate value="${activityPromotion.beginTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
		<p>
			<span>结束时间：</span>
			<span class="title_value"><fmt:formatDate value="${activityPromotion.endTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
	<div class="col-md-6">
		<p> <span>资源名称：</span><span class="title_value">
			<c:if test="${activityPromotion.resourceType == 1}">${activityPromotion.goodsTitle}</c:if>
			<c:if test="${activityPromotion.resourceType == 2}">${activityPromotion.commodityTitle}</c:if>
		</span></p>
		<p> <span>规格列表：</span><span class="title_value">${activityPromotion.priceId}</span></p>
		<p>
			<span>站点：</span><span class="title_value">${activityPromotion.siteName}</span>
		</p>
		<p>
			<span>平台：</span><span class="title_value">${activityPromotion.platformName}</span>
		</p>
		<p>
			<span>创建人：</span><span class="title_value">${activityPromotion.creatorName}</span>
		</p>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${activityPromotion.createTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
</div>