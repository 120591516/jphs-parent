<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" value="0" name="status"/>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 180px;" id="type" name="type">
						<option value="0" >虚拟卡</option>
						<option value="1" >实体卡</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">批次号：</label>
			<div class="controls col-md-6">
				<input type="text" id="batchNo" name="batchNo" placeholder="" class="form-control">
			</div>
			<button type="button" onclick="hqsjs()">点击随机生成</button>
		</div>
	
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">生成数量：</label>
			<div class="controls col-md-6">
				<input type="text" id="cardNumber" name="cardNumber" placeholder="" class="form-control">
			</div>
		</div>
		
		<%-- <div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">验证编码：</label>
			<div class="controls col-md-6">
				<input type="text" id="validateCodes" name="validateCodes" disabled="disabled" placeholder=""
					value="${familyMode.validateCode}" class="form-control">
				<input type="hidden" id="validateCode" name="validateCode">
			</div>
			<button type="button" onclick="hqsjs()">点击获取验证编码</button>
		</div> --%>
	</fieldset>
</form>