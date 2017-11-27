<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />产品管理<i class="public1-horn-45"></i>商品一级分类
	<i class="public1-horn-45"></i>商品一级类别列表
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<jphs:hasPermission url="/commodity/type/parent/redirectAddPage.jhtml">
	<button id="redirectAddPage" type="button" class="public-info public_btn">添加一级分类</button>
	</jphs:hasPermission>
	<!-- 右对齐按钮 -->
	<jsp:include page="list.jsp"></jsp:include>

</div>