package com.jinpaihushi.jphs.analysis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.analysis.dao.AnalysisTaskDao;
import com.jinpaihushi.jphs.analysis.model.AnalysisTask;
import com.jinpaihushi.jphs.analysis.service.AnalysisTaskService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 10:44:27
 * @version 1.0
 */
@Service("analysisTaskService")
public class AnalysisTaskServiceImpl extends BaseServiceImpl<AnalysisTask> implements AnalysisTaskService {

    @Autowired
    private AnalysisTaskDao analysisTaskDao;

    @Override
    protected BaseDao<AnalysisTask> getDao() {
        return analysisTaskDao;
    }

    @Override
    public AnalysisTask queryTaskToStart() {
        return analysisTaskDao.queryTaskToStart();
    }

}