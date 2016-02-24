package com.ejob.job.internal.sharding;

import com.ejob.job.api.JobConfiguration;
import com.ejob.job.internal.config.ConfigurationService;
import com.ejob.job.internal.election.LeaderElectionService;
import com.ejob.job.internal.env.LocalHostService;
import com.ejob.job.internal.execution.ExecutionService;
import com.ejob.job.internal.server.ServerService;
import com.ejob.job.internal.storage.JobNodeStorage;
import com.ejob.reg.base.CoordinatorRegistryCenter;

/**
 * Created by wanghongxing on 16/2/24.
 */
public class ShardingService {

    private final String jobName;

    private final JobNodeStorage jobNodeStorage;

    private final ConfigurationService configService;

    private final LeaderElectionService leaderElectionService;

    private final LocalHostService localHostService = new LocalHostService();

    private final ServerService serverService;

    private final ExecutionService executionService;

    public ShardingService(final CoordinatorRegistryCenter coordinatorRegistryCenter,
                           final JobConfiguration jobConfiguration){

        jobName = jobConfiguration.getJobName();
        jobNodeStorage = new JobNodeStorage(coordinatorRegistryCenter,jobConfiguration);
        leaderElectionService = new LeaderElectionService(coordinatorRegistryCenter,jobConfiguration);
        configService = new ConfigurationService(coordinatorRegistryCenter,jobConfiguration);
        serverService = new ServerService(coordinatorRegistryCenter,jobConfiguration);
        executionService = new ExecutionService(coordinatorRegistryCenter,jobConfiguration);

    }

    /**
     * 设置需要重新分片的标记
     */
    public void setReshardingFlag(){
        jobNodeStorage.createJobNodeIfNeeded(ShardingNode.NECESSARY);
    }

    /**
     * 判断是否需要分片
     *
     * @return
     */
    public boolean isNeedSharding(){
        return jobNodeStorage.isJobNodeExisted(ShardingNode.NECESSARY);
    }




}
