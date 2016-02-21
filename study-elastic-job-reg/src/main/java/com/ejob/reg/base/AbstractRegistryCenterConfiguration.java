package com.ejob.reg.base;

/**
 *
 * 注册中心配置抽象类
 *
 * Created by wanghongxing on 16/2/19.
 */
public class AbstractRegistryCenterConfiguration {


    /**
     * 本地属性文件的路径
     */
    private String localPropertiesPath;


    /**
     * 是否允许本地任务的配置信息值覆盖注册中心
     */
    private boolean overwrite;


    public String getLocalPropertiesPath() {
        return localPropertiesPath;
    }

    public void setLocalPropertiesPath(String localPropertiesPath) {
        this.localPropertiesPath = localPropertiesPath;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
