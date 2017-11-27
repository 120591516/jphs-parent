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
			<span>问诊类型：</span>
			<c:if test="${familyRegister.type == 1}">普通门诊</c:if>
			<c:if test="${familyRegister.type == 2}">专家门诊</c:if>
			<c:if test="${familyRegister.type == 3}">加急门诊</c:if>
		</p>
		<p>
			<span>医院：</span>${familyRegister.hospital}
		</p>
		<p>
			<span>科室：</span>${familyRegister.department}
		</p>
		<p>
			<span>行程名称：</span>${familyRegister.title}
		</p>
		<p>
			<span>拨打电话：</span>${familyRegister.phone}
		</p>
		<p>
			<span>星期：</span>${familyRegister.week}
		</p>
		<p>
			<span>预约时间：</span><fmt:formatDate value="${familyRegister.appointmentTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>管理-创建人：</span>${familyRegister.creatorName}
		</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${familyRegister.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
</div>