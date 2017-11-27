<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${activityPromotion.id}" />
		<input type="hidden" id="resourceTypes" name="resourceTypes" value="${activityPromotion.resourceType}" />
		<input type="hidden" value="<c:if test="${site.status !=null }">${site.status}</c:if><c:if test="${site.status ==null }">1</c:if>" name="status"/>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">资源类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 370px" onchange="getGoodsShop(this);" id="resourceType" name="resourceType">
					<option value="1" <c:if test="${activityPromotion.resourceType == 1 }">selected="selected"</c:if>>服务</option>
					<option value="2" <c:if test="${activityPromotion.resourceType == 2 }">selected="selected"</c:if>>商品</option>
					<option value="3" <c:if test="${activityPromotion.resourceType == 3 }">selected="selected"</c:if>>套餐</option>
				</select>
			</div>
		</div>
		
		<div class="form-group product_class">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">品类列表：</label>
			<div class="controls col-md-6">
				<input type="hidden" id="productTypes" value="" />
				<select class="form-control input-xlarge" style="width: 370px" id="productType" onchange="getIfGoodsasd(this);" name="productType">
					<option value="0" id="product0" >请选择品类</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务列表：</label>
			<div class="controls col-md-6">
				<input type="hidden" id="resourceIds" value="${activityPromotion.resourceId }" />
				<select class="form-control input-xlarge" style="width: 370px" id="resourceId" onchange="getIfPriceasd(this);" name="resourceId">
					<option value="0" id="goods0" >请先选择品类</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
          <label class="control-label col-md-3" for="input01">规格列表：</label>
          <input type="hidden" id="priceIds" value="${activityPromotion.priceId }" />
          <div class="controls col-md-6" id="priceList" style="margin-left: 20px;">
          </div>
        </div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">活动类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" style="width: 370px" id="type" name="type">
					<option value="" >全部</option>
					<option value="1" <c:if test="${activityPromotion.type == 1 }">selected="selected"</c:if>>立减</option>
					<option value="2" <c:if test="${activityPromotion.type == 2 }">selected="selected"</c:if>>首单立减</option>
					<option value="3" <c:if test="${activityPromotion.type == 3 }">selected="selected"</c:if>>第二件立减</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">优惠价格：</label>
			<div class="controls col-md-6">
				<input type="number" id="price" style="width: 370px" name="price" placeholder="请输入价格"
					value="${activityPromotion.price}" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="images" 
					src="${activityPromotion.image}"> <input class="input-file"
					type="file" name="myfiles" id="image_s"
					onchange="ajaxFileUpload('image_s','image');" /> <input
					class="input-file" type="hidden" value="${activityPromotion.image}"
					id="image" name="image" />
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">站点列表：</label>
			<div class="controls col-md-6">
				<input type="hidden" id="siteIds" value="${activityPromotion.siteId }" />
				<select class="form-control input-xlarge" style="width: 370px" id="siteId"  name="siteId">
					<option value="0" id="platformId0" >请先选择站点-</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">平台列表：</label>
			<div class="controls col-md-6">
				<input type="hidden" id="platformIds" value="${activityPromotion.platformId }" />
				<select class="form-control input-xlarge" style="width: 370px" id="platformId"  name="platformId">
					<option value="0" id="platformId0" >请先选择平台-</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">开始时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="beginTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${activityPromotion.beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择开始时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly style="width: 327px;margin-left: 15px;"> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> --> <input
					type="hidden" name="beginTime" id="beginTime" value="<fmt:formatDate value='${activityPromotion.beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">结束时间：</label>	
			<div  class="controls col-md-6 input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd HH:mm:ss" data-link-field="endTime"
				data-link-format="yyyy-mm-dd HH:mm:ss">
				<input class="form-control" value="<fmt:formatDate value='${activityPromotion.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" size="14"
					placeholder="请选择结束时间" type="text" pattern="yyyy-MM-dd HH:mm:ss" value="" readonly style="width: 327px;margin-left: 15px;"> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove" title="清空"></span></span> <!-- <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar" title="选择日期"></span></span> --> <input
					type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value='${activityPromotion.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
			</div>
		</div>
	</fieldset>
</form>