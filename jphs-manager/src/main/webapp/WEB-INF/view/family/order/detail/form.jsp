<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p> <span>购买人姓名：</span><span class="title_value">${familyOrder.user.name}</span></p>
		<p> <span>购买人手机号：</span><span class="title_value">${familyOrder.user.phone}</span> </p>
		<p>
			<span>购买人微信号：</span><span class="title_value">${familyOrder.wxNo}</span>
		</p>
		<p>
			<span>实付款：</span><span class="title_value">${familyOrder.payPrice}</span>
		</p>
		<p>
			<span>活动码：</span><span class="title_value">${familyOrder.code}</span>
		</p>
		<p>
			<span>到期时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyOrder.endTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>

	</div>
	<div class="col-md-6">

		<p> <span>套餐名称：</span><span class="title_value">${familyOrder.familyMode.familyPackage.title}</span></p>
		<p> <span>套餐类型：</span><span class="title_value"><c:if test="${familyOrder.familyMode.familyPackage.type == 1}">单人版</c:if>
								<c:if test="${familyOrder.familyMode.familyPackage.type == 2}">三人版</c:if></span> </p>
		<p>
			<span>套餐价格：</span><span class="title_value">${familyOrder.familyMode.familyPackage.price}</span>
		</p>
		<p>
			<span>获取方式：</span><span class="title_value"><c:if test="${familyOrder.familyMode.accessMode == 1}">实付款</c:if>
								<c:if test="${familyOrder.familyMode.accessMode == 2}">兑换码</c:if>
								<c:if test="${familyOrder.familyMode.accessMode == 3}">免费</c:if></span>
		</p>
		<p>
			<span>验证编码：</span><span class="title_value">${familyOrder.familyMode.validateCode}</span>
		</p>
		<p>
			<span>有效期：</span><span class="title_value">${familyOrder.familyMode.day}/${familyOrder.familyMode.unit}</span>
		</p>
		<p>
			<span>开始时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyOrder.familyMode.beginTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
		<p>
			<span>结束时间：</span>
			<span class="title_value"><fmt:formatDate value="${familyOrder.familyMode.endTime}"
								pattern="yy-MM-dd HH:mm" /></span>
		</p>
	</div>
</div>

<div class="details_box clearfix">
	<p class="details_box_xinxi">问答信息</p>
	<%-- <c:choose>
		<c:when test="${fn:length(familyOrder.familyConsultationList) >0}">
			<c:forEach items="${familyOrder.familyConsultationList}" var="familyConsultationOne" varStatus="status">
				<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
					<p> <span>问题：</span><span class="title_value">${familyConsultationOne.quiz}</span></p>
					<p> <span>答案：</span><span class="title_value">${familyConsultationOne.answer}</span> </p>
					<p>
						<span>创建人：</span><span class="title_value">${familyConsultationOne.creatorName}</span>
					</p>
					<p>
						<span>创建时间：</span>
						<span class="title_value"><fmt:formatDate value="${familyConsultationOne.createTime}"
											pattern="yy-MM-dd HH:mm" /></span>
					</p>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose> --%>
</div>
<table class="data_table text-center" style="width: 865px;">
<thead>
 <tr>
    <th width="200">问题</th>
    <th width="300">答案</th>
    <th width="80">创建人</th>
    <th width="100">创建时间</th>
    <th width="50">操作</th>
  </tr>
</thead>
 <tbody>
 	<c:choose>
		<c:when test="${fn:length(familyOrder.familyConsultationList) >0}">
			<c:forEach items="${familyOrder.familyConsultationList}" var="familyConsultationOne" varStatus="status">
				<tr>
					<td><span class="title_value"><c:if test="${fn:length(familyConsultationOne.quiz) > 20 }">
													${fn:substring(familyConsultationOne.quiz,0,12)}
												</c:if><c:if test="${fn:length(familyConsultationOne.quiz) < 20 }">
													${familyConsultationOne.quiz}
												</c:if></span></td>
					<td><span class="title_value"><c:if test="${fn:length(familyConsultationOne.answer) > 20 }">
													${fn:substring(familyConsultationOne.answer,0,24)}
												</c:if>
												<c:if test="${fn:length(familyConsultationOne.answer) < 20 }">
													${familyConsultationOne.answer}
												</c:if></span></td>
					<td><span class="title_value">${familyConsultationOne.creatorName}</span></td>
					<td><span class="title_value"><fmt:formatDate value="${familyConsultationOne.createTime}" pattern="yy-MM-dd HH:mm" /></span></td>
					<td>
						<a href="/family/consultation/detail.jhtml?id=${familyConsultationOne.id }">
							<img src="/static/images/chakan.png">
						</a>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose>
  </tbody>
</table>

<div class="details_box clearfix">
	<p class="details_box_xinxi">问诊记录</p>
	<%-- <c:choose>
		<c:when test="${fn:length(familyOrder.familyRegisterList) >0}">
			<c:forEach items="${familyOrder.familyRegisterList}" var="familyRegisterOne" varStatus="status">
				<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
					<p> <span>门诊类型：</span><span class="title_value"><c:if test="${familyRegisterOne.type == 1}">普通门诊</c:if>
																	<c:if test="${familyRegisterOne.type == 2}">专家门诊</c:if>
																	<c:if test="${familyRegisterOne.type == 3}">加急门诊</c:if>
						</span></p>
					<p> <span>医院：</span><span class="title_value">${familyRegisterOne.hospital}</span> </p>
					<p> <span>医院：</span><span class="title_value">${familyRegisterOne.department}</span> </p>
					<p> <span>医院：</span><span class="title_value">${familyRegisterOne.title}</span> </p>
					<p> <span>医院：</span><span class="title_value">${familyRegisterOne.phone}</span> </p>
					<p> <span>医院：</span><span class="title_value"><fmt:formatDate value="${familyRegisterOne.appointmentTime}" pattern="yy-MM-dd HH:mm" /></span> </p>
					<p> <span>医院：</span><span class="title_value">${familyRegisterOne.week}</span> </p>
					<p>
						<span>创建人：</span><span class="title_value">${familyRegisterOne.creatorName}</span>
					</p>
					<p>
						<span>创建时间：</span>
						<span class="title_value"><fmt:formatDate value="${familyRegisterOne.createTime}"
											pattern="yy-MM-dd HH:mm" /></span>
					</p>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose> --%>
</div>
<table class="data_table text-center" style="width: 865px;">
<thead>
 <tr>
    <th width="60">问诊类型</th>
    <th width="100">医院</th>
    <th width="120">科室</th>
    <th width="140">行程名称</th>
    <th width="100">拨打电话</th>
    <th width="60">星期</th>
    <th width="100">预约时间</th>
    <th width="50">操作</th>
  </tr>
</thead>
 <tbody>
 	<c:choose>
		<c:when test="${fn:length(familyOrder.familyRegisterList) >0}">
			<c:forEach items="${familyOrder.familyRegisterList}" var="familyRegisterOne" varStatus="status">
				<tr>
					<td><span class="title_value"><c:if test="${familyRegisterOne.type == 1}">普通门诊</c:if>
																	<c:if test="${familyRegisterOne.type == 2}">专家门诊</c:if>
																	<c:if test="${familyRegisterOne.type == 3}">加急门诊</c:if>
						</span></td>
					<td><span class="title_value">${familyRegisterOne.hospital}</span></td>
					<td><span class="title_value">${familyRegisterOne.department}</span></td>
					<td><span class="title_value">${familyRegisterOne.title}</span></td>
					<td><span class="title_value">${familyRegisterOne.phone}</span></td>
					<td><span class="title_value">${familyRegisterOne.week}</span></td>
					<td><span class="title_value"><fmt:formatDate value="${familyRegisterOne.appointmentTime}" pattern="yy-MM-dd HH:mm" /></span></td>
					<td>
						<a href="/family/register/detail.jhtml?id=${familyRegisterOne.id }">
							<img src="/static/images/chakan.png">
						</a>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose>
	  <%-- <tr>
	    <td>Row 1: Col 1</td>
	    <td>Row 1: Col 2</td>
	    <td>Row 1: Col 1</td>
	    <td>Row 1: Col 2</td>
	    <td>
	    	<a onclick="redirectDetailPage('${e.id}')">
				<img src="/static/images/chakan.png">
			</a>
	    </td>
	  </tr> --%>
  </tbody>
</table>

<div class="details_box clearfix">
	<p class="details_box_xinxi">康复计划</p>
	<%-- <c:choose>
		<c:when test="${fn:length(familyOrder.familyMemberList) >0}">
			<c:forEach items="${familyOrder.familyMemberList}" var="familyMemberOne" varStatus="status">
				<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
					<p> <span>姓名：</span><span class="title_value">${familyMemberOne.name}</span></p>
					<p> <span>手机号：</span><span class="title_value">${familyMemberOne.phone}</span> </p>
					<p>
						<span>身份证：</span><span class="title_value">${familyMemberOne.sfz}</span>
					</p>
					<p>
						<span>关系：</span><span class="title_value">${familyMemberOne.relation}</span>
					</p>
					<p>
						<span>创建时间：</span>
						<span class="title_value"><fmt:formatDate value="${familyMemberOne.createTime}"
											pattern="yy-MM-dd HH:mm" /></span>
					</p>
				</div>
				<c:forEach items="${familyMemberOne.familyHealthy}" var="familyHealthyOne" varStatus="statuss">
					<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
						<p> <span>健康计划标题：</span><span class="title_value">${familyHealthyOne.title}</span></p>
						<p> <span>总周期：</span><span class="title_value">${familyHealthyOne.cycle}</span></p>
						<p> <span>相关病史：</span><span class="title_value">${familyHealthyOne.history}</span></p>
						<p> <span>病史内容：</span><span class="title_value">${familyHealthyOne.historyContent}</span></p>
						<p> <span>病史周期：</span><span class="title_value">${familyHealthyOne.historyCycle}</span></p>
						<p> <span>治疗计划：</span><span class="title_value">${familyHealthyOne.plan}</span></p>
						<p> <span>计划内容：</span><span class="title_value">${familyHealthyOne.planContent}</span></p>
						<p> <span>计划周期：</span><span class="title_value">${familyHealthyOne.planCycle}</span></p>
						<p> <span>饮食安排：</span><span class="title_value">${familyHealthyOne.diet}</span></p>
						<p> <span>饮食内容：</span><span class="title_value">${familyHealthyOne.dietContent}</span></p>
						<p> <span>饮食周期：</span><span class="title_value">${familyHealthyOne.dietCycle}</span></p>
						<p>
							<span>创建时间：</span>
							<span class="title_value"><fmt:formatDate value="${familyHealthyOne.createTime}"
												pattern="yy-MM-dd HH:mm" /></span>
						</p>
					</div>
				</c:forEach>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose> --%>
</div>
<table class="data_table text-center" style="width: 865px;">
<thead>
 <tr>
 	<th width="80">姓名</th>
    <th width="60">关系</th>
    <th width="100">手机号</th>
    <th width="230">身份证</th>
    <th width="220">健康计划标题</th>
    <th width="120">创建时间</th>
    <th width="50">操作</th>
  </tr>
</thead>
 <tbody>
 	<c:choose>
		<c:when test="${fn:length(familyOrder.familyMemberList) >0}">
			<c:forEach items="${familyOrder.familyMemberList}" var="familyMemberOne" varStatus="status">
				<c:choose>
					<c:when test="${fn:length(familyMemberOne.familyHealthy) >0}">
						<c:forEach items="${familyMemberOne.familyHealthy}" var="familyHealthyOne" varStatus="statuss">
							<tr>
								<td><span class="title_value">${familyMemberOne.name}</span></td>
								<td><span class="title_value">${familyMemberOne.relation}</span></td>
								<td><span class="title_value">${familyMemberOne.phone}</span></td>
								<td><span class="title_value">${familyMemberOne.sfz}</span></td>
								<td><span class="title_value">${familyHealthyOne.title}</span></td>
								<td><span class="title_value"><fmt:formatDate value="${familyHealthyOne.createTime}" pattern="yy-MM-dd HH:mm" /></span></td>
								<td>
									<a href="/family/healthy/detail.jhtml?id=${familyHealthyOne.id }">
										<img src="/static/images/chakan.png">
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="20" align="center">没有可显示的记录。</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="20" align="center">没有可显示的记录。</td>
			</tr>
		</c:otherwise>
	</c:choose>
  </tbody>
</table>