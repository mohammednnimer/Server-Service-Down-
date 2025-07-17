package com.asd.repository;

import com.asd.controller.SettingsResource;
import com.db.entitie.GeneralSettings;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class SettingRepo implements PanacheRepository<GeneralSettings> {
}
