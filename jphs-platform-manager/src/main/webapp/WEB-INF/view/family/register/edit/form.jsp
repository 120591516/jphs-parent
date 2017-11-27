<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" value="${familyOrder.creatorId}" name="creatorId"/>
		<input type="hidden" value="1" name="status"/>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">问诊类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="type" name="type" >
						<option value="1" selected="selected">普通门诊</option>
						<option value="2" >专家门诊</option>
						<option value="3" >加急问诊</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">医院：</label>
			<div class="controls col-md-6">
				<input type="text" id="hospital" name="hospital" placeholder="请输入医院"
					value="" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">科室：</label>
			<div class="controls col-md-6">
				<input type="text" id="department" name="department" placeholder="请输入科室"
					value="" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">行程名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" placeholder="请输入行程名称"
					value="" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">拨打电话：</label>
			<div class="controls col-md-6">
				<input type="text" id="phone" name="phone" placeholder="请输入拨打电话"
					value="" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">开始时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="appointmentTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="" size="14"
					placeholder="请选择开始时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input
					type="hidden" name="appointmentTime" id="appointmentTime" value="" />
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">星期：</label>
			<div class="controls col-md-6">
				<input type="text" id="week" name="week" placeholder="请输入星期"
					value="" class="form-control">
			</div>
		</div>

	</fieldset>
</form>