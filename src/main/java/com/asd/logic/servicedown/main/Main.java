package com.asd.logic.servicedown.main;

import com.asd.logic.servicedown.controller.ControllerManager;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
//        ServiceModel serviceModel = new ServiceModel();
//        serviceModel.setServiceUrl("hbravo.scopef.com");
//        serviceModel.setPortNum(9086);
//        new WebsiteChecker().isWebsiteUp(serviceModel);


System.out.println("VERSION - 2.0.0");
System.out.println("WebChecker Starting At : " + LocalDateTime.now().toString());
//        ControllerManager.mainFun();

        System.out.println("WebChecker Stoped  At : " + LocalDateTime.now().toString());
 System.exit(0);

    }
}