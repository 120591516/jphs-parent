<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<c:forEach var="e" items="${smsHistory }" varStatus="status">
		<table  style="margin-left: 150px;">
			<tr>
				<td style="text-align: right;">模板类型：</td>
				<td  class="title_value"> <c:if test="${e.smsTemplate.type == 1}">
					    <c:out value="标准短信" />
					 </c:if> 
					 <c:if test="${e.smsTemplate.type == 2}">
						<c:out value="推广短信 "/>
					 </c:if></td>
			</tr>
			<tr>
				<td style="text-align: right;">模板ID：</td>
				<td class="title_value">${e.smsTemplate.smsId}</td>
			</tr>
			<tr>
				<td style="text-align: right;">模板名称：</td>
				<td class="title_value">${e.smsTemplate.title}</td>
			</tr>
			<tr>
				<td style="text-align: right;">短信内容：</td>
				<td class="title_value">${e.smsTemplate.content}</td>
			</tr>
			<tr>
				<td style="text-align: right;">发送者IP：</td>
				<td class="title_value">${e.sendIp}</td>
			</tr>
			<tr>
				<td style="text-align: right;">发送时间：</td>
				<td class="title_value">
				<fmt:formatDate value="${e.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">发送人：</td>
				<td class="title_value">${e.creatorName}</td>
			</tr>
			<tr>
				<td style="text-align: right;">接受者手机号：</td>
				<td class="title_value">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td class="title_value">
					<textarea readonly="readonly"  class="form-control"  style="height: 150px;width: 500px;" >${e.phone}</textarea>
				</td>
			</tr>
		</table>
		</c:forEach>
		
		
	</fieldset>
</form>