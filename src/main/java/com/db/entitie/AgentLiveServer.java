package com.db.entitie;
import com.asd.dto.sub.HarddiskUtilization;
import com.asd.repository.AgentLiveServerRepository;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Agent_Live_Server")
public class AgentLiveServer {

    @Id
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "cpu_utilization")
    private double cpuUtilization;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "server_ip")
    private List<Harddisk> harddiskUtilizations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "process_report")
    private List<ProcessReport> ProecessReport;

    public List<ProcessReport> getProecessReport() {
        return ProecessReport;
    }


    public void setProecessReport(List<ProcessReport> proecessReport) {
        ProecessReport = proecessReport;
    }

    @Column(name = "ram_utilization")
    private double ramUtilization;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;



    @Column(name = "physical_cores")
    private Integer physicalCores;

    @Column(name = "logical_cores")
    private Integer logicalCores;

    @Column(name = "free_ram")
    private Long  freeRam;

    @Column(name = "total_ram")
    private Long  totalRam;

    @Column(name = "used_ram")
    private Long  usedRam;





    public Integer getPhysicalCores() {
        return physicalCores;
    }

    public void setPhysicalCores(Integer physicalCores) {
        this.physicalCores = physicalCores;
    }

    public Integer getLogicalCores() {
        return logicalCores;
    }

    public void setLogicalCores(Integer logicalCores) {
        this.logicalCores = logicalCores;
    }

    public Long  getFreeRam() {
        return freeRam;
    }

    public void setFreeRam(Long  freeRam) {
        this.freeRam = freeRam;
    }

    public Long  getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(Long  totalRam) {
        this.totalRam = totalRam;
    }

    public Long  getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(Long  usedRam) {
        this.usedRam = usedRam;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public double getCpuUtilization() {
        return cpuUtilization;
    }
    public void setCpuUtilization(double cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public List<Harddisk> getHarddiskUtilizations() {
        return harddiskUtilizations;
    }
    public void setHarddiskUtilizations(List<Harddisk> harddiskUtilizations) {
        this.harddiskUtilizations = harddiskUtilizations;
    }

    public double getRamUtilization() {
        return ramUtilization;
    }
    public void setRamUtilization(double ramUtilization) {
        this.ramUtilization = ramUtilization;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


}
