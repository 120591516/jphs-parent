<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
	<thead>
		<tr >
			<th width="30"></th>
			<th width="200px">套餐</th>
			<th width="200px">套餐类型</th>
			<th width="200px">获取方式</th>
			<th width="200px">套餐价格</th>
			<th width="100px">实付金额</th>
			<!-- <th width="200px">微信号</th> -->
		<!-- 	<th width="200px">到期时间</th> -->
			<th width="200px">活动码</th>
			<th width="200px">用户手机号</th>
			<th width="200px">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.familyMode.familyPackage.title}"/></td>
							<td>
								<c:if test="${e.familyMode.familyPackage.type ==1}">
									单人版
								</c:if>
								<c:if test="${e.familyMode.familyPackage.type ==2}">
									三人版
								</c:if>
							</td>
							<td>
								<c:if test="${e.familyMode.accessMode == 1}">
									实付购买
								</c:if> <c:if test="${e.familyMode.accessMode == 2}">
									兑换码
								</c:if> <c:if test="${e.familyMode.accessMode == 3}">
									免费识别
								</c:if>
							</td>
							<td><c:out value="${e.familyMode.familyPackage.price}"/></td>
							<td><c:out value="${e.payPrice}"/></td>
							<%-- <td><c:out value="${e.wxNo}"/></td> --%>
							<%-- <td><fmt:formatDate value="${e.endTime}" pattern="yy-MM-dd HH:mm"/></td> --%>
							<td><c:out value="${e.code}"/></td>
							<td><c:out value="${e.user.phone}"/></td>
							<td>
							<jphs:hasPermission url="/family/consultation/redirectAddPage.jhtml">
							<a onclick="putFamilyConsultation('${e.id}')">
								<img style="width: 20px;height: 20px;" src="/static/images/wenda.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/family/register/redirectAddPage.jhtml">
							<a onclick="putFamilyRegister('${e.id}')">
								<img style="width: 20px;height: 20px;" src="/static/images/wenzhen.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/family/healthy/redirectAddPage.jhtml">
							<a onclick="putFamilyHealthy('${e.id}')">
								<img style="width: 20px;height: 20px;" src="/static/images/jiankangjihua.png">
							</a>
							</jphs:hasPermission>
							<jphs:hasPermission url="/family/order/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>									
							</jphs:hasPermission>
							<%-- <a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a> --%>
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
		<div class="page">
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
	
</div>