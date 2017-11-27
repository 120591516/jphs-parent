package com.jinpaihushi.jphs.share.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.share.dao.ShareStatisticsDao;
import com.jinpaihushi.jphs.share.model.ShareStatistics;
import com.jinpaihushi.jphs.share.service.ShareStatisticsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author scj
 * @date 2017-11-02 17:09:13
 * @version 1.0
 */
@Service("shareStatisticsService")
public class ShareStatisticsServiceImpl extends BaseServiceImpl<ShareStatistics> implements ShareStatisticsService{

	@Autowired
	private ShareStatisticsDao shareStatisticsDao;
	
	@Override
	protected BaseDao<ShareStatistics> getDao(){
		return shareStatisticsDao;
	}
	
	/**
	 * 新建分享记录
	 * @param shareStatistics
	 * @return
	 */
	public int setShareRecord(ShareStatistics shareStatistics){
		
		if(shareStatistics.getShareDevice() == 5 && shareStatistics.getType()==null && StringUtils.isEmpty(shareStatistics.getGoodsId())){
			if(!StringUtils.isEmpty(shareStatistics.getUrl())){
				int type = 3;
				String str = shareStatistics.getUrl();
				String str_1 = "";
				if(str.indexOf("&c=")>0){
					type = 2;
					str_1 = str.substring(str.indexOf("&c="), str.length());
				}
				if(str.indexOf("?c=")>0){
					type = 2;
					str_1 = str.substring(str.indexOf("?c="), str.length());
				}
				if(str.indexOf("&id=")>0){
					type = 1;
					str_1 = str.substring(str.indexOf("&id="), str.length());
				}
				if(str.indexOf("?id=")>0){
					type = 1;
					str_1 = str.substring(str.indexOf("?id="), str.length());
				}
				if(!StringUtils.isEmpty(str_1)){
					String str_2 = str_1.substring(1, str_1.length());
					shareStatistics.setType(type);
					if(str.indexOf("&")<=0){
						shareStatistics.setGoodsId(str_2.split("=")[1]);
					}else{
						shareStatistics.setGoodsId(str_2.substring(0,str_2.indexOf("&")).split("=")[1]);
					}
				}
			}
		}
		shareStatistics.setId(UUIDUtils.getId());
		shareStatistics.setStatus(0);
		shareStatistics.setCreateTime(new Date());
		int s = shareStatisticsDao.insert(shareStatistics);
		return s;
	}

}