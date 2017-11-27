<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />家庭护士<i class="public1-horn-45"></i>订单管理<i class="public1-horn-45"></i>
			订单编辑
</div>
<form class="form-horizontal" id="productForm" method="post" data-role="validate-form" action="/family/package/insert.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
		<jphs:hasPermission url="/family/package/insert.jhtml">
			<!-- <button id="save" class="public-info public_btn public_btn_left" data-role="save-btn">保存</button> -->
			</jphs:hasPermission>
			<input type="button" class="public-info public_btn public_btn_left"
				name="button1" id="button1" value="返回" onclick="history.go(-1)" />
		</div>
		<hr class="mt-5" />
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</form>