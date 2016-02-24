package com.ejob.reg.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册模块异常捕捉类
 *
 * Created by wanghongxing on 16/2/21.
 */
public class RegExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RegExceptionHandler.class);

    /**
     *
     * handle异常
     *
     * @param cause
     */
    public static void handleException(final Exception cause){

        if(isIgnoredException(cause) || isIgnoredException(cause.getCause())){//捕捉异常,某些异常不进行处理
            log.debug("elastic job : ignored exception for : {} " , cause.getMessage() );
        }else if(cause instanceof InterruptedException){
            Thread.currentThread().interrupt();
        } else{
            throw new RegException(cause);
        }

    }

    /**
     * 过滤不需要handle的异常
     * @param cause
     * @return
     */
    public static boolean isIgnoredException(final Throwable cause){
        if(null == cause){
            return false;
        }

        //TODO: if cause instanceof ConnectionLossException NoNodeException NodeExistsException return true
        return false;
    }

}
