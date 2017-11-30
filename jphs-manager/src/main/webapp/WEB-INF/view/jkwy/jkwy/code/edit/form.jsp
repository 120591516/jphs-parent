<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${jkwyCode.id}" />
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">活动码：</label>
			<div class="controls col-md-6">
				<input type="text" id="code" name="code" maxlength="20" value="${jkwyCode.code}" placeholder="活动码"
					class="form-control" required="required" />
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">推荐人Id：</label>
			<div class="controls col-md-6">
				<input type="text" id="recommendId" name="recommendId" maxlength="20" value="${jkwyCode.recommendId}" placeholder="推荐人Id"
					class="form-control" required="required" />
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">推荐人姓名：</label>
			<div class="controls col-md-6">
				<input type="text" id="recommendName" name="recommendName" maxlength="20" value="${jkwyCode.recommendName}" placeholder="推荐人姓名"
					class="form-control" required="required" />
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">推荐人手机号：</label>
			<div class="controls col-md-6">
				<input type="text" id="recommendPhone" name="recommendPhone" maxlength="20" value="${jkwyCode.recommendPhone}" placeholder="推荐人手机号"
					class="form-control" required="required" />
			</div>
		</div>
	</fieldset>
</form>