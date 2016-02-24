package com.ejob.job.exception;

/**
 * 分布式作业异常类基类
 *
 * Created by wanghongxing on 16/2/23.
 */
public class JobException extends RuntimeException {


    private static final long serialVersionUID = -2798459856503762405L;

    public JobException(Exception cause){
        super(cause);
    }

    public JobException(String errormsg,Object... args){
        super(String.format(errormsg,args));
    }

}
