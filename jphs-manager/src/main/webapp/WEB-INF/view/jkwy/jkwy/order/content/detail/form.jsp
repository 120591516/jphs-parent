<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="jkwyOrderContentTable" class="tableStyle">
		<tr>
			<td align="right"width="100">：</td>
			<td>
				<c:out value="${jkwyOrderContent.jkwyOrderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">服务标题：</td>
			<td>
				<c:out value="${jkwyOrderContent.title}"/>
			</td>
		</tr>
		<tr>
			<td align="right">超文本服务内容介绍：</td>
			<td>
				<c:out value="${jkwyOrderContent.content}"/>
			</td>
		</tr>
		<tr>
			<td align="right">总服务次数：</td>
			<td>
				<c:out value="${jkwyOrderContent.number}"/>
			</td>
		</tr>
		<tr>
			<td align="right">剩余服务次数：</td>
			<td>
				<c:out value="${jkwyOrderContent.surplusNumber}"/>
			</td>
		</tr>
	</table>
</div>