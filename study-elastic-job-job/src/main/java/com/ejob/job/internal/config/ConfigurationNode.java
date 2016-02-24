package com.ejob.job.internal.config;

/**
 *
 * Elastic job 配置跟节点名称的常量类
 *
 * Created by wanghongxing on 16/2/23.
 */
public final class ConfigurationNode {

    static final String ROOT = "config";

    static final String JOB_CLASS = ROOT + "/jobClass";

    static final String SHARDING_TOTAL_COUNT = ROOT + "/shardingTotalCount";

    static final String CRON = ROOT + "/cron";

    static final String SHARDING_ITEM_PARAMETERS = ROOT + "/shardingItemParameters";

    static final String JOB_PARAMETER = ROOT + "/jobParameter";

    static final String MONITOR_EXECUTION = ROOT + "/monitorExecution";

    static final String PROCESS_COUNT_INTERVAL_SECONDS = ROOT + "/processCountIntervalSeconds";

    static final String CONCURRENT_DATA_PROCESS_THREAD_COUNT = ROOT + "/concurrentDataProcessThreadCount";

    static final String FETCH_DATA_COUNT = ROOT + "/fetchDataCount";

    static final String MAX_TIME_DIFF_SECONDS = ROOT + "/maxTimeDiffSeconds";

    static final String FAILOVER = ROOT + "/failover";

    static final String MISFIRE = ROOT + "/misfire";

    static final String JOB_SHARDING_STRATEGY_CLASS = ROOT + "/jobShardingStrategyClass";

    static final String DESCRIPTION = ROOT + "/description";

    static final String MONITOR_PORT = ROOT + "/monitorPort";


}
