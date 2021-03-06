﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />护士管理 <i
		class="public1-horn-45"></i> 护士管理 <i class="public1-horn-45"></i>${nurse.id == null?'添加护士':'编辑护士'}
</div>
<div class="marage_right_content">
	<form class="form-horizontal edit_form" id="nurseForm" method="post"
		data-role="validate-form" action="/nurse/insert.jhtml">
		<div class="clearfix">
			<jphs:hasPermission url="/nurse/insert.jhtml">
				<button type="submit" id="save" class="public-info public_btn public_btn_left" data-role="save-btn">保存</button>
			</jphs:hasPermission>
			<input type="button" class="public-info public_btn public_btn_left" name="button1" id="button1"
				value="返回" onclick="history.go(-1)">
		</div>
		<div class="event_handle_body">
			<div style="width: 630px; margin: 0 auto; padding: 10px 0px;">
				<jsp:include page="form.jsp"></jsp:include>
			</div>
		</div>
	</form>
</div>
