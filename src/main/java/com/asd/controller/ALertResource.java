package com.asd.controller;


import com.asd.dto.ClientUtilization;
import com.asd.dto.EmailNotification;
import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.Alert;
import com.asd.dto.sub.HarddiskUsage;
import com.asd.dto.sub.PartitionInfo;
import com.asd.dto.sub.ProcessReport;
import com.asd.mapper.ProcessMapper;
import com.asd.repository.AgentLiveServerRepository;
import com.asd.service.AgentLiveServerService;
import com.asd.service.NotificationService;
import com.db.entitie.AgentLiveServer;
import com.db.entitie.Harddisk;

import com.utils.constant.SystemPaths;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path(SystemPaths.alert)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ALertResource {
    @Inject
    RoutingContext routingContext;

    @Inject
    AgentLiveServerService agentLiveServerService;


    @Inject
    NotificationService notificationService;

    private long lastReceived;
    private final int IGNORE_DURATION = 8;
    @POST
    public void receiveAlert(ReciveAlert reciveAlert) {
        if (lastReceived == 0 || lastReceived + (60000L * 60000 * IGNORE_DURATION) >= System.currentTimeMillis()) {
        //    notificationService.SendEmail(reciveAlert.getClientUtilization().getIp(), reciveAlert);
            lastReceived = System.currentTimeMillis();
        }
         agentLiveServerService.saveOrUpdate(reciveAlert);
   }



    @GET
    @Path("/get-agentliveserver-byId/{ip}")
    public Response getAgentLiveServerById(@PathParam("ip") String ip) {
        return Response.ok().entity(agentLiveServerService.findbyip(ip)).build();}





}
