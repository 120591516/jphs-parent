<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
 
 	<input type="hidden" name="id" value="${commodity.id }">
 	
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">所属商家：</label>
			<div class="controls col-md-6">
				<select class="marage_select" id="businessId" name="businessId">
					<c:forEach items="${businessList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${commodity.businessId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">一级分类：
				<input id="ctpId" value="${ctpId }" type="hidden">
				<input id="cTypeId" value="${ctId }" type="hidden">
			</label>
			<div class="controls col-md-6">
				<td width="200">
				
				<select class="form-control input-xlarge" id="ctpId"
					name="ctpId" value="" onchange="change(this.options[this.options.selectedIndex].value)">
					<option >请选择</option>
				
					<c:forEach items="${ctpList}" var="e" varStatus="s">
						<option value="${e.id }"
						<c:if test="${ctpId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</td>
			</div>
		</div>
	<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">二级分类：
				<input id="ctpId" value="${ctpId }" type="hidden">
				<input id="cTypeId" value="${ctId }" type="hidden">
			</label>
			<div class="controls col-md-6">
			<td>	
			<select class="form-control input-xlarge" id="ctId"
					name="commodityTypeId" >
					<option value="" >请选择</option>
					<c:forEach items="${goodsList}" var="e" varStatus="s">
						<option value="${e.id }"
						<c:if test="${ctId==e.id}">selected="selected"</c:if>>${e.title }</option>
					</c:forEach>
				</select>
			</td>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">标题：</label>
			<div class="controls col-md-6">
				<input value="${commodity.title}" id="title" name="title"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">副标题，简介：</label>
			<div class="controls col-md-6">
				<input value="${commodity.subTitle}" id="subTitle" name="subTitle"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[500]]"></input>
			</div>
		</div>
＜
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">型号/规格：</label>
			<div class="controls col-md-6">
				<input value="${commodity.model}" id="model" name="model"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>


		<%-- <div class="form-group">
			 
			<label class="control-label col-md-4" for="input01">内容：</label>
			<div class="controls col-md-6">
				<textArea id="content" name="content" rows="12"
					data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(commodity.content)}</textArea>
			</div>
		</div> --%>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">商品参数：</label>
			<div class="controls col-md-6">
				
					<input type="checkbox" name="quality" <c:if test="${commodity.quality==1}">checked="checked"</c:if>>正品认证
					<input type="checkbox" name="privacy"  <c:if test="${commodity.privacy==1}">checked="checked"</c:if> >隐私配送
					<input type="checkbox" name="security" <c:if test="${commodity.security==1}">checked="checked"</c:if> >药检认证
			</div>
		</div>
<%-- 
		<div class="form-group">
			 
			<label class="control-label col-md-4" for="input01">参数：</label>
			<div class="controls col-md-6">
				<textArea id="parameter" name="parameter" rows="12"
					data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(commodity.parameter)}</textArea>
			</div>
		</div> --%>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">是否支持使用优惠券：</label>
			<div class="controls col-md-6">
				<%-- <input value="${commodity.supportVoucher}" id="supportVoucher"
					name="supportVoucher" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input> --%>
					<input type="radio" name="supportVoucher" value="1" <c:if test="${commodity.supportVoucher==1}">checked="checked"</c:if>>支持
					<input type="radio" name="supportVoucher" value="0" <c:if test="${commodity.supportVoucher==0}">checked="checked"</c:if>>不支持
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">限购数量：</label>
			<div class="controls col-md-6">
				<input value="${commodity.limitNumber}" id="limitNumber"
					name="limitNumber" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">保护期(天）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.protectDay}" id="protectDay"
					name="protectDay" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4">商品PC端图片：</label>
			<!-- 文件上传 -->
		 	<div class="controls col-md-6">
		 	
		 	
				<img alt="" height="200" width="200" id="urls"
					src="${pc_image.url}" />
					<span>尺寸 360*350 <br>*&nbsp;图片格式必须为.png、.jpg格式
				</span> <input class="input-file" type="file" name="myfiles" id="pcurl_s"
					onchange="ajaxFileUpload('pcurl_s','url');" /> <input
					class="input-file" type="hidden" id="url" name="url" value="${url}" />
			</div>  
		</div>

		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">备注：</label>
			<div class="controls col-md-6">
				<input value="${commodity.remark}" id="remark" name="remark"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[500]]"></input>
			</div>
		</div>
		<div class="public_editor">
			<h5 style="font-size:14px;padding-left:140px">商品详情：</h5>
			<p>
				<textarea name="content" id="myEditor" class="form-control"
					style="width: 805px; height: 500px;">${ci2.url }</textarea>
			</p>
		</div>
		
		
		
		<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
			<div class="price_gradeAdd">
				<div class="marage_right_content"  style="margin-top:0;padding-top:0">
					<table id="dateTable"   class="data_table" style="width:780px;margin:0 auto;margin-top:20px;margin-right:105px">
						<tbody id="addtrprice0">
							<tr class="headClass">
								<td width="100">序号</td>
								<td width="60">商品SKU</td>
								<!-- <td width="60px" >销售价格</td> -->
								<td width="60" >成本价</td>
								<td width="60" >原价</td>
								<td width="60" >销售价</td>
								<td width="60" >分销收益</td>
								<td width="60" >库存</td>
								<td width="60" >状态</td>
								<td width="60" >操作</td>
							</tr>
							<input type="hidden" value="${fn:length(cpList)}" id="cpList"/>
							<c:choose>
							
							
			<c:when test="${fn:length(cpList) >0}">
				<c:forEach items="${cpList}" var="e" varStatus="s">
				
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td>
							<input type="hidden"   name="cpList[${s.index }].id" value="${e.id }" />
							
							<input class="form-control" style="width:100%"  name="cpList[${s.index }].name" value="${e.name }" />
							</td>
						<td>
							<input class="form-control" style="width:100%"  name="cpList[${s.index }].costPrice" value="${e.costPrice }" />
						</td>
						<td> <input class="form-control" style="width:100%"  name="cpList[${s.index }].oldPrice" value="${e.oldPrice }" /></td>
						<td >
							<input class="form-control" style="width:100%"  name="cpList[${s.index }].price" value="${e.price }" />
						</td>
						<td>
							 
						<input class="form-control" style="width:100%"  name="cpList[${s.index }].profit" value="${e.profit }" />
							 
						</td>
						<td><input class="form-control" style="width:100%"  name="cpList[${s.index }].number" value="${e.number }" /></td>
						<td>
							<c:if test="${e.status ==1 }">启用</c:if>
							<c:if test="${e.status ==0 }">停用</c:if>
							<c:if test="${e.status ==-1 }">停用</c:if>
						</td>
						<td>
							<c:if test="${e.status ==1 }"><a onclick=updateStatus('${e.id }','0')>停用</a></c:if>
							<c:if test="${e.status ==-1 }"><a onclick=updateStatus('${e.id }','1')>启用</a></c:if>
							<c:if test="${e.status ==0 }"><a onclick=updateStatus('${e.id }','1')>启用</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
						</tbody>
					</table>
					<div class="btn_add" onclick="addTd(0);" style="width:780px;margin-right:105px">+</div>
					</div>
				</div>
			</div>
	</fieldset>
