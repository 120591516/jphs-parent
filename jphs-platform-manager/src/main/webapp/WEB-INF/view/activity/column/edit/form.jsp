<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${column.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		 
	</div>

	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">栏目名称：</label>
			<div class="controls col-md-6">
				<input value="${column.name}" id="name" name="name" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">栏目说明：</label>
			<div class="controls col-md-6">
				<textArea id=remark name="remark" rows="3"
					data-validation-engine="validate[maxSize[65535]]">${column.remark}</textArea>
			</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-md-4" for="input01">栏目服务项：</label>
			
		</div>
		<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
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
								<!-- <td width="60" >创建者</td> -->
								<td width="60" >排序</td>
								<td width="60" >状态</td>
								<td width="60" >操作</td>
							</tr>
							<input type="hidden" value="${fn:length(csList)}" id="csList"/>
							<c:choose>
							
							
			<c:when test="${fn:length(csList) >0}">
				<c:forEach items="${csList}" var="e" varStatus="s">
				
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td>
							<input type="hidden" value="${e.id }" name="csList[${s.index }].id"/>
							<img alt="" height="50" width="50" id="${s.index+1}urls"
					src="${e.image}" />
					<span>尺寸 360*350 <br>*&nbsp;图片格式必须为.png格式
				</span> <input class="input-file" type="file" name="myfiles" id="${s.index+1}pcurl_s"
					onchange="ajaxFileUpload('${s.index+1}pcurl_s','${s.index+1}url');" /> <input
					class="input-file" type="hidden" id="${s.index+1}url" name="csList[${s.index }].image" value="${url}" />
							</td>
						<td>
							<input class="form-control" style="width:100%"  name="csList[${s.index }].name" value="${e.name }" />
						</td>
						 
						<td >
							<input class="form-control" style="width:100%"  name="csList[${s.index }].link" value="${e.link }" />
						</td>
						<td>
							<input class="form-control" style="width:100%"  name="csList[${s.index }].brief" value="${e.brief }" />
						</td>
						<%-- <td>
							 <input disabled="disabled" class="form-control" style="width:100%"  name="csList[${s.index }].creatorName" value="${e.creatorName }" />
						</td> --%>
						<td>
				 			<input class="form-control" style="width:100%"  name="csList[${s.index }].sort" value="${e.sort }" />
						</td>
						<td>
				 			 <c:if test="${e.status ==1 }">
				 			 	启用中
				 			 </c:if>
				 			  <c:if test="${e.status ==0 }">
				 			 	停用中
				 			 </c:if>
				 			  <c:if test="${e.status ==-1 }">
				 			 	停用中
				 			 </c:if>
						</td>
						<td>
							<a onclick="updateCsStatus('${e.id}','${e.status}')">
								 <c:if test="${e.status ==1 }">
					 			 	停用
					 			 </c:if>
					 			  <c:if test="${e.status ==0 }">
					 			 	启用
					 			 </c:if>
					 			 <c:if test="${e.status ==-1 }">
					 			 	启用
					 			 </c:if>
					 		</a>	 
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
	
</div>
