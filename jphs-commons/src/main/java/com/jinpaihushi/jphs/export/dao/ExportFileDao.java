package com.jinpaihushi.jphs.export.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.export.model.ExportFile;

/**
 * 
 * @author scj
 * @date 2017-10-12 14:13:45
 * @version 1.0
 */
@Repository("exportFileDao")
public interface ExportFileDao extends BaseDao<ExportFile> {
	
	
	
}
