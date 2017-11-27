<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib uri="http://www.jinpaihushi.com/jsp/core" prefix="jphs"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_left">
	<div class="common_left_h">数据统计</div>
	<dl>
		<%-- <jphs:hasAnyPermission
			url="/access/log/showDataByDay.jhtml,/access/log/showDataByWeek.jhtml,/access/log/showDataByMonth.jhtml,/access/log/showDataByYear.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>访问量统计 <i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/access/log/showDataByDay.jhtml">
					<dd>
						<a href="/access/log/showDataByDay.jhtml"><img
							src="/static/images/Group7.png" />日访问量</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/access/log/showDataByWeek.jhtml">
					<dd>
						<a href="/access/log/showDataByWeek.jhtml"><img
							src="/static/images/Group7.png" />周访问量</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/access/log/showDataByMonth.jhtml">
					<dd>
						<a href="/access/log/showDataByMonth.jhtml"><img
							src="/static/images/Group7.png" />月访问量</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/access/log/showDataByYear.jhtml">
					<dd>
						<a href="/access/log/showDataByYear.jhtml"><img
							src="/static/images/Group7.png" />年访问量</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission> --%>
		<jphs:hasAnyPermission
			url="/order/statistics/quantity.jhtml,/order/statistics/serviceRankingIndex.jhtml,/order/statistics/cityRankingIndex.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>订单量统计<i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/order/statistics/quantity.jhtml">
					<dd>
						<a href="/order/statistics/quantity.jhtml"><img
							src="/static/images/Group7.png" />订单量统计</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission
					url="/order/statistics/serviceRankingIndex.jhtml">
					<dd>
						<a href="/order/statistics/serviceRankingIndex.jhtml"><img
							src="/static/images/Group7.png" />服务排名</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/order/statistics/cityRankingIndex.jhtml">
					<dd>
						<a href="/order/statistics/cityRankingIndex.jhtml"><img
							src="/static/images/Group7.png" />区域排名</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
		<!--  -->
		<jphs:hasAnyPermission
			url="/person/statistics/userRegister.jhtml,/person/statistics/nurseRegister.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>注册量统计<i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/person/statistics/userRegister.jhtml">
					<dd>
						<a href="/person/statistics/userRegister.jhtml"><img
							src="/static/images/Group7.png" />用户注册量</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/person/statistics/nurseRegister.jhtml">
					<dd>
						<a href="/person/statistics/nurseRegister.jhtml"><img
							src="/static/images/Group7.png" />服务人员</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
		<jphs:hasAnyPermission
			url="/nurse/statistics/properties.jhtml,/nurse/statistics/serviceRank.jhtml,/nurse/statistics/orderRank.jhtml">
			<dt class="public_left_active">
				<span class="common_left_icon"> </span>服务人员统计<i
					class="public-horn-135 left-mg"></i>
			</dt>
			<div class="public_left_list">
				<jphs:hasPermission url="/nurse/statistics/properties.jhtml">
					<dd >
						<a href="/nurse/statistics/properties.jhtml"><img
							src="/static/images/Group7.png" />人员属性分析</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/nurse/statistics/serviceRank.jhtml">
					<dd >
						<a href="/nurse/statistics/serviceRank.jhtml"><img
							src="/static/images/Group7.png" />个人服务分析</a>
					</dd>
				</jphs:hasPermission>
				<jphs:hasPermission url="/nurse/statistics/orderRank.jhtml">
					<dd>
						<a href="/nurse/statistics/orderRank.jhtml"><img
							src="/static/images/Group7.png" />个人订单分析</a>
					</dd>
				</jphs:hasPermission>
			</div>
		</jphs:hasAnyPermission>
	</dl>
</div>