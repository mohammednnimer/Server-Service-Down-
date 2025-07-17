package com.asd.logic.servicedown.controller;

import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.CertificateStatusModel;
import com.asd.logic.servicedown.models.ServiceModel;
import com.asd.logic.servicedown.services.*;
import com.asd.logic.servicedown.utils.ICertStatus;

import java.util.LinkedList;
import java.util.List;
public class ControllerManager {
private WebsiteChecker websiteChecker = new WebsiteChecker();
private CertificateChecker certificateChecker = new CertificateChecker();

public void  mainFun ()  {
    List<ServiceModel> serviceModelList = new LinkedList<>();
    try {
        System.out.println("Starting Loading Services from File ... ");
        serviceModelList=  Loaders.loadServices() ;
        System.out.println("Finished  Loading Services from File ... ");

        System.out.println("Services : " + serviceModelList.toString());

    }
    catch (Exception e ) {
        e.printStackTrace();
        System.exit(1);
    }

    NotificationServices notificationServices = new NotificationServices();
   for (ServiceModel serviceModel : serviceModelList ) {
       try {
           boolean status = websiteChecker.isWebsiteUp(serviceModel) ;
           if (!status){
               if (!ServicesUtil.withIntervalStopTime() && !serviceModel.getIsBlocked()){
                   notificationServices.sendWhatsAppMsg(serviceModel) ;

               }
           }

           if (serviceModel.getHttps()) {
               CertificateStatusModel certificateStatusModel = certificateChecker.checkCertificate(serviceModel);
               if (certificateStatusModel.getStatus().equalsIgnoreCase(ICertStatus.EXPIRED) || certificateStatusModel.getStatus().equalsIgnoreCase(ICertStatus.EXPIRING_SOON) )  {
                   if (!ServicesUtil.withIntervalStopTime() && !serviceModel.getIsBlocked()) {
                       if (CertNotificationTracker.shouldNotify(serviceModel.getServiceUrl())) {
                           notificationServices.sendEmail(certificateStatusModel);
                           CertNotificationTracker.updateLastSent(serviceModel.getServiceUrl());
                       } else {
                           System.out.println("⏳ No notification sent for " + serviceModel.getServiceUrl() + ", waiting 8 hours...");
                       }
                   }
               }
           }


       }
       catch (Exception e ) {
           e.printStackTrace();
       }

   }



}
}
