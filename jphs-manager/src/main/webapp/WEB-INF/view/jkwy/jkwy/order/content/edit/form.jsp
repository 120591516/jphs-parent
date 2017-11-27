<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${jkwyOrderContent.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${jkwyOrderContent.id == null?'添加':'编辑'}</div>
		<table id="jkwyOrderContentTable" class="tableStyle">
			<tr>
				<td align="right"width="100">：</td>
				<td>
					<input value="${jkwyOrderContent.jkwyOrderId}" 
						id="jkwyOrderId"
						name="jkwyOrderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">服务标题：</td>
				<td>
					<input value="${jkwyOrderContent.title}" 
						id="title"
						name="title"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">超文本服务内容介绍：</td>
				<td>
					<textArea id="content"
						name="content"
						rows="12"
					 	data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(jkwyOrderContent.content)}</textArea>
				</td>
			</tr>
			<tr>
				<td align="right">总服务次数：</td>
				<td>
					<input value="${jkwyOrderContent.number}" 
						id="number"
						name="number"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">剩余服务次数：</td>
				<td>
					<input value="${jkwyOrderContent.surplusNumber}" 
						id="surplusNumber"
						name="surplusNumber"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>