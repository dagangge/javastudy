package com.shinva.javastudy.exception;

import com.shinva.javastudy.constant.Status;
import lombok.Getter;

/**
 * @author lrg
 * @date 2020/9/8
 **/
@Getter
public class JsonException extends BaseException{
    public JsonException(Integer code, String message) {
        super(code, message);
    }

    public JsonException(Status status) {
        super(status);
    }
}
