package com.asd.controller;


import com.asd.dto.ClientUtilization;
import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.Alert;
import com.utils.constant.SystemPaths;
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
    @POST
    public String  receiveAlert(ReciveAlert reciveAlert) {

        System.out.println("mohammmad nemer111");
        System.out.println(reciveAlert.toString());
         return "mohammad nemer";


    }


}
