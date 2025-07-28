package com.asd.controller;


import com.asd.dto.AgentDto;
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
        System.out.println(agent.getKeywords().get(0));
        return Response.ok(agent).build();
    }

     @POST
    public Response createAgent(Agent agent) {
        return agentService.createAgent(agent);
    }






    @PUT
    @Path("/{token}")
    public Response updateAgent(@PathParam("token") String token, AgentDto updatedAgent) {
        return agentService.updateAgent(token, updatedAgent);
    }

     @DELETE
    @Path("/{token}")
    public Response deleteAgent(@PathParam("token") String token) {
        return agentService.deleteAgent(token);
    }
}
