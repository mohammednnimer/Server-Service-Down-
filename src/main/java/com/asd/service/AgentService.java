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
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;
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

    public String getip(String token)
    {

        return  agentRepo.getServerIpByToken(token);
    }
    public String getToken(String id)
    {
        return agentRepo.findByServerId(id).getToken();


    }
    public Agent getAgent(String id)
    {
        return agentRepo.findByServerId(id);


    }



    @Transactional
    public Response createAgent(Agent agent) {
        if (agent.getToken() == null || agent.getToken().isBlank()) {

            agent.setToken(ASDKey.generate4096BitKey(agent.getToken()));
            System.out.println("yes1");
        }
        if (agentRepo.findByServerId(agent.getServer())!=null) {
            System.out.println(agentRepo.findByServerId(agent.getServer()).getManager_ip());
            return Response.status(Response.Status.CONFLICT)
                    .entity(ErrorMsgs.conflict).build();
        }
        System.out.println("yes3");
        agentRepo.persist(agent);
        return Response.status(Response.Status.CREATED).entity(agent.getToken()).build();
    }

    @Transactional
    public Response updateAgent(String token, AgentDto updatedAgent) throws URISyntaxException {
        boolean isupdate=false;
        Agent existing = agentRepo.findByToken(token);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(ErrorMsgs.NotFound).build();
        }
        if (updatedAgent.getKeywords() != null) {
            isupdate=true;
            existing.setKeywords(updatedAgent.getKeywords());
        }
        if (updatedAgent.getPorts() != null) {
            isupdate=true;
            existing.setPorts(updatedAgent.getPorts());
        }
        if (isupdate) {
            String ip = agentRepo.getServerIpByToken(existing.getToken());
            Httpsrequest client = RestClientBuilder.newBuilder()
                    .baseUri(new URI("http://172.20.10.2"  + ":40006"))
                    .build(Httpsrequest.class);
            Response responseFromServer = client.SendAgent(existing);

            if (responseFromServer.getStatus() == 200) {
                String responseBody = responseFromServer.readEntity(String.class);
                System.out.println("Response from server: " + responseBody);
            } else {
                 System.out.println("Failed to send agent data. Status: " + responseFromServer.getStatus());
            }
            responseFromServer.close();
        }

        return Response.ok(existing).build();

    }


    public Response sendAgent(String token) throws URISyntaxException {


        Agent existing = agentRepo.findByToken(token);
        String ip = agentRepo.getServerIpByToken(existing.getToken());
        Httpsrequest client = RestClientBuilder.newBuilder()
                .baseUri(new URI("http://172.20.10.2" + ":40006"))
                .build(Httpsrequest.class);
        Response responseFromServer = client.SendAgent(existing);

        if (responseFromServer.getStatus() == 200) {
            String responseBody = responseFromServer.readEntity(String.class);
            System.out.println("Response from server: " + responseBody);
        } else {
            System.out.println("Failed to send agent data. Status: " + responseFromServer.getStatus());
        }
        responseFromServer.close();
        return Response.ok(existing).build();


    }

    @Transactional
    public Response deleteAgent(String token) throws URISyntaxException {

        int count =agentRepo.deleteByID(token);

        if(count==0)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(ErrorMsgs.NotFound).build();

        }
        Httpsrequest client = RestClientBuilder.newBuilder()
                .baseUri(new URI("http://localhost" + ":40006"))
                .build(Httpsrequest.class);
        Response responseFromServer = client.deleetagent();
        if (responseFromServer.getStatus() == 200) {
            String responseBody = responseFromServer.readEntity(String.class);
            System.out.println("Response from server: " + responseBody);
        } else {
            System.out.println("Failed to send agent data. Status: " + responseFromServer.getStatus());
        }
        responseFromServer.close();
        return Response.ok(SuccMsgs.agentsucc).build();
    }
}
