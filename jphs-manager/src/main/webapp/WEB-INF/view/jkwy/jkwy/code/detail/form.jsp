<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="jkwyCodeTable" class="tableStyle">
		<tr>
			<td align="right"width="100">活动码：</td>
			<td>
				<c:out value="${jkwyCode.code}"/>
			</td>
		</tr>
		<tr>
			<td align="right">推广人ID：</td>
			<td>
				<c:out value="${jkwyCode.recommendId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">推广人姓名：</td>
			<td>
				<c:out value="${jkwyCode.recommendName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">推广人手机号：</td>
			<td>
				<c:out value="${jkwyCode.recommendPhone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">创建人：</td>
			<td>
				<c:out value="${jkwyCode.creatorName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">添加时间：</td>
			<td>
				<fmt:formatDate value="${jkwyCode.createTime }" pattern="yy-MM-dd HH:mm" />
			</td>
		</tr>
		<tr>
			<td align="right">状态：</td>
			<td>
				<c:if test="${jkwyCode.status == 0}">
					<span style="color: #34BC2C;"><c:out value="使用中" /></span>
				</c:if> <c:if test="${jkwyCode.status == -1}">
					<span style="color: #F0BB1C;"><c:out value="已停用" /></span>
				</c:if>
			</td>
		</tr>
	</table>
</div>