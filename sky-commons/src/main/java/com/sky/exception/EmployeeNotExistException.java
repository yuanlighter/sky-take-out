package com.sky.exception;

/**
 * @author: yuan
 * @Date: 2025-07-17 6:42
 * @Description:
 */

/**
 * 员工不存在异常
 */
public class EmployeeNotExistException extends BaseException{
    public EmployeeNotExistException() {
    }
    public EmployeeNotExistException(String msg){
        super(msg);
    }
}
