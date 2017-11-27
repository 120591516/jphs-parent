<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/family/card/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;" id="type" name="type">
					<option value="">全部</option>
					<c:if test="${familyCard.type == 0}">
						<option value="0" selected="selected">虚拟卡</option>
						<option value="1" >实体卡</option>		
					</c:if>
					<c:if test="${familyCard.type == 1}">
						<option value="0" >虚拟卡</option>
						<option value="1" selected="selected">实体卡</option>		
					</c:if>
					<c:if test="${familyCard.type == null}">
						<option value="0" >虚拟卡</option>
						<option value="1" >实体卡</option>		
					</c:if>
				</select>
			</td>
			<td>批次号：</td>
			<td width="200">
				<input type="text" name="batchNo" id="batchNo" value="${familyCard.batchNo}" />
			</td>
			<%-- <td>卡号：</td>
			<td width="200">
				<input type="text" name="no" id="no" value="${familyCard.no}" />
			</td>
			<td>兑换码：</td>
			<td width="200">
				<input type="text" name="code" id="code" value="${familyCard.code}" />
			</td> --%>
		</tr>
		<%-- <tr>
			<td>绑定人：</td>
			<td width="200">
				<input type="text" name="remmond" id="remmond" value="${familyCard.remmond}" />
			</td>
			<td>批次号：</td>
			<td width="200">
				<input type="text" name="batchNo" id="batchNo" value="${familyCard.batchNo}" />
			</td>
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

