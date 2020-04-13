package com.bean;

public class CommonResult {
    private Boolean state;
    private String msg;
    private Object data;
 
    public CommonResult() {
        this.state=true;
        this.msg="成功";
    }
 
    public CommonResult(Boolean state, String msg) {
        this.state = state;
        this.msg = msg;
    }
 
    public CommonResult(Boolean state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
 
    public Boolean getState() {
        return state;
    }
 
    public void setState(Boolean state) {
        this.state = state;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public Object getData() {
        return data;
    }
 
    public void setData(Object data) {
        this.data = data;
    }
}
