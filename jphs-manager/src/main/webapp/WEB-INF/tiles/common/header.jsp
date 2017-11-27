<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.jinpaihushi.com/jsp/core" prefix="jphs"%>
<header class="manager_header">
	<img src="/static/images/logo.png" class="public-logo" />
	<ul>
		<li class="li1"><a href="/welcome/index.jhtml">首页</a></li>
		<jphs:hasPermission url="/welcome/user.jhtml">
			<li class="li8">
			<a href="/welcome/user.jhtml">会员管理</a>
			<div class="header_erji">
			<div class="clearfix header_width">
				<dl>
					<jphs:hasPermission url="/user/index.jhtml">
						<dd>
							<a href="/user/index.jhtml">会员管理</a>
						</dd>
					</jphs:hasPermission>
					<jphs:hasPermission url="/transaction/index.jhtml">
						<dd>
							<a href="/transaction/index.jhtml">收支记录</a>
						</dd>
					</jphs:hasPermission>
					<jphs:hasPermission url="/voucher/user/index.jhtml">
						<dd>
							<a href="/voucher/user/index.jhtml">会员优惠券</a>
						</dd>
					</jphs:hasPermission>
				</dl>
			</div>
			</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/nurse.jhtml">
			<li class="li2">
			<a href="/welcome/nurse.jhtml">护士管理</a>
			<div class="header_erji">
				<div class="clearfix header_width">
					<dl>
						<jphs:hasPermission url="/audit/index.jhtml">
							<dd>
								<a href="/audit/index.jhtml">资质认证</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/nurse/index.jhtml">
							<dd>
								<a href="/nurse/index.jhtml">护士管理</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/jobtitle/index.jhtml">
							<dd>
								<a href="/jobtitle/index.jhtml">职称类型管理</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/jobtitle/index.jhtml">
							<dd>
								<a href="/jobtitle/index.jhtml">职称管理</a>
							</dd>
						</jphs:hasPermission>
					</dl>
				</div>
			</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/order.jhtml">
			<li class="li3"><a href="/welcome/order.jhtml">订单管理</a>
				<div class="header_erji">
				<div class="clearfix header_width">
					<dl>
						<jphs:hasPermission url="/order/index.jhtml">
							<dd>
								<a href="/order/index.jhtml">服务订单</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/commodity/order/index.jhtml">
							<dd>
								<a href="/commodity/order/index.jhtml?flag=1">商品订单</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/commodity/order/index.jhtml">
							<dd>
								<a href="/commodity/order/index.jhtml?crStatus=1&&flag=2">退货管理</a>
							</dd>
						</jphs:hasPermission>
						<jphs:hasPermission url="/custom/service/index.jhtml">
							<dd>
								<a href="/custom/service/index.jhtml">定制服务</a>
							</dd>
						</jphs:hasPermission>
					</dl>
				</div>
			</div>			
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/product.jhtml">
			<li class="li4"><a href="/welcome/product.jhtml">产品管理</a>
				<div class="header_erji">
							<div class="clearfix header_width">
								<dl>
									<jphs:hasPermission url="/product/index.jhtml">
										<dd>
											<a href="/product/index.jhtml">品类管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/goods/index.jhtml">
										<dd>
											<a href="/goods/index.jhtml">服务管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/platform/index.jhtml">
										<dd>
											<a href="/platform/index.jhtml">平台管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/site/index.jhtml">
										<dd>
											<a href="/site/index.jhtml">站点管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/commodity/index.jhtml">
										<dd>
											<a href="/commodity/index.jhtml">商品管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/business/index.jhtml">
										<dd>
											<a href="/business/index.jhtml">商家管理</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/commodity/type/parent/index.jhtml">
										<dd>
											<a href="/commodity/type/parent/index.jhtml">商家一级分类</a>
										</dd>
									</jphs:hasPermission>
									<jphs:hasPermission url="/commodity/type/index.jhtml">
										<dd>
											<a href="/commodity/type/index.jhtml">商家二级分类</a>
										</dd>
									</jphs:hasPermission>
								</dl>
							</div>
				</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/activity.jhtml">
			<li class="li7"><a href="/welcome/activity.jhtml">活动管理</a>
				<div class="header_erji">
					<div class="clearfix header_width">
						<dl>
							<jphs:hasPermission url="/advertising/index.jhtml">
								<dd>
									<a href="/advertising/index.jhtml">轮播图管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/voucher/index.jhtml">
								<dd>
									<a href="/voucher/index.jhtml">优惠券管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/column/index.jhtml">
								<dd>
									<a href="/column/index.jhtml">栏目管理</a>
								</dd>
							</jphs:hasPermission>
						</dl>
					</div>
				</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/information.jhtml">
			<li class="li10"><a href="/welcome/information.jhtml">信息管理</a>
				<div class="header_erji">
					<div class="clearfix header_width">
						<dl>
							<jphs:hasPermission url="/information/channel/index.jhtml">
								<dd>
									<a href="/information/channel/index.jhtml">频道管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/information/index.jhtml">
								<dd>
									<a href="/information/index.jhtml">资讯管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/information/evaluate/index.jhtml">
								<dd>
									<a href="/information/evaluate/index.jhtml">评论管理</a>
								</dd>
							</jphs:hasPermission>
						</dl>
					</div>
				</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/statistics.jhtml">
			<li class="li5"><a href="/welcome/statistics.jhtml">数据统计</a>
				<div class="header_erji">
					<div class="clearfix header_width">
						<dl>
							<%-- <jphs:hasPermission url="/access/log/showDataByDay.jhtml">
								<dd>
									<a href="/access/log/showDataByDay.jhtml">日访问量</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/access/log/showDataByWeek.jhtml">
								<dd>
									<a href="/access/log/showDataByWeek.jhtml">周访问量</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/access/log/showDataByMonth.jhtml">
								<dd>
									<a href="/access/log/showDataByMonth.jhtml">月访问量</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/access/log/showDataByYear.jhtml">
								<dd>
									<a href="/access/log/showDataByYear.jhtml">年访问量</a>
								</dd>
							</jphs:hasPermission> --%>
							<jphs:hasPermission url="/order/statistics/quantity.jhtml">
								<dd>
									<a href="/order/statistics/quantity.jhtml">订单统计</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/order/statistics/serviceRankingIndex.jhtml">
								<dd>
									<a href="/order/statistics/serviceRankingIndex.jhtml">服务排名</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/order/statistics/cityRankingIndex.jhtml">
								<dd>
									<a href="/order/statistics/cityRankingIndex.jhtml">区域排名</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/person/statistics/userRegister.jhtml">
								<dd>
									<a href="/person/statistics/userRegister.jhtml">用户注册量</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/person/statistics/nurseRegister.jhtml">
								<dd>
									<a href="/person/statistics/nurseRegister.jhtml">服务人员</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/nurse/statistics/properties.jhtml">
								<dd>
									<a href="/nurse/statistics/properties.jhtml">人员属性分析</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/nurse/statistics/serviceRank.jhtml">
								<dd>
									<a href="/nurse/statistics/serviceRank.jhtml">个人服务分析</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/nurse/statistics/orderRank.jhtml">
								<dd>
									<a href="/nurse/statistics/orderRank.jhtml">个人订单分析</a>
								</dd>
							</jphs:hasPermission>
						</dl>
					</div>
				</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/withdraw.jhtml"> 
			<li class="li2"><a href="/welcome/withdraw.jhtml">账户管理</a>
				<div class="header_erji">
					<div class="clearfix header_width">
						<dl>
							<jphs:hasPermission url="/withdraw/cash/index.jhtml">
								<dd>
									<a href="/withdraw/cash/index.jhtml">提现管理</a>
								</dd>
							</jphs:hasPermission>
						</dl>
					</div>
				</div>
			</li>
		</jphs:hasPermission>
		<jphs:hasPermission url="/welcome/system.jhtml">
			<li class="li9"><a href="/welcome/system.jhtml">系统管理</a>
				<div class="header_erji">
					<div class="clearfix header_width">
						<dl>
							<jphs:hasPermission url="/system/user/index.jhtml">
								<dd>
									<a href="/system/user/index.jhtml">用户管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/system/role/index.jhtml">
								<dd>
									<a href="/system/role/index.jhtml">角色管理</a>
								</dd>
							</jphs:hasPermission>
							<jphs:hasPermission url="/system/module/moduleTree.jhtml">
								<dd>
									<a href="/system/module/moduleTree.jhtml">模块管理</a>
								</dd>
							</jphs:hasPermission>
						</dl>
					</div>
				</div>
			</li>
		</jphs:hasPermission>
		<%-- 		<li class="li6"><span>${sessionScope.session_user.name }</span> <span><img
				src="/static/images/sanjiaox.png" /></span></li> --%>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" role="button" aria-haspopup="true"
			aria-expanded="false"><span>${sessionScope.session_user.name }</span>
				<span><img src="/static/images/sanjiaox.png" /></span></a>
			<ul class="dropdown-menu">
				<li><a href="#" onclick="logout()">退出</a></li>
			</ul></li>
	</ul>
</header>
<script>
	$(".manager_header ul li").click(function() {
		$(".manager_header ul li").removeClass("active");
		$(this).addClass("active");
	})
	$("ul li").hover(function() {
		/* $(".line").show(); */
		$(this).find(".header_erji").stop(true, true).slideDown(300);
	}, function() {
		/* $(".line").hide(); */
		$(this).find(".header_erji").hide();
	})
	$(".header_erji").hover(function() {
		$(this).show();
	}, function() {
		$(this).slideUp(200);
	})
</script>
