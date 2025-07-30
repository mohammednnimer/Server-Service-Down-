package com.asd.controller;


import com.asd.dto.AgentDto;
import com.asd.dto.Token;
import com.asd.service.AgentService;
import com.db.entitie.Agent;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/agents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgentResource {

    @Inject
    AgentService agentService;

    @GET
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @GET
    @Path("/{token}")
    public Response getAgentByToken(@PathParam("token") String token) {
        Agent agent = agentService.getAgentByToken(token);

        if (agent == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agent not found").build();
        }
        return Response.ok(agent).build();
    }

     @POST
    public Response createAgent(Agent agent) {
        return agentService.createAgent(agent);
    }



    @GET
    @Path("get-token/{id}")
    public Response getToken(@PathParam("id") String id )
    {
       String token= agentService.getToken(id);
        if(token!=null)
        {
            return Response.ok().entity(new Token(token)).build();
        }


        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }


    @GET
    @Path("/get_agent_by_server_id")
    public Response getAgent(String id)
    {
        Agent agent= agentService.getAgent(id);
        if(agent!=null)
        {
            return Response.ok().entity(agent).build();
        }


        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }



    @PUT
    @Path("/{token}")
    public Response updateAgent(@PathParam("token") String token, AgentDto updatedAgent) {
        return agentService.updateAgent(token, updatedAgent);
    }

     @DELETE
    @Path("/{server_id}")
    public Response deleteAgent(@PathParam("server_id") String token) {
        return agentService.deleteAgent(token);
    }
}
