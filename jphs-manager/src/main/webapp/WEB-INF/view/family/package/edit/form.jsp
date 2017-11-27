<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${familyPackage.id}" />
		<input type="hidden" value="0" name="status"/>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" placeholder="请输入名称"
					value="${familyPackage.title}" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="type" name="type" >
					<c:if test="${familyPackage.type == 1 }">
						<option value="1" selected="selected">单人版</option>
					</c:if>
					<c:if test="${familyPackage.type == 2 }">
						<option value="2" selected="selected">三人版</option>
					</c:if>
					<c:if test="${familyPackage.type != 1 }">
						<option value="1" >单人版</option>
					</c:if>
					<c:if test="${familyPackage.type != 2 }">
						<option value="2">三人版</option>
					</c:if>
				</select>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐价格：</label>
			<div class="controls col-md-6">
				<input type="number" id="price" name="price" placeholder="请输入价格"
					value="${familyPackage.price}" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">头图：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="images" src="${familyPackage.image}">
					<div>尺寸   94*94 比例（ 1:1 ）<br>*&nbsp;图片格式必须为.png格式</div>
				<input class="input-file" type="file" name="myfiles" id="image_s" onchange="ajaxFileUpload('image_s','image');" />
				<input class="input-file" type="hidden" id="image" name="image" value="${familyPackage.image}" />
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">介绍：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="subTitle" name="subTitle" placeholder="说明"
						class="form-control">${familyPackage.subTitle}</textarea>
				</div>
			</div>
		</div>
		
		<div class="public_editor">
			<h5 style="font-size:14px;padding-left:58px">服务详情：</h5>
			<p>
				<textarea name="content" id="myEditor"  class="form-control" style="width:805px;height:500px;">${familyPackage.content}</textarea>
			</p>
		</div>

	</fieldset>
</form>