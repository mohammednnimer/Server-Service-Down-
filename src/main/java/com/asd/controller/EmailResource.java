package com.asd.controller;


import com.asd.service.EmailService;
import com.db.entitie.EmailParticipants;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SystemPaths;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(SystemPaths.Email)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmailResource {


    @Inject
    EmailService emailService;


    @POST
    @Path(SystemPaths.add)
    public Response addEmail(EmailParticipants emailParticipants){
        emailService.addEmail(emailParticipants);
        return Response.status(Response.Status.OK).entity(new com.db.entitie.Response(Response.Status.OK,SystemPaths.addsucc)).build();
    }

    @DELETE
    @Path(SystemPaths.DELETE+"{id}")
    public Response deleteEmail(String email)
    {
        if(emailService.deleteEmail(email))
        {
            return Response.status(Response.Status.OK).entity(new com.db.entitie.Response(Response.Status.OK,SystemPaths.addsucc)).build();

        }else {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();

        }
    }













}
