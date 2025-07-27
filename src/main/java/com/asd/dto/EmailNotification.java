package com.asd.dto;

public class EmailNotification {
    private String message;
    private String clientIp;
    private double cpuUtilization;
    private double ramUtilization;
    private double harddiskUtilization;

    public EmailNotification() {
    }

    public EmailNotification(String message, String clientIp, double cpuUtilization, double ramUtilization, double harddiskUtilization) {
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

    public void setRamUtilization(double ramUtilization) {
        this.ramUtilization = ramUtilization;
    }

    public double getHarddiskUtilization() {
        return harddiskUtilization;
    }

    public void setHarddiskUtilization(double harddiskUtilization) {
        this.harddiskUtilization = harddiskUtilization;
    }

    public void generateAlertMessage() {
        StringBuilder alert = new StringBuilder("Alert on client IP: " + clientIp + "\n");

        boolean hasIssue = false;

        if (cpuUtilization > 150) {
            alert.append("- High CPU usage: ").append(cpuUtilization).append("%\n");
            hasIssue = true;
        }
        if (ramUtilization > 90) {
            alert.append("- High RAM usage: ").append(ramUtilization).append("%\n");
            hasIssue = true;
        }
        if (harddiskUtilization > 85) {
            alert.append("- High Hard Disk usage: ").append(harddiskUtilization).append("%\n");
            hasIssue = true;
        }
        if (!hasIssue) {
            alert.append("No critical issues detected.");
        }

        this.message = alert.toString();
    }
}
