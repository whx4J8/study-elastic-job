package com.ejob.reg.zookeeper;

import com.ejob.reg.base.CoordinatorRegistryCenter;
import com.sun.tools.javac.util.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wanghongxing on 16/2/21.
 */
public class ZookeeperRegistryCenterTest {

    @Test
    public void testInit() throws Exception {

        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(
                "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",
                "test ejob reg",
                1000,
                3000,
                3);

        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConfig);

        regCenter.init();//block 直到连上zk

        Assert.check(true);

    }
}