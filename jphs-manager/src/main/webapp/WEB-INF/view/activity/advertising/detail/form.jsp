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
				<span>显示位置：</span>
				<span class="title_value">
					<c:if test="${advertising.type==1}">app护士端</c:if>
					<c:if test="${advertising.type==2}">app用户端</c:if>
					<c:if test="${advertising.type==3}">官网首页</c:if>
					<c:if test="${advertising.type==4}">app资讯</c:if>
				</span>
			</p>
			<p>
				<span>产品类型：</span>
				<span class="title_value">
					<c:if test="${advertising.serviceType==1}">服务</c:if>
					<c:if test="${advertising.serviceType==2}">商品</c:if>
					<c:if test="${advertising.serviceType==3}">其他</c:if>
				</span>
			</p>
			<p>
				<span>状态：</span><span class="title_value">
					<c:if test="${advertising.status == 1}"><c:out value="开启"/></c:if>
					<c:if test="${advertising.status == 0}"><c:out value="关闭"/></c:if>
				</span>
			</p>
			<p>
				<span>排序：</span><span class="title_value"><c:out value="${advertising.sort}"/>
				</span>
			</p>
			<p>
				<span>开始时间：</span><span class="title_value"><fmt:formatDate value="${advertising.startTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</p>
			<p>
				<span>结束时间：</span><span class="title_value"><fmt:formatDate value="${advertising.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</p>
			<p>
				<span>链接地址：</span><span class="title_value">${advertising.link}</span>
			</p>
			<p>
				<span>创建人：</span><span class="title_value">${advertising.creatorName}</span>
			</p>
			<p>
				<span>创建时间：</span><span class="title_value"><fmt:formatDate value="${advertising.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</p>
		</div>
		<div class="col-md-6">
			<p>
				<span>banner：</span>
			</p>
			<p>
				<span></span><img alt="图片" style="width: 300px;height: 200px;" src="${advertising.image }" />
			</p>
			<c:if test="${advertising.bgImage != null }">
				<p>
					<span></span><img alt="图片" style="width: 300px;height: 200px;" src="${advertising.bgImage }" />
				</p>
			</c:if>
			<%-- <p>
				<span>审核人ID：</span><span class="title_value">${withdrawCash.auditUserId}</span>
			</p>
			<p>
				<span>审核时间：</span><span class="title_value"><fmt:formatDate value="${withdrawCash.auditTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</p>
			<p>
				<span>审核意见：</span><span class="title_value">${withdrawCash.remark}</span>
			</p> --%>
		</div>
</div>
