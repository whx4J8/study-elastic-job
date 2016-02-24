package com.ejob.reg.exception;

/**
 *
 * 注册中心模块跑出的异常基类
 *
 * Created by wanghongxing on 16/2/21.
 */
public class RegException extends RuntimeException {

    private static final long serialVersionUID = 4016210481122716257L;

    public RegException(String message) {
        super(message);
    }

    public RegException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegException(Throwable cause) {
        super(cause);
    }

    public RegException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
