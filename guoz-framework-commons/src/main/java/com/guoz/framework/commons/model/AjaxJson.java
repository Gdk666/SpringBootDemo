package com.guoz.framework.commons.model;

/**
 * 封装返回的JSON
 *
 * @author Guoz
 * @since 2018-01-10
 */
public class AjaxJson {

    private String msg;
    private boolean success;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
