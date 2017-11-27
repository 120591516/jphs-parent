<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib uri="http://www.jinpaihushi.com/jsp/core" prefix="jphs"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_left">
	<div class="common_left_h">订单管理</div>
	<dl>
		<jphs:hasAnyPermission
			url="/order/index.jhtml,/commodity/order/index.jhtml,/order/evaluation/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>订单管理 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/order/index.jhtml">
					<dd>
						<a href="/order/index.jhtml"><img
							src="/static/images/Group7.png" />服务订单</a>
					</dd>
					<!-- <dd>
					<a href="/order/index.jhtml?schedule=0"><img
						src="/static/images/Group7.png" />待支付</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=1"><img
						src="/static/images/Group7.png" />待接单</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=2"><img
						src="/static/images/Group7.png" />已接单</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=3"><img
						src="/static/images/Group7.png" />执行中</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=4"><img
						src="/static/images/Group7.png" />待确定</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=5"><img
						src="/static/images/Group7.png" />已完成</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=6"><img
						src="/static/images/Group7.png" />已取消</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=7"><img
						src="/static/images/Group7.png" />申诉中</a>
				</dd> -->
				</jphs:hasPermission>
				<!-- 
				<jphs:hasPermission url="/order/evaluation/index.jhtml">
					<dd>
						<a href="/order/evaluation/index.jhtml"><img
							src="/static/images/Group7.png" />服务评价</a>
					</dd>
				</jphs:hasPermission>
				 -->
				<jphs:hasPermission url="/commodity/order/index.jhtml">
					<dd>
						<a href="/commodity/order/index.jhtml?flag=1"><img
							src="/static/images/Group7.png" />商品订单</a>
					</dd>
					<dd>
						<a href="/commodity/order/index.jhtml?crStatus=1&&flag=2"><img
							src="/static/images/Group7.png" />退货管理</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
		<jphs:hasAnyPermission url="/custom/service/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>定制服务 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/custom/service/index.jhtml">
					<dd>
						<a href="/custom/service/index.jhtml"><img
							src="/static/images/Group7.png" />定制服务</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
		<jphs:hasAnyPermission
			url="/family/package/index.jhtml,/family/mode/index.jhtml,/family/order/index.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>家庭护士 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/family/package/index.jhtml">
					<dd>
						<a href="/family/package/index.jhtml"><img
							src="/static/images/Group7.png" />套餐管理</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/family/mode/index.jhtml">
					<dd>
						<a href="/family/mode/index.jhtml"><img
							src="/static/images/Group7.png" />获取方式管理</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/family/order/index.jhtml">
					<dd>
						<a href="/family/order/index.jhtml"><img
							src="/static/images/Group7.png" />订单管理</a>
					</dd>
				</jphs:hasPermission>
				
				<jphs:hasPermission url="/family/card/index.jhtml">
					<dd>
						<a href="/family/card/index.jhtml"><img
							src="/static/images/Group7.png" />兑换码管理</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
	</dl>
</div>