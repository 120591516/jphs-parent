<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${column.id}" />

	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">栏目名称：</label>
			<div class="controls col-md-6">
				${column.name}
			</div>
		</div>
			<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">生成接口地址：</label>
			<div class="controls col-md-6">
				<%
							String URL = request.getHeader("host");
							URL = URL.substring(0,1);
							if(URL.equals("l"))
							{
								%>
								<c:out value="http://localhost:8080/column/getColumnService.json?columnId=${column.id }"/>
								<% 
							}else if(URL.equals("1"))
							{
								%>
								<c:out value="https://s.goldnurse.com/column/getColumnService.json?columnId=${column.id }"/>
								<% 
							}else
							{
								%>
								<c:out value="/column/getColumnService.json?columnId=${column.id }"/>
								<% 
							}
							%>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">栏目说明：</label>
			<div class="controls col-md-6">
				${column.remark}
			</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-md-4" for="input01">栏目服务项：</label>
			
		</div>
		<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
			<hr><input type="hidden" value="0" id="cpList"/>
			<div class="price_gradeAdd">
				<div class="marage_right_content"  style="margin-top:0;padding-top:0">
					<table id="dateTable"   class="data_table" style="width:780px;margin:0 auto;margin-top:20px;margin-right:105px">
						<tbody id="addtrprice0">
							<tr class="headClass">
								<td width="100">序号</td>
								<td width="60">图片</td>
								<!-- <td width="60px" >销售价格</td> -->
								<td width="60" >名称</td>
								<!-- <td width="60" >2222</td> -->
								<td width="60" >链接</td>
								<td width="60" >介绍</td>
								<td width="60" >创建者</td>
								<td width="60" >排序</td>
								<td width="60" >状态</td>
								 
							</tr>
							<input type="hidden" value="${fn:length(csList)}" id="csList"/>
							<c:choose>
							
							
			<c:when test="${fn:length(csList) >0}">
				<c:forEach items="${csList}" var="e" varStatus="s">
				
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td>
							<input type="hidden"   name="csList[${s.index }].id" value="${e.id }" />
							<img alt="" height="50" width="50"
							id="aptitude_positives" src="${e.image}">
							</td>
						<td>
							${e.name }
						</td>
						 
						<td >
							${e.link }
						</td>
						<td>
							${e.brief }
						</td>
						<td>
							 ${e.creatorName }
						</td>
						<td>
				 			${e.sort }
						</td>
						<td>
				 			 <c:if test="${e.status ==1 }">
				 			 	启用中
				 			 </c:if>
				 			  <c:if test="${e.status ==0 }">
				 			 	停用中
				 			 </c:if>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
						</tbody>
					</table>
					</div>
				</div>
			</div>
	</fieldset>
	
</div>
