package com.asd.service;

import com.asd.dto.UpdateSettingEmail;
import com.asd.dto.UpdateSettingStopTime;
import com.asd.dto.UpdateSettingWhatsapp;
import com.asd.dto.sub.EmailSettings;
import com.asd.dto.sub.SystemSettings;
import com.asd.dto.sub.WhatsappSettings;
import com.asd.repository.SettingRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @Transactional
    public void updateWhatsapp(UpdateSettingWhatsapp updateSettingWhatsapp){
        if (updateSettingWhatsapp.getURL()!=null){
            settingRepo.updateSetting("whatsapp_url",updateSettingWhatsapp.getURL());
        }
    }

    @Transactional
    public void updateStopTime(UpdateSettingStopTime updateSettingStopTime){
        if (updateSettingStopTime.getFrom()!=null){
            settingRepo.updateSetting("stop_time_from", updateSettingStopTime.getFrom());
        }

        if (updateSettingStopTime.getTo() != null) {
            settingRepo.updateSetting("stop_time_to", updateSettingStopTime.getTo());
        }
    }

    public EmailSettings getEmailSettings() {
        String host = settingRepo.getSetting("email_host").getSettingValue();
        String port = settingRepo.getSetting("email_port").getSettingValue();
        String username = settingRepo.getSetting("email_username").getSettingValue();
        String password = settingRepo.getSetting("email_password").getSettingValue();
        String from = settingRepo.getSetting("email_from").getSettingValue();

        return new EmailSettings(host,port,username,password,from);
    }

    public WhatsappSettings getWhatsappSettings() {
        String url = settingRepo.getSetting("whatsapp_url").getSettingValue();

        return new WhatsappSettings(url);
    }

    public SystemSettings getSystemSettings() {
        String stopTimeFrom = settingRepo.getSetting("stop_time_from").getSettingValue();
        String stopTimeTo = settingRepo.getSetting("stop_time_to").getSettingValue();

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return new SystemSettings(LocalTime.parse(stopTimeFrom, formatter),
                LocalTime.parse(stopTimeTo, formatter));
    }
}
