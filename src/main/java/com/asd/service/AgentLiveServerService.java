package com.asd.service;

import com.asd.repository.AgentLiveServerRepository;
import com.db.entitie.AgentLiveServer;

import com.db.entitie.Harddisk;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AgentLiveServerService {

    @Inject
    AgentLiveServerRepository repository;

    @Transactional
    public AgentLiveServer saveOrUpdate(AgentLiveServer incoming) {
        AgentLiveServer existing = repository.findByIp(incoming.getIpAddress());
        if (existing != null) {
             existing.setCpuUtilization(incoming.getCpuUtilization());
            existing.setRamUtilization(incoming.getRamUtilization());
            existing.setLastUpdate(incoming.getLastUpdate());

            List<Harddisk> disks = existing.getHarddiskUtilizations();
            disks.clear();
            disks.addAll(incoming.getHarddiskUtilizations());

            return existing;
        } else {
            repository.persist(incoming);
            return incoming;
        }
    }

    @Transactional
    public AgentLiveServer finsbyip(String ipAddress) {
      return   repository.findByIp(ipAddress);
    }

}
