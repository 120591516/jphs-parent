<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />信息管理<i class="public1-horn-45"></i>短信模板<i class="public1-horn-45"></i>短信模板列表 
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<jphs:hasPermission url="/sms/template/insert.jhtml">
	<button id="redirectAddPage" type="button" class="public-info public_btn">添加模板</button>
	</jphs:hasPermission>
	<jsp:include page="list.jsp"></jsp:include>

</div>