<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${jkwyTimeAxis.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${jkwyTimeAxis.id == null?'添加':'编辑'}</div>
		<table id="jkwyTimeAxisTable" class="tableStyle">
			<tr>
				<td align="right"width="100">：</td>
				<td>
					<input value="${jkwyTimeAxis.resourceId}" 
						id="resourceId"
						name="resourceId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">时间轴标题：</td>
				<td>
					<input value="${jkwyTimeAxis.title}" 
						id="title"
						name="title"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">时间轴内容介绍：</td>
				<td>
					<input value="${jkwyTimeAxis.content}" 
						id="content"
						name="content"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>