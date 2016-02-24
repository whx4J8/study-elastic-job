package com.ejob.job.internal.config;

import com.ejob.job.api.JobConfiguration;
import com.ejob.job.exception.JobConflictException;
import com.ejob.job.exception.ShardingItemParametersException;
import com.ejob.job.internal.storage.JobNodeStorage;
import com.ejob.reg.base.CoordinatorRegistryCenter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * 弹性化分布式作业配置服务
 * <p>
 *     配置信息与注册中心交互service
 * </p>
 *
 * Created by wanghongxing on 16/2/22.
 */
public class ConfigurationService {

    private final JobNodeStorage jobNodeStorage;

    public ConfigurationService(final CoordinatorRegistryCenter registryCenter,
                                final JobConfiguration jobConfiguration) {
        jobNodeStorage = new JobNodeStorage(registryCenter,jobConfiguration);
    }

    /**
     * 持久化分布式作业配置信息
     */
    public void persistJobConfiguratoin(){
        checkConfictJob();
        registerJobInfo();
    }

    /**
     * 检查冲突作业
     */
    private void checkConfictJob(){
        if(jobNodeStorage.isNodeExisted(ConfigurationNode.JOB_CLASS)){
            String registeredJobClassName = jobNodeStorage.getJobData(ConfigurationNode.JOB_CLASS);
            String toBeRegisteredJobClassName = jobNodeStorage.getJobConfiguration().getJobClass().getCanonicalName();
            if(!toBeRegisteredJobClassName.equals(registeredJobClassName)){
                throw new JobConflictException(jobNodeStorage.getJobConfiguration().getJobName(),registeredJobClassName,toBeRegisteredJobClassName);
            }
        }
    }

    /**
     * 注册作业信息
     */
    private void registerJobInfo(){
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.JOB_CLASS, jobNodeStorage.getJobConfiguration().getJobClass().getCanonicalName());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.SHARDING_TOTAL_COUNT, jobNodeStorage.getJobConfiguration().getShardingTotalCount());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.SHARDING_ITEM_PARAMETERS, jobNodeStorage.getJobConfiguration().getShardingItemParameters());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.JOB_PARAMETER, jobNodeStorage.getJobConfiguration().getJobParameter());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.CRON, jobNodeStorage.getJobConfiguration().getCron());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.MONITOR_EXECUTION, jobNodeStorage.getJobConfiguration().isMonitorExecution());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.PROCESS_COUNT_INTERVAL_SECONDS, jobNodeStorage.getJobConfiguration().getProcessCountIntervalSeconds());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.CONCURRENT_DATA_PROCESS_THREAD_COUNT, jobNodeStorage.getJobConfiguration().getConcurrentDataProcessThreadCount());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.FETCH_DATA_COUNT, jobNodeStorage.getJobConfiguration().getFetchDataCount());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.MAX_TIME_DIFF_SECONDS, jobNodeStorage.getJobConfiguration().getMaxTimeDiffSeconds());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.FAILOVER, jobNodeStorage.getJobConfiguration().isFailover());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.MISFIRE, jobNodeStorage.getJobConfiguration().isMisfire());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.JOB_SHARDING_STRATEGY_CLASS, jobNodeStorage.getJobConfiguration().getJobShardingStrategyClass());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.DESCRIPTION, jobNodeStorage.getJobConfiguration().getDescription());
        jobNodeStorage.fillJobNodeIfNullOrOverwrite(ConfigurationNode.MONITOR_PORT, jobNodeStorage.getJobConfiguration().getMonitorPort());

    }

    /**
     * 获取作业总分片数
     * @return
     */
    public Integer getShardingTotalCount(){
        return Integer.parseInt(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.SHARDING_TOTAL_COUNT));
    }

    /**
     *
     * 获取分片序列号和个性化参数对照表
     *
     * @return
     */
    public Map<Integer, String> getShardingItemParameters(){
        String value = jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.SHARDING_ITEM_PARAMETERS);
        if(Strings.isNullOrEmpty(value)){
            return Collections.emptyMap();
        }
        String[] shardingItemParameters = value.split(",");
        Map<Integer,String> result = Maps.newHashMap();
        for(String each : shardingItemParameters){
            String[] pair = each.split("=");
            if(2 != pair.length){
                throw new ShardingItemParametersException("sharding item parameters %s format error, should be in int=a,int=b",value);
            }
            try{
                result.put(Integer.parseInt(pair[0]),pair[1]);
            }catch (NumberFormatException e){
                throw new ShardingItemParametersException("sharding item parameters format error ,the %s must be integer ",pair[0]);
            }
        }

        return result;
    }

    /**
     * 获取作业自定义参数
     *
     * @return
     */
    public String getJobParameter() {
        return jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.JOB_PARAMETER);
    }

    /**
     * 获取cron表达式
     *
     * @return
     */
    public String getCron() {
        return jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.CRON);
    }

    /**
     * 是否监控
     *
     * @return
     */
    public boolean isMonitorExecution(){
        return Boolean.valueOf(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.MONITOR_EXECUTION));
    }

    /**
     * 获取分片策略实现类全路径
     *
     * @return
     */
    public String getJobShardingStrategyClass(){
        return jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.JOB_SHARDING_STRATEGY_CLASS);
    }

    /**
     * 获取监控端口
     *
     * @return
     */
    public Integer getMonitorPort(){
       return Integer.parseInt(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.MONITOR_PORT));
    }

    /**
     * 获取任务名称,jobName唯一字段不变
     *
     * @return
     */
    public String getJobName(){
        return jobNodeStorage.getJobConfiguration().getJobName();
    }


}

