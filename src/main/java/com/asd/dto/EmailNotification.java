package com.asd.dto;

import com.asd.dto.sub.HarddiskUsage;

import java.util.ArrayList;
import java.util.List;

public class EmailNotification {
    private String message;
    private String clientIp;
    private double cpuUtilization;
    private double ramUtilization;
    private List<HarddiskUsage> harddiskUtilization=new ArrayList<>();



    public EmailNotification() {
    }

    public EmailNotification(String message, String clientIp, double cpuUtilization, double ramUtilization, List<HarddiskUsage> harddiskUtilization) {
        this.message = message;
        this.clientIp = clientIp;
        this.cpuUtilization = cpuUtilization;
        this.ramUtilization = ramUtilization;
        this.harddiskUtilization = harddiskUtilization;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public double getRamUtilization() {
        return ramUtilization;
    }

    public List<HarddiskUsage> getHarddiskUtilization() {
        return harddiskUtilization;
    }

    public void setHarddiskUtilization(List<HarddiskUsage> harddiskUtilization) {
        this.harddiskUtilization = harddiskUtilization;
    }

    public void setRamUtilization(double ramUtilization) {
        this.ramUtilization = ramUtilization;
    }



}
