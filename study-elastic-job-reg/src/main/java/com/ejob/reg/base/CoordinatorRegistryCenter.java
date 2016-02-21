package com.ejob.reg.base;

import java.util.List;

/**
 * 协调分布式服务的注册中心
 *
 * Created by wanghongxing on 16/2/20.
 */
public interface CoordinatorRegistryCenter extends RegistryCenter {

    /**
     * 直接从注册中心而非本地缓存中获取数据
     * @param key
     * @return
     */
    String getDirectly(String key);

    /**
     * 获取子节点名称集合
     * @param key
     * @return
     */
    List<String> getChildrenKeys(String key);

    /**
     * 持久化临时注册数据
     * @param key
     * @param value
     */
    void persistEphemeral(String key,String value);

    /**
     * 持久化临时顺序数据
     * @param key
     */
    void persistEphemeralSequential(String key);

    /**
     * 添加数据到本地缓存
     * @param cachePath
     */
    void addCacheData(String cachePath);

    /**
     * TODO:这里需要看实现
     * 获取注册中心数据缓存对象
     * @param cachePath 缓存的节点路径
     * @return
     */
    Object getRawCache(String cachePath);

}
