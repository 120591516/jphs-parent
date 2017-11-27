<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/share/statistics/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>资源名称：</td>
			<td width="200">
				<input type="text" name="goodsName" id="goodsName" value="${shareStatistics.goodsName}" />
			</td>
			<td>资源类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;"  name="type" id="type">
					<option value="">全部</option>
					<option value="1" <c:if test="${shareStatistics.type==1}">selected="selected"</c:if>>服务</option>
					<option value="2" <c:if test="${shareStatistics.type==2}">selected="selected"</c:if>>商品</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>目标平台：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;"  name="sharePlatform" id="sharePlatform">
				<!-- 	分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台 -->
					<option value="">全部</option>
					<option value="1" <c:if test="${shareStatistics.sharePlatform==1}">selected="selected"</c:if>>微信好友</option>
					<option value="2" <c:if test="${shareStatistics.sharePlatform==2}">selected="selected"</c:if>>微信朋友圈</option>
					<option value="3" <c:if test="${shareStatistics.sharePlatform==3}">selected="selected"</c:if>>QQ好友</option>
					<option value="4" <c:if test="${shareStatistics.sharePlatform==4}">selected="selected"</c:if>>QQ空间</option>
					<option value="5" <c:if test="${shareStatistics.sharePlatform==5}">selected="selected"</c:if>>微博</option>
					<option value="6" <c:if test="${shareStatistics.sharePlatform==6}">selected="selected"</c:if>>其他平台</option>
				</select>
			</td>
			<td>分享终端：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;"  name="shareDevice" id="shareDevice">
				<!-- 1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端 -->
					<option value="">全部</option>
					<option value="1" <c:if test="${shareStatistics.shareDevice==1}">selected="selected"</c:if>>ios用户端</option>
					<option value="2" <c:if test="${shareStatistics.shareDevice==2}">selected="selected"</c:if>>ios护士端</option>
					<option value="3" <c:if test="${shareStatistics.shareDevice==3}">selected="selected"</c:if>>Android用户端</option>
					<option value="4" <c:if test="${shareStatistics.shareDevice==4}">selected="selected"</c:if>>Android护士端</option>
					<option value="5" <c:if test="${shareStatistics.shareDevice==5}">selected="selected"</c:if>>wap站</option>
					<option value="6" <c:if test="${shareStatistics.shareDevice==6}">selected="selected"</c:if>>其他终端</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>分享开始时间：</td>
			<td width="200">
				<div style="width: 100%;" class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-mm-dd" data-link-field="workYears" data-link-format="yyyy-mm-dd">
					<input class="form-control" id="beginTime" name="beginTime" value="<fmt:formatDate value="${shareStatistics.beginTime }" type="both" pattern="yyyy-MM-dd" />"
						size="14" placeholder="请选择创建时间" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-remove" title="清空"></span></span>
				</div>
			</td>
			<td>分享结束时间：</td>
			<td width="200">
				<div style="width: 100%;" class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-mm-dd" data-link-field="workYears" data-link-format="yyyy-mm-dd">
					<input class="form-control" id="endTime" name="endTime" value="<fmt:formatDate value="${shareStatistics.endTime }" type="both" pattern="yyyy-MM-dd" />"
						size="14" placeholder="请选择创建时间" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-remove" title="清空"></span></span>
				</div>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>