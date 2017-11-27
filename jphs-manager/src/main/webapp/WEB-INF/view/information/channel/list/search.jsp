<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/information/channel/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>频道名称：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${informationChannel.name}" />
			</td>
			<td>状态：</td>
			<td width="200">
				<div class="controls col-md-6">
					<select class="form-control input-xlarge" style="width: 180px;" id="status" name="status">
						<option value="">全部</option>
							<c:if test="${informationChannel.status == 1 }">
								<option value="1" selected="selected">开启</option>
								<option value="0">关闭</option>
							</c:if>
							<c:if test="${informationChannel.status == 0 }">
								<option value="1">开启</option>
								<option value="0" selected="selected">关闭</option>
							</c:if>
							<c:if test="${informationChannel.status == null }">
								<option value="1">开启</option>
								<option value="0">关闭</option>
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

