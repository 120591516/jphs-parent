<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${shareStatistics.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${shareStatistics.id == null?'添加':'编辑'}</div>
		<table id="shareStatisticsTable" class="tableStyle">
			<tr>
				<td align="right"width="100">资源id-服务。商品：</td>
				<td>
					<input value="${shareStatistics.goodsId}" 
						id="goodsId"
						name="goodsId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">资源名称：</td>
				<td>
					<input value="${shareStatistics.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">源资链接地址：</td>
				<td>
					<input value="${shareStatistics.url}" 
						id="url"
						name="url"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">分享目标平台，1微信好友，2微信朋友圈，3QQ好友，4QQ空间，5微博，6其他平台：</td>
				<td>
					<input value="${shareStatistics.sharePlatform}" 
						id="sharePlatform"
						name="sharePlatform"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">1ios用户端，2ios护士端，3Android用户端，4Android护士端，5wap站，6其他终端：</td>
				<td>
					<input value="${shareStatistics.shareDevice}" 
						id="shareDevice"
						name="shareDevice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>