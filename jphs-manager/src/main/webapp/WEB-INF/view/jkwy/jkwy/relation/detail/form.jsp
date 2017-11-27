<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="jkwyRelationTable" class="tableStyle">
		<tr>
			<td align="right"width="100">姓名：</td>
			<td>
				<c:out value="${jkwyRelation.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">电话：</td>
			<td>
				<c:out value="${jkwyRelation.phone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">性别：</td>
			<td>
				<c:out value="${jkwyRelation.sex}"/>
			</td>
		</tr>
		<tr>
			<td align="right">关系：</td>
			<td>
				<c:out value="${jkwyRelation.relation}"/>
			</td>
		</tr>
		<tr>
			<td align="right">身份证：</td>
			<td>
				<c:out value="${jkwyRelation.sfz}"/>
			</td>
		</tr>
		<tr>
			<td align="right">出生日期：</td>
			<td>
				<fmt:formatDate value="${jkwyRelation.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>
</div>