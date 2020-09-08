package com.shinva.javastudy.handler;

import com.shinva.javastudy.exception.JsonException;
import com.shinva.javastudy.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lrg
 * @date 2020/9/8
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse JsonExceptionHandler(JsonException exception) {
        log.error("【发生异常】:JsonException ",exception);
        return ApiResponse.ofException(exception);
    }
}
