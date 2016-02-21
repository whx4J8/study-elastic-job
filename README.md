
学习当当网大神们的项目,阅读源码,记录每个模块

elastic-job分布式任务调度系统,原github:https://github.com/dangdangdotcom/elastic-job

elastic-job主要在quartz和zk上进行的分布式开发,
    将quartz原来的集群且单一执行的任务执行模式,变为将数据分片到每个机器上并行执行,加速任务执行

1.study-elastic-job-reg
    elastic-job注册zk的模块