package com.asd.dto.sub;


public class CpuUtilization {

    private double utilization;
    private int physicalCores;
    private int logicalCores;

    public double getUtilization() {
        return utilization;
    }

    @Override
    public String toString() {
        return "CpuUtilization{" +
                "utilization=" + utilization +
                ", physicalCores=" + physicalCores +
                ", logicalCores=" + logicalCores +
                '}';
    }

    public CpuUtilization() {
    }

    public CpuUtilization(double utilization, int physicalCores, int logicalCores) {
        this.utilization = utilization;
        this.physicalCores = physicalCores;
        this.logicalCores = logicalCores;
    }
    public int getPhysicalCores() {
        return physicalCores;
    }
    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }
    public void setPhysicalCores(int physicalCores) {
        this.physicalCores = physicalCores;
    }
    public void setLogicalCores(int logicalCores) {
        this.logicalCores = logicalCores;
    }
    public int getLogicalCores() {
        return logicalCores;
    }
}





