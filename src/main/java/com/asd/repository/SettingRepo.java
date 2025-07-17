package com.asd.repository;

import com.asd.controller.SettingsResource;
import com.db.entitie.GeneralSettings;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SettingRepo implements PanacheRepository<GeneralSettings> {

    @Transactional
    public void updateemail(String key,String value)
    {
        update("set settingValue =?1 where settingId =?2",value,key);
    }


}
