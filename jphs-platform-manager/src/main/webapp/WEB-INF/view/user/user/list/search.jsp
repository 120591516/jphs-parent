<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/user/index.jhtml" id="serach-form">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>昵称：</td>
				<td width="200"><input type="text" name="name" id="name"
					value="${user.name}" /></td>
				<td>手机号：</td>
				<td width="200"><input type="text" name="phone" id="phone"
					value="${user.phone}" style="float:left; " /></td>
			</tr>
			<tr>
				<td>用户ID：</td>
				<td width="200"><input type="text" name="id" id="id"
					value="${user.id}"  /></td>
				<td>所在地：</td>
				<td width="300">
  <div id="distpicker5" style="width:300px">
    <div class="form-group" style="width:150px;float:left">
      <label class="sr-only" for="province10">Province</label>
      <select class="form-control" id="province10" name="province"></select>
    </div>
    <div class="form-group"  style="width:150px;float:left">
      <label class="sr-only" for="city10">City</label>
      <select class="form-control" id="city10" name="city"></select>
    </div>
   <!--  <div class="form-group">
      <label class="sr-only" for="district10">District</label>
      <select class="form-control" id="district10"></select>
    </div> -->
  </div>

				</td>
			</tr>
			
			<tr>
				<td>CODE：</td>
				<td width="200"><input type="text" name="code" id="code"
					value="${user.code}" /></td>
				<td>注册端：</td>
				<td width="200">
				<select class="form-control input-xlarge" id="device"
					name="device" value="" onchange="changeType(this.options[this.options.selectedIndex].value)">
					<option value ="">请选择</option>
					<option value ="1" <c:if test="${user.device==1}">selected="selected"</c:if>>IOS</option>
					<option value ="2" <c:if test="${user.device==2}">selected="selected"</c:if>>安卓</option>
					<option value ="3" <c:if test="${user.device==3}">selected="selected"</c:if>>微信</option>
					<option value ="4" <c:if test="${user.device==4}">selected="selected"</c:if>>114等平台</option>
					<option value ="5" <c:if test="${user.device==5}">selected="selected"</c:if>>PC端</option>
					<option value ="6" <c:if test="${user.device==6}">selected="selected"</c:if>>WAP站</option>
					<option value ="7" <c:if test="${user.device==7}">selected="selected"</c:if>>呼啦圈</option>
				</select>
				
				</td>
			</tr>
			<tr>
			    <td>注册时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 120%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="beginTime"
								name="beginTime"
								value="<fmt:formatDate value="${user.beginTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
						</td><td>----
							</td><td>
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="stopTime"
								name="stopTime"
								value="<fmt:formatDate value="${user.stopTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
					</div></td> 
			</tr>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>
	<script>
			
		  $("#province10").attr("data-province", "${user.province}");
		  $("#city10").attr("data-city", "${user.city}");
			
	</script>

