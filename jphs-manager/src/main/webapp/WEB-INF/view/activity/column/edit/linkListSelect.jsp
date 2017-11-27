<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<!-- <script type="text/javascript">
	 var typeC =${type };
	 var linkC =${link};
	 var linkArrC =${linkArr };
</script> --><%-- 
<input type="hidden" id="linkArrC" value="${linkArr }" /> --%>
<input type="hidden" id="typeC" value="${type }" />
<input type="hidden" id="linkC" value="${link }" />
<input type="hidden" id="p" value="${p }" />
<input type="hidden" id="numberLen" value="${numberLen }" />
<c:choose>
	<c:when test="${fn:length(linkArr) >0}">
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01"><c:if test="${type == 1 }">品类：</c:if><c:if test="${type == 2 }">商家：</c:if></label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 180px;" id="product" name="product" onchange="productSelect();">
					<option value="0" >全部</option>
					<c:forEach items="${linkArr}" var="linkOne" varStatus="s">
						<option value="${linkOne.pId }" <c:if test="${linkOne.pId eq fn:trim(p) }">selected="selected"</c:if>>${linkOne.pTitle }</option>
					</c:forEach>
				</select>
			</div>
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01"><c:if test="${type == 1 }">服务</c:if><c:if test="${type == 2 }">商品</c:if>：</label>
			<div class="controls col-md-6">
				<c:forEach items="${linkArr}" var="linkOnes" varStatus="s">
					<c:if test="${s.index == 0 && link == null}">
						<select class="form-control input-xlarge classProduct" style="width: 180px;" name="p_${linkOnes.pId }" id="p_${linkOnes.pId }">
							<option value="0" >全部</option>
							<c:forEach items="${linkOnes.goodsList }" var="goodsOne" varStatus="ss">
								<option value="${goodsOne.id }" <c:if test="${fn:trim(goodsOne.id) eq fn:trim(p) }">selected="selected"</c:if>>${goodsOne.title }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${fn:trim(linkOnes.pId) eq fn:trim(p) }">
						<select class="form-control input-xlarge classProduct" style="width: 180px;" name="p_${linkOnes.pId }" id="p_${linkOnes.pId }">
							<option value="0" >全部</option>
							<c:forEach items="${linkOnes.goodsList }" var="goodsOne" varStatus="ss">
								<option value="${goodsOne.id }" <c:if test="${fn:trim(goodsOne.id) eq fn:trim(link) }">selected="selected"</c:if>>${goodsOne.title }</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${s.index != 0 }">
						<select class="form-control input-xlarge classProduct hide" style="width: 180px;" name="p_${linkOnes.pId }" id="p_${linkOnes.pId }">
							<option value="0" >全部</option>
							<c:forEach items="${linkOnes.goodsList }" var="goodsOne" varStatus="ss">
								<option value="${goodsOne.id }" <c:if test="${fn:trim(goodsOne.id) eq fn:trim(link) }">selected="selected"</c:if>>${goodsOne.title }</option>
							</c:forEach>
						</select>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="20" align="center">没有可显示的记录。</td>
		</tr> 
	</c:otherwise>
</c:choose>