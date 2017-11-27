<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/family/mode/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>获取方式<!-- (1实付购买，2兑换码，3免费识别) -->：</td>
			<td width="200">
				<%-- <input type="text" name="accessMode" id="accessMode" value="${familyMode.accessMode}" /> --%>
				<select class="form-control input-xlarge" style="width: 180px;" id="accessMode" name="accessMode">
					<option value="">全部</option>
					<c:if test="${familyMode.accessMode == null}">
						<option value="1" >实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyMode.accessMode == 1 }">
						<option value="1" selected="selected">实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyMode.accessMode == 2}">
						<option value="1" >实付购买</option>
						<option value="2" selected="selected">兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyMode.accessMode == 3}">
						<option value="1" >实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" selected="selected">免费识别</option>
					</c:if>
				</select>
			</td>
			<td>验证编号：</td>
			<td width="200">
				<input type="text" name="validateCode" id="validateCode" value="${familyMode.validateCode}" />
			</td>
		</tr>
		<%-- <tr>
			<td>开始时间：</td>
			<td width="200">
				<input type="text" name="beginTime" id="beginTime" value="${familyMode.beginTime}" />
			</td>
			<td>结束时间：</td>
			<td width="200">
				<input type="text" name="endTime" id="endTime" value="${familyMode.endTime}" />
			</td>
		</tr> --%>
		<%-- <tr>
			<td>有效天数：</td>
			<td width="200">
				<input type="text" name="day" id="day" value="${familyMode.day}" />
			</td>
			<td>单位：</td>
			<td width="200">
				<input type="text" name="unit" id="unit" value="${familyMode.unit}" />
			</td>
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

