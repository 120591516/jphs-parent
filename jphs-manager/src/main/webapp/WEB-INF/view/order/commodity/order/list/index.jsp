<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />订单管理<i class="public1-horn-45"></i>商品订单管理<i class="public1-horn-45"></i>商品订单列表 
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<jphs:hasPermission url="/commodity/order/getExcel.jhtml">
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#dxModal">导出</button>
	</jphs:hasPermission>	
	<!-- 右对齐按钮 -->
	<jsp:include page="list.jsp"></jsp:include>

</div>



<!-- 模态框（Modal） -->
<div class="modal fade" id="dxModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="border:0">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<p class="text-center" style="font-size: 18px">
			安全验证
			</p>
				<div class="form-group">
						<label class="control-label col-md-3 text-right">手机号:</label>
						<div class="controls  col-md-6">
							 
							   ${fn:substring(session_user.phone, 0, 3)}****
							   ${fn:substring(session_user.phone, 7, 11)}
						</div>
					</div> 
					<div class="clearfix"></div>
			 	<div class="form-group">
						<label class="control-label col-md-3 text-right">验证码:</label>
						<div class="controls  col-md-4">
							<input type="text" value="" id="verificattionCode" name="verificattionCode" class="form-control">
						</div>
						<div class="controls  col-md-2">
						<button style="width: 95px;height: 33px;font-size: 14px;" type="button" class="public-info" onclick="putcode();">发送验证码</button>
						</div>
					</div> 
		<div class="clearfix"></div>
			<div class="modal-footer" style="border:0;text-align:center">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button> -->
				<button class="btn btn-primary" onclick="getExcel();">导出</button>
			</div>
		</div>
	</div>
</div>