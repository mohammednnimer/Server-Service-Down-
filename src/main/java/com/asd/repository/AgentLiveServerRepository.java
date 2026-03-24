package com.asd.repository;

import com.db.entitie.AgentLiveServer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgentLiveServerRepository implements PanacheRepository<AgentLiveServer> {

    public AgentLiveServer findByIp(String ip) {
        return find("ipAddress", ip).firstResult();
    }
}







