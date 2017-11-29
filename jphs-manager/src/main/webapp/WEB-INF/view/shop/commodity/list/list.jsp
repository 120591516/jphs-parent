<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable"
	class="data_table" style="width:950px;margin-top:10px">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="118">图片</th>
			<th width="118">商品名称</th>
			<th width="118">所属商家</th>
			<th width="70">商品分类</th>
			<th width="80">型号/规格</th>
			<th width="400" colspan="2">商品SKU/库存</th>
			<th width="50">销量</th>
			<th width="60">状态</th>
			<th width="120">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td  style="text-align: center;"><img alt="" height="50" width="50"
							id="aptitude_positives" src="${e.url}"></td>
						<td><c:out value="${e.title}" /></td>
						<td style="text-align: center;"><c:if test="${fn:length(e.businessName)>5 }">
								${fn:substring(e.businessName, 0, 5)}... 
							</c:if> <c:if test="${fn:length(e.businessName)<5 }">
								${e.businessName} 
							</c:if></td>
						<td  style="text-align: center;"> ${e.commodityTypeParent} ${e.commodityType} </td>
						<td><c:out value="${e.model}" /></td>
						<td><c:out value="${e.totals}" /></td>
						<td>
							<c:choose>
								<c:when test="${fn:length(e.skuNameList) >0}">
									<table>
										<c:forEach items="${e.skuNameList}" var="k" varStatus="s">
										<tr>
										<td style="border:0">
											<c:out value="${k.name}" />
										 </td>
										 <td style="border:0">
											<c:out value="${k.number}" />
											</td>
										</tr>
										</c:forEach>
									</table>
								</c:when>
							</c:choose>
						</td>

						<td><c:out value="${e.count}" /></td>
						<td>
							<c:if test="${e.status ==1}">
							<span style="color: #34BC2C;">已上架</span>
							</c:if>
							<c:if test="${e.status ==-1}">
							已下架
							</c:if>
							<c:if test="${e.status ==0}">
							<span style="color: #F0BB1C;">待审核</span>
							</c:if>

						<td width="10%">
						<jphs:hasPermission url="/commodity/updateStatus.jhtml">
						<a onclick=updateStatus('${e.id }','${e.status }')>
								<c:if test="${e.status ==1}">
								<span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">下架</span>
								</c:if>
								<c:if test="${e.status ==-1}">
								<span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">上架</span>
								</c:if>
								<c:if test="${e.status ==0}">
								<span style="color: blue;text-decoration: underline;padding-left: 25px;padding-right: 8px;">上架</span>
								</c:if>
							</a>
						</jphs:hasPermission>
						<jphs:hasPermission url="/commodity/detail.jhtml">
						<a onclick="redirectDetailPage('${e.id}')"> <img
								src="/static/images/chakan.png">
						</a> 
						</jphs:hasPermission>
						<jphs:hasPermission url="/commodity/redirectUpdate.jhtml">
						<a onclick="redirectUpdatePage('${e.id}')"> <img
								src="/static/images/xiugai.png">
						</a> 
						</jphs:hasPermission>
						<jphs:hasPermission url="/commodity/delete.jhtml">
						<a onclick="deleteById('${e.id}')"> <img
								src="/static/images/shanchu.png">
						</a>
						</jphs:hasPermission>
							
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
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>
</div>

</div>