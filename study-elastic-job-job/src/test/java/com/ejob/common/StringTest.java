package com.ejob.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wanghongxing on 16/2/23.
 */
public class StringTest {

    @Test
    public void testFormat(){

        String jobName = "simpleJob";
        String nodeName = "simpleNode";
        String path = String.format("/%s/config/%s",jobName,nodeName);

        Assert.assertTrue("/simpleJob/config/simpleNode".equals(path));
    }
}
