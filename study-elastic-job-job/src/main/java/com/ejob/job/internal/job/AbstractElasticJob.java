package com.ejob.job.internal.job;

import com.ejob.job.api.ElasticJob;
import com.ejob.job.internal.config.ConfigurationService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * 弹性化分布式作业的基类
 *
 * Created by wanghongxing on 16/2/22.
 */
public abstract class AbstractElasticJob implements ElasticJob {

    private volatile boolean stoped;//TODO: why use volatile

    private ConfigurationService configService;


    public ConfigurationService getConfigService() {
        return configService;
    }

    public void setConfigService(ConfigurationService configService) {
        this.configService = configService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

}
