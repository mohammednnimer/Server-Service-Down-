package com.db.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Threshold_tbl")
public class Threshold_Setting {

    @Id
    @Column(name = "server_id", nullable = false)
    private String server;

    @Column(name = "cpu_threshold")
    private Long cpu_threshold ;

    @Column(name = "ram_threshold")
    private Long ram_threshold ;

    @Column(name = "hard_disk_threshold")
    private Long hard_disk_threshold;

    public Threshold_Setting(String server, Long cpu_threshold, Long ram_threshold, Long hard_disk_threshold) {
        this.server = server;
        this.cpu_threshold = cpu_threshold;
        this.ram_threshold = ram_threshold;
        this.hard_disk_threshold = hard_disk_threshold;
    }

    public Threshold_Setting() {
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Long getCpu_threshold() {
        return cpu_threshold;
    }

    public void setCpu_threshold(Long cpu_threshold) {
        this.cpu_threshold = cpu_threshold;
    }

    public Long getRam_threshold() {
        return ram_threshold;
    }

    public void setRam_threshold(Long ram_threshold) {
        this.ram_threshold = ram_threshold;
    }

    public Long getHard_disk_threshold() {
        return hard_disk_threshold;
    }

    public void setHard_disk_threshold(Long hard_disk_threshold) {
        this.hard_disk_threshold = hard_disk_threshold;
    }
}
