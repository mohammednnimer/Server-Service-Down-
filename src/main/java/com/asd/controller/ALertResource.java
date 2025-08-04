package com.asd.controller;


import com.asd.dto.ClientUtilization;
import com.asd.dto.EmailNotification;
import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.Alert;
import com.asd.dto.sub.HarddiskUsage;
import com.asd.dto.sub.PartitionInfo;
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
        AgentLiveServer agentLiveServer=new AgentLiveServer();
        agentLiveServer.setFreeRam(reciveAlert.getClientUtilization().getRamUtilzation().getFree());

        System.out.println(reciveAlert.getClientUtilization().getRamUtilzation().getFree());
        System.out.println(reciveAlert.getClientUtilization().getRamUtilzation().getFree());

        System.out.println(reciveAlert.getClientUtilization().getRamUtilzation().getFree());


        agentLiveServer.setLogicalCores(reciveAlert.getClientUtilization().getCpuUtilzation().getLogicalCores());

        agentLiveServer.setPhysicalCores(reciveAlert.getClientUtilization().getCpuUtilzation().getPhysicalCores());
        agentLiveServer.setUsedRam(reciveAlert.getClientUtilization().getRamUtilzation().getUsed());
        agentLiveServer.setTotalRam(reciveAlert.getClientUtilization().getRamUtilzation().getTotal());
        agentLiveServer.setIpAddress(reciveAlert.getClientUtilization().getIp());
        agentLiveServer.setCpuUtilization(reciveAlert.getClientUtilization().getCpuUtilzation().getUtilization());
        agentLiveServer.setRamUtilization(reciveAlert.getClientUtilization().getRamUtilzation().getUtilization());
        int i=0;

        List<Harddisk> harddisks=new ArrayList<>();
         for(PartitionInfo partitionInfo:reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions())
         {
             Harddisk harddiskUsage=new Harddisk(partitionInfo.getPath(),partitionInfo.getUtilization());
             harddiskUsage.setFree(partitionInfo.getFreeSpace());
             harddiskUsage.setTotalSpace(partitionInfo.getTotalSpace());
             harddiskUsage.setUsedSpace(partitionInfo.getUsedSpace());
             harddisks.add(harddiskUsage);

         }agentLiveServer.setHarddiskUtilizations(harddisks);
        agentLiveServer.setLastUpdate(LocalDateTime.now());

        System.out.println("mohammmmmmmmmmmmmmmmmmmmmm");
        agentLiveServerService.saveOrUpdate(agentLiveServer);


   }

    @GET
    @Path("/get-agentliveserver-byId/{ip}")
    public Response getAgentLiveServerById(@PathParam("ip") String ip) {return Response.ok().entity(agentLiveServerService.findbyip(ip)).build();}





}
