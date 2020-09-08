package com.shinva.javastudy.model;

import com.shinva.javastudy.constant.Status;
import com.shinva.javastudy.exception.BaseException;
import lombok.Data;

/**
 * @author lrg
 * @date 2020/9/8
 **/
@Data
public class ApiResponse {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回内容
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse ofStatus(Status status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    public static ApiResponse ofStatus(Status status) {

        return ofStatus(status, null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(Status.OK, data);
    }

    public static ApiResponse ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    public static <T extends BaseException> ApiResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }
}
