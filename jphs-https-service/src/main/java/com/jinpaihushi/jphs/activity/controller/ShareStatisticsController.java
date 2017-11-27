package com.jinpaihushi.jphs.activity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.share.model.ShareStatistics;
import com.jinpaihushi.jphs.share.service.ShareStatisticsService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/shareStatistics")
public class ShareStatisticsController {
	
	@Autowired
	ShareStatisticsService shareStatisticsSrevice;
	
	@ResponseBody
	@RequestMapping(name = "/新建分享记录", path = "/insert.json")
	public byte[] setShareStatistics(HttpServletRequest req, HttpServletResponse resp,ShareStatistics shareStatistics){
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("shareStatistics.insert.json  type="+shareStatistics.getType()+"  goods_id="+shareStatistics.getGoodsId()
				+"  name="+shareStatistics.getName()
				+"  url="+shareStatistics.getUrl()
				+"  share_platform="+shareStatistics.getSharePlatform()
				+"  share_device="+shareStatistics.getShareDevice()
				+"  creator_id="+shareStatistics.getCreatorId());
			}
			if(shareStatistics.getShareDevice() == null || shareStatistics.getSharePlatform()== null){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			int s = shareStatisticsSrevice.setShareRecord(shareStatistics);
			return JSONUtil.toJSONResult(1, "操作成功", s);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("shareStatistics.insert.json  type="+shareStatistics.getType()+"  goods_id="+shareStatistics.getGoodsId()
			+"  name="+shareStatistics.getName()
			+"  url="+shareStatistics.getUrl()
			+"  share_platform="+shareStatistics.getSharePlatform()
			+"  share_device="+shareStatistics.getShareDevice()
			+"  creator_id="+shareStatistics.getCreatorId(), e);
		}
		return null;
	}
	
}
