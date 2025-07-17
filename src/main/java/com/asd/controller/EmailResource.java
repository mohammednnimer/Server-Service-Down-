package com.asd.controller;


import com.asd.service.EmailService;
import com.db.entitie.EmailParticipants;
import com.utils.constant.SystemPaths;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(SystemPaths.Email)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmailResource {


    @Inject
    EmailService email;


    @POST
    @Path("/add")
    public Response addEmail(EmailParticipants emailParticipants){
        return Response.status(Response.Status.OK).entity(new com.db.entitie.Response(Response.Status.OK,"The entry process was successful.")).build();
    }













}
