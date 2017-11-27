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
			<span>健康计划标题：${familyHealthy.title}</span>
		</p>
		<p>
			<span>总周期：</span>${familyHealthy.cycle}
		</p>
		<p>
			<span>相关病史：</span>${familyHealthy.history}
		</p>
		<p>
			<span>病史内容：</span>${familyHealthy.historyContent}
		</p>
		<p>
			<span>病史周期：</span>${familyHealthy.historyCycle}
		</p>
		<p>
			<span>治疗计划：</span>${familyHealthy.plan}
		</p>
		<p>
			<span>计划内容：</span>${familyHealthy.planContent}
		</p>
		<p>
			<span>计划周期：</span>${familyHealthy.planCycle}
		</p>
		<p>
			<span>饮食安排：</span>${familyHealthy.diet}
		</p>
		<p>
			<span>饮食内容：</span>${familyHealthy.dietContent}
		</p>
		<p>
			<span>饮食周期：</span>${familyHealthy.dietCycle}
		</p>
		<p>
			<span>创建时间：</span><fmt:formatDate value="${familyHealthy.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p> 
			<span>姓名：${familyHealthy.familyMember.name}</span>
		</p>
		<p>
			<span>关系：</span>${familyHealthy.familyMember.relation}
		</p>
		<p>
			<span>手机号：</span>${familyHealthy.familyMember.phone}
		</p>
		<p>
			<span>身份证：</span>${familyHealthy.familyMember.sfz}
		</p>
		<p>
			<span>管理-创建人：</span>${familyHealthy.creatorName}
		</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${familyHealthy.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>
	</div>
</div>