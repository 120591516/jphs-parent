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
			<span>服务名称：</span><span class="title_value">${familyPackage.title}</span></p>
		<p>
			<span>服务类型：</span><span class="title_value"><c:if test="${familyPackage.type == 1}">单人版</c:if>
								<c:if test="${familyPackage.type == 2}">三人版</c:if></span>
		</p>
		<p>
			<span>服务价格：</span><span class="title_value">${familyPackage.price}</span>
		</p>
		<p>
			<span>介绍：</span><span class="title_value">${familyPackage.subTitle}</span>
		</p>
		<p>
			<span>创建人：</span><span class="title_value">${familyPackage.creatorName}</span>
		</p>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyPackage.createTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>

	</div>
	<div class="col-md-6">
		<p>
			<span>头图：</span> <img alt="" height="200" width="200" id="pcurls"
				src="${familyPackage.image}">
		</p>
	</div>
</div>
<p class="details_box_xinxi">服务详情</p>
<div class="text-center">${familyPackage.content} </div>