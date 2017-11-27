<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${familyMember.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${familyMember.id == null?'添加':'编辑'}</div>
		<table id="familyMemberTable" class="tableStyle">
			<tr>
				<td align="right"width="100">姓名：</td>
				<td>
					<input value="${familyMember.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">手机号：</td>
				<td>
					<input value="${familyMember.phone}" 
						id="phone"
						name="phone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">身份证：</td>
				<td>
					<input value="${familyMember.sfz}" 
						id="sfz"
						name="sfz"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">关系：</td>
				<td>
					<input value="${familyMember.relation}" 
						id="relation"
						name="relation"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>