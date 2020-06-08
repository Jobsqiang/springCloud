package com.qiangjiang.provider.model;
public class User {

    private Long id;
    private String userName;
    private String passWord;
    private String token;
    private String myOrderNo;
    private String payWayType;
    private String extra;
    private String settleUnitID;
    private String code;
    private String message;
    private String messageParams;
    private String traceID;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMyOrderNo() {
        return myOrderNo;
    }

    public void setMyOrderNo(String myOrderNo) {
        this.myOrderNo = myOrderNo;
    }

    public String getPayWayType() {
        return payWayType;
    }

    public void setPayWayType(String payWayType) {
        this.payWayType = payWayType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSettleUnitID() {
        return settleUnitID;
    }

    public void setSettleUnitID(String settleUnitID) {
        this.settleUnitID = settleUnitID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(String messageParams) {
        this.messageParams = messageParams;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }
}
