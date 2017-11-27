<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${familyVoucher.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${familyVoucher.id == null?'添加':'编辑'}</div>
		<table id="familyVoucherTable" class="tableStyle">
			<tr>
				<td align="right"width="100">：</td>
				<td>
					<input value="${familyVoucher.voucherId}" 
						id="voucherId"
						name="voucherId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注说明：</td>
				<td>
					<input value="${familyVoucher.remark}" 
						id="remark"
						name="remark"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>