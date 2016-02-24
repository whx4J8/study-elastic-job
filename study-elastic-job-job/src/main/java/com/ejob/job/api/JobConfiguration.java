package com.ejob.job.api;

/**
 *
 * 作业配置信息
 *
 * Created by wanghongxing on 16/2/22.
 */
public class JobConfiguration  {

    /**
     * job name
     */
    private String jobName = null;

    /**
     * 作业执行类
     */
    private Class<? extends ElasticJob> jobClass;

    /**
     * 作业分片总数
     */
    private int shardingTotalCount;

    /**
     * 作业启动时间的cron表达式
     */
    private String cron = "";

    /**
     * 分片序列号和个性化参数对照表
     */
    private String shardingItemParameters = "";

    /**
     * 作业自定义参数
     */
    private String jobParameter = "";

    /**
     * 监控作业执行时状态
     */
    private boolean monitorExecution = true;

    /**
     * 统计作业处理数量的间隔时间
     */
    private int processCountIntervalSeconds = 300;

    /**
     * 处理数据的并发线程数
     */
    private int concurrentDataProcessThreadCount = 1;

    /**
     * 每次抓取的数据量
     */
    private int fetchDataCount = 1;

    /**
     * 最大容忍本机与注册中心的时间误差秒数
     */
    private int maxTimeDiffSeconds = -1;

    /**
     * 是否开启失效转移
     * TODO:干嘛的未知
     */
    private boolean failover;

    /**
     * 是否开启misfire
     * TODO:干嘛的未知
     */
    private boolean misfire = true;

    /**
     * 作业辅助监控端口
     */
    private int monitorPort = -1;

    /**
     * 任务分片策略实现类全路径
     */
    private String jobShardingStrategyClass = "";

    /**
     * 作业描述信息
     */
    private String description = "";

    /**
     * 作业是否禁止启动
     */
    private boolean disabled;

    /**
     * 本地配置可否覆盖注册中心配置
     */
    private boolean overwrite;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Class<? extends ElasticJob> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends ElasticJob> jobClass) {
        this.jobClass = jobClass;
    }

    public int getShardingTotalCount() {
        return shardingTotalCount;
    }

    public void setShardingTotalCount(int shardingTotalCount) {
        this.shardingTotalCount = shardingTotalCount;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getShardingItemParameters() {
        return shardingItemParameters;
    }

    public void setShardingItemParameters(String shardingItemParameters) {
        this.shardingItemParameters = shardingItemParameters;
    }

    public String getJobParameter() {
        return jobParameter;
    }

    public void setJobParameter(String jobParameter) {
        this.jobParameter = jobParameter;
    }

    public boolean isMonitorExecution() {
        return monitorExecution;
    }

    public void setMonitorExecution(boolean monitorExecution) {
        this.monitorExecution = monitorExecution;
    }

    public int getProcessCountIntervalSeconds() {
        return processCountIntervalSeconds;
    }

    public void setProcessCountIntervalSeconds(int processCountIntervalSeconds) {
        this.processCountIntervalSeconds = processCountIntervalSeconds;
    }

    public int getConcurrentDataProcessThreadCount() {
        return concurrentDataProcessThreadCount;
    }

    public void setConcurrentDataProcessThreadCount(int concurrentDataProcessThreadCount) {
        this.concurrentDataProcessThreadCount = concurrentDataProcessThreadCount;
    }

    public int getFetchDataCount() {
        return fetchDataCount;
    }

    public void setFetchDataCount(int fetchDataCount) {
        this.fetchDataCount = fetchDataCount;
    }

    public int getMaxTimeDiffSeconds() {
        return maxTimeDiffSeconds;
    }

    public void setMaxTimeDiffSeconds(int maxTimeDiffSeconds) {
        this.maxTimeDiffSeconds = maxTimeDiffSeconds;
    }

    public boolean isFailover() {
        return failover;
    }

    public void setFailover(boolean failover) {
        this.failover = failover;
    }

    public boolean isMisfire() {
        return misfire;
    }

    public void setMisfire(boolean misfire) {
        this.misfire = misfire;
    }

    public int getMonitorPort() {
        return monitorPort;
    }

    public void setMonitorPort(int monitorPort) {
        this.monitorPort = monitorPort;
    }

    public String getJobShardingStrategyClass() {
        return jobShardingStrategyClass;
    }

    public void setJobShardingStrategyClass(String jobShardingStrategyClass) {
        this.jobShardingStrategyClass = jobShardingStrategyClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
