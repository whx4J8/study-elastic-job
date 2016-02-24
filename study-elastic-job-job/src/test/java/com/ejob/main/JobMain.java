//package com.ejob.main;
//
//import com.ejob.job.api.JobConfiguration;
//import com.ejob.reg.base.CoordinatorRegistryCenter;
//import com.ejob.reg.zookeeper.ZookeeperConfiguration;
//import com.ejob.reg.zookeeper.ZookeeperRegistryCenter;
//
///**
// * Created by wanghongxing on 16/2/22.
// */
//public class JobMain {
//
//    public void testDemo(){
//
//        ZookeeperConfiguration zkConf = new ZookeeperConfiguration(//配置zk
//                "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183"
//                ,"ejob-demo"
//                ,1000
//                ,3000
//                ,3);
//
//
//        JobConfiguration jobConfig = new JobConfiguration("simpleJob", DemoJob.class, 1, "0/5 * * * * ?");//配置job
//
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConf);//注册中心
//
//        regCenter.init();//注册中心启动
//
//        new JobScheduler(regCenter,jobConfig).init();//任务启动
//
//    }
//}
