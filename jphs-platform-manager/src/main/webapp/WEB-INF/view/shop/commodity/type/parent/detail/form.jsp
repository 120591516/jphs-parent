<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${commodityTypeParent.id}" />

	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">类别名称：</label>
			<div class="controls col-md-6">
				${commodityTypeParent.name}
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">创建人：</label>
			<div class="controls col-md-6">
				${systemUser.name}
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">创建时间：</label>
			<div class="controls col-md-6">
				<fmt:formatDate value="${commodityTypeParent.createTime}" pattern="yy-MM-dd HH:mm"/>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">状态：</label>
			<div class="controls col-md-6">
				<c:if test="${commodityTypeParent.status == 1 }">启用中</c:if>
				<c:if test="${commodityTypeParent.status == -1 }">停用中</c:if>
			</div>
		</div>
		
	</fieldset>
	
</div>
