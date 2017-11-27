<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${smsTemplate.id}" />
				<div class="form-group">

			<!-- 下拉列表 -->
			<label class="control-label col-md-3"><a style="color: red">*&nbsp;</a>模板类型：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${smsTemplate.type == 1}">
						<input type="radio" value="1" name="type" checked="checked">
					</c:if>
					<c:if test="${smsTemplate.type != 1}">
						<input type="radio" value="1" name="type" checked="checked">
					</c:if> 标准短信
				</label> <label class="radio radio-inline">
					<c:if test="${smsTemplate.type == 2}">
						<input type="radio" value="2" name="type" checked="checked">
					</c:if>
					<c:if test="${smsTemplate.type != 2}">
						<input type="radio" value="2" name="type">
					</c:if>推广短信
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01"><a style="color: red">*&nbsp;</a>模板ID：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="请输入模板ID" name="smsId" class="form-control"  style="float:left" value="${smsTemplate.smsId}" maxlength="15" />
			<span style="float:right">没有模板？
			<a href="https://dayu.aliyun.com/center/sign/list/sms?spm=a3142.8062824.2.d31.2e53173cKtZmxc" target="_black">
			<span style="color: #0099FF">马上去添加</span>
			</a>
			</span>
			<span  style="float:right">
			・短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
			</span>
			</div>
			
		</div>
		<div class="form-group">


			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">模板名称：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="请输入模板名称  不超过20个字" name="title" class="form-control"  value="${smsTemplate.title}"  maxlength="20"/>
			</div>
		</div>
		<div class="form-group">
          <label class="control-label col-md-3">备案短信内容：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea name="content" placeholder="请输入备案短信内容  最多不超过250个字" maxlength="250" class="form-control" style="height: 150px;width: 500px;" >${smsTemplate.content }</textarea>
           			
            </div>
          </div>
		</div>
		
	</fieldset>
</form>