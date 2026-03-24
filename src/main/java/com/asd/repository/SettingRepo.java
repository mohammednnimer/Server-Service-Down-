package com.asd.repository;

import com.db.entitie.GeneralSettings;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalTime;

@ApplicationScoped
public class SettingRepo implements PanacheRepository<GeneralSettings> {

    @Transactional
    public void updateSetting(String key,String value)
    {
        update("set settingValue =?1 where settingId =?2",value,key);
    }

    public GeneralSettings getSetting(String key) {
        return find("settingId =?1", key).firstResult();
    }
}
