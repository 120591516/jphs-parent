<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${jkwyPackage.id}" /> <input
			type="hidden" id="moveid" name="moveid" value="${wap_image.id}" /> <input
			type="hidden" id="pcid" name="pcid" value="${pc_image.id}" /> <input
			type="hidden"
			value="<c:if test="${jkwyPackage.status !=null }">${jkwyPackage.status}</c:if><c:if test="${jkwyPackage.status ==null }">0</c:if>"
			name="status" />

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" maxlength="20" value="${jkwyPackage.title}" placeholder="套餐名称"
					class="form-control" required="required" />
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">套餐简介：</label>
			<div class="controls col-md-6">
				<textarea id="subTitle" name="subTitle" placeholder="套餐简介(限110字内)"
					cols="" rows="6" class="form-control" maxlength="110"
					onkeydown='countChar("subTitle","counter");'
					onkeyup='countChar("subTitle","counter");' required="required">${jkwyPackage.subTitle}</textarea>
				可以输入<span id="counter">110</span>字<br />
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">排序：</label>
			<div class="controls col-md-6">

				<input type="text" id="sort" name="sort" placeholder="排序"
					class="form-control"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					value="${jkwyPackage.sort}" required="required" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">套餐手机端图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="moveurls"
					src="${wap_image.url}" />
				<div>
					尺寸 140*140 比例（1：1）<br>*&nbsp;图片格式必须为.png格式
				</div>
				<input class="input-file" type="file" name="myfiles" id="moveurl_s"
					onchange="ajaxFileUpload('moveurl_s','moveurl');" /> <input
					class="input-file" type="hidden" id="moveurl" name="moveurl"
					value="${wap_image.url}" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">套餐PC端图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="pcurls"
					src="${pc_image.url}" />
				<div>
					尺寸 360*350 <br>*&nbsp;图片格式必须为.png格式
				</div>
				<input class="input-file" type="file" name="myfiles" id="pcurl_s"
					onchange="ajaxFileUpload('pcurl_s','pcurl');" /> <input
					class="input-file" type="hidden" id="pcurl" name="pcurl"
					value="${pc_image.url}" />
			</div>
		</div>
		<div class="public_editor">
			<h5 style="font-size: 14px; padding-left: 58px">套餐详情：</h5>
			<p>
				<textarea name="content" id="myEditor" class="form-control"
					style="width: 805px; height: 500px;">${jkwyPackage.content}</textarea>
			</p>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3" for="input01">平台列表：</label> <input
				type="hidden" id="priceIds" value="${activityPromotion.priceId }" />
			<div class="controls col-md-6" style="margin-left: 20px;">
				<c:forEach items="${platform }" var="e" varStatus="s">
					<label class="checkbox inline"> <input type="checkbox"
						id="platformId" name="platformId"
						<c:if test="${e.checked }">checked = "checked"</c:if>
						required="required" value="${e.id }">${e.name }</label>
				</c:forEach>
			</div>
		</div>
		<div class="norm_service">
			<div id="dateTableList" class="marage_right_content"
				style="margin-top: 0; padding-top: 0">
				<c:if test="${fn:length(jkwyPackage.packagePrice) >0 }">
					<c:forEach var="e" items="${jkwyPackage.packagePrice }"
						varStatus="s">
						<div id="price${s.index }" class="price">
							<table id="dateTable" cellpadding="0" cellspacing="0"
								class="data_table  text-center"
								style="width: 900px; margin: 0 auto">
								<thead id="addtrprice">
									<tr class="headClass">
										<td width="50">规格名称</td>
										<td width="40">原价</td>
										<td width="40">销售价</td>
										<td width="60">服务时长</td>
										<td width="60">单位</td>
										<td width="50">支持套餐人数</td>
										<td width="50">支持续费</td>
										<td width="50">支持变更升级套餐</td>
										<td style="width: 40px;">操作</td>
									</tr>
									<tr class="addClassprice" id="delete${s.index }">
										<input type="hidden" name="packagePrice[${s.index }].id"
											value="${e.id }" />
										<input type="hidden" id="status${s.index }"
											name="packagePrice[${s.index }].status" value="${e.status }" />
										<input type="hidden" id="jkwyPackageId${s.index }"
											name="packagePrice[${s.index }].jkwyPackageId"
											value="${e.jkwyPackageId }" />
										<td><input class="form-control" id="title${s.index }"
											value="${e.title }" name="packagePrice[${s.index }].title" /></td>
										<td><input style="width: 100%" class="form-control"
											id="service_number${e.id}${s.index }" value="${e.oldPrice }"
											name="packagePrice[${s.index }].oldPrice" /></td>
										<td><input style="width: 100%" class="form-control"
											id="price${e.id}${s.index }"
											name="packagePrice[${s.index }].price" value="${e.price }" /></td>
										<td><input type="number" max="365" min="1"
											value="${e.serviceTime }" style="width: 100%;" max="100"
											min="1" class="form-control input-xlarge"
											id="service_time${e.id}${s.index }"
											name="packagePrice[${s.index }].serviceTime" /></td>
										<td><select style="width: 100%;"
											class="form-control input-xlarge" id="unit${e.id}${s.index }"
											name="packagePrice[${s.index }].unit">
												<option
													<c:if test="${e.unit=='天' }">selected="selected"</c:if>>天</option>
										</select></td>
										<td><input type="number" style="width: 100%;"
											value="${e.supportNumber }" max="100" min="1"
											class="form-control input-xlarge"
											id="supportNumber${e.id}${s.index }"
											name="packagePrice[${s.index }].supportNumber" /></td>
										<td><select style="width: 100%;"
											class="form-control input-xlarge" id="unit${e.id}${s.index }"
											name="packagePrice[${s.index }].supportFee">
												<option value="0"
													<c:if test="${e.supportFee=='0' }">selected="selected"</c:if>>否</option>
												<option value="1"
													<c:if test="${e.supportFee=='1' }">selected="selected"</c:if>>是</option>
										</select></td>
										<td><select style="width: 100%;"
											class="form-control input-xlarge" id="unit${e.id}${s.index }"
											name="packagePrice[${s.index }].supportChange">
												<option value="0"
													<c:if test="${e.supportChange=='0' }">selected="selected"</c:if>>否</option>
												<option value="1"
													<c:if test="${e.supportChange=='1' }">selected="selected"</c:if>>是</option>
										</select></td>
										<td><img style="width: 20px; height: 20px;"
											src="/static/images/shanchu.png" id="delete${e.id}"
											onclick="deleteTable(${s.index });"></td>
											
									</tr>
								</thead>
							</table>
							<table class="data_table  text-center"
								style="width: 900px; margin: 0 auto">
								<thead>
								<tr>
									<td>服务模块</td>
									<td>服务内容</td>
									<td>服务说明</td>
									<td>总服务次数</td>
									<td>剩余服务次数</td>
									<td style="width: 40px;">操作</td>
								</tr>
								</thead>
								<tbody id="addPrice${s.index }Content">
								 <c:forEach var="f" items="${e.jkwyPackageContentList }"
										varStatus="t">
									<tr class="packagePrice${s.index }addPriceContent" id="packagePrice${s.index }addPriceContent${t.index}">
									<td>
										<input  type="hidden" value="${f.id }" name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].id" />
										<input  type="hidden" class="status${s.index }" value="${f.status }" name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].status" />
										<input
										name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].title"
										value="${f.title }" required="required" /></td>
									<td><input
										name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].subTitle"
										value="${f.subTitle }" /></td>
									<td><input
										name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].content"
										value="${f.content }" /></td>
									<td><input type="number" style="width: 60px;" min="1"
										name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].number"
										value="${f.number }" /></td>
									<td><input type="number" id="surplusNumber" min="1"
										name="packagePrice[${s.index }].jkwyPackageContentList[${t.index }].subTitle"
										value="${f.surplusNumber }" readonly="readonly" required="required" /></td>
									<td>
										<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+length+'" onclick="deleteTr('${s.index }','${t.index }');">
									</td>
								</tr>
								</c:forEach> 
								</tbody>
							</table>
							<div class="btn_add" onclick="addTr(${s.index });" style="width: 800px">+</div>
						</div>
					</c:forEach>
				</c:if>


			</div>
		</div>
		<div class="btn_add" onclick="addTable();" style="width: 100px"
			title="添加服务规格">+</div>
	</fieldset>
</form>
