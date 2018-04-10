package com.xmy.portal.socket;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 10:09 2018/4/10
 */
public class MessageDto {
    private String messageType;
    private String data;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
