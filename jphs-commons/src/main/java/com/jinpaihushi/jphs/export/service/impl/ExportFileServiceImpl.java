package com.jinpaihushi.jphs.export.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.export.dao.ExportFileDao;
import com.jinpaihushi.jphs.export.model.ExportFile;
import com.jinpaihushi.jphs.export.service.ExportFileService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-10-12 14:13:45
 * @version 1.0
 */
@Service("exportFileService")
public class ExportFileServiceImpl extends BaseServiceImpl<ExportFile> implements ExportFileService{

	@Autowired
	private ExportFileDao exportFileDao;
	
	@Override
	protected BaseDao<ExportFile> getDao(){
		return exportFileDao;
	}

}