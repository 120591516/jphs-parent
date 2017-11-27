<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${jkwyOrder.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${jkwyOrder.id == null?'添加':'编辑'}</div>
		<table id="jkwyOrderTable" class="tableStyle">
			<tr>
				<td align="right"width="100">单号：</td>
				<td>
					<input value="${jkwyOrder.no}" 
						id="no"
						name="no"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${jkwyOrder.jkwyPackageId}" 
						id="jkwyPackageId"
						name="jkwyPackageId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${jkwyOrder.jkwyPackagePriceId}" 
						id="jkwyPackagePriceId"
						name="jkwyPackagePriceId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${jkwyOrder.voucherUserId}" 
						id="voucherUserId"
						name="voucherUserId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">优惠金额：</td>
				<td>
					<input value="${jkwyOrder.voucherPrice}" 
						id="voucherPrice"
						name="voucherPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">活动id：</td>
				<td>
					<input value="${jkwyOrder.activityPromotionId}" 
						id="activityPromotionId"
						name="activityPromotionId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">活动优惠价格：</td>
				<td>
					<input value="${jkwyOrder.activityPromotionPrice}" 
						id="activityPromotionPrice"
						name="activityPromotionPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">推广码：</td>
				<td>
					<input value="${jkwyOrder.code}" 
						id="code"
						name="code"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">原价：</td>
				<td>
					<input value="${jkwyOrder.oldPrice}" 
						id="oldPrice"
						name="oldPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">销售价：</td>
				<td>
					<input value="${jkwyOrder.price}" 
						id="price"
						name="price"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支付价：</td>
				<td>
					<input value="${jkwyOrder.payPrice}" 
						id="payPrice"
						name="payPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支付时间：</td>
				<td>
					<input value="<fmt:formatDate value="${jkwyOrder.payTime}" type="both" pattern="yyyy-MM-dd" />"
						id="payTime"
						name="payTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">套餐结束日期：</td>
				<td>
					<input value="<fmt:formatDate value="${jkwyOrder.endTime}" type="both" pattern="yyyy-MM-dd" />"
						id="endTime"
						name="endTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">上门地址：</td>
				<td>
					<input value="${jkwyOrder.address}" 
						id="address"
						name="address"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址：</td>
				<td>
					<input value="${jkwyOrder.detailAddress}" 
						id="detailAddress"
						name="detailAddress"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>