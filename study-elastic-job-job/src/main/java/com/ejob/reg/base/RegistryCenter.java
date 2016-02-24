package com.ejob.reg.base;

/**
 *
 * 注册中心
 * Created by wanghongxing on 16/2/20.
 */
public interface RegistryCenter {

    /**
     * 初始化注册中心
     */
    void init();

    /**
     * 关闭注册中心
     */
    void close();

    /**
     * 从注册中心获取数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 判断数据是否存在
     * @param key
     * @return
     */
    boolean isExisted(String key);

    /**
     * 持久化数据到注册中心
     * @param key
     * @param value
     */
    void persist(String key,String value);

    /**
     * 更新数据
     * @param key
     * @param value
     */
    void update(String key , String value);

    /**
     * 删除数据
     * @param key
     */
    void remove(String key);

    /**
     * 获取注册中心的时间
     * @param key 获取时间的key
     * @return
     */
    long getRegistryCenterTime(String key);

    /**
     * 获取注册中心的原生客户端
     *
     * @return
     */
    Object getRawClient();

}
