<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${familyMode.id}" />
		<input type="hidden" value="0" name="status"/>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">获取方式：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 180px;" id="accessMode" name="accessMode">
					<c:if test="${familyMode.accessMode == null}">
						<option value="1" selected="selected">实付购买</option>
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
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐名称：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 260px;" id="familyPackageId" name="familyPackageId">
					<c:forEach var="familyPackageOne" items="${familyPackage_list }" varStatus="status">
						<c:if test="${familyPackageOne.id == familyMode.familyPackageId }">
							<option value="${familyPackageOne.id }" selected="selected">${familyPackageOne.title }-<c:if test="${familyPackageOne.type == 1}">单人版</c:if><c:if test="${familyPackageOne.type == 2}">三人版</c:if> </option>
						</c:if>
						<c:if test="${familyPackageOne.id != familyMode.familyPackageId }">
							<option value="${familyPackageOne.id }">${familyPackageOne.title }-<c:if test="${familyPackageOne.type == 1}">单人版</c:if><c:if test="${familyPackageOne.type == 2}">三人版</c:if></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">验证编码：</label>
			<div class="controls col-md-6">
				<input type="text" id="validateCodes" name="validateCodes" disabled="disabled" placeholder=""
					value="${familyMode.validateCode}" class="form-control">
				<input type="hidden" id="validateCode" name="validateCode">
			</div>
			<button type="button" onclick="hqsjs()">点击获取验证编码</button>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">开始时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="beginTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${familyMode.beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择开始工作时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input
					type="hidden" name="beginTime" id="beginTime" value="<fmt:formatDate value='${familyMode.beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">结束时间：</label>	
			<div  class="controls col-md-6 input-group date form_dates col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="endTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${familyMode.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择开始工作时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input
					type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value='${familyMode.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">有效期：</label>
			<div class="controls col-md-6">
				<input type="number" id="day" name="day" placeholder="请输入天数"
					value="${familyMode.day}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">单位：</label>
			<div class="controls col-md-6">
				<input type="text" id="unit" name="unit" placeholder="请输入单位"
					value="${familyMode.unit}" class="form-control">
			</div>
		</div>

	</fieldset>
</form>