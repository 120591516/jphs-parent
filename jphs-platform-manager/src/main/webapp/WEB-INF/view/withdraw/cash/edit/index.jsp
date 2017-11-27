<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />账户管理<i class="public1-horn-45"></i>账户管理 <i class="public1-horn-45"></i>提现服务
</div>
<div class="marage_right_content">
	<div class="clearfix">
		<input type="button" class="public-info public_btn public_btn_left" name="button1" id="button1" value="返回" onclick="history.go(-1)">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${withdrawCash.status!=1}">
			<button class="public-info public_btn public_btn_left" data-toggle="modal" data-target="#myModal">审核</button>
		</c:if>
	</div>
		<hr class="mt-5" />
	<jsp:include page="form.jsp"></jsp:include>
	<div style="padding-bottom:50px">
<!-- 		<button id="save" class="public-info public_btn public_btn_right" data-role="save-btn" style="margin-right:50px;">审核</button>
 -->	<c:if test="${withdrawCash.status!=1}">
		<button class="public-info public_btn public_btn_right" data-toggle="modal" data-target="#myModal" style="margin-top:10px">审核</button>
		</c:if>
    </div>
</div>
