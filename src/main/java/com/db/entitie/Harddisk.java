package com.db.entitie;

import jakarta.persistence.*;

@Entity
@Table(name = "harddisk_utilization")
public class Harddisk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "disk_name")
    private String diskName;

    @Column(name = "free")
    private Long  free;

    @Column(name = "total_space")
    private Long  totalSpace;

    @Column(name = "used_space")
    private Long  usedSpace;

    @Column(name = "utilization_percent")
    private double utilizationPercent;

    // Constructors
    public Harddisk() {
    }

    public Harddisk(String diskName, double utilizationPercent) {
        this.diskName = diskName;
        this.utilizationPercent = utilizationPercent;
    }

    public Harddisk(String diskName, Long  free, Long  totalSpace, Long  usedSpace, double utilizationPercent) {
        this.diskName = diskName;
        this.free = free;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
        this.utilizationPercent = utilizationPercent;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public Long  getFree() {
        return free;
    }

    public void setFree(Long  free) {
        this.free = free;
    }

    public Long  getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long  totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long  getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Long  usedSpace) {
        this.usedSpace = usedSpace;
    }

    public double getUtilizationPercent() {
        return utilizationPercent;
    }

    public void setUtilizationPercent(double utilizationPercent) {
        this.utilizationPercent = utilizationPercent;
    }
}
