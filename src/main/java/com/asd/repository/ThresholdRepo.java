package com.asd.repository;

import com.db.entitie.Threshold_Setting;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ThresholdRepo implements PanacheRepository<Threshold_Setting> {

    public void addthreshlod(String Server_ip)
    {

        Threshold_Setting thresholdSetting=new Threshold_Setting();
        thresholdSetting.setHard_disk_threshold(85L);
        thresholdSetting.setCpu_threshold(150L);
        thresholdSetting.setRam_threshold(90L);
        thresholdSetting.setServer(Server_ip);
        persist(thresholdSetting);
    }

    public void delete(String ip)
    {
         getEntityManager().createQuery("delete from Threshold_Setting where server = :server")
                .setParameter("server", ip)
                .executeUpdate();

    }
    public Threshold_Setting findByServerIp(String ip) {
        return find("server", ip).firstResult();
    }
}
