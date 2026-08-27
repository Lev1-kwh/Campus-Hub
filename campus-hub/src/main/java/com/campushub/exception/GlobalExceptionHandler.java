package com.campushub.exception;

import com.campushub.dto.ExceptionResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//Controller层全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {
    //如果程序出现BusinessException就调用下面方法
    @ExceptionHandler(BusinessException.class)
    public ExceptionResponse exceptionHandle(BusinessException exception){
        ExceptionResponse response = new ExceptionResponse();
            response.setCode(exception.getCode());
            response.setMessage(exception.getMessage());
        return response;
    }
}
