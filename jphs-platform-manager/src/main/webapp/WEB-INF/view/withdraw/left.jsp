<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">账户管理</div>
	<dl>
		<jphs:hasAnyPermission url="/withdraw/cash/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>账户管理 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/withdraw/cash/index.jhtml">
					<dd>
						<a href="/withdraw/cash/index.jhtml"><img
							src="/static/images/Group7.png" />提现管理</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
	</dl>
</div>
