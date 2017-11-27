<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/family/consultation/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>问题：</td>
			<td width="200">
				<input type="text" name="quiz" id="quiz" value="${familyConsultation.quiz}" />
			</td>
			<td>答案：</td>
			<td width="200">
				<input type="text" name="answer" id="answer" value="${familyConsultation.answer}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

