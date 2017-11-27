<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>服务类型：</span><span class="title_value"><c:if test="${familyMode.accessMode == 1}">实付购买</c:if>
								<c:if test="${familyMode.accessMode == 2}">兑换码</c:if>
								<c:if test="${familyMode.accessMode == 3}">免费</c:if></span>
		</p>
		<p>
			<span>验证编号：</span><span class="title_value">${familyMode.validateCode}</span>
		</p>
		<p>
			<span>有效天数：</span><span class="title_value">${familyMode.day}/${familyMode.unit}</span>
		</p>
		<p>
			<span>开始时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyMode.beginTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
		<p>
			<span>结束时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyMode.endTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
		<p>
			<span>创建人：</span><span class="title_value">${familyMode.creatorName}</span>
		</p>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyMode.createTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>服务名称：</span><span class="title_value">${familyMode.familyPackage.title}</span></p>
		<p>
			<span>服务类型：</span><span class="title_value"><c:if test="${familyMode.familyPackage.type == 1}">单人版</c:if>
								<c:if test="${familyMode.familyPackage.type == 2}">三人版</c:if></span>
		</p>
		<p>
			<span>服务价格：</span><span class="title_value">${familyMode.familyPackage.price}</span>
		</p>
		<p>
			<span>介绍：</span><span class="title_value">${familyMode.familyPackage.subTitle}</span>
		</p>
		<p>
			<span>创建人：</span><span class="title_value">${familyMode.familyPackage.creatorName}</span>
		</p>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyMode.familyPackage.createTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
</div>