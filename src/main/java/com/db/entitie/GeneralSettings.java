package com.db.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "general_settings")
public class GeneralSettings {

    @Id
    @Column(name = "setting_id")
    private String settingId;


    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    @Column(name = "setting_Value")
    private String settingValue;
}
