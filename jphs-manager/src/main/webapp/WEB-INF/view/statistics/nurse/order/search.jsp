﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div id ="quantity-year">
	<div class="marage_right_title">
		<div class="clearfix">
			<table class="text-right ">
				<tr>
					<td>区域：</td>
					<td width="200">
						<select class="form-control input-xlarge annual_select"  id="city" name="city">
						<option value="">全部</option>
						<option value="北京" <c:if test="${city=='北京' }">selected="selected"</c:if>>北京市</option>
						<option value="上海" <c:if test="${city=='上海' }">selected="selected"</c:if>>上海市</option>
						<option value="广州" <c:if test="${city=='广州' }">selected="selected"</c:if> >广州市</option>
						<option value="深圳" <c:if test="${city=='深圳' }">selected="selected"</c:if>>深圳市</option>
						<option value="成都" <c:if test="${city=='成都' }">selected="selected"</c:if>>成都市</option>
						<option value="重庆" <c:if test="${city=='重庆' }">selected="selected"</c:if>>重庆市</option>
						<option value="大连" <c:if test="${city=='大连' }">selected="selected"</c:if>>大连市</option>
						<option value="福州" <c:if test="${city=='福州' }">selected="selected"</c:if>>福州市</option>
						<option value="哈尔滨" <c:if test="${city=='哈尔滨' }">selected="selected"</c:if>>哈尔滨市</option>
						<option value="杭州" <c:if test="${city=='杭州' }">selected="selected"</c:if>>杭州市</option>
						<option value="济南" <c:if test="${city=='济南' }">selected="selected"</c:if>>济南市</option>
						<option value="南京" <c:if test="${city=='南京' }">selected="selected"</c:if>>南京市</option>
						<option value="青岛" <c:if test="${city=='青岛' }">selected="selected"</c:if>>青岛市</option>
						<option value="厦门" <c:if test="${city=='厦门' }">selected="selected"</c:if>>厦门市</option>
						<option value="沈阳" <c:if test="${city=='沈阳' }">selected="selected"</c:if>>沈阳市</option>
						<option value="苏州" <c:if test="${city=='苏州' }">selected="selected"</c:if>>苏州市</option>
						<option value="天津" <c:if test="${city=='天津' }">selected="selected"</c:if>>天津市</option>
						<option value="武汉" <c:if test="${city=='武汉' }">selected="selected"</c:if>>武汉市</option>
						<option value="西安" <c:if test="${city=='西安' }">selected="selected"</c:if>>西安市</option>
						<option value="长春" <c:if test="${city=='长春' }">selected="selected"</c:if>>长春市</option>
						<option value="长沙" <c:if test="${city=='长沙' }">selected="selected"</c:if>>长沙市</option>
						<option value="郑州" <c:if test="${city=='郑州' }">selected="selected"</c:if>>郑州市</option>
					</select>
					</td>
					<td>服务名称：</td>
					
					<td width="200">
						<select class="form-control input-xlarge annual_select"  id="goodsId"
									name="goodsId" >
									<option value="">全部</option>
									 <c:forEach items="${goods}" var="e" varStatus="s">
										<option value="${e.id }" id="goods_${e.id }" <c:if test="${goodsId==e.id}">selected="selected"</c:if> >${e.title }</option>
										<%--  --%>
									</c:forEach> 
								</select>
					</td>
				</tr>
			</table>
			<div class="marage_search_btn">
				<button id="search" class="search_btn" data-role="search-btn">搜索</button>
				<button id="clear" class="search_btn">清空</button>
			</div>
		</div>
	</div>
	<div class="marage_right_content">
		<script type="text/javascript">
			 var xAxisData =${xAxisData };
			 var yAxisData=${yAxisData };
			 var yearTimeList =${yearTimeList };
			 var flag =${flag};
		</script>
		<div class="marage_right_title">
			<div class="form-group" style="background: #fff">
				<jphs:hasPermission url="/nurse/statistics/orderRankSearch.jhtml">
				<button id="next-year" style="display:none;" class="search_btn" value="/nurse/statistics/orderRankSearch.jhtml">下一年</button>
				</jphs:hasPermission>
				&nbsp;&nbsp;
				<button id="this-year" class="search_btn" style="width: 70px;"></button>
				&nbsp;&nbsp;
				<jphs:hasPermission url="/nurse/statistics/orderRankSearch.jhtml">
				<button id="last-year"  class="search_btn" value="/nurse/statistics/orderRankSearch.jhtml">上一年</button>
				</jphs:hasPermission>
			</div>
		</div>
		<div class="marage_right_content_statis">
			<!-- ECharts单文件引入 -->
			<div id="quantityYearChart" class="chart_parent"></div>
		</div>
	</div>
</div>



