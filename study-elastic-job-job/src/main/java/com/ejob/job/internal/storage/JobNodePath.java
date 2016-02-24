package com.ejob.job.internal.storage;

/**
 *
 * 任务节点路径
 *
 * Created by wanghongxing on 16/2/22.
 */
public class JobNodePath {

    private final String jobName;

    public JobNodePath(String jobName) {
        this.jobName = jobName;
    }

    public String getFullPath(final String node) {
        return String.format("/%s/%s", jobName, node);
    }

}
