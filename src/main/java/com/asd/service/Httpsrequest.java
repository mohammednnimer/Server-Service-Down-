package com.asd.service;

import com.utils.constant.SystemPaths;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


public interface Httpsrequest
{

    @GET
    @Path("/sendresponce")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    jakarta.ws.rs.core.Response broadcast();

}
