<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>问题：</span>${familyConsultation.quiz}</p>
		<p>
			<span>答案：</span>${familyConsultation.answer}
		</p>
	</div>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>管理-创建人：</span>${familyConsultation.creatorName}
		</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${familyConsultation.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
</div>