package com.asd.controller;

import com.asd.service.ThresoldService;
import com.db.entitie.Threshold_Setting;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/threshold")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThresholdResource {

    @Inject
    ThresoldService thresoldService;

    @GET
    public Response getallthreshlod()
    {
        return Response.ok().entity(thresoldService.getall()).build();
    }

    @PUT
    @Path("/update")
    public Response updateThreshold(Threshold_Setting request) {
        boolean updated = thresoldService.updateThresholds(request);

        if (updated) {
            return Response.ok(SuccMsgs.Thresholds).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMsgs.NotFound).build();
        }
    }
}
