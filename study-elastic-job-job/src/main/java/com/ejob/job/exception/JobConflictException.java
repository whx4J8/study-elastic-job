package com.ejob.job.exception;

/**
 * Created by wanghongxing on 16/2/24.
 */
public class JobConflictException extends JobException {

    private static final long serialVersionUID = -9177464017462068133L;

    private static final String ERROR_MSG = "Job conflict with register center . " +
            "the job [%s] in register center is [%s],your job class is [%s]";

    public JobConflictException(final String jobName,final String registerJobClassName,
                                final String toBeRegisterJobClassName){
        super(ERROR_MSG,jobName,toBeRegisterJobClassName,registerJobClassName);
    }

}
