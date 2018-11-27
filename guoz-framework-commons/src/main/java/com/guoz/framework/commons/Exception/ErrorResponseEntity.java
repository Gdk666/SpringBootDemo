package com.guoz.framework.commons.Exception;

/**
 * @description:
 * @Data 14:01
 * @Version 1.0
 * 定义返回的异常信息格式，这样异常信息风格更为统一
 **/
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
