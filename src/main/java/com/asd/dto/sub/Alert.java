package com.asd.dto.sub;


public class Alert {
    private String alertType;
    private String alertLevel;
    private String alertMessage;
    private String alertStatus;
    private String timestamp;
    private String agentName;
    private String agentId;
    private String os;
    private String environment;
    private String senderIp;
    private String userId;


    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public Alert() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Alert(String alertType, String alertLevel, String alertMessage, String alertStatus, String timestamp, String agentName, String agentId, String os, String environment, String senderIp, String userId) {
        this.alertType = alertType;
        this.alertLevel = alertLevel;
        this.alertMessage = alertMessage;
        this.alertStatus = alertStatus;
        this.timestamp = timestamp;
        this.agentName = agentName;
        this.agentId = agentId;
        this.os = os;
        this.environment = environment;
        this.senderIp = senderIp;
        this.userId = userId;
    }
}

