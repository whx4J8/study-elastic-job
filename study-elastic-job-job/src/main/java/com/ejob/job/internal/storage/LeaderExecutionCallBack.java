package com.ejob.job.internal.storage;

/**
 *
 * 主节点执行操作的回调接口
 *
 * Created by wanghongxing on 16/2/23.
 */
public interface LeaderExecutionCallBack {

    /**
     * 主节点执行后的回调方法
     */
    void execute();
}
