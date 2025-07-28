package com.asd.controller;


import com.asd.dto.ClientUtilization;
import com.asd.dto.EmailNotification;
import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.Alert;
import com.asd.service.NotificationService;
import com.utils.constant.SystemPaths;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import java.io.BufferedReader;

@Path(SystemPaths.alert)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ALertResource {
    @Inject
    RoutingContext routingContext;

    @Inject
    NotificationService notificationService;

    private long lastReceived;
    private final int IGNORE_DURATION = 8;
    @POST
    public void receiveAlert(ReciveAlert reciveAlert) {
        String clientIp = routingContext.request().remoteAddress().host();
        if (lastReceived == 0 || lastReceived + (60000L * 60000 * IGNORE_DURATION) >= System.currentTimeMillis()) {
          //  notificationService.SendEmail(clientIp, reciveAlert);
            lastReceived = System.currentTimeMillis();
        }

        System.out.println(clientIp +"        ");
     //   System.out.println(reciveAlert.toString());
    }


}
