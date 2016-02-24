package com.ejob.job.api;

/**
 *
 * 可停止的任务接口
 *
 * Created by wanghongxing on 16/2/22.
 */
public interface Stopable {

    /**
     * 停止任务
     */
    void stop();


    /**
     * 恢复任务
     */
    void resume();

}
