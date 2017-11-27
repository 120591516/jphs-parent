<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">信息管理</div>
	<dl>
		<jphs:hasAnyPermission
			url="/information/channel/index.jhtml,/information/index.jhtml,/information/evaluate/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>资讯管理 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/information/channel/index.jhtml">
					<dd>
						<a href="/information/channel/index.jhtml"><img
							src="/static/images/Group7.png" />频道管理</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/information/index.jhtml">
					<dd>
						<a href="/information/index.jhtml"><img
							src="/static/images/Group7.png" />资讯管理</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/information/evaluate/index.jhtml">
					<dd>
						<a href="/information/evaluate/index.jhtml"><img
							src="/static/images/Group7.png" />评论管理</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
			<jphs:hasAnyPermission
			url="/information/channel/index.jhtml,/information/index.jhtml,/information/evaluate/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>短信服务 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/sms/template/index.jhtml">
					<dd>
						<a href="/sms/template/index.jhtml"><img
							src="/static/images/Group7.png" />短信模板</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/sms/history/index.jhtml">
					<dd>
						<a href="/sms/history/index.jhtml"><img
							src="/static/images/Group7.png" />短信中心</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
	</dl>
</div>