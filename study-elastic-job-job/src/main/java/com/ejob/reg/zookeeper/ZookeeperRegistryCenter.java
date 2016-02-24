package com.ejob.reg.zookeeper;

import com.ejob.reg.base.CoordinatorRegistryCenter;
import com.ejob.reg.exception.RegExceptionHandler;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import static org.apache.curator.framework.CuratorFrameworkFactory.Builder;

/**
 * Created by wanghongxing on 16/2/20.
 */
public class ZookeeperRegistryCenter implements CoordinatorRegistryCenter {

    private final static Logger log = LoggerFactory.getLogger(ZookeeperRegistryCenter.class);


    /**
     * 注册中心配置文件
     */
    private ZookeeperConfiguration zkConfig;

    /**
     * 注册中心数据的本地缓存
     */
    private Map<String,TreeCache> caches = Maps.newHashMap();

    /**
     * zk客户端
     */
    private CuratorFramework zkClient;

    public ZookeeperRegistryCenter(ZookeeperConfiguration zkConfig) {
        this.zkConfig = zkConfig;
    }

    /**
     * @param key
     * @return
     */
    public String getDirectly(String key) {

        return null;
    }

    public List<String> getChildrenKeys(String key) {
        return null;
    }

    public void persistEphemeral(String key, String value) {

    }

    public void persistEphemeralSequential(String key) {

    }

    public void addCacheData(String cachePath) {

    }

    public Object getRawCache(String cachePath) {
        return null;
    }

    public void init() {

        if(zkConfig.isUseNestedZookeeper()){
            //TODO:启动本地zk
        }

        log.debug("elastic job : zookeeper registry center init , server list is :{ " + zkConfig.getNestedDataDir() + " }");

        Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getServerLists())
                .retryPolicy(new ExponentialBackoffRetry(zkConfig.getBaseSleepTimeMilliseconds(),//TODO:还得看看CuratorFrameworkFactory
                        zkConfig.getMaxRetries(),zkConfig.getMaxSleepTimeMilliseconds()))
                .namespace(zkConfig.getNamespace());

        if( 0!= zkConfig.getSessionTimeoutMilliseconds()){
            builder.sessionTimeoutMs(zkConfig.getSessionTimeoutMilliseconds());
        }

        if( 0!= zkConfig.getConnectionTimeoutMilliseconds()){
            builder.connectionTimeoutMs(zkConfig.getConnectionTimeoutMilliseconds());
        }

        if(!Strings.isNullOrEmpty(zkConfig.getDigest())){//zk 权限相关
            builder.authorization("digest",zkConfig.getDigest().getBytes(Charset.forName("UTF-8")))
                    .aclProvider(new ACLProvider() {
                        public List<ACL> getDefaultAcl() {
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }

                        public List<ACL> getAclForPath(String path) {
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }
                    });
        }

        zkClient = builder.build();
        zkClient.start();
        try {
            zkClient.blockUntilConnected();//block 直到连接成功

            log.debug("elastic job : zk connected");

            if(!Strings.isNullOrEmpty(zkConfig.getLocalPropertiesPath())){//local propertiespath not null
                fillData();
            }
        } catch (final Exception e) {
            RegExceptionHandler.handleException(e);
        }

    }

    /**
     *
     */
    private void fillData() {

    }


    public void close() {

    }

    public String get(String key) {
        return null;
    }

    public boolean isExisted(String key) {
        return false;
    }

    public void persist(String key, String value) {

    }

    public void update(String key, String value) {

    }

    public void remove(String key) {

    }

    public long getRegistryCenterTime(String key) {
        return 0;
    }

    public Object getRawClient() {
        return null;
    }
}
