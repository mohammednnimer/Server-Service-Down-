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

    public Harddisk(String diskName, double utilizationPercent) {
        this.diskName = diskName;
        this.utilizationPercent = utilizationPercent;
    }

    public Harddisk() {
    }




    @Column(name = "utilization_percent")
    private double utilizationPercent;

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
    public double getUtilizationPercent() {
        return utilizationPercent;
    }
    public void setUtilizationPercent(double utilizationPercent) {
        this.utilizationPercent = utilizationPercent;
    }
}
