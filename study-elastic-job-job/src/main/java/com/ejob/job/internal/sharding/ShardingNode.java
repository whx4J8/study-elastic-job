package com.ejob.job.internal.sharding;

import com.ejob.job.internal.election.ElectionNode;
import com.ejob.job.internal.server.ServerNode;

/**
 * elastic job 分片节点名称的常量类
 *
 * Created by wanghongxing on 16/2/24.
 */
public class ShardingNode {


    /**
     * 主服务器分片节点
     */
    static final String LEADER_SHARDING_ROOT = ElectionNode.ROOT + "/sharding";

    /**
     * 标识必须分片节点
     */
    static final String NECESSARY = LEADER_SHARDING_ROOT + "/necessary";

    /**
     *
     */
    static final String PROCESSING = LEADER_SHARDING_ROOT + "/processing";

    /**
     * 分片节点
     */
    private static final String SERVER_SHARDING = ServerNode.ROOT + "/%s/sharding";

    /**
     * 获取某ip的分片节点
     *
     * @param ip
     * @return
     */
    public static String getShardingNode(final String ip){
        return String.format(SERVER_SHARDING,ip);
    }
}
