package Guoz.kafka.beans;

import java.util.Date;

/**
 *
 * 功能描述: kafka bean
 *
 * @param: 
 * @return: 
 * @auther: Guoz666
 * @date: 2019/6/12 11:21
 */
public class Message {

    private Long id;    //id

    private int code;  //返回码

    private String msg; //消息

    private Date startTime;  //时间戳

    private Date sendTime;  //时间戳

    private String logPath; //日志地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}