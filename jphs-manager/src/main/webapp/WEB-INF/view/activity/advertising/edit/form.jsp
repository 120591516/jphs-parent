<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<input type="hidden" value="${advertising.id}" name="id" id="id" />
	<div class="form-group">
		<label class="control-label col-md-5">图片：</label>
		<!-- 文件上传 -->
		<div class="controls col-md-6">
			<img alt="" height="200" width="200" id="images"
				src="${advertising.image}"> <input class="input-file"
				type="file" name="myfiles" id="image_s"
				onchange="ajaxFileUpload('image_s','image');" /> <input
				class="input-file" type="hidden" value="${advertising.image}"
				id="image" name="image" />
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5">背景图片：</label>
		<!-- 文件上传 -->
		<div class="controls col-md-6">
			<img alt="" height="200" width="200" id="bgImages"
				src="${advertising.bgImage}"> <input class="input-file"
				type="file" name="myfiles" id="bgImage_s"
				onchange="ajaxFileUpload('bgImage_s','bgImage');" /> <input
				class="input-file" type="hidden" value="${advertising.bgImage}"
				id="bgImage" name="bgImage" />
		</div>
	</div>
	
	<div class="form-group">
			<label class="control-label col-md-5" for="input01">开始时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="startTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${advertising.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择开始时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly readonly style="margin-left: 15px; width: 277px;"> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span>  --><input
					type="hidden" name="startTime" id="startTime" value="<fmt:formatDate value='${advertising.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">结束时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="endTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${advertising.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择结束时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly style="margin-left: 15px; width: 277px;"> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> --> <input
					type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value='${advertising.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
	
	
	<div class="form-group">
		<!-- 文本输入 -->
		<label class="control-label col-md-5" for="input01">链接地址：</label>
		<div class="controls col-md-6">
			<textarea class="form-control" id="link" name="link"
				placeholder="链接地址">${advertising.link}</textarea>
		</div>
	</div>
	<div class="form-group">
		<!-- 文本输入 -->
		<label class="control-label col-md-5" for="input01">排序：</label>
		<div class="controls col-md-6">
			<input type="number"
				value="${advertising.sort == null?1:advertising.sort}"
				onkeypress='return /^\d$/.test(String.fromCharCode(event.keyCode||event.keycode||event.which))'
				oninput='this.value = this.value.replace(/\D+/g, "")'
				onpropertychange='if(!/\D+/.test(this.value)){return;};this.value=this.value.replace(/\D+/g, "")'
				onblur='this.value = this.value.replace(/\D+/g, "")' min="1"
				step="1" value="15" style="width: 320px" 
				id="sort" name="sort" placeholder="定义排序顺序" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">产品类型：</label>
		<div class="controls col-md-6">
			<select class="form-control input-xlarge" id="serviceType" name="serviceType">
				<option value="">请选择</option>
				<option value="1" <c:if test="${advertising.serviceType==1}">selected="selected"</c:if>>服务</option>
				<option value="2" <c:if test="${advertising.serviceType==2}">selected="selected"</c:if>>商品</option>
				<option value="3" <c:if test="${advertising.serviceType==3}">selected="selected"</c:if>>其他</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">渠道：</label>
		<div class="controls col-md-6">
			<select class="form-control input-xlarge" id="type" name="type">
				<option value="">请选择</option>
				<option value="1"
					<c:if test="${advertising.type==1}">selected="selected"</c:if>>app护士端</option>
				<option value="2"
					<c:if test="${advertising.type==2}">selected="selected"</c:if>>app用户端</option>
				<option value="3"
					<c:if test="${advertising.type==3}">selected="selected"</c:if>>官网首页</option>
				<option value="4"
					<c:if test="${advertising.type==4}">selected="selected"</c:if>>app资讯</option>
			</select>
		</div>
	</div>
	
	<%-- <div class="form-group">
		<label class="control-label col-md-5">状态：</label>
		<div class="controls  col-md-6">
			<!-- 单行单选项目 -->
			<label class="radio radio-inline"> <input type="radio"
				value="0" name="status" id="status"
				<c:if test="${advertising.status==0}">checked="checked"</c:if>>
				启用
			</label> <label class="radio radio-inline"> <input type="radio"
				value="-1" name="status" id="status"
				<c:if test="${advertising.status==-1}">checked="checked"</c:if>>
				禁用
			</label>
		</div>
	</div> --%>
</form>
