package com.asd.service;

import com.asd.dto.UpdateSettingEmail;
import com.asd.repository.SettingRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SettingService {

    @Inject
    SettingRepo settingRepo;

    @Transactional
    public void updateEmail(UpdateSettingEmail updateSettingEmail){
        if (updateSettingEmail.getFrom()!=null){
            settingRepo.updateSetting("email_from",updateSettingEmail.getFrom());
        }if (updateSettingEmail.getHost()!=null){

            settingRepo.updateSetting("email_host",updateSettingEmail.getHost());
        }
        if (updateSettingEmail.getPassword()!=null){
            settingRepo.updateSetting("email_password",updateSettingEmail.getPassword());
        }
        if (updateSettingEmail.getPort()!=null){
            settingRepo.updateSetting("email_port",updateSettingEmail.getPort() );
        }
        if (updateSettingEmail.getUsername()!=null){
            settingRepo.updateSetting("email_username",updateSettingEmail.getUsername());
        }





    }



}
