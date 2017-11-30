<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="sunbox-alret">
	<div class="xuanzhuan"></div>
	<img src=""   class="fork"/>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span  style="font-size: 20px;">[订单信息]</span>
		</p>
		<p>
			<span>订单号：</span><span class="title_value"> ${jkwyOrder.no }</span>
		</p>
		<p>
			<span>下单时间：</span><span class="title_value">
			<fmt:formatDate value="${jkwyOrder.createTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<p>
			<span>订单状态：</span><span class="title_value">
				<c:if test="${jkwyOrder.schedule==0 }">待支付</c:if>
				<c:if test="${jkwyOrder.schedule==1 }">已支付</c:if>
				<c:if test="${jkwyOrder.schedule==2 }">已失效</c:if>
			</span>
		</p>
		<p>
			<span>支付时间：</span><span class="title_value">
			<fmt:formatDate value="${jkwyOrder.payTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<p>
			<span>套餐结束时间：</span><span class="title_value">
			<fmt:formatDate value="${jkwyOrder.endTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<p>
			<span>服务地址：</span><span class="title_value">${jkwyOrder.address },${jkwyOrder.detailAddress }</span>
		</p>
		<%-- <p>
			<span>服务备注：</span><span class="title_value">${order.remarks }</span>
		</p> --%>
		<%-- <p>
			<span>订单来源：</span><span class="title_value">
			<c:if test="${order.device ==1 }">IOS</c:if>
			<c:if test="${order.device ==2 }">安卓</c:if>
			<c:if test="${order.device ==3 }">微信</c:if>
			<c:if test="${order.device ==4 }">114等网站</c:if>
			<c:if test="${order.device ==5 }">后台</c:if>
			<c:if test="${order.device ==6 }">wap站</c:if></span>
		</p> --%>
		<%-- <p>
			<span>平台：</span><span class="title_value">${order.platformName }</span>
		</p> --%>
		<p>
			<span>推广码：</span><span class="title_value">${jkwyOrder.code }</span>
		</p>
		<p>
			<span>订单备注：</span><span class="title_value">${remark.content }</span>
		</p>
	</div>
	<div class="col-md-6">
		<p>
			<span style="font-size: 20px;">[用户信息]</span>
		</p>
		<p>
			<span>用户名称：</span><span class="title_value">${user.name }</span></span></p>
		<p>
			<span>用户联系电话：</span><span class="title_value">${user.phone }</span></p>
		<p>
			<span>用户性别：</span><span class="title_value">
			<c:if test="${user.sex==0 }">男</c:if>
			<c:if test="${user.sex==1 }">女</c:if></span>
		</p>

		<%-- <p>
			<span> 指定人姓名：</span><span class="title_value">
			<c:choose>
				<c:when test="${order.expectorId==order.acceptUserId }">${order.nurseName }</c:when>
				<c:otherwise>
					无指定人
				</c:otherwise>
			</c:choose></span>
		</p> 
		<p>
			<span>接单人信息</span>
		</p>
		<p>
			<span>接单人名称：</span><span class="title_value">${order.nurseName }</span></p>
		<p>
			<span>接单时间：</span><span class="title_value">
			<fmt:formatDate value="${order.acceptTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<p>
			<span>接单人联系电话：</span><span class="title_value">${order.nursePhone }</span></p>--%>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">套餐内服务内容</p>
	<div >
		<table id="dateTable" class="data_table" style="width:865px;margin-top:10px;text-align: center;">
	<thead>
		<th>序号</th>
			<th width="150">服务标题</th>
			<th width="450">服务内容</th>
			<th>总服务次数</th>
			<th>剩余服务次数</th>
			<th>操作</th>
			</thead>
			<c:choose>
				<c:when test="${fn:length(jkwyOrderContentList) >0}">
					<c:forEach items="${jkwyOrderContentList}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30" >${s.index+1}</td>
							<td class="title_value">${e.title}</td>
							<td class="title_value">${e.content}</td>
							<td class="title_value">${e.number}</td>
							<td class="title_value">${e.surplusNumber}</td>
							<td class="title_value">
								<%-- <jphs:hasPermission url="/product/redirectUpdate.jhtml">	 --%>			
								<a onclick="updataNumber('${e.id}')" title="修改">
									<img  src="/static/images/xiugai.png">
								</a>
								<%-- </jphs:hasPermission> --%>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示的记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">套餐亲友信息</p>
	<div >
		<table id="dateTable" class="data_table" style="width:865px;margin-top:10px;text-align: center;">
	<thead>
		<th>序号</th>
			<th>姓名</th>
			<th>电话</th>
			<th>性别</th>
			<th>关系</th>
			<th>身份证</th>
			<th>出生日期</th>
			</thead>
			<c:choose>
				<c:when test="${fn:length(relationList) >0}">
					<c:forEach items="${relationList}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30" >${s.index+1}</td>
							<td class="title_value">${e.name}</td>
							<td class="title_value">${e.phone}</td>
							<td class="title_value">
								<c:if test="${e.sex==1}">男</c:if>
								<c:if test="${e.sex==0}">女</c:if>
							</td>
							<td class="title_value">${e.relation}</td>
							<td class="title_value">${e.sfz}</td>
							<td class="title_value"><fmt:formatDate value="${e.birthday}" pattern="yyyy-MM-dd"/> </td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示的记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">交易信息</p>
	<div class="col-md-12">
		<p>
			<span>订单金额：</span><span class="title_value"><fmt:formatNumber value="${jkwyOrder.price }" type="currency"/></span><span>实际支付：</span><span class="title_value"><fmt:formatNumber value="${jkwyOrder.payPrice }" type="currency"/></span>
			<span>特价优惠金额：</span><span class="title_value"><fmt:formatNumber value="${jkwyOrder.activityPromotionPrice }" type="currency"/></span><span>优惠券优惠金额：</span><span class="title_value"><fmt:formatNumber value="${jkwyOrder.voucherPrice }" type="currency"/></span>
		</p>
		<p>
			<c:choose>
				<c:when test="${null!=transactionUser }">
					<span>支付时间：</span><span class="title_value">
					<fmt:formatDate value="${transactionUser.createTime }"
						pattern="yyyy-mm-dd  HH:mm:ss" /></span>
						<!-- (1支付宝，2微信，3余额，4银联，5vip卡支付) -->
					<span>支付方式：</span><span class="title_value">
					<c:if test="${transactionUser.payType==1 }">支付宝</c:if>
					<c:if test="${transactionUser.payType==2 }">微信</c:if>
					<c:if test="${transactionUser.payType==3 }">余额</c:if>
					<c:if test="${transactionUser.payType==4 }">银联</c:if>
					<c:if test="${transactionUser.payType==5 }">vip卡支付</c:if></span>
					<span>交易号：</span><span class="title_value">${transactionUser.outTradeNo }</span>
				</c:when>
				<c:otherwise>
					<span>无支付信息</span>
				</c:otherwise>
			</c:choose>
		</p>
		<c:choose>
			<c:when test="${null!=voucherUse }">
				<p>
					<span>优惠券类型：</span>
					<span class="title_value">
						<c:if test="${voucherUse.voucherRepertory.voucher.type==1 }">现金券</c:if>
						<c:if test="${voucherUse.voucherRepertory.voucher.type==2 }">满减卷</c:if>
						<c:if test="${voucherUse.voucherRepertory.voucher.type==3 }">折扣卷</c:if>
					</span>
					<span>优惠券面额：</span>
					<span class="title_value">
						<c:if test="${voucherUse.type!=3 }"><fmt:formatNumber value="${voucherUse.amount }" type="currency"/></c:if>
						<c:if test="${voucherUse.type==3 }">${voucherUse.amount*10 }折</c:if>
					</span>
				</p>
				<p>
					<span>优惠券使用结束时间：</span>
					<span class="title_value">
						<fmt:formatDate value="${voucherUse.endTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
					<span>优惠券使用时间：</span>
					<span class="title_value">
						<fmt:formatDate value="${voucherUse.useTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
				</p>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${null!=activityPromotion }">
				<p>
					<span>活动类型：</span>
					<span class="title_value">
						<c:if test="${activityPromotion.type==1 }">下单立减</c:if>
						<c:if test="${activityPromotion.type==2 }">首单立减</c:if>
						<c:if test="${activityPromotion.type==3 }">第二件立减</c:if>
					</span>
					<span>活动优惠金额：</span>
					<span class="title_value">
						<fmt:formatNumber value="${activityPromotion.price }" type="currency"/>
					</span>
				</p>
				<p>
					<span>活动开始时间：</span>
					<span>
						<fmt:formatDate value="${activityPromotion.beginTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
					<span>活动结束时间：</span>
					<span>
						<fmt:formatDate value="${voucherUse.endTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
				</p>
			</c:when>
		</c:choose>
	</div>

</div>
<!-- 模态框（Modal） -->
<div>
	<div class="modal fade" id="remarkModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">编辑订单备注信息</h4>
			</div>
				<form id="addRemark">
					<div style="text-align: center;">
						<div class="controls col-md-6"
							style="width: 100%; margin-left: 15px;">
							 <input type="hidden" id="sourceId" name= "sourceId" value ="${jkwyOrder.id }">
							  <input type="hidden" id="id" name= "id" value ="${remark.id }"> 
							 <textarea rows="" cols="" name="content" >${remark.content}</textarea>
						</div>
						<div>
							<input class="btn btn-primary" type="button" value="确定" onclick='addRemark()' />
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
