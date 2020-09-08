package com.shinva.javastudy.exception;

import com.shinva.javastudy.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lrg
 * @date 2020/9/8
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public BaseException(Status status){
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
