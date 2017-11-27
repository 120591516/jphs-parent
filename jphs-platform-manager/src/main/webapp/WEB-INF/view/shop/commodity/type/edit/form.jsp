<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>


<div>
	<input type="hidden" id="id" name="id" value="${commodityType.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		 
	</div>

	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">类别名称：</label>
			<div class="controls col-md-6">
				<input value="${commodityType.name}" id="name" name="name" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">所属分类：</label>
			<div class="controls col-md-6">
				<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="commodityTypeParentId"
							name="commodityTypeParentId">
							<option value="">全部</option>
							<c:forEach items="${list}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${commodityType.commodityTypeParentId==e.id}">selected="selected"</c:if>>${e.name }</option>
							</c:forEach>
						</select>
					</div>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">备注：</label>
			<div class="controls col-md-6">
				<input value="${commodityType.remark}" id="remark" name="remark" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>
	 
	</fieldset>
	
</div>