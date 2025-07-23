package com.asd.dto.sub;

public class ProcessReport {

    private String processName;
    private long pid;
    private int port;
    private String cpuUsage;
    private String memoryUsage;

    private String startTime;
    private String matchedBy;

    public ProcessReport() {
    }

    public ProcessReport(String processName, long pid, int port, String cpuUsage, String memoryUsage,
                         String startTime, String matchedBy) {
        this.processName = processName;
        this.pid = pid;
        this.port = port;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;

        this.startTime = startTime;
        this.matchedBy = matchedBy;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }



    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMatchedBy() {
        return matchedBy;
    }

    public void setMatchedBy(String matchedBy) {
        this.matchedBy = matchedBy;
    }

    @Override
    public String toString() {
        return "ProcessReport{" +
                "processName='" + processName + '\'' +
                ", pid=" + pid +
                ", port=" + port +
                ", cpuUsage='" + cpuUsage + '\'' +
                ", memoryUsage='" + memoryUsage + '\'' +
                ", startTime='" + startTime + '\'' +
                ", matchedBy='" + matchedBy + '\'' +
                '}';
    }
}
