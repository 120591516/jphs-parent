<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>


<div>
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">商家名称：</label>
			<div class="controls col-md-6">
				${business.name}
			</div>
		</div>
		
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">官网地址：</label>
			<div class="controls col-md-6">
				${business.officialWebsiteUrl}
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">商家地址：</label>
			<div class="controls col-md-6">
				${business.address}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">联系人：</label>
			<div class="controls col-md-6">
				${business.contactsName}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">联系电话：</label>
			<div class="controls col-md-6">
				${business.contactsPhone}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">结算周期：</label>
			<div class="controls col-md-6">
				${business.settlementCycle}${business.unit }
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">结算方式：</label>
			<div class="controls col-md-6">
				${business.payType}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">银行：</label>
			<div class="controls col-md-6">
				${business.bank}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">开户行：</label>
			<div class="controls col-md-6">
				${business.openBankAddress}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">卡号：</label>
			<div class="controls col-md-6">
				${business.cardNumber}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">开卡人：</label>
			<div class="controls col-md-6">
				${business.cardName}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">备注：</label>
			<div class="controls col-md-6">
				${business.remark}
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">创建人：</label>
			<div class="controls col-md-6">
				${business.creatorName}
			</div>
		</div>
	</fieldset>
	
</div>

