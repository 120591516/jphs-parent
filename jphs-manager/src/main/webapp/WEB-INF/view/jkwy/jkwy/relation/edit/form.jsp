<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${jkwyRelation.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${jkwyRelation.id == null?'添加':'编辑'}</div>
		<table id="jkwyRelationTable" class="tableStyle">
			<tr>
				<td align="right"width="100">姓名：</td>
				<td>
					<input value="${jkwyRelation.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">电话：</td>
				<td>
					<input value="${jkwyRelation.phone}" 
						id="phone"
						name="phone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">性别：</td>
				<td>
					<input value="${jkwyRelation.sex}" 
						id="sex"
						name="sex"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">关系：</td>
				<td>
					<input value="${jkwyRelation.relation}" 
						id="relation"
						name="relation"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">身份证：</td>
				<td>
					<input value="${jkwyRelation.sfz}" 
						id="sfz"
						name="sfz"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">出生日期：</td>
				<td>
					<input value="<fmt:formatDate value="${jkwyRelation.birthday}" type="both" pattern="yyyy-MM-dd" />"
						id="birthday"
						name="birthday"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
		</table>
	</div>
</div>