package com.Guoz.utils.Exception;

/**
 * @ClassName Guoz
 * @Data 13:57
 * @Version 1.0
 **/
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(String message, int code) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
