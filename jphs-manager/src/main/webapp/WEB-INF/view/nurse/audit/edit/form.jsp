<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	
	<div class="sunbox-alret">
	<div class="xuanzhuan"></div>
	<img src=""   class="fork"/>
	
</div>

<div class="xie-loading"></div>
<div class="pageBeijing"></div>
	<div class="col-md-6">
	

		<p class="sunbox">
			<img alt=""  id="head_portraits"
				src="${nurseJobtitle.sculpture }">
		</p>
		
		<p class="sunbox" style="width:150px;float:left;margin-right:10px">
			<img alt=""  id="id_positives" src="${nurseJobtitle.sfzz }" style="width:150px; height:150px">
		</p>	
		<p class="sunbox" style="width:150px;float:left">
			<img alt=""  id="id_negatives" src="${nurseJobtitle.sfzf }" style="width:150px; height:150px">
		</p>
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>姓名：</span>${nurseJobtitle.name}</p>
		<p>
			<span>性别：</span>
			<c:if test="${nurseJobtitle.sex==0}">男</c:if>
			<c:if test="${nurseJobtitle.sex==1}">女</c:if>
		</p>
		<p>
			<span>年龄：</span><c:if test="${fn:length(nurseJobtitle.sfz)>0 }">
								<c:set var="idcard" value="${fn:substring(nurseJobtitle.sfz,6,10) }" />
								<jsp:useBean id="nowDate" class="java.util.Date" />
								<fmt:formatDate var="nowStr" value="${nowDate}" pattern="yyyy" />
								<c:if test="${nowStr-idcard<=0 }">1</c:if>
								<c:if test="${nowStr-idcard>0 }">${nowStr-idcard}</c:if>
							</c:if>
		</p>
		<%-- <p>
			<span>手机号：</span>${nurse.user.phone}</p> --%>
		<p>
			<span>身份证号：</span>${nurseJobtitle.sfz}</p>
		<p>
			<span>联系方式：</span>${nurseJobtitle.phone}</p>
		<p>
			<span>学历：</span>${nurseJobtitle.education}</p>
		 <p>
			<span>联系地址：</span>
			<c:choose>
				<c:when test="${fn:length(nurseJobtitle.address) > 19}">
					<c:out value="${fn:substring(nurseJobtitle.address, 0, 19)}......" />
				</c:when>
				<c:otherwise>${nurseJobtitle.address }</c:otherwise>
			</c:choose>
		</p> 
		<%-- <p>
			<span>推荐人：</span>${nurse.recommendName}
		</p> --%>
		<p>
			<span>认证时间：</span>
			<fmt:formatDate value="${nurseJobtitle.createTime}" pattern="yyyy-MM-dd" />
		</p>
		<%-- <p>
			<span>个人简介：</span>${fn:escapeXml(nurse.brief)}</p>
		<p>
			<span>备注：</span>${fn:escapeXml(nurse.details)}</p> --%>

	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">工作信息</p>
	<div class="col-md-6">
		<p>
			<span>工作时间：</span>
			<fmt:formatDate value="${nurseJobtitle.workYears}" type="both"
				pattern="yyyy-MM-dd" />
		</p>
		<c:if test="${nurseJobtitle.type==1 }">
		<p>
			<span>
			在职医院：
			
			</span>${nurseJobtitle.hospital}</p>
		<p>
			<span>科室：</span>
			<c:forEach items="${department}" var="e" varStatus="s">
				<c:if test="${nurseJobtitle.departmentId==e.id}">${e.name }</c:if>
			</c:forEach>
		</p>
		</c:if>
		<c:if test="${nurseJobtitle.type>1 }">
		<p>
			<span>
			执业机构：
			</span>${nurseJobtitle.hospital}</p>
		<p>
			<span>毕业院校：</span>
			${nurseJobtitle.studyInstitution}
		</p>
		</c:if>
		<p>
			<span>护士分类：</span>
			${nurseJobtitle.jobtitleTypeName }
		</p>
		<p>
			<span>职称：</span>${nurseJobtitle.jobtitleName }</p>
		
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
			<c:if test="${nurseJobtitle.type==1 }">
			 <p class="sunbox">
				<img alt="" width="120" id="aptitude_positives"
				src="${nurseJobtitle.seniorityProve }"> 
				</p>
				<p class="sunbox" style="width:150px;float:left;margin-right:10px">
				<img alt=""  id="aptitude_negatives" src="${nurseJobtitle.charteredProve }" style="width:150px; height:150px">
				</p>
				<p  class="sunbox" style="width:150px;float:left">
				<img alt=""  id="aptitude_negatives" src="${nurseJobtitle.hospitalContract }" style="width:150px; height:150px">
				</p> 				
			</c:if>
			<c:if test="${nurseJobtitle.type==2 }">
	
			<p class="sunbox">
				<img alt=""  id="aptitude_positives"
				src="${nurseJobtitle.therapistZ }"> 
			</p>	 		
			</c:if>
			<c:if test="${nurseJobtitle.type==3 }">
			 <p class="sunbox">
				<img alt="" width="120" id="aptitude_positives"
				src="${nurseJobtitle.fransnanaCard }">
			</p>
			</c:if>

		<%-- <p>
			<span>服务区域：</span>
		<table style="width: 300px;">
			<c:forEach items="${nurse.location}" var="e" varStatus="s">
				<tr>
					<c:set value="${ fn:split(e.content, '-') }" var="str1" />
					<c:forEach items="${ str1 }" var="s">
						<td>${s }</td>
					</c:forEach>

				</tr>
			</c:forEach>
		</table> --%>
	</div>
</div>
<div class="from-group">
	<table id="dateTable" class="data_table" style="width:900px">
		<thead>
			<tr>
				<th width="30"></th>
				<th>审核结果</th>
				<th>审核时间</th>
				<th>审核人</th>
				<th>审核备注</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(audit) >0}">
					<c:forEach items="${audit}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:if test="${e.audit==0 }">未通过</c:if> <c:if
									test="${e.audit==1 }">已认证</c:if></td>
							<td><fmt:formatDate value="${e.auditTime}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${e.auditUserName}" /></td>
							<td><c:out value="${e.remark}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示审核记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">审核信息</h4>
			</div>
			<div class="modal-body">
				<form id="auditForm" method="post" class="form-horizontal" action="/audit/insert.jhtml">
					<input type="hidden" id="creatorId" name="creatorId" value="${nurseJobtitle.creatorId}" /> 
					<input type="hidden" id="creatorName" name="creatorName" value="${nurseJobtitle.name}" />
					<input type="hidden" id="nurseJobtitleId" name="nurseJobtitleId" value="${nurseJobtitle.id}" />
					<div class="form-group">
						<label class="control-label col-md-3">审核结果</label>
						<div class="controls  col-md-6">
							<!-- 单行单选项目 -->
							<label class="radio radio-inline"> <input type="radio"
								value="1" id="audit" name="audit" checked="checked"> 通过
							</label> <label class="radio radio-inline"> <input type="radio"
								value="0" id="audit" name="audit"> 不通过
							</label>
						</div>
					</div>
					<div class="form-group">
						<!-- 文本区域 -->
						<label class="control-label col-md-3">审核意见</label>
						<div class="controls col-md-6">
							<div class="textarea">
								<textarea type="" id="remark" name="remark" class="form-control"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="submit" id="save" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<script type="text/javascript">
$(document).ready(function(){ 
    
    $(".sunbox img").click(function(){      
        var Img=$(this).attr("src");           
        $(".xuanzhuan").fadeIn();
        $(".fork").fadeIn();
        $(".xuanzhuan").fadeIn();
        $(".fork").fadeIn();
        $(".pageBeijing").css("display","block");
        $(".xie-loading").fadeIn();
        $(".sunbox-alret img").attr("src",Img)
            $(".xie-loading").fadeOut();              
            $(".sunbox-alret").fadeIn();
            $(".sunbox-alret").rotate(0);     
            $(".sunbox-alret").css({
                "marginTop":-$(".sunbox-alret").outerHeight()/2,
                "marginLeft":-$(".sunbox-alret").outerWidth()/2,
            })            
    })
    var num = 0; 
    $(function(){
        $(".xuanzhuan").click(function(){ 
            num ++; 
            $(".fork").rotate(90*num); 
        })
    })
    
    $(".fork").click(function(){
        $(".pageBeijing").css("display","none");
        $(".sunbox-alret").fadeOut();
        $(".xuanzhuan").fadeOut();
        $(".xie-loading").fadeOut();
        $(".fork").fadeOut();
        $(".sunbox-alret img").attr("src","");
        num = 0;
    })
$(".pageBeijing").click(function(){
	$(".sunbox-alret").hide();
	$(this).hide();
})

})
</script>