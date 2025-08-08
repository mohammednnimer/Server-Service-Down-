package com.asd.service;

import com.db.entitie.Agent;
import com.utils.constant.SystemPaths;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public interface Httpsrequest
{

    @GET
    @Path("/sendresponce")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    jakarta.ws.rs.core.Response broadcast();

    @POST
    @Path("/getConfig/getAgent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response SendAgent(Agent agent);



}
