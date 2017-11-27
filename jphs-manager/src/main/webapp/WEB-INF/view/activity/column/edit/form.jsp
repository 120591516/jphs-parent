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
		
		<%-- <div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">活动类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 180px;" id="type" name="type">
					<option value="0" >全部</option>
					<option value="1" <c:if test="${column.type == 1 }">selected="selected"</c:if>>服务</option>
					<option value="2" <c:if test="${column.type == 2 }">selected="selected"</c:if>>商品</option>
					<option value="3" <c:if test="${column.type == 3 }">selected="selected"</c:if>>自定义</option>
				</select>
			</div>
		</div> --%>

		<div class="form-group">
			<label class="control-label col-md-4" for="input01">栏目服务项：</label>
		</div>
		<div class="norm_service"<%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
			<div class="price_gradeAdd">
				<div class="marage_right_content"
					style="margin-top: 0; padding-top: 0">
					<table id="dateTable" class="data_table"
						style="width: 900px; margin: 0 auto;">
						<thead>
						<tr class="headClass">
								<th width="60">序号</th>
								<th width="60">图片</th>
								<!-- <td width="60px" >销售价格</td> -->
								<th width="200">名称</th>
								<th width="150">类型</th>
								<!-- <td width="60" >2222</td> -->
								<th width="200">链接</th>
								<th width="200">介绍</th>
								<!-- <td width="60" >创建者</td> -->
								<th width="60">排序</th>
								<th width="150">状态</th>
								<th width="60">操作</th>
							</tr>
						</thead>
						<tbody id="addtrprice0">
						<!-- 	<tr class="headClass">
								<td width="60">序号</td>
								<td width="60">图片</td>
								<td width="60px" >销售价格</td>
								<td width="200">名称</td>
								<td width="200">类型</td>
								<td width="60" >2222</td>
								<td width="200">链接</td>
								<td width="200">介绍</td>
								<td width="60" >创建者</td>
								<td width="60">排序</td>
								<td width="150">状态</td>
								<td width="60">操作</td>
							</tr> -->
							<c:choose>
								<c:when test="${fn:length(csList) >0}">
									<c:forEach items="${csList}" var="e" varStatus="s">
										<tr class="bg_list_body addClassprice">
											<td>${s.index+1}</td>
											<td>
												<input type="hidden" value="${e.id }" name="csList[${s.index }].id" />
												<label for="image_${s.index+1}_s">
													<img alt="" height="50" width="50" id="image_${s.index+1}s" src="${e.image}" />
												</label>
												<input class="input-file" type="file" name="myfiles" id="image_${s.index+1}_s" onchange="ajaxFileUpload('image_${s.index+1}_s','image_${s.index+1}');" style="display:none" />
												<input class="input-file" type="hidden" id="image_${s.index+1}" name="csList[${s.index }].image" value="${url}" />
											</td>
											<td>
												<input class="form-control" style="width: 100%" name="csList[${s.index }].name" value="${e.name }" />
											</td>
											<td>
												<select style="width: 100%;" class="form-control input-xlarge" id="type${s.index }" name="csList[${s.index }].type" onchange="getTypeSelect('${s.index }');"  >
													<option value="1" <c:if test="${e.type ==1 }">selected="selected"</c:if>>服务</option>
													<option value="2" <c:if test="${e.type ==2 }">selected="selected"</c:if>>商品</option>
													<option value="3" <c:if test="${e.type ==3 }">selected="selected"</c:if>>自定义</option>
												</select>
												<input type="hidden" id="linkType${s.index }" value="${e.type }"/>
											</td>
											<td id="setTypeSelect${s.index }">
												<c:if test="${e.type ==3 }">
													<input class="form-control" style="width: 100%" name="csList[${s.index }].link" value="${e.link }" />
												</c:if>
												<c:if test="${e.type !=3 }">
													<input type="hidden" id="link${s.index }" name="csList[${s.index }].link" value="${e.link }"/>
													<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setRelevance('${s.index }','${e.type }');" data-target="#myModal" />
												</c:if>
											</td>
											<td>
												<input class="form-control" style="width: 100%" name="csList[${s.index }].brief" value="${e.brief }" />
											</td>
											<td>
												<input class="form-control" style="width: 100%" name="csList[${s.index }].sort" value="${e.sort }" />
											</td>
											<td>
												<select style="width: 100%;" class="form-control input-xlarge" id="status${s.index }" name="csList[${s.index }].status" onchange="updateCsStatus('${s.index }','${e.id }');">
													<option value="1" <c:if test="${e.status ==1 }">selected="selected"</c:if>>启用中</option>
													<option value="0" <c:if test="${e.status ==0 }">selected="selected"</c:if>>停用中</option>
													<option value="-1" <c:if test="${e.status ==-1 }">selected="selected"</c:if>>已删除</option>
												</select>
											<td>
												<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete${s.index }" onclick="deleteTr('${e.id }');">
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<!-- <tr>
										<td colspan="20" align="center">没有可显示的记录。</td>
									</tr> -->
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="btn_add" onclick="addTd(0);"
						style="width: 900px; margin-right: 105px">+</div>
				</div>
			</div>
		</div>
	</fieldset>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="height:600px">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">设置授权</h4>
			</div>
			<div class="linkList">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveLink" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>