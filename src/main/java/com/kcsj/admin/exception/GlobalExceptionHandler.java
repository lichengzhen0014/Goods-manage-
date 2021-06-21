package com.kcsj.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author lcz
 * @create 2021-06-07-9:34
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String handlerArithException(Exception e){

        log.error("异常是：{}",e);
        return "login";
    }
}
