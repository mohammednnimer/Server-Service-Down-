package com.asd.logic.servicedown.models;

public class ReceiverModel {
private String alias ="user1" ;
private  String mobNum ;

private String msg ;



    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReceiverModel{" +
                "alias='" + alias + '\'' +
                ", mobNum='" + mobNum + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
