package com.ejob.job.api;

import org.quartz.Job;
import org.quartz.JobExecutionException;


/**
 *
 * 弹性化分布式作业接口
 *
 * Created by wanghongxing on 16/2/22.
 */
public interface ElasticJob extends Job,Stopable {

    /**
     * 处理作业执行时的异常
     *
     * @param jobExecutionException     作业执行时的异常
     * @throws JobExecutionException    作业执行时的异常
     */
    void handleJobExcutionException(JobExecutionException jobExecutionException) throws
            JobExecutionException;

}
