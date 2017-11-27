<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${smsHistory.id}" />
				<div class="form-group">

			<!-- 下拉列表 -->
			<label class="control-label col-md-3"><a style="color: red">*&nbsp;</a>模板类型：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${smsHistory.smsTemplate.type == 1}">
						<input type="radio" value="1" id="type" name="type" checked="checked" onclick="showCom()">
					</c:if>
					<c:if test="${smsHistory.smsTemplate.type != 1}">
						<input type="radio" value="1" id="type" name="type" checked="checked" onclick="showCom()">
					</c:if> 标准短信
				</label> <label class="radio radio-inline">
					<c:if test="${smsHistory.smsTemplate.type == 2}">
						<input type="radio" value="2" id="type" name="type" checked="checked" onclick="showCom2()">
					</c:if>
					<c:if test="${smsHistory.smsTemplate.type != 2}">
						<input type="radio" value="2" id="type" name="type" onclick="showCom2()">
					</c:if>推广短信
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01"><a style="color: red">*&nbsp;</a>模板ID：</label>
			<div class="controls col-md-6" id="d1">
				<select class="form-control input-xlarge" id="smsTemplateId" name="smsTemplateId" onchange="sl()" style="float: left">
				<option value="">请选择（标准短信模板）</option>
					<c:forEach var="productone" items="${list }" varStatus="status">
					<option value="${productone.id }" title="${productone.title }" id="${productone.content }">${productone.smsId }</option>
					</c:forEach>
				</select>
			<span style="float:left;color: #0099FF ">
			<a href="/sms/template/redirectAddPage.jhtml" target="_black">&nbsp;马上去添加
			</a>
			</span>
			</div>
			<div class="controls col-md-6"  id="d2" style="display: none;">
				<select class="form-control input-xlarge" id="smsTemplateId2" name="smsTemplateId" onchange="sl2()">
				<option value="">请选择（推广短信模板）</option>
					<c:forEach var="productone2" items="${list2 }" varStatus="status">
					<option value="${productone2.id }" title="${productone2.title }" id="${productone2.content }">${productone2.smsId }</option>
					</c:forEach>
				</select>
			</div>
		
		</div>
		
		<div class="form-group">


			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">模板名称：</label>
			<div class="controls col-md-6">
				<input type="text" readonly="readonly" placeholder="" id="m_title" name="" class="form-control"  value=""  maxlength="20"/>
			</div>
		</div>
		<div class="form-group">
          <label class="control-label col-md-3">短信内容：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea id="m_content" name="" readonly="readonly" placeholder=" " class="form-control" style="height: 150px;width: 500px;" >${smsHistory.phone }
                  </textarea>
            </div>
          </div>
		</div>
		 
				<div class="form-group">
          <label class="control-label col-md-3"><a style="color: red">*&nbsp;</a>接收者手机号：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea id="phone" name="phone" placeholder="请输入接收者手机号  最多不超过250个字" maxlength="60000" class="form-control" style="height: 150px;width: 500px;" >${smsHistory.phone }</textarea>
           			 <span><img alt="" src="https://jinpai.b0.upaiyun.com/upload/JP20171125190445-54247.png"></span><br>
           			 ・请上传txt、xlsx格式文件，<span style="color: #0099FF"><a href="http://download.taobaocdn.com/freedom/26568/compress/example.zip?spm=a3142.10692679.3.7.19e53a63HC1fVk&file=example.zip">下载范例</a></span><br>
           			・ 支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86<br>
           			・群发短信需传入多个号码，以英文逗号分隔<br>
           			・【标准短信】一次调用最多传入200个号码。【推广短信】5000个号码<br>
           			・批量发送，会产生相应的延迟，触达时间要求高的建议单条发送
            </div>
          </div>
          
		</div>
		
	</fieldset>
</form>