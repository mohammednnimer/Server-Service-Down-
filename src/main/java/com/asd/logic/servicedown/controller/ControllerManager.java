package com.asd.logic.servicedown.controller;

import com.asd.enums.CertificateStatus;
import com.asd.enums.ServiceStatus;
import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.CertificateStatusModel;
import com.asd.logic.servicedown.models.ServiceModel;
import com.asd.logic.servicedown.services.*;
import com.asd.repository.ServiceRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class ControllerManager {
    @Inject
    ServiceRepo serviceRepo;
    private WebsiteChecker websiteChecker = new WebsiteChecker();

    @Inject
    Loaders loaders;

    @Inject
    CertNotificationTracker certNotificationTracker;

    @Inject
    NotificationServices notificationServices;

    @Inject
    ServicesUtil servicesUtil;

    @Inject
    CertificateChecker certificateChecker;

    public void mainFun() {
        List<ServiceModel> serviceModelList = new LinkedList<>();
        loaders.loadWhatsAppConfigUtil();

        try {
            System.out.println("Starting Loading Services from File ... ");
            serviceModelList = loaders.loadServices();
            System.out.println("Finished  Loading Services from File ... ");

            System.out.println("Services : " + serviceModelList.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (ServiceModel serviceModel : serviceModelList) {
            try {
                boolean status = websiteChecker.isWebsiteUp(serviceModel);
                serviceRepo.updateServiceStatus(serviceModel.getServiceUrl(), ServiceStatus.DOWN);
                if (!status) {
                    if (!servicesUtil.withIntervalStopTime() && !serviceModel.getIsBlocked()) {
                        notificationServices.sendWhatsAppMsg(serviceModel);
                    }
                } else {
                    serviceRepo.updateServiceStatus(serviceModel.getServiceUrl(), ServiceStatus.UP);
                }

                if (serviceModel.getHttps()) {
                    CertificateStatusModel certificateStatusModel = certificateChecker.checkCertificate(serviceModel);
                    if (certificateStatusModel.getStatus() == CertificateStatus.EXPIRED || certificateStatusModel.getStatus() == CertificateStatus.EXPIRING_SOON) {
                        if (!servicesUtil.withIntervalStopTime() && !serviceModel.getIsBlocked()) {
                            if (certNotificationTracker.shouldNotify(serviceModel.getServiceUrl())) {
                                notificationServices.sendEmail(certificateStatusModel);
                                certNotificationTracker.updateLastSent(serviceModel.getServiceUrl());
                            } else {
                                System.out.println("⏳ No notification sent for " + serviceModel.getServiceUrl() + ", waiting 8 hours...");
                            }
                        }
                    }
                } else {
                    serviceRepo.updateCertificate(serviceModel.getServiceUrl(), CertificateStatus.NONE);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
