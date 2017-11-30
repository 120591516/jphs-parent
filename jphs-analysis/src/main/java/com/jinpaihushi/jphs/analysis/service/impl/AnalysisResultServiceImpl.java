package com.jinpaihushi.jphs.analysis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.analysis.dao.AnalysisResultDao;
import com.jinpaihushi.jphs.analysis.model.AnalysisResult;
import com.jinpaihushi.jphs.analysis.service.AnalysisResultService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 10:44:26
 * @version 1.0
 */
@Service("analysisResultService")
public class AnalysisResultServiceImpl extends BaseServiceImpl<AnalysisResult> implements AnalysisResultService {

    @Autowired
    private AnalysisResultDao analysisResultDao;

    @Override
    protected BaseDao<AnalysisResult> getDao() {
        return analysisResultDao;
    }

}