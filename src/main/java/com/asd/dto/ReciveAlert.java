package com.asd.dto;

import com.asd.dto.sub.ProcessReport;

import java.util.List;

public class ReciveAlert{
    private ClientUtilization clientUtilization;
    private List<ProcessReport> processReports;

    public ClientUtilization getClientUtilization() {
        return clientUtilization;
    }

    public void setClientUtilization(ClientUtilization clientUtilization) {
        this.clientUtilization = clientUtilization;
    }

    public ReciveAlert() {
    }

    public ReciveAlert(ClientUtilization clientUtilization, List<ProcessReport> processReports) {
        this.clientUtilization = clientUtilization;
        this.processReports = processReports;
    }

    @Override
    public String toString() {
        return "ReciveAlert{" +
                "clientUtilization=" + clientUtilization +
                ", processReports=" + processReports +
                '}';
    }

    public List<ProcessReport> getProcessReports() {
        return processReports;
    }

    public void setProcessReports(List<ProcessReport> processReports) {
        this.processReports = processReports;
    }
}