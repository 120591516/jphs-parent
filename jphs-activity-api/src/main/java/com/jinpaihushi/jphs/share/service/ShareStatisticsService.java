package com.jinpaihushi.jphs.share.service;

import com.jinpaihushi.jphs.share.model.ShareStatistics;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-11-02 17:09:13
 * @version 1.0
 */
public interface ShareStatisticsService extends BaseService<ShareStatistics> {

	/**
	 * 新建分享记录
	 * @param shareStatistics
	 * @return
	 */
	int setShareRecord(ShareStatistics shareStatistics);

}