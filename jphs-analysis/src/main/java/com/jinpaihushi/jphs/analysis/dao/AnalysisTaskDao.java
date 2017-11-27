package com.jinpaihushi.jphs.analysis.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.analysis.model.AnalysisTask;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 10:44:27
 * @version 1.0
 */
@Repository("analysisTaskDao")
public interface AnalysisTaskDao extends BaseDao<AnalysisTask> {

    /**
     * 查询需要开始的任务
     * @return 日志任务对象
     */
    AnalysisTask queryTaskToStart();

    /**
     * 查询需要停止的任务
     * @return 日志任务对象
     */
    AnalysisTask queryTaskToStop();

}
