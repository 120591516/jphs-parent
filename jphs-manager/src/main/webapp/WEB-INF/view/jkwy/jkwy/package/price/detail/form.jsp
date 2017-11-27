<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="jkwyPackagePriceTable" class="tableStyle">
		<tr>
			<td align="right"width="100">：</td>
			<td>
				<c:out value="${jkwyPackagePrice.jkwyPackageId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">价格标题：</td>
			<td>
				<c:out value="${jkwyPackagePrice.title}"/>
			</td>
		</tr>
		<tr>
			<td align="right">价原：</td>
			<td>
				<c:out value="${jkwyPackagePrice.oldPrice}"/>
			</td>
		</tr>
		<tr>
			<td align="right">销售价：</td>
			<td>
				<c:out value="${jkwyPackagePrice.price}"/>
			</td>
		</tr>
		<tr>
			<td align="right">服务时长：</td>
			<td>
				<c:out value="${jkwyPackagePrice.serviceTime}"/>
			</td>
		</tr>
		<tr>
			<td align="right">单位：</td>
			<td>
				<c:out value="${jkwyPackagePrice.unit}"/>
			</td>
		</tr>
		<tr>
			<td align="right">支持套餐人数：</td>
			<td>
				<c:out value="${jkwyPackagePrice.supportNumber}"/>
			</td>
		</tr>
		<tr>
			<td align="right">支持续费（0不支持，1支持）：</td>
			<td>
				<c:out value="${jkwyPackagePrice.supportFee}"/>
			</td>
		</tr>
		<tr>
			<td align="right">支持变更升级套餐（0不支持，1支持）：</td>
			<td>
				<c:out value="${jkwyPackagePrice.supportChange}"/>
			</td>
		</tr>
	</table>
</div>