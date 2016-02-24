package com.ejob.reg.zookeeper;

import com.ejob.reg.base.AbstractRegistryCenterConfiguration;
import com.google.common.base.Strings;


/**
 *
 * 基于zookeeper的注册中心配置类
 *
 * Created by wanghongxing on 16/2/19.
 */
public class ZookeeperConfiguration extends AbstractRegistryCenterConfiguration {

    /**
     * 连接zk 服务器的列表字符串,ip和端口,逗号分割
     * host1:2081,host2:2081
     */
    private String serverLists;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 等待重试的间隔时间初使值
     */
    private int baseSleepTimeMilliseconds;

    /**
     * 等待重试的间隔时间最大值
     */
    private int maxSleepTimeMilliseconds;

    /**
     * 最大重试值
     */
    private int maxRetries;


    /**
     * 连接zk的权限令牌
     * 缺省为不需要
     */
    private String digest;


    /**
     * 内嵌zk的端口号
     * -1表示不开启内嵌zk
     */
    private int nestedPort = -1;

    /**
     * 内嵌zk的数据存储路径
     * null表示不开启内嵌zk
     */
    private String nestedDataDir;


    /**
     * session time out
     */
    private int sessionTimeoutMilliseconds;

    /**
     * connection time out
     */
    private int connectionTimeoutMilliseconds;


    public ZookeeperConfiguration(String serverLists, String namespace, int baseSleepTimeMilliseconds, int maxSleepTimeMilliseconds, int maxRetries) {
        this.serverLists = serverLists;
        this.namespace = namespace;
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
        this.maxRetries = maxRetries;
    }

    /**
     * 判断是否需要开启内嵌zk
     * @return
     */
    public boolean isUseNestedZookeeper(){
        return -1 != nestedPort & !Strings.isNullOrEmpty(nestedDataDir);
    }

    public String getServerLists() {
        return serverLists;
    }

    public void setServerLists(String serverLists) {
        this.serverLists = serverLists;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getBaseSleepTimeMilliseconds() {
        return baseSleepTimeMilliseconds;
    }

    public void setBaseSleepTimeMilliseconds(int baseSleepTimeMilliseconds) {
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
    }

    public int getMaxSleepTimeMilliseconds() {
        return maxSleepTimeMilliseconds;
    }

    public void setMaxSleepTimeMilliseconds(int maxSleepTimeMilliseconds) {
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getNestedPort() {
        return nestedPort;
    }

    public void setNestedPort(int nestedPort) {
        this.nestedPort = nestedPort;
    }

    public String getNestedDataDir() {
        return nestedDataDir;
    }

    public void setNestedDataDir(String nestedDataDir) {
        this.nestedDataDir = nestedDataDir;
    }

    public int getSessionTimeoutMilliseconds() {
        return sessionTimeoutMilliseconds;
    }

    public void setSessionTimeoutMilliseconds(int sessionTimeoutMilliseconds) {
        this.sessionTimeoutMilliseconds = sessionTimeoutMilliseconds;
    }

    public int getConnectionTimeoutMilliseconds() {
        return connectionTimeoutMilliseconds;
    }

    public void setConnectionTimeoutMilliseconds(int connectionTimeoutMilliseconds) {
        this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
    }
}

