<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">优惠券名称：</label>
		<div class="controls col-md-6">
			<span class="title_value">${voucher.name }</span>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">产品类型：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <c:if
					test="${voucher.productType==1 }">服务</c:if> <c:if
					test="${voucher.productType==2 }">商品</c:if>
					<c:if
					test="${voucher.productType==3 }">套餐</c:if></span>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">优惠券类型：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <c:if test="${voucher.type==1 }">现金券</c:if>
				<c:if test="${voucher.type==2 }">满减券</c:if> <c:if
					test="${voucher.type==3 }">折扣券</c:if></span>
		</div>
	</div>
	<c:if test="${voucher.productType==2 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">支持类型：</label>
			<div class="controls col-md-6">
				<span class="title_value"> <c:if
						test="${voucher.supportType==1 }">商品</c:if> <c:if
						test="${voucher.supportType==2 }">商家</c:if> <c:if
						test="${voucher.supportType==3 }">通用</c:if></span>
			</div>
		</div>
	</c:if>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">支持品类/服务：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <c:if
					test="${voucher.supportType!=3 }">${voucher.productName }${voucher.goodsName }</c:if>

				<c:if test="${voucher.supportType==3 }">所有商品</c:if></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">批次号：</label>
		<div class="controls col-md-6">
			<span class="title_value">${voucher.batchNo}</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">兑换开始时间：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <fmt:formatDate
					value="${voucher.startTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">兑换结束时间：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <fmt:formatDate
					value="${voucher.endTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">激活开始时间：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <fmt:formatDate
					value="${voucher.activationStartTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">激活结束时间：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <fmt:formatDate
					value="${voucher.activationEndTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		</div>
	</div>


	<div class="form-group">
		<label class="control-label col-md-5" for="input01">数量：</label>
		<div class="controls col-md-6">
			<span class="title_value">${count }</span>
		</div>
	</div>

	<c:if test="${voucher.type==1 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">面值：</label>
			<div class="controls col-md-6">
				<span class="title_value"> <fmt:formatNumber
						value="${voucher.amount }" type="currency" /></span>
			</div>
		</div>
	</c:if>
	<c:if test="${voucher.type==2 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">满减券：</label>
			<div class="controls col-md-6">
				<span class="title_value">消费满 <fmt:formatNumber
						value="${voucher.conditionAmount }" type="currency" /> 减 <fmt:formatNumber
						value="${voucher.amount }" type="currency" /></span>
			</div>
		</div>
	</c:if>
	<c:if test="${voucher.type==3 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">折扣券：</label>
			<div class="controls col-md-6">
				<span class="title_value">${voucher.amount *10}折</span>
			</div>
		</div>
	</c:if>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">创建人：</label>
		<div class="controls col-md-6">
			<span class="title_value">${voucher.creatorName }</span>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">创建时间：</label>
		<div class="controls col-md-6">
			<span class="title_value"> <fmt:formatDate
					value="${voucher.createTime}" pattern="yyyy-MM-dd HH:mm" /></span>
		</div>
	</div>
</form>
<button type="button" class="btn btn-info public_btn_right"
	data-toggle="modal" data-target="#dxModal"
	style="margin-right: 30px; margin-bottom: 20px">批量发放</button>
<div class="marage_right_content">
	<table id="dateTable" class="data_table text-center"
		style="width: 930px">
		<thead>
			<tr>
				<th width="40" style="padding-right: 10px"><jphs:hasPermission
						url="/voucher/addUser.jhtml">
						<a onclick="showDiv('${voucher.id}',${voucher.amount })"> <input
							type="button" class="public-info public_btn public_btn_left"
							value="操作">
						</a>
					</jphs:hasPermission></th>
				<th>优惠券编号</th>
				<th>兑换码</th>
				<th>领取时间</th>
				<th>领取人</th>
				<th>发放人</th>
				<th>状态</th>
				<!-- <td width="60px">操作</td> -->
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr>
							<td><input type="checkbox" value="${e.repertoryId }"
								name="repertoryId" id="repertoryId"
								<c:if test="${e.status ==1}">disabled="disabled"</c:if> /></td>
							<td>${e.no }</td>
							<td>${e.code }</td>
							<td><fmt:formatDate value="${e.createTime}"
									pattern="yy-MM-dd HH:mm" /></td>
							<td>${e.creatorName}</td>
							<td><c:if test="${e.grantName !=null}">${e.grantName}</c:if>
								<c:if test="${e.status ==1}">
									<c:if test="${e.grantName ==null}">本人领取</c:if>
								</c:if></td>
							<td><c:if test="${e.status ==1}">已领取</c:if> <span
								style="color: #34BC2C;"><c:if test="${e.status ==0}">未领取</c:if></span>
								<span style="color: red;"><c:if test="${e.status ==-1}">已停用</c:if></span></td>
							<%-- <td><c:if test="${e.status ==0}">
									</c:if></td> --%>
						<tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
	<div class="page">
		<jphs:page pageInfos="${pageInfo}"></jphs:page>
	</div>
</div>




<!-- 模态框（Modal） -->
<div class="modal fade" id="dxModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="border: 0">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<p class="text-center" style="font-size: 15px; margin-bottom: 10px">
				请输入手机号<br />（多个手机号用英文逗号隔开）
			</p>
			<div class="form-group text-center">
				<textarea rows="" cols="" name="phnoes" id="phones"
					style="width: 200px;">
					 </textarea>
			</div>
			<div class="clearfix"></div>

			<div class="clearfix"></div>
			<div class="modal-footer" style="border: 0; text-align: center">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button> -->
				<button class="btn btn-primary" onclick="sendVoucher();">发放</button>
			</div>
		</div>
	</div>
</div>
