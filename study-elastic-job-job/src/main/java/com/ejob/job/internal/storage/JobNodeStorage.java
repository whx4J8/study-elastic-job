package com.ejob.job.internal.storage;

import com.ejob.job.api.JobConfiguration;
import com.ejob.job.exception.JobException;
import com.ejob.reg.base.CoordinatorRegistryCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.util.List;

/**
 * 作业节点访问类
 *
 * <p>
 *      作业节点是普通节点加上作业名称的前缀
 * </p>
 *
 * Created by wanghongxing on 16/2/22.
 */
public class JobNodeStorage {

    private final CoordinatorRegistryCenter coordinatorRegistryCenter;

    private final JobConfiguration jobConfiguration;

    private final JobNodePath jobNodePath;

    public JobNodeStorage(CoordinatorRegistryCenter coordinatorRegistryCenter, JobConfiguration jobConfiguration) {
        this.jobNodePath = new JobNodePath(jobConfiguration.getJobName());
        this.jobConfiguration = jobConfiguration;
        this.coordinatorRegistryCenter = coordinatorRegistryCenter;
    }

    /**
     * 判断节点存在
     *
     * @param node 节点名称
     * @return
     */
    public boolean isNodeExisted(final String node){
        return coordinatorRegistryCenter.isExisted(jobNodePath.getFullPath(node));
    }

    /**
     * 获取节点数据
     * @param node
     * @return
     */
    public String getJobData(final String node){
        return coordinatorRegistryCenter.get(jobNodePath.getFullPath(node));
    }

    /**
     * 直接从注册中心节点获取数据,而非本地的缓存数据
     * @return
     */
    public String getJobNodeDataDirectly(final String node){
        return coordinatorRegistryCenter.getDirectly(jobNodePath.getFullPath(node));
    }

    /**
     * 获取任务节点的子节点的名称列表
     *
     * @return
     */
    public List<String> getJobNodeChildrenKeys(final String node){
        return coordinatorRegistryCenter.getChildrenKeys(jobNodePath.getFullPath(node));
    }

    /**
     * 如果不存在则创建作业节点
     *
     * @param node
     */
    public void createJobNodeIfNeeded(final String node){

        if(!isNodeExisted(node)){
            coordinatorRegistryCenter.persist(jobNodePath.getFullPath(node), "");
        }

    }

    /**
     * 判断作业节点是否存在
     *
     * @param node
     * @return
     */
    public boolean isJobNodeExisted(final String node){
        return coordinatorRegistryCenter.isExisted(jobNodePath.getFullPath(node));
    }


    /**
     * 删除作业节点
     *
     * @param node
     */
    public void removeJobNodeIfExisted(final String node){
        if(isNodeExisted(node)){
            coordinatorRegistryCenter.remove(jobNodePath.getFullPath(node));
        }
    }

    /**
     * 节点不存在||允许重写 则填充数据
     *
     * @param node
     * @param value
     */
    public void fillJobNodeIfNullOrOverwrite(final String node,final Object value){

        if(!isNodeExisted(node) || (jobConfiguration.isOverwrite() &&
                value.toString().equals(getJobNodeDataDirectly(jobNodePath.getFullPath(node))))){
            coordinatorRegistryCenter.persist(jobNodePath.getFullPath(node),value.toString());
        }
    }

    /**
     * 填充临时节点数据
     *
     * @param node
     * @param value
     */
    public void fillEphemeralJobNode(final String node, final Object value){
        coordinatorRegistryCenter.persistEphemeral(jobNodePath.getFullPath(node),
                value.toString());
    }


    /**
     * 更新节点的数据
     *
     * @param node 节点
     * @param value 数据
     */
    public void updateJobNode(final String node , final Object value){
        coordinatorRegistryCenter.update(jobNodePath.getFullPath(node), value.toString());
    }

    /**
     * 替换节点的数据
     *
     * @param node 节点
     * @param value 待替换的数据
     */
    public void replaceJobNode(final String node , final Object value){
        coordinatorRegistryCenter.persist(jobNodePath.getFullPath(node),value.toString());
    }


    /**
     * 在主节点执行操作
     *
     * @param latchNode 实现分布式锁的节点名称
     * @param callBack  执行操作的回调方法
     */
    public void executeInLeader(final String latchNode,final LeaderExecutionCallBack callBack){

        try(LeaderLatch leaderLatch = new LeaderLatch(getClient(),jobNodePath.getFullPath(latchNode))) {
            leaderLatch.start();
            leaderLatch.close();
            callBack.execute();
        } catch (Exception e) {
            if(e instanceof InterruptedException){
                Thread.currentThread().interrupt();
            }else{
                throw new JobException(e);
            }

        }
    }


    /**
     * 添加连接状态监听器
     *
     * @param connectionStateListener
     */
    public void addConnectionStateListener(final ConnectionStateListener connectionStateListener){
        getClient().getConnectionStateListenable().addListener(connectionStateListener);
    }

    /**
     * 获取客户端
     *
     * @return
     */
    private CuratorFramework getClient(){
        return (CuratorFramework) coordinatorRegistryCenter.getRawClient();
    }

    /**
     * 添加数据监听器
     *
     * @param treeCacheListener
     */
    public void addDataListener(final TreeCacheListener treeCacheListener){
        TreeCache cache = (TreeCache) coordinatorRegistryCenter.getRawCache("/" +
                jobConfiguration.getJobName());
        cache.getListenable().addListener(treeCacheListener);
    }

    /**
     * 获取注册中心当前时间
     * @return
     */
    public long getRegistryCenterTime(){
        return coordinatorRegistryCenter.getRegistryCenterTime(jobNodePath.getFullPath("systemTime/current"));
    }


    public CoordinatorRegistryCenter getCoordinatorRegistryCenter() {
        return coordinatorRegistryCenter;
    }

    public JobConfiguration getJobConfiguration() {
        return jobConfiguration;
    }

    public JobNodePath getJobNodePath() {
        return jobNodePath;
    }
}
