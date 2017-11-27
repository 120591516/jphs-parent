<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%-- 
<c:choose>
	<c:when test="${fn:length(jkwyPackage.packagePrice) >0}">
		<div style="margin-top: 0; padding-top: 0;">
			<table id="dateTable" class="data_table text-center"
				style="width: 850px; margin: 0 auto">
				<thead>
					<tr class="headClass">
						<td width="50">规格名称</td>
						<td width="40">原价</td>
						<td width="40">销售价</td>
						<td width="60">服务时长</td>
						<td width="60">单位</td>
						<td width="50">支持套餐人数</td>
						<td width="50">支持续费</td>
						<td width="50">支持变更升级套餐</td>
					</tr>
				</thead>
				<tbody id="addtrprice${status_g.index }">
					<c:if test="${fn:length(jkwyPackage.packagePrice) >0 }">
						<c:forEach var="e" items="${jkwyPackage.packagePrice }"
							varStatus="s">
							<tr class="addClassprice" id="delete${s.index }">
								<input type="hidden" name="packagePrice[${s.index }].id"
									value="${e.id }" />
								<input type="hidden" id="status${s.index }"
									name="packagePrice[${s.index }].status" value="${e.status }" />
								<input type="hidden" id="jkwyPackageId${s.index }"
									name="packagePrice[${s.index }].jkwyPackageId"
									value="${e.jkwyPackageId }" />
								<td>${e.title }</td>
								<td>${e.oldPrice }</td>
								<td>${e.price }</td>
								<td>${e.serviceTime }</td>
								<td>${e.unit }</td>
								<td>${e.supportNumber }</td>
								<td>${e.supportFee }</td>
								<td>${e.supportChange }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</c:when>
</c:choose>
aa --%>

<c:if test="${fn:length(jkwyPackage.packagePrice) >0 }">
	<c:forEach var="e" items="${jkwyPackage.packagePrice }" varStatus="s">
		<div id="price${s.index }" class="price">
			<table id="dateTable" cellpadding="0" cellspacing="0"
				class="data_table  text-center" style="width: 900px; margin: 0 auto">
				<thead id="addtrprice">
					<tr class="headClass">
						<td width="50">规格名称</td>
						<td width="40">原价</td>
						<td width="40">销售价</td>
						<td width="60">服务时长</td>
						<td width="60">单位</td>
						<td width="50">支持套餐人数</td>
						<td width="50">支持续费</td>
						<td width="50">支持变更升级套餐</td>
					</tr>
					<tr class="addClassprice" id="delete${s.index }">
						<td>${e.title }</td>
						<td>${e.oldPrice }</td>
						<td>${e.price }</td>
						<td>${e.serviceTime }</td>
						<td>${e.unit}</td>
						<td>${e.supportNumber }</td>
						<td>
							<c:if test="${e.supportFee=='0' }">否</c:if>
							<c:if test="${e.supportFee=='1' }">是</c:if>
						</td>
						<td>
							<c:if test="${e.supportChange=='0' }">否</c:if>
							<c:if test="${e.supportChange=='1' }">是</c:if>
						</td>
					</tr>
				</thead>
			</table>
			<table class="data_table  text-center"
				style="width: 900px; margin: 0 auto">
				<thead>
					<tr>
						<td width="60">服务模块</td>
						<td>服务内容</td>
						<td>服务说明</td>
						<td>总服务次数</td>
						<td>剩余服务次数</td>
					</tr>
				</thead>
				<tbody id="addPrice${s.index }Content">
					<c:forEach var="f" items="${e.jkwyPackageContentList }"
						varStatus="t">
						<tr class="packagePrice${s.index }addPriceContent"
							id="packagePrice${s.index }addPriceContent${t.index}">
							<td>${f.title }
								</td>
							<td>${f.subTitle }</td>
							<td>${f.content }</td>
							<td>${f.number }</td>
							<td>${f.surplusNumber }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:if>