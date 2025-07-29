package com.asd.service;

import com.asd.dto.AgentDto;
import com.asd.repository.AgentRepo;
import com.db.entitie.Agent;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import com.utils.functions.ASDKey;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class AgentService {

    @Inject
    AgentRepo agentRepo;

    public List<Agent> getAllAgents() {
        return agentRepo.listAll();
    }

    public Agent getAgentByToken(String token) {
        return agentRepo.findByToken(token);
    }

    public String getToken(String id)
    {
        return agentRepo.findByServerId(id).getToken();


    }



    @Transactional
    public Response createAgent(Agent agent) {
        if (agent.getToken() == null || agent.getToken().isBlank()) {
            agent.setToken(ASDKey.generate4096BitKey(agent.getToken()));
        }
        if (agentRepo.findByServerId(agent.getServer())!=null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(ErrorMsgs.conflict).build();
        }
        agentRepo.persist(agent);
        return Response.status(Response.Status.CREATED).entity(agent.getToken()).build();
    }

    @Transactional
    public Response updateAgent(String token, AgentDto updatedAgent) {
        Agent existing = agentRepo.findByToken(token);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(ErrorMsgs.NotFound).build();
        }
        if (updatedAgent.getKeywords() != null) {
            existing.setKeywords(updatedAgent.getKeywords());
        }

        if (updatedAgent.getPorts() != null) {
            existing.setPorts(updatedAgent.getPorts());
        }

        return Response.ok(existing).build();
    }

    @Transactional
    public Response deleteAgent(String token) {

        int count =agentRepo.deleteByID(token);

        if(count==0)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(ErrorMsgs.NotFound).build();

        }
        return Response.ok(SuccMsgs.agentsucc).build();
    }
}
