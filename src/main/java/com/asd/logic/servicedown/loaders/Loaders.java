package com.asd.logic.servicedown.loaders;

import com.asd.enums.CertificateStatus;
import com.asd.enums.ServiceStatus;
import com.asd.logic.servicedown.models.*;
import com.asd.repository.EmailRepo;
import com.asd.repository.ServiceRepo;
import com.asd.repository.SettingRepo;
import com.asd.repository.WhatsappRepo;
import com.db.entitie.GeneralSettings;
import com.db.entitie.PanelService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class Loaders {

    @Inject
    ServiceRepo serviceRepo;

    @Inject
    SettingRepo settingRepo;

    @Inject
    EmailRepo emailRepo;

    @Inject
    WhatsappRepo whatsappRepo;

    @Inject
    WhatsAppConfigModel whatsAppConfigModel;

    public WhatsAppConfigModel loadWhatsAppConfigUtil() {
        GeneralSettings url = settingRepo.getSetting("whatsapp_url");
        System.out.println(url.getSettingValue() + "!!!!!");
        whatsAppConfigModel.setUrl(url.getSettingValue());

        return null ;
    }

    public List<ServiceModel> loadServices() {
        List<ServiceModel> serviceModels = new LinkedList<>();
        for (PanelService service : serviceRepo.getAllServices(-1, -1)) {
            if (service.getServiceDns().startsWith("http://") || service.getServiceDns().startsWith("https://") ){
                ServiceModel serviceModel = new ServiceModel() ;

                serviceModel.setServiceUrl(service.getServiceDns());
                serviceModel.setIsBlocked(service.isBlocked());
                serviceModel.setServiceStatus(service.getServiceStatus());
                serviceModel.setCertificateStatus(service.getCertificateStatus());
                serviceModel.setLastShutdown(service.getLastShutdown());
                serviceModels.add(serviceModel) ;
            }
            else {
                service.setCertificateStatus(CertificateStatus.ERROR);
                service.setServiceStatus(ServiceStatus.DOWN);
                serviceRepo.updateServiceStatus(service.getServiceDns(), service.getServiceStatus());
                serviceRepo.updateCertificate(service.getServiceDns(), service.getCertificateStatus());
                System.err.println("Error while converting PanelService into Server Model: " +
                        "Http protocol couldn't be found within the URL: "  + service.getServiceDns());
            }

        }
        return serviceModels ;
    }

    public StoppingTime loadIntervalTime() {
        GeneralSettings stopTimeFrom = settingRepo.getSetting("stop_time_from");
        GeneralSettings stopTimeTo = settingRepo.getSetting("stop_time_to");

        StoppingTime  stoppingTime = new StoppingTime();
        stoppingTime.setStopTimeFrom(stopTimeFrom.getSettingValue());
        stoppingTime.setStopTimeTo(stopTimeTo.getSettingValue());

        return stoppingTime;
    }

    private EmailConfigModel emailConfigModel = null;

    public EmailConfigModel loadEmailConfig() {
        if (emailConfigModel == null) {
            emailConfigModel = loadEmailConfigUtil();
        }
        return emailConfigModel;
    }

    private EmailConfigModel loadEmailConfigUtil() {
        GeneralSettings host = settingRepo.getSetting("email_host");
        GeneralSettings port = settingRepo.getSetting("email_port");
        GeneralSettings username = settingRepo.getSetting("email_username");
        GeneralSettings password = settingRepo.getSetting("email_password");
        GeneralSettings from = settingRepo.getSetting("email_from");

        EmailConfigModel config = new EmailConfigModel();
        config.setHost(host.getSettingValue());
        config.setPort(port.getSettingValue());
        config.setPassword(password.getSettingValue());
        config.setUsername(username.getSettingValue());
        config.setFrom(from.getSettingValue());

        return config;
    }
}
