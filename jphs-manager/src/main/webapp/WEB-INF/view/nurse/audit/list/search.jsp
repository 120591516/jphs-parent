﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/audit/index.jhtml" id="serach-form"
	style="margin-top: -31px;">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>姓名：</td>
				<td width="200"><input type="text" name="name"
					id="name" value="${nurseJobtitle.name}" placeholder="请输入搜索联系人姓名" /></td>
				<td>身份证号：</td>
				<td width="200"><input type="text" name="sfz" id="sfz"
					value="${nurseJobtitle.sfz}" placeholder="请输入搜索身份证号" /></td>
				<td>手机号：</td>
				<td width="200"><input type="text" name="phone" id="phone"
					value="${nurseJobtitle.phone}" placeholder="请输入搜索手机号" /></td>
			</tr>
			<tr>
				<td>医院：</td>
				<td width="200"><input type="text" name="hospital"
					id="hospital" value="${nurseJobtitle.hospital}" placeholder="请输入搜索医院" /></td>
				<td>推荐人：</td>
				<td width="200"><input type="text" name="user.recommendId" id="user.recommendId"
					value="${nurseJobtitle.user.recommendId}" placeholder="请输入搜索推荐人ID" /></td>
				<td>CODE：</td>
				<td width="200"><input type="text" name="user.code"
					id="user.code" value="${nurseJobtitle.user.code}"
					placeholder="请输入搜索CODE" /></td>
			</tr>
			<tr>
				<td>护士ID：</td>
				<td width="200"><input type="text" name="user.id"
					id="user.id" value="${nurseJobtitle.user.id}"
					placeholder="请输入搜索CODE" /></td>
				<td>所属科室：</td>
				<td>
					<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="departmentId"
							name="departmentId">
							<option value="">全部</option>
							<c:forEach items="${department}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${nurseJobtitle.departmentId==e.id}">selected="selected"</c:if>>${e.name }</option>
							</c:forEach>
						</select>
					</div>
				</td>
				<td>状态：</td>
				<td>
					<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="status"
							name="status">
							<option value="">全部</option>
							<option value="1" <c:if test="${nurseJobtitle.status==1}">selected="selected"</c:if>>已审核</option>
							<option value="0" <c:if test="${nurseJobtitle.status==0}">selected="selected"</c:if>>待审核</option>
						</select>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>渠道：</td>
				<td width="200">
				<div class="controls col-md-6"
						style="width: 80%; margin-left: -15px;">
					<select class="marage_select" id="user.device" name="user.device">
						<option value="">全部</option>
						<option value="1" <c:if test="${nurseJobtitle.user.device==1 }">selected="selected"</c:if> >ios </option>
						<option value="2" <c:if test="${nurseJobtitle.user.device==2 }">selected="selected"</c:if>>安卓</option>
						<option value="3" <c:if test="${nurseJobtitle.user.device==3}">selected="selected"</c:if>>微信</option>
						<option value="7" <c:if test="${nurseJobtitle.user.device==7 }">selected="selected"</c:if>>呼啦圈</option>
					</select></div>
				</td>
			    <td>注册时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="bgTime"
								name="bgTime"
								value="<fmt:formatDate value="${nurseJobtitle.bgTime}" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar" title="选择日期"></span></span>  -->
						</div>
						</td><td>--
							</td><td>
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="enTime"
								name="enTime"
								value="<fmt:formatDate value="${nurseJobtitle.enTime}" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar" title="选择日期"></span></span>  -->
						</div>
					</div></td> 
			</tr>
			<tr>
				<td></td>
				<td width="200">
				
				</td>
			</tr>
			<%-- <tr>
				<td>手机号：</td>
				<td width="200"><input type="text" name="user.phone"
					id="user.phone" value="${nurse.user.phone}"
					placeholder="请输入搜索联系人手机号" /></td>
				<td>联系地址：</td>
				<td width="200"><input type="text" name="address"
					id="address" value="${nurse.address}"
					placeholder="请输入搜索公司地址" /></td>
				<!--<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>-->
			</tr> --%>
			<%-- <tr>
			<td>参加工作时间：</td>
			<td width="200">
				<input type="text" name="workYears" id="workYears" value="${nurse.workYears}" />
			</td>
			<td>个人简介：</td>
			<td width="200">
				<input type="text" name="brief" id="brief" value="${nurse.brief}" />
			</td>
			<td>详情：</td>
			<td width="200">
				<input type="text" name="details" id="details" value="${nurse.details}" />
			</td>
			<td>在职医院：</td>
			<td width="200">
				<input type="text" name="hospital" id="hospital" value="${nurse.hospital}" />
			</td>
			<td>所属科室：</td>
			<td width="200">
				<input type="text" name="departmentId" id="departmentId" value="${nurse.departmentId}" />
			</td>
			<td>封号标识：</td>
			<td width="200">
				<input type="text" name="active" id="active" value="${nurse.active}" />
			</td>
		</tr> --%>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>

