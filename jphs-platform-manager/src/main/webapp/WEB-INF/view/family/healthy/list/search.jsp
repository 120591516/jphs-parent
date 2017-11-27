<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/family/healthy/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>：</td>
			<td width="200">
				<input type="text" name="familyMemberId" id="familyMemberId" value="${familyHealthy.familyMemberId}" />
			</td>
			<td>健康计划标题：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${familyHealthy.title}" />
			</td>
			<td>总周期：</td>
			<td width="200">
				<input type="text" name="cycle" id="cycle" value="${familyHealthy.cycle}" />
			</td>
			<td>相关病史：</td>
			<td width="200">
				<input type="text" name="history" id="history" value="${familyHealthy.history}" />
			</td>
			<td>病史内容，编辑器：</td>
			<td width="200">
				<input type="text" name="historyContent" id="historyContent" value="${familyHealthy.historyContent}" />
			</td>
			<td>病史周期：</td>
			<td width="200">
				<input type="text" name="historyCycle" id="historyCycle" value="${familyHealthy.historyCycle}" />
			</td>
			<td>治疗计划：</td>
			<td width="200">
				<input type="text" name="plan" id="plan" value="${familyHealthy.plan}" />
			</td>
			<td>计划内容，编辑器：</td>
			<td width="200">
				<input type="text" name="planContent" id="planContent" value="${familyHealthy.planContent}" />
			</td>
			<td>计划周期：</td>
			<td width="200">
				<input type="text" name="planCycle" id="planCycle" value="${familyHealthy.planCycle}" />
			</td>
			<td>饮食安排：</td>
			<td width="200">
				<input type="text" name="diet" id="diet" value="${familyHealthy.diet}" />
			</td>
			<td>饮食内容，编辑器：</td>
			<td width="200">
				<input type="text" name="dietContent" id="dietContent" value="${familyHealthy.dietContent}" />
			</td>
			<td>饮食周期：</td>
			<td width="200">
				<input type="text" name="dietCycle" id="dietCycle" value="${familyHealthy.dietCycle}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

