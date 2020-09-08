package com.shinva.javastudy.controller;

import com.shinva.javastudy.constant.Status;
import com.shinva.javastudy.exception.JsonException;
import com.shinva.javastudy.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lrg
 * @date 2020/9/8
 **/
@RestController
public class ExceptionController {
    @GetMapping("/json")
    public ApiResponse jsonException(){
        throw new JsonException(Status.UNKNOWN_ERROR);
    }
}
