<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<script type="text/javascript">
	var context =${context}
</script>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" /> 数据统计 <i
		class="public1-horn-45"></i>注册量统计<i class="public1-horn-45"></i>服务人员
</div>
<div class="tab">
	<ul id="myTabs" class="nav nav-tabs" role="tablist">
		<li role="presentation" id="all">
			<a href="#all" aria-controls="home" role="tab" data-toggle="tab" value="">全部</a>
		</li>
		<li role="presentation" id="nurse">
			<a href="#nurse" aria-controls="profile" role="tab" data-toggle="tab" value="1">护士</a>
		</li>
		<li role="presentation" id="mys">
			<a href="#mys" aria-controls="messages" role="tab" data-toggle="tab" value="3">母婴师</a>
		</li>
		<li role="presentation" id="kfs">
			<a href="#kfs" aria-controls="settings" role="tab" data-toggle="tab" value="2">康复师</a>
		</li>
	</ul>
</div>
<jsp:include page="week/index.jsp"></jsp:include>
<jsp:include page="month/index.jsp"></jsp:include>
<jsp:include page="year/index.jsp"></jsp:include>
<jsp:include page="annual/index.jsp"></jsp:include>
