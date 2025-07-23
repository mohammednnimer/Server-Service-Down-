package com.asd.dto.sub;


public class RamUtilzation {
    private  long free;
    private  long total;
    private  long used;
    private  double utilization;

    public RamUtilzation() {

    }

    public void setFree(long free) {
        this.free = free;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public RamUtilzation(long free, long total, long used, double utilization) {
        this.free = free;
        this.total = total;
        this.used = used;
        this.utilization = utilization;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    public long getFree() {
        return free;
    }



    public long getTotal() {
        return total;
    }

    public long getUsed() {
        return used;
    }

    public double getUtilization() {
        return utilization;
    }

}

