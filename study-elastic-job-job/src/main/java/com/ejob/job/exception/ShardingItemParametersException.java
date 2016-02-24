package com.ejob.job.exception;

/**
 *
 * Created by wanghongxing on 16/2/24.
 */
public class ShardingItemParametersException extends JobException {

    private static final long serialVersionUID = 5704449041407246694L;

    public ShardingItemParametersException(String errorMsg,Object... args) {
        super(errorMsg,args);
    }

}
