package com.asd.dto.sub;


public class PartitionInfo {
    private String path;
    private long totalSpace;
    private long freeSpace;
    private long usedSpace;
    private double utilization;


    public void setPath(String path) {
        this.path = path;
    }

    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public void setFreeSpace(long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    public PartitionInfo(String path, long totalSpace, long freeSpace) {
        this.path = path;
        this.totalSpace = totalSpace;
        this.freeSpace = freeSpace;
        this.usedSpace = totalSpace - freeSpace;
        this.utilization = totalSpace > 0 ? (double) usedSpace / totalSpace * 100 : 0;
    }

    public String getPath() {
        return path;
    }

    public long getTotalSpace() {
        return totalSpace;
    }

    @Override
    public String toString() {
        return "PartitionInfo{" +
                "path='" + path + '\'' +
                ", totalSpace=" + totalSpace +
                ", freeSpace=" + freeSpace +
                ", usedSpace=" + usedSpace +
                ", utilization=" + utilization +
                '}';
    }

    public long getFreeSpace() {
        return freeSpace;
    }

    public long getUsedSpace() {
        return usedSpace;
    }

    public double getUtilization() {
        return utilization;
    }
}
