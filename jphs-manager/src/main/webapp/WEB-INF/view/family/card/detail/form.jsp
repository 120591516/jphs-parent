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
			<span>类型：</span>
			<span class="title_value">
				<c:if test="${familyCard.type == 0}">虚拟卡</c:if>
				<c:if test="${familyCard.type == 1}">实体卡</c:if>
			</span>
		</p>
		<p> <span>批次号：</span><span class="title_value">${familyCard.batch_no}</span> </p>
		<p>
			<span>总数：</span><span class="title_value">${familyCard.fmsum}</span>
		</p>
		<p>
			<span>已兑换：</span><span class="title_value">${familyCard.fmsumw}</span>
		</p>
		<p>
			<span>未兑换：</span><span class="title_value">${familyCard.fmsumy}</span>
		</p>
	</div>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>创建人：</span><span class="title_value">${familyCard.creator_name}</span>
		</p>
		<p>
			<span>创建时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyCard.create_time}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
</div>
<form id="serach-form" method="get" action="/family/card/detail.jhtml">
	<table class="data_table text-center" style="width: 865px;">
	<thead>
	 <tr>
	    <th width="60">类型</th>
	    <th width="100">卡号</th>
	    <th width="100">兑换码</th>
	    <th width="100">绑定人</th>
	    <th width="100">批次号</th>
	    <th width="100">使用人</th>
	    <th width="100">使用人手机号</th>
	    <th width="120">使用时间</th>
	  </tr>
	</thead>
	 <tbody>
	 	<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="listOne" varStatus="status">
					<tr>
						<td><span class="title_value">
							<c:if test="${listOne.type == 0}">虚拟卡</c:if>
							<c:if test="${listOne.type == 1}">实体卡</c:if>
							</span>
						</td>
						<td><span class="title_value">
							${listOne.no}
						</span></td>
						<td><span class="title_value">${listOne.code}</span></td>
						<td><span class="title_value">${listOne.remmond}</span></td>
						<td><span class="title_value">${listOne.batchNo}</span></td>
						<td><span class="title_value">${listOne.user.name}</span></td>
						<td><span class="title_value">${listOne.user.phone}</span></td>
						<td><span class="title_value"><fmt:formatDate value="${listOne.useTime}" pattern="yy-MM-dd HH:mm" /></span></td>
						<%-- <td>
							<a href="/family/consultation/detail.jhtml?id=${familyConsultationOne.id }">
								<img src="/static/images/chakan.png">
							</a>
						</td> --%>
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
</form>