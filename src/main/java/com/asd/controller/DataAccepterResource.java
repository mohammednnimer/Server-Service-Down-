package com.asd.controller;

import com.utils.constant.SystemPaths;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path(SystemPaths.DATA_ACCEPTER)
public class DataAccepterResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response accept() {
        return Response.ok().build();
    }
}
