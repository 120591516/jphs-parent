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
			<span>姓名：</span><span class="title_value">${withdrawCash.accountName}</span></p>
		<p>
			<span>手机号：</span><span class="title_value">${withdrawCash.phone}</span>
		</p>
		<p>
			<span>提现金额：</span><span class="title_value">${withdrawCash.amount}</span>
		</p>
		<p>
			<span>支付宝账号：</span><span class="title_value">${withdrawCash.alipayAccount}</span>
		</p>
		<p>
			<span>提交审核时间：</span><span class="title_value"><fmt:formatDate value="${withdrawCash.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		</p>
	</div>
	<div class="col-md-6">
		<p>
			<span>审核结果：</span><span class="title_value"><c:if test="${withdrawCash.status==1}"><span style="color: #34BC2C;">已提现</span></c:if></span></p>
		<p>
			<span>审核人姓名：</span><span class="title_value">${withdrawCash.auditUserName}</span>
		</p>
		<p>
			<span>审核人ID：</span><span class="title_value">${withdrawCash.auditUserId}</span>
		</p>
		<p>
			<span>审核时间：</span><span class="title_value"><fmt:formatDate value="${withdrawCash.auditTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		</p>
		<p>
			<span>审核意见：</span><span class="title_value">${withdrawCash.remark}</span>
		</p>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">提现记录信息</p>
</div>

<form id="serach-form" method="get" action="/withdraw/cash/redirectUpdate.jhtml">
	<table class="data_table text-center" style="width: 950px;">
	<thead>
	 <tr>
	    <th width="50">序号</th>
	    <th width="140">订单号</th>
	    <th width="260">说明</th>
	    <th width="100">支付方式</th>
	    <th width="100">用户实付金额</th>
	    <th width="100">利润</th>
	    <th width="100">护士应得金额</th>
	    <th width="100">订单状态</th>
	  </tr>
	</thead>
	 <tbody>
	 	<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="tOne" varStatus="status">
					<c:choose>
						<c:when test="${tOne.order != null}">
							<tr>
								<td><span class="title_value">${status.index +1 }</span></td>
								<td><span class="title_value">${tOne.order.orderNo }</span></td>
								<td><span class="title_value">${tOne.remark }</span></td>
								<td>
									<span class="title_value">
										<c:if test="${tOne.order.transaction.payType == 1 }">
											支付宝
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 2 }">
											微信
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 3 }">
											余额
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 4 }">
											银联
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 5 }">
											vip卡
										</c:if>
									</span>
								</td>
								<td>
									<span class="title_value userPayPrice">
										<c:choose>
											<c:when test="${tOne.order.orderGoods.payPrice !=null }">
												${tOne.order.orderGoods.payPrice }
											</c:when>
											<c:otherwise>
											0
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<td>
									<span class="title_value profit">
										<c:choose>
											<c:when test="${tOne.order.orderGoods.profit !=null }">
												${tOne.order.orderGoods.profit }
											</c:when>
											<c:otherwise>
											0
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<td><span class="title_value nursePayPrice">${tOne.order.orderServiceList[0].price }</span></td>
								<td>
									<span class="title_value">
										<c:if test="${tOne.order.schedule == 0 }">
											待支付
										</c:if>
										<c:if test="${tOne.order.schedule == 1 }">
											待接单
										</c:if>
										<c:if test="${tOne.order.schedule == 2 }">
											已接单
										</c:if>
										<c:if test="${tOne.order.schedule == 3 }">
											执行中
										</c:if>
										<c:if test="${tOne.order.schedule == 4 }">
											待确定
										</c:if>
										<c:if test="${tOne.order.schedule == 5 }">
											已完成
										</c:if>
										<c:if test="${tOne.order.schedule == 6 }">
											已取消
										</c:if>
										<c:if test="${tOne.order.schedule == 7 }">
											申诉中
										</c:if>
									</span>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td><span class="title_value">${status.index +100 }</span></td>
								<td><span class="title_value">${tOne.order.orderNo }</span></td>
								<td><span class="title_value">${tOne.remark }</span></td>
								<td>
									<span class="title_value">
										<c:if test="${tOne.order.transaction.payType == 1 }">
											支付宝
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 2 }">
											微信
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 3 }">
											余额
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 4 }">
											银联
										</c:if>
										<c:if test="${tOne.order.transaction.payType == 5 }">
											vip卡
										</c:if>
									</span>
								</td>
								<td>
									<span class="title_value userPayPrice">
										<c:choose>
											<c:when test="${tOne.order.orderGoods.payPrice !=null }">
												${tOne.order.orderGoods.payPrice }
											</c:when>
											<c:otherwise>
											0
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<td>
									<span class="title_value profit">
										<c:choose>
											<c:when test="${tOne.order.orderGoods.profit !=null }">
												${tOne.order.orderGoods.profit }
											</c:when>
											<c:otherwise>
											0
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<td><span class="title_value nursePayPrice">${tOne.amount }</span></td>
								<td>
									<span class="title_value">
										<c:if test="${tOne.order.schedule == 0 }">
											待支付
										</c:if>
										<c:if test="${tOne.order.schedule == 1 }">
											待接单
										</c:if>
										<c:if test="${tOne.order.schedule == 2 }">
											已接单
										</c:if>
										<c:if test="${tOne.order.schedule == 3 }">
											执行中
										</c:if>
										<c:if test="${tOne.order.schedule == 4 }">
											待确定
										</c:if>
										<c:if test="${tOne.order.schedule == 5 }">
											已完成
										</c:if>
										<c:if test="${tOne.order.schedule == 6 }">
											已取消
										</c:if>
										<c:if test="${tOne.order.schedule == 7 }">
											申诉中
										</c:if>
									</span>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<tr>
					<td><span class="title_value">合计</span></td>
					<td></td>
					<td></td>
					<td></td>
					<td><span class="title_value" id="totalUserPayPrice"></span></td>
					<td><span class="title_value" id="totalProfit"></span></td>
					<td><span class="title_value" id="totalNursePayPrice"></span></td>
					<td></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="20" align="center">没有可显示的记录。</td>
				</tr>
			</c:otherwise>
		</c:choose>
	  </tbody>
	</table>
	<input name="id" type="hidden" value="${withdrawCash.id }"/>
	<div class="page">
		<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
	</div>
</form>
<%-- <div>
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<table id="withdrawCashTable" class="tableStyle">
			<tr>
				<td align="right">姓名：</td>
				<td  width="200">
					${withdrawCash.accountName}
				</td>
			</tr>
			<tr>
				<td align="right">手机号：</td>
				<td  width="200">
					${withdrawCash.phone}
				</td>
			</tr>
			<tr>
				<td align="right" width="200">提现金额：</td>
				<td width="200">
					${withdrawCash.amount}
				</td>
			</tr>
			<tr>
				<td align="right">支付宝账号：</td>
				<td  width="200">
					${withdrawCash.alipayAccount}
				</td>
			</tr>
			<tr>
				<td align="right">提现时间：</td>
				<td  width="200">
					<fmt:formatDate value="${withdrawCash.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td align="right">审核结果：</td>
				<td width="200">
					<c:if test="${withdrawCash.status==1}"><span style="color: #34BC2C;">已提现</span></c:if>
				</td>
			</tr>
			<tr>
				<td align="right">审核人姓名：</td>
				<td align="right" width="200">
					${withdrawCash.auditUserName}
				</td>
			</tr>
			<tr>
				<td align="right">审核人ID：</td>
				<td align="right" width="200">
					${withdrawCash.auditUserId}
				</td>
			</tr>
			<tr>
				<td align="right">审核时间：</td>
				<td  width="200">
					<fmt:formatDate value="${withdrawCash.auditTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td align="right">审核意见：</td>
				<td  width="200">
					${withdrawCash.remark}
				</td>
			</tr>
		</table>
	</div> --%>
	<%-- <div class="event_start_from">
		<table id="withdrawCashTable" class="data_table text-center" style="width:100%">
			<tr>
				<td  style="text-align: center;" width="60">序号</td>
				<td  style="text-align: center;" width="200">订单号</td>
				<td  style="text-align: center;" width="200">支付方式</td>
				<td  style="text-align: center;" width="200">用户实付金额</td>
				<td  style="text-align: center;" width="200">利润</td>
				<td  style="text-align: center;" width="200">护士应得金额</td>
				<td  style="text-align: center;" width="200">订单状态</td>
			</tr>
			
			<c:choose>
				<c:when test="${fn:length(withdrawCash.transactionList) > 0 }">
					<c:forEach	var="withdrawCashOne" items="${withdrawCash.transactionList }" varStatus="status">
						<tr>
							<td  style="text-align: center;" width="60">${status.index +1 }</td>
							<td  style="text-align: center;" width="200">${withdrawCashOne.order.orderNo }</td>
							<td  style="text-align: center;" width="200">
								<c:if test="${withdrawCashOne.order.transactions.payType == 1 }">
									支付宝
								</c:if>
								<c:if test="${withdrawCashOne.order.transactions.payType == 2 }">
									微信
								</c:if>
								<c:if test="${withdrawCashOne.order.transactions.payType == 3 }">
									余额
								</c:if>
								<c:if test="${withdrawCashOne.order.transactions.payType == 4 }">
									银联
								</c:if>
								<c:if test="${withdrawCashOne.order.transactions.payType == 5 }">
									vip卡
								</c:if>
							</td>
							<td  style="text-align: center;" width="200" class="userPayPrice">${withdrawCashOne.order.orderGoods.payPrice }</td>
							<td  style="text-align: center;" width="200" class="profit">${withdrawCashOne.order.orderGoods.profit }</td>
							<td  style="text-align: center;" width="200" class="nursePayPrice">${withdrawCashOne.order.orderServiceList[0].price }</td>
							<td  style="text-align: center;" width="200">
								<c:if test="${withdrawCashOne.order.schedule == 0 }">
									待支付
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 1 }">
									待接单
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 2 }">
									已接单
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 3 }">
									执行中
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 4 }">
									待确定
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 5 }">
									已完成
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 6 }">
									已取消
								</c:if>
								<c:if test="${withdrawCashOne.order.schedule == 7 }">
									申诉中
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td  style="text-align: center;" width="60">合计</td>
						<td  style="text-align: center;" width="200"></td>
						<td  style="text-align: center;" width="200"></td>
						<td  style="text-align: center;" width="200" id="totalUserPayPrice"></td>
						<td  style="text-align: center;" width="200" id="totalProfit"></td>
						<td  style="text-align: center;" width="200" id="totalNursePayPrice"></td>
						<td  style="text-align: center;" width="200"></td>
					</tr>
				</c:when>
				<c:otherwise>
					数据为空
				</c:otherwise>
			</c:choose>
		</table>
	</div> --%>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">审核信息</h4>
			</div>
			<div class="modal-body">
				<form id="withdrawForm" method="post" class="form-horizontal" action="/withdraw/cash/insert.jhtml">
					<input type="hidden" id="id" name="id" value="${withdrawCash.id}" /> 
					<div class="form-group">
						<label class="control-label col-md-3">审核结果</label>
						<div class="controls  col-md-6">
							<!-- 单行单选项目 -->
							<label class="radio radio-inline"> <input type="radio"
								value="1" id="status" name="status" checked="checked"> 通过
							</label> <label class="radio radio-inline"> <input type="radio"
								value="-1" id="status" name="status"> 不通过
							</label>
						</div>
					</div>
					<div class="form-group">
						<!-- 文本区域 -->
						<label class="control-label col-md-3">审核意见</label>
						<div class="controls col-md-6">
							<div class="textarea">
								<textarea id="remark" name="remark" maxlength="500" class="form-control"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button id="save" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>