package com.sky.exception;

/**
 * 账号被锁定异常
 */
public class AliMessageException extends BaseException {

    public AliMessageException() {
    }

    public AliMessageException(String msg) {
        super(msg);
    }

}
