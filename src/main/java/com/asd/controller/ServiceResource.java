package com.asd.controller;

import com.asd.dto.SearchCriteria;

import com.asd.dto.UpdateService;
import com.asd.logic.servicedown.controller.CheckerBean;
import com.asd.service.ServicePanel;
import com.db.entitie.PanelService;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import com.utils.constant.SystemPaths;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(SystemPaths.SERVICE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceResource {

    @Inject
    CheckerBean checkerBean;

    @Inject
    ServicePanel ServicePanel;
    @GET
    public Response GetService(@QueryParam("limit") Integer limit,@QueryParam("page") Integer page) {
        return ServicePanel.GetService(limit,page);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(SystemPaths.SEARCH)
    public Response GetFilter(SearchCriteria filtering) {
       return  Response.status(Response.Status.OK).entity(ServicePanel.GetFilter(filtering)).build();
    }


    @POST
    @Path(SystemPaths.CREATE)
    public Response CreateService(PanelService panelService) {
        boolean succ= ServicePanel.Create(panelService);
        if(succ){
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.succcreate)).build();

        }else {
            return Response.ok(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE, ErrorMsgs.ServerNotFound)).build();

        }

    }
    @GET
    @Path(SystemPaths.Byserver)
    public Response getService(@QueryParam("id") String Id) {
        return Response.status(Response.Status.OK).entity(ServicePanel.GetServiesByIdServer(Id)).build();
    }

    @DELETE
    @Path(SystemPaths.DELETE)
    public Response DeleteService(@QueryParam("id")  String Id) {
       return ServicePanel.DeleteService(Id);
    }

    @PUT
    @Path(SystemPaths.UPDATE)
    public Response UpdateService(UpdateService service) {
        return ServicePanel.UpdateService(service);
    }
    @GET
    @Path(SystemPaths.Byserver)
    public Response GetServiceByServerID(@QueryParam("id") String id) {
        return ServicePanel.GetServiceById(id);
    }










}








