package com.db.entitie;
import jakarta.persistence.*;

@Entity
@Table(name = "process_report")
public class ProcessReport {

    @Id
    @Column(name = "pid")
    private Long pid;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "port")
    private Integer port;

    @Column(name = "cpu_usage")
    private String cpuUsage;

    @Column(name = "memory_usage")
    private String memoryUsage;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "process_path")
    private String processPath;

    public ProcessReport() {
    }

    public ProcessReport(String processName, Long pid, Integer port, String cpuUsage, String memoryUsage,
                         String startTime, String processPath) {
        this.processName = processName;
        this.pid = pid;
        this.port = port;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.startTime = startTime;
        this.processPath = processPath;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
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

    public String getProcessPath() {
        return processPath;
    }
    public void setProcessPath(String processPath) {
        this.processPath = processPath;
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
                ", processPath='" + processPath + '\'' +
                '}';
    }
}
