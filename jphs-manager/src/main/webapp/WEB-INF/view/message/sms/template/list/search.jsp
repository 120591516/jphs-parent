<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/sms/template/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>模板ID：</td>
			<td width="200">
				<input type="text" name="smsId" id="smsId" value="${smsTemplate.smsId}" />
			</td>
			<td>模板名称：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${smsTemplate.title}" />
			</td>
			<td>短信类型：</td>
				<td width="100">
				<div class="controls col-md-6">
					<select class="form-control input-xlarge" style="width: 100px;" id="type" name="type">
						<option value="">全部</option>
							<c:if test="${smsTemplate.type == 1 }">
								<option value="1" selected="selected">标准短信</option>
								<option value="2">推广短信</option>
							</c:if>
							<c:if test="${smsTemplate.type == 2 }">
								<option value="1">标准短信</option>
								<option value="2" selected="selected">推广短信</option>
							</c:if>
							<c:if test="${smsTemplate.type == null }">
								<option value="1">标准短信</option>
								<option value="2">推广短信</option>
							</c:if>
					</select>
				</div>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

