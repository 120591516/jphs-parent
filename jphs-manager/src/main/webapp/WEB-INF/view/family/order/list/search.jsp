<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/family/order/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>实付金额：</td>
			<td width="200">
				<input type="text" name="payPrice" id="payPrice" value="${familyOrder.payPrice}" />
			</td>
			<td>微信号：</td>
			<td width="200">
				<input type="text" name="wxNo" id="wxNo" value="${familyOrder.wxNo}" />
			</td>
		</tr>
		<tr>
			<td>手机号：</td>
			<td width="200">
				<input type="text" name="phone" id="phone" value="${familyOrder.phone}" />
			</td>
			<td>活动码：</td>
			<td width="200">
				<input type="text" name="code" id="code" value="${familyOrder.code}" />
			</td>
		</tr>
		<tr>
			<td>获取方式<!-- (1实付购买，2兑换码，3免费识别) -->：</td>
			<td width="200">
				<%-- <input type="text" name="accessMode" id="accessMode" value="${familyMode.accessMode}" /> --%>
				<select class="form-control input-xlarge" style="width: 180px;" id="accessMode" name="accessMode">
					<option value="">全部</option>
					<c:if test="${familyOrder.accessMode == null}">
						<option value="1" >实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyOrder.accessMode == 1 }">
						<option value="1" selected="selected">实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyOrder.accessMode == 2}">
						<option value="1" >实付购买</option>
						<option value="2" selected="selected">兑换码</option>
						<option value="3" >免费识别</option>
					</c:if>
					<c:if test="${familyOrder.accessMode == 3}">
						<option value="1" >实付购买</option>
						<option value="2" >兑换码</option>
						<option value="3" selected="selected">免费识别</option>
					</c:if>
				</select>
			</td>
			<td>套餐类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;" id="type" name="type">
					<option value="">全部</option>
					<c:if test="${familyOrder.type == 1}">
						<option value="1" selected="selected">单人版</option>
						<option value="2" >三人版</option>		
					</c:if>
					<c:if test="${familyOrder.type == 2}">
						<option value="1" >单人版</option>
						<option value="2" selected="selected">三人版</option>		
					</c:if>
					<c:if test="${familyOrder.type == null}">
						<option value="1" >单人版</option>
						<option value="2" >三人版</option>		
					</c:if>
				</select>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

