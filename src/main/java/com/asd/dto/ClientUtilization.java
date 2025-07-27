package com.asd.dto;

import com.asd.dto.sub.CpuUtilization;
import com.asd.dto.sub.HarddiskUtilization;
import com.asd.dto.sub.RamUtilzation;

public class ClientUtilization {


    public ClientUtilization(CpuUtilization cpuUtilzation, HarddiskUtilization harddiskUtilization, RamUtilzation ramUtilzation) {
        this.cpuUtilzation = cpuUtilzation;
        this.harddiskUtilization = harddiskUtilization;
        this.ramUtilzation = ramUtilzation;
    }

    public ClientUtilization() {
    }

    private CpuUtilization cpuUtilzation;
    private HarddiskUtilization harddiskUtilization;
    private RamUtilzation ramUtilzation;

    public CpuUtilization getCpuUtilzation() {
        return cpuUtilzation;
    }


    public HarddiskUtilization getHarddiskUtilization() {
        return harddiskUtilization;
    }

    public void setCpuUtilzation(CpuUtilization cpuUtilzation) {
        this.cpuUtilzation = cpuUtilzation;
    }

    public void setHarddiskUtilization(HarddiskUtilization harddiskUtilization) {
        this.harddiskUtilization = harddiskUtilization;
    }

    public void setRamUtilzation(RamUtilzation ramUtilzation) {
        this.ramUtilzation = ramUtilzation;
    }

    @Override
    public String toString() {
        return "ClientUtilization{" +
                "cpuUtilzation=" + cpuUtilzation +
                ", harddiskUtilization=" + harddiskUtilization +
                ", ramUtilzation=" + ramUtilzation +
                '}';
    }

    public RamUtilzation getRamUtilzation() {
        return ramUtilzation;
    }

}
