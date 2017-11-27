<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="creatorId" name="creatorId" value="${familyOrder.creatorId}" />
		<input type="hidden" value="1" name="status"/>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="familyMemberId" name="familyMemberId" >
					<c:forEach items="${familyMemberList }" var="familyMemberOne" varStatus="status">
						<option value="${familyMemberOne.id }" >${familyMemberOne.relation }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">健康计划标题：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" placeholder="请输入请输入名称"
					value="${familyHealthy.title}" maxlength="49" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">总周期：</label>
			<div class="controls col-md-6">
				<input type="text" id="cycle" name="cycle" placeholder="请输入总周期"
					value="${familyHealthy.cycle}" maxlength="49" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">相关病史：</label>
			<div class="controls col-md-6">
				<input type="text" id="history" name="history" placeholder="请输入相关病史"
					value="${familyHealthy.history}" maxlength="49" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">病史内容：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="historyContent" name="historyContent" placeholder="请输入病史内容，编辑器"
						class="form-control">${familyPackage.historyContent}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">病史周期：</label>
			<div class="controls col-md-6">
				<input type="text" id="historyCycle" name="historyCycle" placeholder="请输入病史周期"
					value="${familyHealthy.historyCycle}" maxlength="49" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">治疗计划：</label>
			<div class="controls col-md-6">
				<input type="text" id="plan" name="plan" placeholder="请输入治疗计划"
					value="${familyHealthy.historyCycle}" maxlength="49" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">计划内容：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="planContent" name="planContent" placeholder="请输入计划内容"
						class="form-control">${familyPackage.planContent}</textarea>
				</div>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">计划周期：</label>
			<div class="controls col-md-6">
				<input type="text" id="planCycle" name="planCycle" placeholder="请输入计划周期"
					value="${familyPackage.planCycle}" maxlength="49" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">饮食安排：</label>
			<div class="controls col-md-6">
				<input type="text" id="diet" name="diet" placeholder="请输入饮食安排"
					value="${familyPackage.diet}" maxlength="49" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">饮食内容：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="dietContent" name="dietContent" placeholder="请输入饮食内容"
						class="form-control">${familyPackage.dietContent}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">饮食周期：</label>
			<div class="controls col-md-6">
				<input type="text" id="dietCycle" name="dietCycle" placeholder="请输入饮食周期"
					value="${familyPackage.dietCycle}" maxlength="49" class="form-control">
			</div>
		</div>
		
	</fieldset>
</form>