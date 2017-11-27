<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>

<div class="details_box clearfix">

	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span  style="font-size: 20px;">[订单信息]</span>
		</p>
		<p>
			<span>订单号：</span><span class="title_value">${commodityOrder.orderNo }</span> 
		</p>
		<p>
			<span>订单状态：</span><span style="color:red">
			<c:if test="${commodityOrder.schedule == -3}">
								已取消
								</c:if>
			<c:if test="${commodityOrder.schedule == -2}">
								退货中
								</c:if>
			<c:if test="${commodityOrder.schedule == -1}">
								已取消
								</c:if>
			<c:if test="${commodityOrder.schedule == 0}">
								待支付
								</c:if>
			<c:if test="${commodityOrder.schedule == 1}">
								待发货
								</c:if>
			<c:if test="${commodityOrder.schedule == 2}">
								待收货
								</c:if>
			<c:if test="${commodityOrder.schedule == 4}">
								已完成
								</c:if>
			<c:if test="${commodityOrder.schedule == 5}">
								已删除
								</c:if>
								</span> 
		</p>
		<p>
			<span>订单金额：</span><span class="title_value"> ￥${commodityOrder.payPrice }</span> 
			</p><p>
			<span>实付金额：</span><span class="title_value"> ￥${commodityOrder.payPrice }</span> 
		</p>
		<p>
			<span>优惠券：</span><span class="title_value">
			<c:choose>
				<c:when test="${commodityOrder.voucherPrice!=null}">
				 ￥${commodityOrder.voucherPrice}
				</c:when>
				<c:otherwise>
					￥0.00
				</c:otherwise>
			</c:choose>
			</span> 
			</p><p>
			<span>物流费：</span><span class="title_value"> ￥0.00</span> 
		</p>
		<p>
			<span>物流信息：</span> <span class="title_value">
			<c:if test="${cl.no!=null }">
				${cl.name }
				：${cl.no }
				
			
			</c:if>
			</span> 
			<a data-toggle="modal" data-target="#Modal" style="color: blue;"  >点击查看物流>></a>
		</p>
		<p>
			<span>支付方式：</span>
			  <span class="title_value">
			<c:if test="${transaction.payType==1 }">
				 支付宝
			 </c:if>
			 <c:if test="${transaction.payType==2 }">
				 微信
			 </c:if>
			 <c:if test="${transaction.payType==3 }">
				余额
			 </c:if>
			 <c:if test="${transaction.payType==4 }">
				银联
			 </c:if>
			<c:if test="${transaction.payType==5 }">
				VIP卡
			 </c:if>
			 </span> 
		</p>
	<p>

		<span>下单时间：</span><span class="title_value">
		<fmt:formatDate value="${commodityOrder.createTime}"
							pattern="yy-MM-dd HH:mm" />
							</span> 
	</p>
	<p> 
		<span>支付时间：</span><span class="title_value">
		 	<fmt:formatDate value="${commodityOrder.payTime}"
					pattern="yy-MM-dd HH:mm" />
				</span> 	
	</p>
	<p>
		<span>提醒发货：</span><span class="title_value">
		<fmt:formatDate value="${commodityOrder.remindTime}"
							pattern="yy-MM-dd HH:mm" />
							</span> 
	</p>
	<p>
		<span>发货时间：</span><span class="title_value">
		<fmt:formatDate value="${commodityOrder.sendTime}"
							pattern="yy-MM-dd HH:mm" />
							</span> 
	</p>
	<p>
		<span>收货时间：</span><span class="title_value">
		<fmt:formatDate value="${commodityOrder.takeTime}"
							pattern="yy-MM-dd HH:mm" />
							</span> 
		<span></span><span></span><span></span> <span></span><span></span><span></span><span></span>
		<span></span> <%-- <span>评价时间：</span>
		<fmt:formatDate value="${commodityOrder.sendTime}"
							pattern="yyyy-MM-dd HH：mm" /> --%>
	</p>
	</div>
	<div class="col-md-6">
		<p>
			<span  style="font-size: 20px;">[用户信息]</span>
		</p>
		<p>
			<span>姓名：</span><span class="title_value">${user.name }</span> </p>
		<p>
			<span>联系电话：</span><span class="title_value">${user.phone }</span> </p>
		<p>
			<span>收货人：</span><span class="title_value">
			 ${commodityOrder.receiveName }</span> 
		</p>

		<p>
			<span> 收货地址：</span><span class="title_value">${commodityOrder.address } ${commodityOrder.detailAddress }</span> 
		</p>
		<p>
			<span>收货人电话：</span><span class="title_value">${commodityOrder.phone }</span> 
		</p>
		<p>
			<span>商品备注：</span><span class="title_value">${remark } 
		</p>
		<p>
			<span>订单备注：</span><span class="title_value">${r.content}
		</p>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi" >商品信息<input type="hidden" id="coId" name = "coId" value="${commodityOrder.id}"></p>
	<div class="col-md-6">
		<table class="data_table" style="width:850px">
	<thead>
		<tr>
			<th width="250px">序号</th>
			<th width="25px">商品LOGO</th>
			<th width="250px">商品名称</th>
			<th width="250px">型号/规格</th>
			<th width="250px">商品SKU</th>
			<th width="250px">数量</th>
			<th width="200px">金额</th>
			<th width="250px">分销人</th>
			<th width="250px">分销金额</th>
			<th width="250px">CODE</th>
			 <c:if test="${commodityOrder.flag == 2}">
			<th width="250px">申请原因</th>
			<th width="250px">退款审核</th>
			<th width="300px">操作<input type="hidden" id = "comCount" value = "${count}"> </th>
			
			</c:if>
			</tr>
			</thead>
			<c:choose>
			
			
				<c:when test="${fn:length(coiList) >0}">
					<c:forEach items="${coiList}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30"  class="title_value">${s.index+1}</td>
							<td><img alt="" height="50" width="50" id="aptitude_positives"
			src="${e.url}"></td>
							<td  class="title_value">${e.title}</td>
							<td  class="title_value">${e.commodityModel}</td>
							<td  class="title_value">${e.commodityPriceName}</td>
							<td class="title_value">${e.number}</td>
							<td class="title_value">
								<fmt:formatNumber value="${e.price }" type="currency"/>
							</td>
							<td class="title_value">${e.userName }</td>
							<td class="title_value"> 
								<fmt:formatNumber value="${e.profit * e.number}" type="currency"/>
							</td>
							<td class="title_value">${e.code }</td>
							 <c:if test="${commodityOrder.flag == 2}">
							<td class="title_value">${e.crReason }</td>
							<td class="title_value">	
								<c:if test="${e.crStatus == -2 }">
									拒绝
								</c:if>
								<c:if test="${e.crStatus == 2 }">
									同意
								</c:if> 
							  </td>
							<td>
								<%-- <c:if test="${e.crStatus == -2 }">
									拒绝
								</c:if>
								<c:if test="${e.crStatus == 2 }">
									同意
								</c:if> --%>
								<c:if test="${e.crStatus == 1 }">
									<a onclick=updateStatus("${e.crId }","-2") style="color: red">拒绝</a><br>
									<%-- <a onclick=updateStatus("${e.crId }","2")>退款</a> --%>
								<a style="color: green;"  data-toggle="modal" data-target="#tk${s.index+1}" onclick=updateStatus("${e.crId }","-2">同意</a>
								</c:if>
							</td>
							</c:if>
						</tr>
						
						<tr>
							<div class="modal fade" id="tk${s.index+1}" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">编辑订单备注信息</h4>
									</div>
										<form id ="tkForm">
											<div style="text-align: center;">
												<div class="controls col-md-6"
													style="width: 100%; margin-left: 15px;">
													 <input type="hidden" id="payType" name="payType" value="${transaction.payType }" /> 
													 <input type="hidden" id="creatorId" name="creatorId" value="${user.id }" /> 
													 <input type="hidden" id="creatorName" name="creatorName" value="${user.name }" /> 
													 <label class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
													<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8' id='tkInfo'>
														<p>
															<span>订单编号：</span>${commodityOrder.orderNo} 
															<input type="hidden"id="orderId" name="orderId" value="${commodityOrder.id }">
															<input type="hidden"id="orderId${s.index+1}" name="orderId${s.index+1}" value="${commodityOrder.id }">
															<input type="hidden"id="totalMoney" name="totalMoney" value="${e.price }">
															<input type="hidden"id="outTradeNo" name="outTradeNo" 
															<c:if test="${transaction.payType==2 }">value="${commodityOrder.orderNo }"</c:if>
															<c:if test="${transaction.payType==1  }">value="${transaction.outTradeNo }"</c:if>
															>
															<input type="hidden" id="crId" name="crId" value="${e.crId }">
															<input type="hidden" id="crId${s.index+1}" name="crId${s.index+1}" value="${e.crId }">
															<input type="hidden" id="coiId" name="coiId" value="${e.id }">
														</p>
														<p>
															退款金额：<input id="amount" name="amount"  value="${e.price }">元
															<input id="operate" name="operate" value="4" type="hidden">
														</p>
													
													</div>
												
												</div>
												<div>
													<!-- <button onclick="check()" >提交</button> -->
													<a onclick="check(${s.index+1})"><input type="button" class="btn btn-primary" value="提交"></a>
												</div>
											</div>
										</form>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal -->
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




<div>
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">物流信息</h4>
			</div>
				
					<div style="text-align: center;">
						<div class="controls col-md-6"
							style="width: 150%; margin-left: 15px;">
						</div>
						<div >
							 <c:choose>
				<c:when test="${fn:length(LogisticsList) >0}">
					<c:forEach items="${LogisticsList}" var="e" varStatus="s">
						<div >
							 
							<span>${e.AcceptStation }</span>
							 <span>${e.AcceptTime }</span>
							  
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示的记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
						</div>
						</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


<div>
	<div class="modal fade" id="fhModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加物流信息</h4>
			</div>
				<form id="logistics">
					<div style="text-align: center;">
						<div class="controls col-md-6"
							style="width: 100%; margin-left: 15px;">
							<select class="marage_select" id="logisticsId" name="logisticsId">
								<option value="">全部</option>
								<c:forEach items="${lList}" var="e" varStatus="s">
									<option value="${e.id }">${e.name }</option>
								</c:forEach>
							</select> <span>物流单号</span> <span> <input type="text"
								id="no" name="no" /> <input type="hidden"
								id="commodityOrderId" name="commodityOrderId" value="${commodityOrder.id }" />
							</span>
						</div>
						<div>
							<a onclick='addLogistics()'>确定</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


<div>
	<div class="modal fade" id="remark" tabindex="-1" role="dialog"
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
							 <input type="hidden" id="sourceId" name= "sourceId" value ="${commodityOrder.id }">
							 <input type="hidden" id="id" name= "id" value ="${r.id }">
							 <textarea rows="" cols="" name="content" >${r.content}</textarea>
						</div>
						<div>
							<input class="btn btn-primary" type="button" value="确定" onclick='addRemark()' >
							
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>







