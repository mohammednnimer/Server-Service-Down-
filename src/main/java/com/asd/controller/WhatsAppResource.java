package com.asd.controller;

import com.asd.service.SettingService;
import com.asd.service.WhatsAppservice;
import com.db.entitie.EmailParticipants;
import com.db.entitie.WhatsappParticipants;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import com.utils.constant.SystemPaths;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(SystemPaths.whatsApp)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WhatsAppResource {


    @Inject
    WhatsAppservice whatsAppservice;

    @Inject
    SettingService settingService;

    @POST
    public Response addPhone(WhatsappParticipants phoneParticipants){
         if( !whatsAppservice.addPhone(phoneParticipants))
        {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE,SystemPaths.exist)).build();
        }
        return Response.status(Response.Status.OK).entity(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.succcreate)).build();
    }

    @DELETE
    @Path("/{phone}")
    public Response deletePhone(@PathParam("phone") String phone)
    {
        if(whatsAppservice.deletephone(phone))
        {
            return Response.status(Response.Status.OK).entity(new com.db.entitie.Response(Response.Status.OK,SuccMsgs.addsucc)).build();

        }else {

            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();
        }
    }

    @Path("/participants")
    @GET
    public Response getParticipants() {
        return Response.status(Response.Status.OK).entity(whatsAppservice.getAllParticipants()).build();
    }

    @GET
    public Response getWhatsappSettings() {
        return Response.status(Response.Status.OK).entity(settingService.getWhatsappSettings()).build();
    }

}
