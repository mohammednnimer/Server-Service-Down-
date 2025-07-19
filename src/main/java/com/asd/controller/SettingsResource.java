package com.asd.controller;


import com.asd.dto.UpdateSettingEmail;
import com.asd.dto.UpdateSettingStopTime;
import com.asd.dto.UpdateSettingWhatsapp;
import com.asd.repository.SettingRepo;
import com.asd.service.SettingService;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import com.utils.constant.SystemPaths;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path(SystemPaths.Setting)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SettingsResource {

    @Inject
    SettingService settingService;

    @Path("/update/email")
    @PATCH
    public Response updateEmail(UpdateSettingEmail updateSettingEmail)
    {
        settingService.updateEmail(updateSettingEmail);
        return Response.status(Response.Status.OK)
                .entity(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.updatesucc)).build();
    }

    @Path("/update/whatsapp")
    @PATCH
    public Response updateWhatsapp(UpdateSettingWhatsapp updateSettingWhatsapp)
    {
        settingService.updateWhatsapp(updateSettingWhatsapp);
        return Response.status(Response.Status.OK)
                .entity(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.updatesucc)).build();
    }

    @Path("/update/stoptime")
    @PATCH
    public Response updateStopTime(UpdateSettingStopTime updateSettingStopTime)
    {
        settingService.updateStopTime(updateSettingStopTime);
        return Response.status(Response.Status.OK)
                .entity(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.updatesucc)).build();
    }

    @Path("/system")
    @GET
    public Response getSystemSettings() {
        return Response.status(Response.Status.OK).entity(settingService.getSystemSettings()).build();
    }
}
