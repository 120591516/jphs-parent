<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" value="${familyOrder.creatorId}" name="creatorId"/>
		<input type="hidden" value="1" name="status"/>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">问题：</label>
			<div class="controls col-md-6">
				<input type="text" id="quiz" name="quiz" placeholder="请输入价格"
					value="" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">答案：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="answer" name="answer" placeholder="答案"
						class="form-control"></textarea>
				</div>
			</div>
		</div>

	</fieldset>
</form>