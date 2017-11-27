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
			<span>套餐名称：</span>${jkwyPackage.title}</p>
		<p>
			<span>套餐简介：</span>${jkwyPackage.subTitle}
		</p>
		<p>
			<span>状态：</span>
			<c:if test="${jkwyPackage.status == 0}">
					使用中
				</c:if>
			<c:if test="${jkwyPackage.status == -1}">
				 	已停用
				</c:if>

		</p>
		<p>
			<span>排序：</span>${jkwyPackage.sort}
		</p>
		<p>
			<span>关联平台列表：</span>${jkwyPackage.platformId}
		</p>
		<p>
			<span>创建人：</span>${jkwyPackage.creatorName}
		</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${jkwyPackage.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>

	</div>
	<div class="col-md-6">
		<p>
			<span>移动端图标：</span> <img alt="" height="200" width="200" id="pcurls"
				src="${wap_image.url}">
		</p>
		<p>
			<span>电脑端图标：</span> <img alt="" height="200" width="200"
				id="moveurls" src="${pc_image.url}">

		</p>
	</div>
</div>
	<p class="details_box_xinxi">套餐内容</p>
	
	<jsp:include page="setmeal.jsp"></jsp:include>
	

	<p class="details_box_xinxi">套餐详情</p>
	<div class="text-center">${jkwyPackage.content} </div>
	
