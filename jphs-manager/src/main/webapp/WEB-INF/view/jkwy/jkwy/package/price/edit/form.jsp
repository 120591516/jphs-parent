<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${jkwyPackagePrice.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${jkwyPackagePrice.id == null?'添加':'编辑'}</div>
		<table id="jkwyPackagePriceTable" class="tableStyle">
			<tr>
				<td align="right"width="100">：</td>
				<td>
					<input value="${jkwyPackagePrice.jkwyPackageId}" 
						id="jkwyPackageId"
						name="jkwyPackageId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">价格标题：</td>
				<td>
					<input value="${jkwyPackagePrice.title}" 
						id="title"
						name="title"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">价原：</td>
				<td>
					<input value="${jkwyPackagePrice.oldPrice}" 
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
					<input value="${jkwyPackagePrice.price}" 
						id="price"
						name="price"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">服务时长：</td>
				<td>
					<input value="${jkwyPackagePrice.serviceTime}" 
						id="serviceTime"
						name="serviceTime"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">单位：</td>
				<td>
					<input value="${jkwyPackagePrice.unit}" 
						id="unit"
						name="unit"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支持套餐人数：</td>
				<td>
					<input value="${jkwyPackagePrice.supportNumber}" 
						id="supportNumber"
						name="supportNumber"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支持续费（0不支持，1支持）：</td>
				<td>
					<input value="${jkwyPackagePrice.supportFee}" 
						id="supportFee"
						name="supportFee"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支持变更升级套餐（0不支持，1支持）：</td>
				<td>
					<input value="${jkwyPackagePrice.supportChange}" 
						id="supportChange"
						name="supportChange"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>