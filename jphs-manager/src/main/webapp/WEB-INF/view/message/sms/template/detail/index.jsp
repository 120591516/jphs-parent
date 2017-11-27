<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 10px 10px 10px;">
	<div class="common_right_title">
			<img src="/static/images/yousanjiaox.png" />信息管理<i class="public1-horn-45"></i>短信模板 <i class="public1-horn-45"></i>模板详情
		</b>
		
	</div>
		<form class="form-horizontal add_body" id="goodsForm" method="post" data-role="validate-form" action="/sms/template/insert.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
			<input type="button" class="public-info public_btn public_btn_left" name="button1" id="button1"
				value="返回" onclick="history.go(-1)">
		</div>
			<hr class="mt-5" />
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</form>
</div>