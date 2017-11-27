package com.jinpaihushi.jphs.analysis.service;

import com.jinpaihushi.jphs.analysis.model.AnalysisTask;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwenteng
 * @date 2017-11-21 10:44:27
 * @version 1.0
 */
public interface AnalysisTaskService extends BaseService<AnalysisTask> {

    /**
     * 查询需要开始的任务
     * @return 日志任务对象
     */
    AnalysisTask queryTaskToStart();

}