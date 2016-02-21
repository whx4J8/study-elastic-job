
为什么从reg模块开始看,阅读源码从demo的源头开始看起,一点点深入

demo 代码:

        ZookeeperConfiguration zkConf = new ZookeeperConfiguration(//配置zk
                "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183"
                ,"ejob-demo"
                ,1000
                ,3000
                ,3);

        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConf);//注册中心

        regCenter.init();//注册中心初始化

这里仿照elastic-job源代码,仿写了注册中心连接zk服务的代码,ejob使用的curator,解决使用原生zk的一些问题
代码主要包括RegistryCenter和RegistryCenterConfiguration两个类的实现
AbstractRegistryCenterConfiguration     定义注册中心配置的一些信息(本地信息能否成重写注册中心的数据等)
        ->ZookeeperConfiguration        定义zk的一些配置信息(zk的serverlist,namespace,重连次数等等)

RegistryCenter                          定义注册中心的一些基础方法
        ->CoordinatorRegistryCenter     定义分布式注册中心的一些方法
            ->ZookeeperRegistryCenter   实现注册中心方法通过zk的方式

