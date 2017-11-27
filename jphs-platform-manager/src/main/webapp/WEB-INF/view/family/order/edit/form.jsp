<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p> <span>购买人姓名：</span>${familyOrder.user.name}</p>
		<p> <span>购买人手机号：</span>${familyOrder.user.phone} </p>
		<p>
			<span>购买人微信号：</span>${familyOrder.wxNo}
		</p>
		<p>
			<span>实付款：</span>${familyOrder.payPrice}
		</p>
		<p>
			<span>活动码：</span>${familyOrder.code}
		</p>
		<p>
			<span>到期时间：</span>
			<fmt:formatDate value="${familyOrder.endTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>

	</div>
	<div class="col-md-6">

		<p> <span>套餐名称：</span>${familyOrder.familyMode.familyPackage.title}</p>
		<p> <span>套餐类型：</span><c:if test="${familyOrder.familyMode.familyPackage.type == 1}">单人版</c:if>
								<c:if test="${familyOrder.familyMode.familyPackage.type == 1}">三人版</c:if> </p>
		<p>
			<span>套餐价格：</span>${familyOrder.familyMode.familyPackage.price}
		</p>
		<p>
			<span>获取方式：</span><c:if test="${familyOrder.familyMode.accessMode == 1}">实付款</c:if>
								<c:if test="${familyOrder.familyMode.accessMode == 2}">兑换码</c:if>
								<c:if test="${familyOrder.familyMode.accessMode == 3}">免费</c:if>
		</p>
		<p>
			<span>验证编码：</span>${familyOrder.familyMode.validateCode}
		</p>
		<p>
			<span>有效期：</span>${familyOrder.familyMode.day}/${familyOrder.familyMode.unit}
		</p>
		<p>
			<span>开始时间：</span>
			<fmt:formatDate value="${familyOrder.familyMode.beginTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
		<p>
			<span>结束时间：</span>
			<fmt:formatDate value="${familyOrder.familyMode.endTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
</div>
