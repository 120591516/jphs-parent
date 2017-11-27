<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="familyMemberTable" class="tableStyle">
		<tr>
			<td align="right"width="100">姓名：</td>
			<td>
				<c:out value="${familyMember.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">手机号：</td>
			<td>
				<c:out value="${familyMember.phone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">身份证：</td>
			<td>
				<c:out value="${familyMember.sfz}"/>
			</td>
		</tr>
		<tr>
			<td align="right">关系：</td>
			<td>
				<c:out value="${familyMember.relation}"/>
			</td>
		</tr>
	</table>
</div>