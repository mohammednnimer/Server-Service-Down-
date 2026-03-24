package com.asd.service;

import com.asd.repository.ThresholdRepo;
import com.db.entitie.Threshold_Setting;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ThresoldService {



    @Inject
    ThresholdRepo thresholdRepo;


    public Threshold_Setting getserverThresould(String ip)
    {
        return thresholdRepo.findByServerIp(ip);
    }
    public List<Threshold_Setting> getall()
    {
        return thresholdRepo.findAll().list();
    }


    @Transactional
    public boolean updateThresholds(Threshold_Setting thresholdSetting) {
        Threshold_Setting existing = thresholdRepo.findByServerIp(thresholdSetting.getServer());
        if (existing == null) {
            return false;
        }
        if (thresholdSetting.getCpu_threshold()!=null)
        {
            System.out.println(thresholdSetting.getCpu_threshold());

            existing.setCpu_threshold(thresholdSetting.getCpu_threshold());

        }
        if (thresholdSetting.getRam_threshold()!=null)
        {
            System.out.println(thresholdSetting.getRam_threshold());

            existing.setRam_threshold(thresholdSetting.getRam_threshold());
        }
        if (thresholdSetting.getHard_disk_threshold()!=null)
        {
            System.out.println(thresholdSetting.getHard_disk_threshold());
            existing.setRam_threshold(thresholdSetting.getHard_disk_threshold());
        }


        thresholdRepo.persist(existing);
        return true;
    }
}
