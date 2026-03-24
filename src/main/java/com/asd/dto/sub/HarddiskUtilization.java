package com.asd.dto.sub;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HarddiskUtilization {


    List<PartitionInfo> partitions;

    public HarddiskUtilization() {
        this.getAllPartitionsInfo();
    }

    public void setPartitions(List<PartitionInfo> partitions) {
        this.partitions = partitions;
    }


    @Override
    public String toString() {
        return "HarddiskUtilization{" +
                "partitions=" + partitions +
                '}';
    }

    public void getAllPartitionsInfo() {
        List<PartitionInfo> partitions = new ArrayList<>();
        File[] roots = File.listRoots();
        for (File root : roots) {
            if (root.exists() && root.getTotalSpace() > 0) {
                partitions.add(new PartitionInfo(
                        root.getAbsolutePath(),
                        root.getTotalSpace(),
                        root.getFreeSpace()
                ));
            }
        }
        this.partitions= partitions;
    }

    public List<PartitionInfo> getPartitions() {
        return partitions;
    }
}
