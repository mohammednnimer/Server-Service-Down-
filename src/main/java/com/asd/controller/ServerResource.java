package com.asd.controller;

import com.asd.dto.SearchCriteria;


import com.asd.dto.sub.Alert;
import com.asd.service.ServerService;
import com.db.entitie.PanelServer;


import com.utils.constant.SuccMsgs;
import com.utils.constant.SystemPaths;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(SystemPaths.SERVER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServerResource {

    @Inject
    ServerService Server;

    @GET
    public Response GetServer(@QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
        return Server.GetServer(limit,page);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(SystemPaths.SEARCH)
    public Response GetByFilter(SearchCriteria filtering) {
        return Response.status(Response.Status.OK).entity(Server.GetByFilter(filtering)).build();
    }

    @GET
    @Path(SystemPaths.GET_BY_ID)
    public Response getserver(@QueryParam("id") String id) {
     return Server.GetServerById(id);
    }

    @POST
    @Path(SystemPaths.CREATE)
    public Response Create(PanelServer panelServer) {
        if(Server.Create(panelServer))
        {
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.succcreate)).build();
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(
                new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE,"There is a server with the same IP And DNS ")).build();
    }

    @DELETE
    @Path(SystemPaths.DELETE)
    public Response DeleteServer(@QueryParam("id") String Id) {

        return Server.DeleteServer(Id);
    }

    @PUT
    @Path(SystemPaths.UPDATE)
    public Response UpdateServer(com.asd.dto.UpdateServer server) {
       return Server.UpdateServer(server);
    }










}
