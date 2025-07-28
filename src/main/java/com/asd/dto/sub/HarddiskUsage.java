package com.asd.dto.sub;

import java.util.ArrayList;
import java.util.List;

public class HarddiskUsage {

    public HarddiskUsage(String name,Double harddiskUtilization) {
        this.name = name;
        this.harddiskUtilization = harddiskUtilization;
    }

    public HarddiskUsage() {
    }

    private String name ;
    private double harddiskUtilization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHarddiskUtilization() {
        return harddiskUtilization;
    }

    public void setHarddiskUtilization(double harddiskUtilization) {
        this.harddiskUtilization = harddiskUtilization;
    }
}
